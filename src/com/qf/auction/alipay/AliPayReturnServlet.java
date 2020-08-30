package com.qf.auction.alipay;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.biz.AuctionOrderBIZ;
import com.qianfeng.auction.biz.AuctionRecordBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.bizimpl.AuctionOrderBIZImpl;
import com.qianfeng.auction.bizimpl.AuctionRecordBIZImpl;
import com.qianfeng.auction.eums.AuctionEnum;
import com.qianfeng.auction.eums.AuctionOrderEnum;
import com.qianfeng.auction.util.AES;

public class AliPayReturnServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String body = request.getParameter("body");
		String sKey = "www.qianfeng.com";
		try {
			String[] userprams = body.split(",");
			String orderno = userprams[0];
			String auctionid = userprams[1];
			String userid = userprams[2];
			String auctionprice = userprams[3];
			// orderno = AES.Decrypt(orderno, sKey);
			
			auctionid  = AES.Decrypt(auctionid, sKey);
			userid = AES.Decrypt(userid, sKey);
			auctionprice = AES.Decrypt(auctionprice, sKey);

			AuctionBIZ auctionBIZ = new AuctionBIZImpl();
			String result = auctionBIZ.updateAuctionState(Integer
					.parseInt(auctionid));
			if (result.equals(AuctionEnum.AUCTION_UPDATE_FAIL.getValue())) {
				response.getWriter().print("no");
				return;
			}

			AuctionOrderBIZ auctionOrderBIZ = new AuctionOrderBIZImpl();
			String result02 = auctionOrderBIZ.addOrder(orderno,
					Integer.parseInt(auctionid), Integer.parseInt(userid),
					Integer.parseInt(auctionprice));
			if (result02.equals(AuctionOrderEnum.ADD_ORDER_FAIL.getValue())) {
				response.getWriter().print("no");
				return;
			}
			// 告訴支付寶 你OK了 如果你不告訴它 它會訪問這個 文件9次
			response.getWriter().print("ok");
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				response.getWriter().print("no");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
