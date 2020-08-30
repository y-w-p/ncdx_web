package com.qianfeng.auction.bizimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.qianfeng.auction.biz.PermissionBIZ;
import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.biz.UserBIZ;
import com.qianfeng.auction.dao.UserDAO;
import com.qianfeng.auction.daoimpl.UserDAOImpl;
import com.qianfeng.auction.entity.Permission;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.eums.UserLoginEnum;
import com.qianfeng.auction.util.StringUtil;

public class UserBIZImpl implements UserBIZ {

	UserDAO userDAO = new UserDAOImpl();
	RoleBIZ roleBIZ = new RoleBIZImpl();
	PermissionBIZ permissionBIZ = new PermissionBIZImpl();

	public String userLogin(String username, String password,
			String validatecode, String syscode, HttpServletRequest request) {
		if (StringUtil.isEmpty(validatecode) || StringUtil.isEmpty(syscode)) {
			return UserLoginEnum.USER_VALIDATE_CODE_IS_FAIL.getValue();
		}
		if (!validatecode.equals(syscode)) {
			return UserLoginEnum.USER_VALIDATE_CODE_IS_FAIL.getValue();
		}
		if (StringUtil.isEmpty(username)) {
			return UserLoginEnum.USER_NAME_IS_NUll.getValue();
		}
		if (StringUtil.isEmpty(password)) {
			return UserLoginEnum.USER_PASSWORD_IS_NULL.getValue();
		}
		User user = null;
		user = userDAO.userLogin(username, new Md5Hash(password,username,1).toString());
		if (user == null) {
			return UserLoginEnum.USER_NAME_OR_PASSWORD_IS_FAIL.getValue();
		}
		// 登录成功后 把当前登录成功后的用户 存入到SESSION中 基本是 所有后台的必备功能
		request.getSession().setAttribute("user", user);
		// 执行用户授权的函数
		userAuthorizition(user.getId(), request.getSession());
		return UserLoginEnum.USER_LOGIN_SUCCESS.getValue();
	}
	
	public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("123123","chensiwei",1);
		System.out.println(md5Hash.toString());
	}

	@Override
	public void userAuthorizition(int userid, HttpSession httpSession) {
		// 实例化 两个容器用来存储 用户的角色和权限
		List<String> usersRole = new ArrayList<String>();
		List<String> rolesPermission = new ArrayList<String>();
		// 根据用户ID 获取该用户的所有 角色 （粗粒度授权）
		List<Role> roleList = roleBIZ.findRoleListByUserID(userid);
		// 迭代roleList 获取角色下面的所有权限
		for (Role role : roleList) {
			// 把该用户的角色权限 存入到 角色集合容器中
			usersRole.add(role.getRolename());
			// 把该用户角色的所有权限 存入到 权限集合容器中
			List<Permission> permissions = permissionBIZ
					.findPermissionByRoleID(role.getId());
			for (Permission permission : permissions) {
				rolesPermission.add(permission.getPermissionname());
			}
		}
		// 把角色容器 和 权限容器 托管给 HTTPSESSION (用户级的作用域)
		httpSession.setAttribute("roles", usersRole);
		httpSession.setAttribute("permissions", rolesPermission);
	}

	@Override
	public List<User> findUsersByPage(BigDecimal pageindex,
			BigDecimal pagenumber) {

		// 分页的公式是 (pageindex-1)*pagenum (从第几条开始)
		BigDecimal beginpagenumber = pageindex.subtract(new BigDecimal(1))
				.multiply(pagenumber);
		return userDAO.findUsersByPage(beginpagenumber, pagenumber);

	}

	@Override
	public BigDecimal getAllCount() {
		return userDAO.getAllCount();
	}

	@Override
	public User findUserByID(int userid) {
		return userDAO.findUserByID(userid);
	}

}
