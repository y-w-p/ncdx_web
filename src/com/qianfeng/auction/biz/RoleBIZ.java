package com.qianfeng.auction.biz;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;

public interface RoleBIZ {

	List<Role> findRoleListByUserID(int userid);

	List<Role> getAll();

	void delAllByUserId(int userid);

	int updateUserRole(int userid, int roleid);

	List<Role> findRolesByPage(BigDecimal pageindex, BigDecimal pagenumber);

	BigDecimal getAllCount();

	Role findRoleByID(int roleid);

}
