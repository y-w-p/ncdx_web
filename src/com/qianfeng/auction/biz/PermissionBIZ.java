package com.qianfeng.auction.biz;

import java.util.List;

import com.qianfeng.auction.entity.Permission;

public interface PermissionBIZ {

	List<Permission> findPermissionByRoleID(int roleid);

	void delAllByRoleId(int roleid);

	int updateRolePermission(int roleid, int permissionid);
	
	List<Permission> getAll();
}
