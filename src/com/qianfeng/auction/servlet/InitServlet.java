package com.qianfeng.auction.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.PermissionBIZ;
import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.bizimpl.PermissionBIZImpl;
import com.qianfeng.auction.bizimpl.RoleBIZImpl;
import com.qianfeng.auction.entity.Permission;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.job.SendEmailJobDetail;
import com.qianfeng.job.SendEmailScheduler;

public class InitServlet extends HttpServlet {

	public void init() throws ServletException {
		RoleBIZ roleBIZ = new RoleBIZImpl();
		PermissionBIZ permissionBIZ = new PermissionBIZImpl();
		List<Permission> permissions = permissionBIZ.getAll();
		List<Role> roles = roleBIZ.getAll();
		// ���뵽 application ��
		// application �Ƿ�������������� �������û�����
		// httpsession ���û��������� ���û����˳��У�
		// httprequest ��һ������Ự
		// ��ͬ�ڰ����� �洢�� application ������
		this.getServletContext().setAttribute("roles", roles);
		this.getServletContext().setAttribute("permissions", permissions);
		
		SendEmailScheduler emailScheduler = new SendEmailScheduler();
		emailScheduler.start();
		

	}

}
