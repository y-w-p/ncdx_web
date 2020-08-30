package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.vo.PageVO;

public class AuctionSearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String auctionname = req.getParameter("auctionname");
		String auctionstartprice = req.getParameter("auctionstartprice");
		String auctionstarttime = req.getParameter("auctionstarttime");
		String auctionendtime = req.getParameter("auctionendtime");
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		List<Auction> auctionList = null;
		try {
			auctionList = auctionBIZ.searchAuctionList(auctionname,
					auctionstartprice, auctionstarttime, auctionendtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageVO<Auction> pageVO = new PageVO<Auction>();
		pageVO.setLists(auctionList);
		pageVO.setpageindex(new BigDecimal(1));
		pageVO.setPagenumber(new BigDecimal(auctionList.size()));
		pageVO.setTotal(new BigDecimal(auctionList.size()));
		pageVO.setEndpage(new BigDecimal(1));
		// 将PAGEVO 存储到请求报文中 （服务器的行为）
		req.setAttribute("pagevo", pageVO);
		try {
			req.getRequestDispatcher("auction_datagrid.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
