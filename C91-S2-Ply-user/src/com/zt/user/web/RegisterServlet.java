package com.zt.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zt.user.bean.User;
import com.zt.user.biz.BizException;
import com.zt.user.biz.userBiz;
/*
 * 1、继承HttpServlet
 * 2、重写doGet,doPost
 * 3、配置xml +注解
 */

@WebServlet("/register.s")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private userBiz ubiz=new userBiz();
      
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		User user =new User();
		user.setName(req.getParameter("name"));
		user.setPhone(req.getParameter("phone"));
		user.setEmail(req.getParameter("email"));
		user.setHead(req.getParameter("head"));
		user.setPwd(req.getParameter("pwd"));
		
		try {
			ubiz.register(user);
			resp.getWriter().append("注册成功");
			
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.getWriter().append("注册失败！原因："+e.getMessage());
		}
	
	}


}
