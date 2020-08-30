package com.qianfeng.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.auction.dao.AuctionRecordDAO;
import com.qianfeng.auction.dto.UserDTO;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.entity.AuctionRecord;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.util.JDBCUtil;

public class AuctionRecordDAOImpl implements AuctionRecordDAO {

	@Override
	public List<AuctionRecord> findAuctionRecordListByAuctionID(int auctionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<AuctionRecord> auctionRecords = new ArrayList<AuctionRecord>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select us.username as username02 ,atr.* from auctionrecord as atr left join user as us on atr.userid = us.id left join auction as ac on ac.id = atr.auctionid where ac.id = ? order by atr.auctionprice desc");
			preparedStatement.setObject(1, auctionid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AuctionRecord auctionRecord = new AuctionRecord();
				Auction auction = new Auction();
				User user = new User();
				auction.setId(resultSet.getInt("AUCTIONID"));
				user.setId(resultSet.getInt("USERID"));
				user.setUsername(resultSet.getString("USERNAME02"));
				auctionRecord.setUser(user);
				auctionRecord.setAuction(auction);
				auctionRecord.setAuctionprice(resultSet.getInt("AUCTIONPRICE"));
				auctionRecord.setAuctiontime(resultSet
						.getTimestamp("AUCTIONTIME"));
				auctionRecord.setCreatetime(resultSet
						.getTimestamp("CREATETIME"));
				auctionRecord.setUpdatetime(resultSet
						.getTimestamp("UPDATETIME"));
				auctionRecords.add(auctionRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auctionRecords;
	}

	public static void main(String[] args) {
	}

	@Override
	public int addAuctionRecord(int userid, int auctionid, int auctionprice) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into auctionrecord (userid,auctionid,auctionprice,auctiontime,createtime,updatetime) values(?,?,?,?,?,?)");
			preparedStatement.setObject(1, userid);
			preparedStatement.setObject(2, auctionid);
			preparedStatement.setObject(3, auctionprice);
			preparedStatement.setObject(4,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setObject(5,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setObject(6,
					new Timestamp(System.currentTimeMillis()));
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public UserDTO findMaxAuctionPriceUserByAuctionID(int auctionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserDTO userDTO = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM `user` AS us LEFT JOIN auctionrecord AS ar ON us.id = ar.userid WHERE ar.auctionid = ? AND ar.auctionprice = ( SELECT max(auctionprice) FROM auctionrecord WHERE auctionid = ? )");
			preparedStatement.setObject(1, auctionid);
			preparedStatement.setObject(2, auctionid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userDTO = new UserDTO();
				userDTO.setId(resultSet.getInt("USERID"));
				userDTO.setUsername(resultSet.getString("USERNAME"));
				userDTO.setEmail(resultSet.getString("EMAIL"));
				userDTO.setAuctionprice(resultSet.getInt("AUCTIONPRICE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return userDTO;
	}
}
