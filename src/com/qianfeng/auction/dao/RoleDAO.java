package com.qianfeng.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;

public interface RoleDAO {

	List<Role> findRoleListByUserID(int userid);

	List<Role> getAll();

	void delAllByUserId(int userid);

	int updateUserRole(int roleid,int userid);
	
	List<Role> findRolesByPage(BigDecimal pageindex, BigDecimal pagenumber);

	BigDecimal getAllCount();

	Role findRoleByID(int roleid);
}
