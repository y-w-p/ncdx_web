package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.biz.UserBIZ;
import com.qianfeng.auction.bizimpl.RoleBIZImpl;
import com.qianfeng.auction.bizimpl.UserBIZImpl;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;

public class UserRoleFindByUserIDServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String userid = req.getParameter("userid");
		RoleBIZ roleBIZ = new RoleBIZImpl();
		UserBIZ userBIZ = new UserBIZImpl();
		List<Role> roles = null;
		User user = null;
		try {
			user = userBIZ.findUserByID(Integer.parseInt(userid));
			roles = roleBIZ.findRoleListByUserID(Integer.parseInt(userid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		req.setAttribute("roles", roles);
		req.setAttribute("user", user);
		try {
			req.getRequestDispatcher("update_user_role.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
