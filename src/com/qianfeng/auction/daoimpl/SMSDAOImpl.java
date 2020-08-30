package com.qianfeng.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.qianfeng.auction.dao.SMSDAO;
import com.qianfeng.auction.entity.SMS;
import com.qianfeng.auction.util.JDBCUtil;

public class SMSDAOImpl implements SMSDAO {

	@Override
	public List<SMS> findTimeOverSMS() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<SMS> smsList = new ArrayList<SMS>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from sms as s where time_to_sec(NOW())-time_to_sec(s.createtime)>300 ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SMS sms = new SMS();
				sms.setId(resultSet.getInt("ID"));
				sms.setMobilenumber("MOBILE_NUMBER");
				sms.setValidatecode("VALIDATE_CODE");
				sms.setCreatetime(resultSet.getTimestamp("CREATETIME"));
				smsList.add(sms);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return smsList;
	}

	@Override
	public int addSMS(SMS sms) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into sms (mobile_number,validate_code,createtime) values (?,?,?)");
			preparedStatement.setObject(1, sms.getMobilenumber());
			preparedStatement.setObject(2, sms.getValidatecode());
			preparedStatement.setObject(3, sms.getCreatetime());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public int delSMSByID(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("delete from sms where id = ?");
			preparedStatement.setObject(1, id);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public SMS findSMSByMaxCreateTime() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SMS sms = new SMS();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from sms where createtime = ( select max(createtime) from sms as s )");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				sms = new SMS();
				sms.setId(resultSet.getInt("ID"));
				sms.setMobilenumber(resultSet.getString("MOBILE_NUMBER"));
				sms.setValidatecode(resultSet.getString("VALIDATE_CODE"));
				sms.setCreatetime(resultSet.getTimestamp("CREATETIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return sms;
	}

}
