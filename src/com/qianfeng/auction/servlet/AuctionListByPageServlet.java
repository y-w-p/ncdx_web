package com.qianfeng.auction.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.eums.AuctionEnum;
import com.qianfeng.auction.eums.UserLoginEnum;
import com.qianfeng.auction.util.StringUtil;
import com.qianfeng.auction.vo.PageVO;

public class AuctionListByPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String pageindex = StringUtil.isEmpty(req.getParameter("pageindex")) ? "1"
				: req.getParameter("pageindex");
		String pagenumber = StringUtil.isEmpty(req.getParameter("pagenumber")) ? "5"
				: req.getParameter("pagenumber");
		String msg = StringUtil.isEmpty(req.getParameter("msg")) ? "" : req
				.getParameter("msg");
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		BigDecimal totalCount = auctionBIZ.getAllCount();
		if (msg.equals(UserLoginEnum.USER_LOGIN_SUCCESS.getValue())) {
			// 进入到这里说明 是登录进来
			pageindex = "1";
		} else if (msg.equals(AuctionEnum.AUCTION_ADD_SUCCESS.getValue())) {
			// 进入到这里说明是 添加成功
			pageindex = totalCount.divide(new BigDecimal(pagenumber), 0,
					BigDecimal.ROUND_UP).toString();
		}
		List<Auction> auctionList = auctionBIZ.findAuctionByPage(
				new BigDecimal(pageindex), new BigDecimal(pagenumber));
		// 实例化pageVO
		PageVO<Auction> pageVO = new PageVO<Auction>();
		try {
			pageVO.setLists(auctionList);
			pageVO.setPagenumber(new BigDecimal(pagenumber));
			pageVO.setTotal(totalCount);
			pageVO.setEndpage(totalCount.divide(new BigDecimal(pagenumber), 0,
					BigDecimal.ROUND_UP));
			pageVO.setpageindex(new BigDecimal(pageindex));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 把实例化后的PAGEVO 存储到请求报文中
		req.setAttribute("pagevo", pageVO);
		// 再把当前用户的HTTP请求转发到 分页文件中
		try {
			if (msg.equals(UserLoginEnum.USER_LOGIN_SUCCESS.getValue())) {
				// 进入到这里说明 是登录进来
				req.getRequestDispatcher(
						"auction_datagrid.jsp?msg="
								+ UserLoginEnum.USER_LOGIN_SUCCESS.getValue()
								+ "").forward(req, resp);
			} else if (msg.equals(AuctionEnum.AUCTION_ADD_SUCCESS.getValue())) {
				// 进入到这里说明是 添加成功
				req.getRequestDispatcher(
						"auction_datagrid.jsp?msg="
								+ AuctionEnum.AUCTION_ADD_SUCCESS.getValue()
								+ "").forward(req, resp);
			} else {
				// 进入到这里说明是正常分页进来
				req.getRequestDispatcher("auction_datagrid.jsp").forward(req,
						resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
