package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.qianfeng.auction.util.EntityCopyUtil;
import com.qianfeng.auction.util.StringUtil;
import com.qianfeng.auction.vo.PageVO;
import com.qianfeng.auction.vo.UserVO;

public class UserListByPageServlet extends HttpServlet {

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
		UserBIZ userBIZ = new UserBIZImpl();
		BigDecimal totalCount = userBIZ.getAllCount();
		List<User> userList = userBIZ.findUsersByPage(
				new BigDecimal(pageindex), new BigDecimal(pagenumber));
		RoleBIZ roleBIZ = new RoleBIZImpl();
		List<UserVO> userVOList = new ArrayList<UserVO>();
		StringBuilder rolenameBuilder = new StringBuilder();
		for (User user : userList) {
			rolenameBuilder = new StringBuilder();
			UserVO userVO = new UserVO();
			EntityCopyUtil.copyPropertys(user, userVO);
			List<Role> usersrole = roleBIZ.findRoleListByUserID(user.getId());

			for (Role role : usersrole) {
				rolenameBuilder.append(role.getRolename() + ",");
			}
			if (usersrole.size() > 0) {
				userVO.setRolename(rolenameBuilder.substring(0,
						rolenameBuilder.length() - 1));
			}
			userVOList.add(userVO);
		}
		// ʵ����pageVO
		PageVO<UserVO> pageVO = new PageVO<UserVO>();
		try {
			pageVO.setLists(userVOList);
			pageVO.setPagenumber(new BigDecimal(pagenumber));
			pageVO.setTotal(totalCount);
			pageVO.setEndpage(totalCount.divide(new BigDecimal(pagenumber), 0,
					BigDecimal.ROUND_UP));
			pageVO.setpageindex(new BigDecimal(pageindex));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ʵ�������PAGEVO �洢����������
		req.setAttribute("pagevo", pageVO);
		// �ٰѵ�ǰ�û���HTTP����ת���� ��ҳ�ļ���
		try {
			req.getRequestDispatcher("user_datagrid.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
