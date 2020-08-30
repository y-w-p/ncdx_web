package com.qianfeng.auction.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.auction.dao.AuctionDAO;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.util.JDBCUtil;

public class AuctionDAOImpl implements AuctionDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianfeng.auction.daoimpl.AuctionDAO#findAuctionByPage(java.math.
	 * BigDecimal, java.math.BigDecimal)
	 */
	public List<Auction> findAuctionByPage(BigDecimal beginpagenumber,
			BigDecimal pagenumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctions = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection
					.prepareStatement("select * from auction where state=1 limit ?,?");
			preparedStatement.setObject(1, beginpagenumber);
			preparedStatement.setObject(2, pagenumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setId(resultSet.getInt("ID"));
				auction.setAuctionname(resultSet.getString("Auctionname"));
				auction.setAuctiondesc(resultSet.getString("Auctiondesc"));
				auction.setauctionstarttime(resultSet
						.getTimestamp("auctionstarttime"));
				auction.setAuctionendtime(resultSet
						.getTimestamp("Auctionendtime"));
				auction.setAuctionstartprice(resultSet
						.getInt("Auctionstartprice"));
				auction.setAuctionlowprice(resultSet.getInt("Auctionlowprice"));
				auction.setAuctionimage(resultSet.getString("Auctionimage"));
				auction.setAuctioncreatetime(resultSet
						.getTimestamp("Auctioncreatetime"));
				auction.setAuctionupdatetime(resultSet
						.getTimestamp("Auctionupdatetime"));
				auctions.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auctions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianfeng.auction.daoimpl.AuctionDAO#getAllCount()
	 */
	public BigDecimal getAllCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BigDecimal totalCount = new BigDecimal(0);
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection
					.prepareStatement("select count(*) from auction");
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
	public int addAuction(String auctionname, String auctionstartprice,
			String auctionlowprice, String auctionstarttime,
			String auctionendtime, String auctiondesc, String auctionimage) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into auction (auctionname,auctionStartPrice,auctionLowPrice,auctionstarttime,auctionEndTime,auctionDESC,auctionCreateTime,auctionUpdateTime,auctionImage) values (?,?,?,?,?,?,?,?,?)");
			preparedStatement.setObject(1, auctionname);
			preparedStatement.setObject(2, auctionstartprice);
			preparedStatement.setObject(3, auctionlowprice);
			preparedStatement.setObject(4, auctionstarttime);
			preparedStatement.setObject(5, auctionendtime);
			preparedStatement.setObject(6, auctiondesc);
			preparedStatement.setObject(7,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setObject(8,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setObject(9, auctionimage);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public int updateAuction(int auctionid, String auctionname,
			String auctionstartprice, String auctionlowprice,
			String auctionstarttime, String auctionendtime, String auctiondesc,
			String auctionimage) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("update auction set auctionname=?,auctionStartPrice=?,auctionLowPrice=?,auctionstarttime=?,auctionEndTime=?,auctionDESC=?,auctionUpdateTime=?,auctionImage=? where id =?");
			preparedStatement.setObject(1, auctionname);
			preparedStatement.setObject(2, auctionstartprice);
			preparedStatement.setObject(3, auctionlowprice);
			preparedStatement.setObject(4, auctionstarttime);
			preparedStatement.setObject(5, auctionendtime);
			preparedStatement.setObject(6, auctiondesc);
			preparedStatement.setObject(7,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setObject(8, auctionimage);
			preparedStatement.setObject(9, auctionid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public Auction findAuctionByID(int auctionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Auction auction = null;
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection
					.prepareStatement("select * from auction where id = ?");
			preparedStatement.setObject(1, auctionid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				auction = new Auction();
				auction.setId(resultSet.getInt("ID"));
				auction.setAuctionname(resultSet.getString("Auctionname"));
				auction.setAuctiondesc(resultSet.getString("Auctiondesc"));
				auction.setauctionstarttime(resultSet
						.getTimestamp("auctionstarttime"));
				auction.setAuctionendtime(resultSet
						.getTimestamp("Auctionendtime"));
				auction.setAuctionstartprice(resultSet
						.getInt("Auctionstartprice"));
				auction.setAuctionlowprice(resultSet.getInt("Auctionlowprice"));
				auction.setAuctionimage(resultSet.getString("Auctionimage"));
				auction.setAuctioncreatetime(resultSet
						.getTimestamp("Auctioncreatetime"));
				auction.setAuctionupdatetime(resultSet
						.getTimestamp("Auctionupdatetime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auction;
	}

	@Override
	public int delAuctionByID(int auctionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("delete from auction where id = ?");
			preparedStatement.setObject(1, auctionid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public List<Auction> searchAuctionList(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctions = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setId(resultSet.getInt("ID"));
				auction.setAuctionname(resultSet.getString("Auctionname"));
				auction.setAuctiondesc(resultSet.getString("Auctiondesc"));
				auction.setauctionstarttime(resultSet
						.getTimestamp("auctionstarttime"));
				auction.setAuctionendtime(resultSet
						.getTimestamp("Auctionendtime"));
				auction.setAuctionstartprice(resultSet
						.getInt("Auctionstartprice"));
				auction.setAuctionlowprice(resultSet.getInt("Auctionlowprice"));
				auction.setAuctionimage(resultSet.getString("Auctionimage"));
				auction.setAuctioncreatetime(resultSet
						.getTimestamp("Auctioncreatetime"));
				auction.setAuctionupdatetime(resultSet
						.getTimestamp("Auctionupdatetime"));
				auctions.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auctions;
	}

	@Override
	public List<Auction> getAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctions = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			// sql 注入 or'1'='1
			preparedStatement = connection
					.prepareStatement("select * from auction");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setId(resultSet.getInt("ID"));
				auction.setState(resultSet.getInt("STATE"));
				auction.setAuctionname(resultSet.getString("Auctionname"));
				auction.setAuctiondesc(resultSet.getString("Auctiondesc"));
				auction.setauctionstarttime(resultSet
						.getTimestamp("auctionstarttime"));
				auction.setAuctionendtime(resultSet
						.getTimestamp("Auctionendtime"));
				auction.setAuctionstartprice(resultSet
						.getInt("Auctionstartprice"));
				auction.setAuctionlowprice(resultSet.getInt("Auctionlowprice"));
				auction.setAuctionimage(resultSet.getString("Auctionimage"));
				auction.setAuctioncreatetime(resultSet
						.getTimestamp("Auctioncreatetime"));
				auction.setAuctionupdatetime(resultSet
						.getTimestamp("Auctionupdatetime"));
				auctions.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auctions;
	}

	@Override
	public int updateAuctionState(int auctionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("update auction set state=2  where id =?");
			preparedStatement.setObject(1, auctionid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

}
