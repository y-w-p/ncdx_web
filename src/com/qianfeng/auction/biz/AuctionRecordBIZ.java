package com.qianfeng.auction.biz;

import java.util.List;

import com.qianfeng.auction.dto.UserDTO;
import com.qianfeng.auction.entity.AuctionRecord;
import com.qianfeng.auction.entity.User;

public interface AuctionRecordBIZ {
	
	 List<AuctionRecord> findAuctionRecordListByAuctionID(int auctionid);
	
	 String addAuctionRecord(int userid,int auctionid,int auctionprice);
	 
	 UserDTO findMaxAuctionPriceUserByAuctionID(int auctionid);

}
