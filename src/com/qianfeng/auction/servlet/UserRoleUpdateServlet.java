package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.bizimpl.RoleBIZImpl;
import com.qianfeng.auction.eums.UserEnum;

public class UserRoleUpdateServlet extends HttpServlet {

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String userid = req.getParameter("userid");
		String[] roleArray = req.getParameterValues("role");
		RoleBIZ roleBIZ = new RoleBIZImpl();
		try {
			roleBIZ.delAllByUserId(Integer.parseInt(userid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			for (String usersrole : roleArray) {
				roleBIZ.updateUserRole(Integer.parseInt(userid),
						Integer.parseInt(usersrole));
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				resp.sendRedirect("UserRoleFindByUserIDServlet?msg="
						+ UserEnum.USER_UPDATE_ROLE_FAIL.getValue() + "&userid="+userid+"");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			resp.sendRedirect("UserListByPageServlet?msg="
					+ UserEnum.USER_UPDATE_SUCCESS.getValue() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
