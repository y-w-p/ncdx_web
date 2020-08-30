package com.qianfeng.auction.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.eums.AuctionEnum;

public class AuctionAddServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		String result = auctionBIZ.addAuction(req, resp,
				this.getServletConfig());
		try {
			if (result.equals(AuctionEnum.AUCTION_ADD_SUCCESS.getValue())) {
				// Ϊʲô�����õ����ض��� ������ת����
				// �ض��������� ��ɾ��
				// ����ѯ������ת��
				resp.sendRedirect("AuctionListByPageServlet?msg=" + result + "");
			} else {
				resp.sendRedirect("add_auction.jsp?msg=" + result + "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
