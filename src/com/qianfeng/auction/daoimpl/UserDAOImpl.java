package com.qianfeng.auction.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.auction.dao.UserDAO;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.util.JDBCUtil;

public class UserDAOImpl implements UserDAO {

	public User userLogin(String username, String password) {
		User user = null;
		java.sql.Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		java.sql.ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select id,username,password from user where username=? and password=?");
			preparedStatement.setObject(1, username);
			preparedStatement.setObject(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUsername(resultSet.getString("USERNAME"));
				user.setPassword(resultSet.getString("PASSWORD"));
				// user.setCreatetime(resultSet.getTimestamp("CREATETIME"));
				// user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

	@Override
	public List<User> findUsersByPage(BigDecimal beginpagenumber,
			BigDecimal pagenumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<User>();
		try {
			connection = JDBCUtil.getConnection();
			// sql ×¢Èë or'1'='1
			preparedStatement = connection
					.prepareStatement("select * from user limit ?,?");
			preparedStatement.setObject(1, beginpagenumber);
			preparedStatement.setObject(2, pagenumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUsername(resultSet.getString("USERNAME"));
				user.setCreatetime(resultSet.getTimestamp("createtime"));
				user.setUpdateTime(resultSet.getTimestamp("updatetime"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return users;
	}

	@Override
	public BigDecimal getAllCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BigDecimal totalCount = new BigDecimal(0);
		try {
			connection = JDBCUtil.getConnection();
			// sql ×¢Èë or'1'='1
			preparedStatement = connection
					.prepareStatement("select count(*) from user");
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
	public User findUserByID(int userid) {
		User user = null;
		java.sql.Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		java.sql.ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select id,username from user where id = ?");
			preparedStatement.setObject(1, userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUsername(resultSet.getString("USERNAME"));
				// user.setPassword(resultSet.getString("PASSWORD"));
				// user.setCreatetime(resultSet.getTimestamp("CREATETIME"));
				// user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

}
