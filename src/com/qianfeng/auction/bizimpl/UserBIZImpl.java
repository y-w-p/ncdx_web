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
		// ��¼�ɹ��� �ѵ�ǰ��¼�ɹ�����û� ���뵽SESSION�� ������ ���к�̨�ıر�����
		request.getSession().setAttribute("user", user);
		// ִ���û���Ȩ�ĺ���
		userAuthorizition(user.getId(), request.getSession());
		return UserLoginEnum.USER_LOGIN_SUCCESS.getValue();
	}
	
	public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("123123","chensiwei",1);
		System.out.println(md5Hash.toString());
	}

	@Override
	public void userAuthorizition(int userid, HttpSession httpSession) {
		// ʵ���� �������������洢 �û��Ľ�ɫ��Ȩ��
		List<String> usersRole = new ArrayList<String>();
		List<String> rolesPermission = new ArrayList<String>();
		// �����û�ID ��ȡ���û������� ��ɫ ����������Ȩ��
		List<Role> roleList = roleBIZ.findRoleListByUserID(userid);
		// ����roleList ��ȡ��ɫ���������Ȩ��
		for (Role role : roleList) {
			// �Ѹ��û��Ľ�ɫȨ�� ���뵽 ��ɫ����������
			usersRole.add(role.getRolename());
			// �Ѹ��û���ɫ������Ȩ�� ���뵽 Ȩ�޼���������
			List<Permission> permissions = permissionBIZ
					.findPermissionByRoleID(role.getId());
			for (Permission permission : permissions) {
				rolesPermission.add(permission.getPermissionname());
			}
		}
		// �ѽ�ɫ���� �� Ȩ������ �йܸ� HTTPSESSION (�û�����������)
		httpSession.setAttribute("roles", usersRole);
		httpSession.setAttribute("permissions", rolesPermission);
	}

	@Override
	public List<User> findUsersByPage(BigDecimal pageindex,
			BigDecimal pagenumber) {

		// ��ҳ�Ĺ�ʽ�� (pageindex-1)*pagenum (�ӵڼ�����ʼ)
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
