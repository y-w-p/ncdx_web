package com.qianfeng.auction.bizimpl;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.auction.biz.RoleBIZ;
import com.qianfeng.auction.dao.RoleDAO;
import com.qianfeng.auction.daoimpl.RoleDAOImpl;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;

public class RoleBIZImpl implements RoleBIZ {

	RoleDAO roleDAO = new RoleDAOImpl();

	@Override
	public List<Role> findRoleListByUserID(int userid) {
		return roleDAO.findRoleListByUserID(userid);
	}

	@Override
	public List<Role> getAll() {
		return roleDAO.getAll();
	}

	@Override
	public void delAllByUserId(int userid) {
		roleDAO.delAllByUserId(userid);

	}

	@Override
	public int updateUserRole(int userid, int roleid) {
		return roleDAO.updateUserRole(userid, roleid);
	}

	@Override
	public List<Role> findRolesByPage(BigDecimal pageindex,
			BigDecimal pagenumber) {
		// 分页的公式是 (pageindex-1)*pagenum (从第几条开始)
		BigDecimal beginpagenumber = pageindex.subtract(new BigDecimal(1))
				.multiply(pagenumber);
		return roleDAO.findRolesByPage(beginpagenumber, pagenumber);
	}

	@Override
	public BigDecimal getAllCount() {
		return roleDAO.getAllCount();
	}

	@Override
	public Role findRoleByID(int roleid) {
		return roleDAO.findRoleByID(roleid);
	}

}
