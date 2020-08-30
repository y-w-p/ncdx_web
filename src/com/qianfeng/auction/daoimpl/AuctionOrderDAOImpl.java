package com.qianfeng.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.qianfeng.auction.dao.AuctionOrderDAO;
import com.qianfeng.auction.util.JDBCUtil;

public class AuctionOrderDAOImpl implements AuctionOrderDAO {

	@Override
	public int addOrder(String orderno, int auctionid, int userid,int auctionprice) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into auctionorder (orderno,userid,auctionid,price,createtime) values(?,?,?,?,?)");
			preparedStatement.setObject(1, orderno);
			preparedStatement.setObject(2, userid);
			preparedStatement.setObject(3, auctionid);
			preparedStatement.setObject(4, auctionprice);
			preparedStatement.setObject(5, new Timestamp(System.currentTimeMillis()));
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

}
