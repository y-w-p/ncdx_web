package com.qianfeng.auction.dao;

public interface AuctionOrderDAO {

	int addOrder(String orderno, int auctionid, int userid,int auctionprice);

}
