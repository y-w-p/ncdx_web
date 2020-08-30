package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;

public class AuctionDelByIDServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String auctionid = req.getParameter("auctionid");
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		boolean del = false;
		try {
			del = auctionBIZ.delAuctionByID(Integer.parseInt(auctionid),req.getRealPath("upload"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		resp.getWriter().print(del);
	}
}
