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

public class AuctionRecordByIDServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String auctionid = req.getParameter("auctionid");
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
		Auction auction = null;
		List<AuctionRecord> records = new ArrayList<AuctionRecord>();
		try {
			auction = auctionBIZ.findAuctionByID(Integer.parseInt(auctionid));
			records = auctionRecordBIZ.findAuctionRecordListByAuctionID(Integer
					.parseInt(auctionid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("AuctionObj", auction);
		req.setAttribute("record_list", records);
		try {
			req.getRequestDispatcher("auction_detail.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String[] temp = {"1","2","2"};
	}
}
