package com.qianfeng.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.auction.dao.PermissionDAO;
import com.qianfeng.auction.entity.Permission;
import com.qianfeng.auction.entity.Role;
import com.qianfeng.auction.util.JDBCUtil;

public class PermissionDAOImpl implements PermissionDAO {

	@Override
	public List<Permission> findPermissionByRoleID(int roleid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Permission> permissionList = new ArrayList<Permission>();
		try {
			connection = JDBCUtil.getConnection();
			// 根据 用户ID 查找出 role表 的 rolename 和 roledesc 和 roleid
			// 用左连接 和 右链接的方式去实现
			// 用SQL去写
			preparedStatement = connection
					.prepareStatement("select id,permissionname,permissiondesc from permission as p left join role_permission as rp on p.id = rp.permissionid where	rp.roleid = ?");
			preparedStatement.setObject(1, roleid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Permission permission = new Permission();
				permission.setId(resultSet.getInt("ID"));
				permission.setPermissionname(resultSet
						.getString("PERMISSIONNAME"));
				permission.setPermissiondesc(resultSet
						.getString("PERMISSIONDESC"));
				permissionList.add(permission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return permissionList;
	}

	@Override
	public void delAllByRoleId(int roleid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("delete from role_permission where roleid = ?");
			preparedStatement.setObject(1, roleid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public int updateRolePermission(int roleid, int permissionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into role_permission (roleid,permissionid) values(?,?) ");
			preparedStatement.setObject(1, roleid);
			preparedStatement.setObject(2, permissionid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public List<Permission> getAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Permission> permissionList = new ArrayList<Permission>();
		try {
			connection = JDBCUtil.getConnection();
			// 根据 用户ID 查找出 Permission表 的 Permissionname 和 Permissiondesc 和
			// Permissionid
			// 用左连接 和 右链接的方式去实现
			// 用SQL去写
			preparedStatement = connection
					.prepareStatement("select id,permissionname,permissiondesc from permission");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Permission Permission = new Permission();
				Permission.setId(resultSet.getInt("ID"));
				Permission.setPermissionname(resultSet
						.getString("PERMISSIONNAME"));
				Permission.setPermissiondesc(resultSet
						.getString("PERMISSIONDESC"));
				permissionList.add(Permission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return permissionList;
	}

}
