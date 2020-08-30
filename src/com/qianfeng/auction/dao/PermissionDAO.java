package com.qianfeng.auction.dao;

import java.util.List;

import com.qianfeng.auction.entity.Permission;

public interface PermissionDAO {

	List<Permission> findPermissionByRoleID(int roleid);

	void delAllByRoleId(int roleid);

	int updateRolePermission(int roleid, int permissionid);

	List<Permission> getAll();

}
