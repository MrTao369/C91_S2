package com.zt.user.biz;


import java.sql.SQLException;

import com.zt.user.bean.User;
import com.zt.user.dao.userDao;

public class userBiz {

	private userDao udao=new userDao();
	
	
	public void register(User user) throws BizException {
		//业务判断：同名判断，非空判断
		if(user.getName()==null||user.getName().isEmpty()) {
			throw new BizException("请填写用户名");
		}
		if(user.getPhone()==null||user.getPhone().isEmpty()) {
			throw new BizException("请填写电话");
		}
		
		
		
		//判断当前用户名是否被注册
	try {
			int cnt =udao.countByName(user.getName());
			if(cnt>0) {
				throw new BizException("该用户已被注册！");
			}
			udao.insert(user);
			udao.insert(user);
		} catch (SQLException e) {
			//业务异常的信息是给用户查看的  异常转型==》异常链
			throw new BizException("系统繁忙，请稍后再试！！",e);
		}
		
		
	}
}
