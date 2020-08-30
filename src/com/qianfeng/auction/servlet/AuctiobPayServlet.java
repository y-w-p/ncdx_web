package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionRecordBIZ;
import com.qianfeng.auction.bizimpl.AuctionRecordBIZImpl;
import com.qianfeng.auction.eums.AuctionEnum;

public class AuctiobPayServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String auctionprice = req.getParameter("auctionprice");
		String auctionid = req.getParameter("auctionid");
		String userid = req.getParameter("userid");
		AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
		String result = auctionRecordBIZ.addAuctionRecord(
				Integer.parseInt(userid), Integer.parseInt(auctionid),
				Integer.parseInt(auctionprice));
		req.getRequestDispatcher("AuctionRecordByIDServlet?msg=" + result + "")
				.forward(req, resp);
	}

}
