package com.qianfeng.auction.bizimpl;

import com.qianfeng.auction.biz.AuctionOrderBIZ;
import com.qianfeng.auction.dao.AuctionOrderDAO;
import com.qianfeng.auction.daoimpl.AuctionOrderDAOImpl;
import com.qianfeng.auction.eums.AuctionOrderEnum;
import com.qianfeng.auction.util.StringUtil;

public class AuctionOrderBIZImpl implements AuctionOrderBIZ {

	AuctionOrderDAO auctionOrderDAO = new AuctionOrderDAOImpl();

	@Override
	public String addOrder(String orderno, int auctionid, int userid,
			int auctionprice) {
		if (StringUtil.isEmpty(orderno)) {
			return AuctionOrderEnum.ADD_ORDER_FAIL.getValue();
		}
		if (auctionid == 0) {
			return AuctionOrderEnum.ADD_ORDER_FAIL.getValue();
		}
		if (userid == 0) {
			return AuctionOrderEnum.ADD_ORDER_FAIL.getValue();
		}
		if (auctionprice == 0) {
			return AuctionOrderEnum.ADD_ORDER_FAIL.getValue();
		}
		int executeCount = auctionOrderDAO.addOrder(orderno, auctionid, userid,
				auctionprice);
		if (executeCount == 0) {
			return AuctionOrderEnum.ADD_ORDER_FAIL.getValue();
		}
		return AuctionOrderEnum.ADD_ORDER_SUCCESS.getValue();
	}

}
