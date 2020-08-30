package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.biz.AuctionRecordBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.bizimpl.AuctionRecordBIZImpl;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.entity.AuctionRecord;
import com.qianfeng.auction.util.AES;

public class AuctionRecordByIDPayReturnServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String auctionid = req.getParameter("auctionid");
		
		try {
			auctionid = AES.Decrypt(auctionid, "www.qianfeng.com");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		Auction auction = null;
		try {
			auction = auctionBIZ.findAuctionByID(Integer.parseInt(auctionid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("AuctionObj", auction);
		try {
			req.getRequestDispatcher("auction_detail_return.jsp").forward(req,
					resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
