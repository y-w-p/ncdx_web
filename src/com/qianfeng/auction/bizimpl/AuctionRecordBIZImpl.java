package com.qianfeng.auction.bizimpl;

import java.util.List;

import com.qianfeng.auction.biz.AuctionRecordBIZ;
import com.qianfeng.auction.dao.AuctionRecordDAO;
import com.qianfeng.auction.daoimpl.AuctionRecordDAOImpl;
import com.qianfeng.auction.dto.UserDTO;
import com.qianfeng.auction.entity.AuctionRecord;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.eums.AuctionEnum;
import com.qianfeng.auction.util.StringUtil;

public class AuctionRecordBIZImpl implements AuctionRecordBIZ {

	AuctionRecordDAO auctionRecordDAO = new AuctionRecordDAOImpl();

	@Override
	public List<AuctionRecord> findAuctionRecordListByAuctionID(int auctionid) {
		return auctionRecordDAO.findAuctionRecordListByAuctionID(auctionid);
	}

	@Override
	public String addAuctionRecord(int userid, int auctionid, int auctionprice) {
		if (userid == 0) {
			return AuctionEnum.AUCTION_FAIL.getValue();
		}
		if (auctionid == 0) {
			return AuctionEnum.AUCTION_FAIL.getValue();
		}
		if (auctionprice == 0) {
			return AuctionEnum.AUCTION_FAIL.getValue();
		}
		int executeCount = auctionRecordDAO.addAuctionRecord(userid, auctionid,
				auctionprice);
		if (executeCount == 0) {
			return AuctionEnum.AUCTION_FAIL.getValue();
		}
		return AuctionEnum.AUCTION_SUCCESS.getValue();
	}

	@Override
	public UserDTO findMaxAuctionPriceUserByAuctionID(int auctionid) {
		return auctionRecordDAO.findMaxAuctionPriceUserByAuctionID(auctionid);
	}

}
