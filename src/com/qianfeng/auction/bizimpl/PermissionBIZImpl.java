package com.qianfeng.auction.bizimpl;

import java.util.List;

import com.qianfeng.auction.biz.PermissionBIZ;
import com.qianfeng.auction.dao.PermissionDAO;
import com.qianfeng.auction.daoimpl.PermissionDAOImpl;
import com.qianfeng.auction.entity.Permission;

public class PermissionBIZImpl implements PermissionBIZ {

	PermissionDAO permissionDAO = new PermissionDAOImpl();

	@Override
	public List<Permission> findPermissionByRoleID(int roleid) {
		return permissionDAO.findPermissionByRoleID(roleid);
	}

	@Override
	public void delAllByRoleId(int roleid) {
		permissionDAO.delAllByRoleId(roleid);

	}

	@Override
	public int updateRolePermission(int roleid, int permissionid) {
		return permissionDAO.updateRolePermission(roleid, permissionid);
	}

	@Override
	public List<Permission> getAll() {
		return permissionDAO.getAll();
	}

}
