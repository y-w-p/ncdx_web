package com.qianfeng.auction.dao;

import java.util.List;

import com.qianfeng.auction.dto.UserDTO;
import com.qianfeng.auction.entity.AuctionRecord;
import com.qianfeng.auction.entity.User;

public interface AuctionRecordDAO {

	List<AuctionRecord> findAuctionRecordListByAuctionID(int auctionid);

	int addAuctionRecord(int userid, int auctionid, int auctionprice);

	UserDTO findMaxAuctionPriceUserByAuctionID(int auctionid);

}
