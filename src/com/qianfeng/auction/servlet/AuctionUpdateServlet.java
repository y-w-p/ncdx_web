package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.eums.AuctionEnum;

public class AuctionUpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		String result = auctionBIZ.updateAuction(req, resp,
				this.getServletConfig());
		try {
			if (result.equals(AuctionEnum.AUCTION_UPDATE_SUCCESS.getValue())) {
				// Ϊʲô�����õ����ض��� ������ת����
				// �ض��������� ��ɾ��
				// ����ѯ������ת��
				resp.sendRedirect("AuctionListByPageServlet?msg=" + result
						+ "&pageindex=" + req.getParameter("pageindex") + "");
			} else {
  				req.getRequestDispatcher(
						"update_auction.jsp?msg=" + result + "").forward(req,
						resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
