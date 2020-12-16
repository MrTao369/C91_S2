package com.zt.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zt.user.bean.User;
import com.zt.user.util.DBHelper;
import com.zt.user.util.DBHelper.ResultSetMapper;

public class userDao {

	public void insert(User user) throws SQLException {
		String sql="insert into user values(null,?,?,?,?,?,default)";
		DBHelper.updata(sql, 
				user.getName(),
				user.getPhone(),
				user.getEmail(),
				user.getPwd(),
				user.getHead());
	}
	
	//查询是否有重名用户
	public int countByName(String name) throws SQLException {
		String sql="select count(*) from user where name=?";
		List<Integer> list=DBHelper.selectList(sql, new ResultSetMapper<Integer>() {
			public Integer map(ResultSet rs) throws SQLException {
				return rs.getInt(1);
			}
		}, name);
		return list.get(0);
	}
}
