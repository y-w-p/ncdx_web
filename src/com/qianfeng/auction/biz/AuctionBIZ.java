package com.qianfeng.auction.biz;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.entity.User;

public interface AuctionBIZ {

	List<Auction> findAuctionByPage(BigDecimal pageindex, BigDecimal pagenumber);

	BigDecimal getAllCount();

	String addAuction(HttpServletRequest request, HttpServletResponse response,
			ServletConfig servletConfig);

	String updateAuction(HttpServletRequest request,
			HttpServletResponse response, ServletConfig servletConfig);

	Auction findAuctionByID(int auctionid);

	boolean delAuctionByID(int auctionid, String hostPath);

	List<Auction> searchAuctionList(String auctionname,
			String auctionstartprice, String auctionstarttime,
			String auctionendtime);
	
	List<Auction> getAll();
	
	String updateAuctionState(int auctionid);

}