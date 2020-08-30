package com.qianfeng.auction.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.auction.dao.RoleDAO;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.util.JDBCUtil;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public List<Role> findRoleListByUserID(int userid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Role> roleList = new ArrayList<Role>();
		try {
			connection = JDBCUtil.getConnection();
			// 根据 用户ID 查找出 role表 的 rolename 和 roledesc 和 roleid
			// 用左连接 和 右链接的方式去实现
			// 用SQL去写
			preparedStatement = connection
					.prepareStatement("select id,rolename,roledesc from role r left join user_role u on r.id = u.roleid where	u.userid = ?");
			preparedStatement.setObject(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("ID"));
				role.setRolename(resultSet.getString("ROLENAME"));
				role.setRoledesc(resultSet.getString("ROLEDESC"));
				roleList.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}

		return roleList;
	}

	@Override
	public List<Role> getAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Role> roleList = new ArrayList<Role>();
		try {
			connection = JDBCUtil.getConnection();
			// 根据 用户ID 查找出 role表 的 rolename 和 roledesc 和 roleid
			// 用左连接 和 右链接的方式去实现
			// 用SQL去写
			preparedStatement = connection
					.prepareStatement("select id,rolename,roledesc from role");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("ID"));
				role.setRolename(resultSet.getString("ROLENAME"));
				role.setRoledesc(resultSet.getString("ROLEDESC"));
				roleList.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return roleList;
	}

	@Override
	public void delAllByUserId(int userid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("delete from user_role where userid = ?");
			preparedStatement.setObject(1, userid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public int updateUserRole(int userid, int roleid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into user_role (userid,roleid) values(?,?) ");
			preparedStatement.setObject(1, userid);
			preparedStatement.setObject(2, roleid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public List<Role> findRolesByPage(BigDecimal beginpagenumber,
			BigDecimal pagenumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Role> roles = new ArrayList<Role>();
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection
					.prepareStatement("select * from role limit ?,?");
			preparedStatement.setObject(1, beginpagenumber);
			preparedStatement.setObject(2, pagenumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("ID"));
				role.setRolename(resultSet.getString("ROLENAME"));
				roles.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return roles;
	}

	@Override
	public BigDecimal getAllCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BigDecimal totalCount = new BigDecimal(0);
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection
					.prepareStatement("select count(*) from role");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				totalCount = resultSet.getBigDecimal(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return totalCount;
	}

	@Override
	public Role findRoleByID(int roleid) {
		Role role = null;
		java.sql.Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		java.sql.ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select id,rolename from role where id = ?");
			preparedStatement.setObject(1, roleid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				role = new Role();
				role.setId(resultSet.getInt("ID"));
				role.setRolename(resultSet.getString("ROLENAME"));
				// Role.setPassword(resultSet.getString("PASSWORD"));
				// Role.setCreatetime(resultSet.getTimestamp("CREATETIME"));
				// Role.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return role;
	}

}
