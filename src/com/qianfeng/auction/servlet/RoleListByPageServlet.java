package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.qianfeng.auction.util.EntityCopyUtil;
import com.qianfeng.auction.util.StringUtil;
import com.qianfeng.auction.vo.PageVO;
import com.qianfeng.auction.vo.RoleVO;
import com.qianfeng.auction.vo.UserVO;

public class RoleListByPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pageindex = StringUtil.isEmpty(req.getParameter("pageindex")) ? "1"
				: req.getParameter("pageindex");
		String pagenumber = StringUtil.isEmpty(req.getParameter("pagenumber")) ? "5"
				: req.getParameter("pagenumber");
		RoleBIZ roleBIZ = new RoleBIZImpl();
		BigDecimal totalCount = roleBIZ.getAllCount();
		List<Role> roleList = roleBIZ.findRolesByPage(
				new BigDecimal(pageindex), new BigDecimal(pagenumber));
		PermissionBIZ permissionBIZ = new PermissionBIZImpl();
		List<RoleVO> roleVOList = new ArrayList<RoleVO>();
		StringBuilder permissionnameBuilder = new StringBuilder();
		for (Role role : roleList) {
			permissionnameBuilder = new StringBuilder();
			RoleVO roleVO = new RoleVO();
			EntityCopyUtil.copyPropertys(role, roleVO);
			List<Permission> rolesperPermissions = permissionBIZ
					.findPermissionByRoleID(role.getId());

			for (Permission permission : rolesperPermissions) {
				permissionnameBuilder.append(permission.getPermissionname()
						+ ",");
			}
			if (rolesperPermissions.size() > 0) {
				roleVO.setPermissionnname(permissionnameBuilder.substring(0,
						permissionnameBuilder.length() - 1));
			}
			roleVOList.add(roleVO);
		}
		// 实例化pageVO
		PageVO<RoleVO> pageVO = new PageVO<RoleVO>();
		try {
			pageVO.setLists(roleVOList);
			pageVO.setPagenumber(new BigDecimal(pagenumber));
			pageVO.setTotal(totalCount);
			pageVO.setEndpage(totalCount.divide(new BigDecimal(pagenumber), 0,
					BigDecimal.ROUND_UP));
			pageVO.setpageindex(new BigDecimal(pageindex));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 把实例化后的PAGEVO 存储到请求报文中
		req.setAttribute("pagevo", pageVO);
		// 再把当前用户的HTTP请求转发到 分页文件中
		try {
			req.getRequestDispatcher("role_permission_datagrid.jsp").forward(
					req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
