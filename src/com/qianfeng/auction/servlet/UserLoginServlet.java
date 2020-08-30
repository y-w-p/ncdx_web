package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// JAVA�м̳��� IS A�Ĺ�ϵ  ���� UserLoginServlet Ŀǰ����һ��servlet

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.qianfeng.auction.biz.UserBIZ;
import com.qianfeng.auction.bizimpl.UserBIZImpl;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.eums.UserLoginEnum;
import com.qianfeng.auction.vo.UserVO;

/*
 * �Զ�������룺ctrl + shift + f
 * ��д���Ʋ㲻���������쳣
 * try catch  alt + shift + z
 */

public class UserLoginServlet extends HttpServlet {

/*	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().print(
				JSONObject.fromObject(new UserVO(req.getParameter("username")))
						.toString());
	}*/

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String result = null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String validatecode = req.getParameter("validatecode");
		String syscode = (String) req.getSession().getAttribute("syscode");
		try {
			UserBIZ userBIZ = new UserBIZImpl();
			result = userBIZ.userLogin(username, password, validatecode,
					syscode, req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (result.equals(UserLoginEnum.USER_LOGIN_SUCCESS.getValue())) {
				req.getRequestDispatcher("index.jsp").forward(
						req, resp);
			} else {
				req.getRequestDispatcher("user_login.jsp?msg=" + result + "")
						.forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
