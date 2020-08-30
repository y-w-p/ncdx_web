package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.PermissionBIZ;
import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.bizimpl.PermissionBIZImpl;
import com.qianfeng.auction.bizimpl.RoleBIZImpl;
import com.qianfeng.auction.eums.UserEnum;

public class RolePermissionUpdateServlet extends HttpServlet {

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String roleid = req.getParameter("roleid");
		String[] permissionArray = req.getParameterValues("permission");
		PermissionBIZ permissionBIZ = new PermissionBIZImpl();
		try {
			permissionBIZ.delAllByRoleId(Integer.parseInt(roleid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			for (String rolespermission : permissionArray) {
				permissionBIZ.updateRolePermission(Integer.parseInt(roleid),
						Integer.parseInt(rolespermission));
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				resp.sendRedirect("RolePermissionFindByroleidServlet?msg="
						+ UserEnum.USER_UPDATE_PERMISSION_FAIL.getValue() + "&roleid="
						+ roleid + "");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			resp.sendRedirect("RoleListByPageServlet?msg="
					+ UserEnum.USER_UPDATE_SUCCESS.getValue() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
