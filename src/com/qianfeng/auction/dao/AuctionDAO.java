package com.qianfeng.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.auction.entity.Auction;

public interface AuctionDAO {

	List<Auction> findAuctionByPage(BigDecimal beginpagenumber,
			BigDecimal pagenumber);

	BigDecimal getAllCount();

	int addAuction(String auctionname, String auctionstartprice,
			String auctionlowprice, String auctionstarttime, String auctionendtime,
			String auctiondesc,String auctionimage);

	int updateAuction(int auctionid,String auctionname, String auctionstartprice,
			String auctionlowprice, String auctionstarttime, String auctionendtime,
			String auctiondesc,String auctionimage);
	
	Auction findAuctionByID(int auctionid);
	
	int delAuctionByID(int auctionid);
	
	List<Auction> searchAuctionList(String sql);
	
	List<Auction> getAll();
	
	int updateAuctionState(int auctionid);


}