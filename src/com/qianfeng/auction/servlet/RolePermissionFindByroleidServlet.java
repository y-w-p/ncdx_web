package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.PermissionBIZ;
import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.biz.UserBIZ;
import com.qianfeng.auction.bizimpl.PermissionBIZImpl;
import com.qianfeng.auction.bizimpl.RoleBIZImpl;
import com.qianfeng.auction.bizimpl.UserBIZImpl;
import com.qianfeng.auction.entity.Permission;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;

public class RolePermissionFindByroleidServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String roleid = req.getParameter("roleid");
		RoleBIZ roleBIZ = new RoleBIZImpl();
		PermissionBIZ permissionBIZ = new PermissionBIZImpl();
		List<Permission> permissions = null;
		Role role = null;
		try {
			role = roleBIZ.findRoleByID(Integer.parseInt(roleid));
			permissions = permissionBIZ.findPermissionByRoleID(Integer
					.parseInt(roleid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		req.setAttribute("permissions", permissions);
		req.setAttribute("role", role);
		try {
			req.getRequestDispatcher("update_role_permission.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
