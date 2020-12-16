package com.zt.user.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;






public class DBHelper {

	/*
	 * DBHelper新增内容
	 * 1、加入配置文件
	 * .propertise java 属性文件
	 * 2、引入实体类
	 * 3、分页查询方法
	 */

	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;
	
	/*
	 * 静态块
	 */
	static {
		try {
		//读取配置文件==》通过类加载器读取类路径里面的文件
		String path="conn.properties";
		InputStream in=DBHelper.class.getClassLoader().getResourceAsStream(path);
		//创建集合对象
		Properties prop=new Properties();
		prop.load(in);
		driver=prop.getProperty("driver");
		url=prop.getProperty("url");
		name=prop.getProperty("name");
		pwd=prop.getProperty("pwd");
		Class.forName(driver);
		}catch (Exception e) {
			//异常转型+抛出运行期异常
			throw new RuntimeException(e);
			
		}
		
		
	}
	
	
	
	/*
	 * 获取连接对象
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, name, pwd);
	}
	
	
	

	/*查询歌手数据
	 * 
	 * @return
	 * @
	 */
	public static <T> List<T>selectList(
		String sql,
		ResultSetMapper<T>callback,
		Object...params)throws SQLException{
		
		System.out.println("SQL:"+sql);
		System.out.println("参数："+Arrays.toString(params));
		Connection conn=getConnection();
		try {
			//创建语句对象
			PreparedStatement ps=conn.prepareStatement(sql);
			//设置查询参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			} 
			//执行语句
			ResultSet rs=ps.executeQuery();
			//定义返回结果
			List<T>ret=new ArrayList<>();
			for (;rs.next();) {
				T t=callback.map(rs);
				ret.add(t);
				
			}
			return ret;
		} finally {
			conn.close();
		}
	}
		//回调接口。泛型接口
	
		public static interface ResultSetMapper<T>{
			T map(ResultSet rs)throws SQLException;
		}

		
		public static int  updata(String sql,Object...params)throws SQLException {
			System.out.println("SQL："+sql);
			System.out.println("参数："+Arrays.toString(params));
			Connection conn=getConnection();
			try {
				//创建语句对象
				PreparedStatement ps=conn.prepareStatement(sql);
				//设置查询
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
				return ps.executeUpdate();
			} finally {
				conn.close();
			}
			
		}
		
	}

