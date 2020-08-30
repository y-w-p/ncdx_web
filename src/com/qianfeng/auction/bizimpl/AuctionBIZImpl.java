package com.qianfeng.auction.bizimpl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.dao.AuctionDAO;
import com.qianfeng.auction.daoimpl.AuctionDAOImpl;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.eums.AuctionEnum;
import com.qianfeng.auction.util.StringUtil;

public class AuctionBIZImpl implements AuctionBIZ {

	AuctionDAO auctionDAO = new AuctionDAOImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianfeng.auction.bizimpl.AuctionBIZ#findAuctionByPage(java.math.
	 * BigDecimal, java.math.BigDecimal)
	 */
	public List<Auction> findAuctionByPage(BigDecimal pageindex,
			BigDecimal pagenumber) {
		// 分页的公式是 (pageindex-1)*pagenum (从第几条开始)
		BigDecimal beginpagenumber = pageindex.subtract(new BigDecimal(1))
				.multiply(pagenumber);
		return auctionDAO.findAuctionByPage(beginpagenumber, pagenumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianfeng.auction.bizimpl.AuctionBIZ#getAllCount()
	 */
	public BigDecimal getAllCount() {
		return auctionDAO.getAllCount();
	}

	@Override
	public String addAuction(HttpServletRequest request,
			HttpServletResponse response, ServletConfig servletConfig) {
		// 实例化smartupload
		SmartUpload smartUpload = new SmartUpload();
		try {
			// 初始化 这里加载完成之后 就可以获取到 用户提交的所有数据
			smartUpload.initialize(servletConfig, request, response);
			smartUpload.setMaxFileSize(1024 * 1024 * 20);
			smartUpload.upload();
		} catch (Exception e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_ADD_FAIL.getValue();
		}

		String auctionname = smartUpload.getRequest().getParameter(
				"auctionname");
		String auctionstartprice = smartUpload.getRequest().getParameter(
				"auctionstartprice");
		String auctionlowprice = smartUpload.getRequest().getParameter(
				"auctionlowprice");
		String auctionstarttime = smartUpload.getRequest().getParameter(
				"auctionstarttime");
		String auctionendtime = smartUpload.getRequest().getParameter(
				"auctionendtime");
		String auctiondesc = smartUpload.getRequest().getParameter(
				"auctiondesc");
		if (StringUtil.isEmpty(auctionname)) {
			return AuctionEnum.AUCTION_NAME_IS_NULL.getValue();
		}
		try {
			if (StringUtil.isEmpty(auctionstartprice)
					&& Integer.parseInt(auctionstartprice) > 0) {
				return AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getValue();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getValue();
		}
		try {
			if (StringUtil.isEmpty(auctionlowprice)
					&& Integer.parseInt(auctionlowprice) > 0) {
				return AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getValue();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getValue();
		}
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(auctionstarttime);
		} catch (Exception e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_START_TIME_IS_FAIL.getValue();
		}
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(auctionendtime);
		} catch (Exception e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_END_TIME_IS_FAIL.getValue();
		}
		if (StringUtil.isEmpty(auctiondesc)) {
			return AuctionEnum.AUCTION_DESC_IS_FAIL.getValue();
		}
		// 如果以上合法化全部通过 那么就获取 用户上传的文件
		com.jspsmart.upload.File usersFile = smartUpload.getFiles().getFile(0);
		String fileName = null;
		if (usersFile.getSize() > 0) {
			// 如果能进来说明用户 有上传文件
			// 抓取文件的后缀
			String fileEXT = usersFile.getFileExt();
			// 通过当前时间+文件后缀生成文件名
			fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date()) + "." + fileEXT;
			// 获取到服务器的路径 （文件存放地址）
			// 系统相关的资源 适合 存储到 关系型数据库中 (好处：数据容易维护 坏处：速度会变慢)
			// 用户相关的资源 适合 存储到 服务器中 (好处：速度块 坏处：维护起来成本高一点)
			String hostPath = request.getRealPath("upload");
			// 上传文件到服务器
			try {
				// File.separator 具有 跨平台的特性
				usersFile.saveAs(hostPath + File.separator + fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return AuctionEnum.AUCTION_ADD_FAIL.getValue();
			}
		}
		int executeCount = auctionDAO.addAuction(auctionname,
				auctionstartprice, auctionlowprice, auctionstarttime,
				auctionendtime, auctiondesc, fileName);
		if (executeCount == 0) {
			return AuctionEnum.AUCTION_ADD_FAIL.getValue();
		}
		return AuctionEnum.AUCTION_ADD_SUCCESS.getValue();
	}

	@Override
	public String updateAuction(HttpServletRequest request,
			HttpServletResponse response, ServletConfig servletConfig) {
		// 实例化smartupload
		SmartUpload smartUpload = new SmartUpload();
		try {
			// 初始化 这里加载完成之后 就可以获取到 用户提交的所有数据
			smartUpload.initialize(servletConfig, request, response);
			smartUpload.setMaxFileSize(1024 * 1024 * 20);
			smartUpload.upload();
		} catch (Exception e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_UPDATE_FAIL.getValue();
		}
		String auctionid = smartUpload.getRequest().getParameter("auctionid");
		String beforeimage = smartUpload.getRequest().getParameter(
				"beforeimage");
		String auctionname = smartUpload.getRequest().getParameter(
				"auctionname");
		String auctionstartprice = smartUpload.getRequest().getParameter(
				"auctionstartprice");
		String auctionlowprice = smartUpload.getRequest().getParameter(
				"auctionlowprice");
		String auctionstarttime = smartUpload.getRequest().getParameter(
				"auctionstarttime");
		String auctionendtime = smartUpload.getRequest().getParameter(
				"auctionendtime");
		String auctiondesc = smartUpload.getRequest().getParameter(
				"auctiondesc");
		if (StringUtil.isEmpty(auctionname)) {
			return AuctionEnum.AUCTION_NAME_IS_NULL.getValue();
		}
		try {
			if (StringUtil.isEmpty(auctionstartprice)
					& Integer.parseInt(auctionstartprice) > 0) {
				return AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getValue();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getValue();
		}
		try {
			if (StringUtil.isEmpty(auctionlowprice)
					& Integer.parseInt(auctionlowprice) > 0) {
				return AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getValue();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getValue();
		}
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(auctionstarttime);
		} catch (Exception e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_START_TIME_IS_FAIL.getValue();
		}
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(auctionendtime);
		} catch (Exception e) {
			e.printStackTrace();
			return AuctionEnum.AUCTION_END_TIME_IS_FAIL.getValue();
		}
		if (StringUtil.isEmpty(auctiondesc)) {
			return AuctionEnum.AUCTION_DESC_IS_FAIL.getValue();
		}
		// 如果以上合法化全部通过 那么就获取 用户上传的文件
		com.jspsmart.upload.File usersFile = smartUpload.getFiles().getFile(0);
		String fileName = null;
		if (usersFile.getSize() > 0) {
			// 如果能进来说明用户 有上传文件
			// 抓取文件的后缀
			String fileEXT = usersFile.getFileExt();
			// 通过当前时间+文件后缀生成文件名
			fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date()) + "." + fileEXT;
			// 获取到服务器的路径 （文件存放地址）
			// 系统相关的资源 适合 存储到 关系型数据库中 (好处：数据容易维护 坏处：速度会变慢)
			// 用户相关的资源 适合 存储到 服务器中 (好处：速度块 坏处：维护起来成本高一点)
			String hostPath = request.getRealPath("upload");
			// 上传文件到服务器
			try {
				// File.separator 具有 跨平台的特性
				usersFile.saveAs(hostPath + File.separator + fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return AuctionEnum.AUCTION_UPDATE_FAIL.getValue();
			}
			int executeCount = 0;
			try {
				executeCount = auctionDAO.updateAuction(
						Integer.parseInt(auctionid), auctionname,
						auctionstartprice, auctionlowprice, auctionstarttime,
						auctionendtime, auctiondesc, fileName);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (executeCount == 0) {
				return AuctionEnum.AUCTION_UPDATE_FAIL.getValue();
			}
			// 上传和持久层添加成功后 把之前的文件删除
			java.io.File beforeFile = new File(hostPath
					+ java.io.File.separator + beforeimage);
			beforeFile.delete();
		} else {
			int executeCount = 0;
			try {
				executeCount = auctionDAO.updateAuction(
						Integer.parseInt(auctionid), auctionname,
						auctionstartprice, auctionlowprice, auctionstarttime,
						auctionendtime, auctiondesc, beforeimage);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (executeCount == 0) {
				return AuctionEnum.AUCTION_UPDATE_FAIL.getValue();
			}
		}
		return AuctionEnum.AUCTION_UPDATE_SUCCESS.getValue();
	}

	@Override
	public Auction findAuctionByID(int auctionid) {
		return auctionDAO.findAuctionByID(auctionid);
	}

	@Override
	public boolean delAuctionByID(int auctionid, String hostPath) {
		Auction auction = auctionDAO.findAuctionByID(auctionid);
		try {
			File file = new File(hostPath + File.separator
					+ auction.getAuctionimage());
			file.delete();
			int executeCount = auctionDAO.delAuctionByID(auctionid);
			if (executeCount == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Auction> searchAuctionList(String auctionname,
			String auctionstartprice, String auctionstarttime,
			String auctionendtime) {
		// sql动态 一定要满足一个恒等条件
		// 在SQL语句拼接的时候 需要注意后面要加一个空格
		StringBuilder sqlBuilder = new StringBuilder(
				"select * from auction where 1=1 ");
		if (StringUtil.isNotEmpty(auctionname)) {
			sqlBuilder.append("and auctionname like '" + auctionname + "%' ");
		}
		if (StringUtil.isNotEmpty(auctionstartprice)) {
			sqlBuilder.append("and auctionstartprice >= '" + auctionstartprice
					+ "' ");
		}
		if (StringUtil.isNotEmpty(auctionstarttime)) {
			sqlBuilder.append("and auctionstarttime >= '" + auctionstarttime
					+ "' ");
		}
		if (StringUtil.isNotEmpty(auctionendtime)) {
			sqlBuilder
					.append("and auctionendtime <= '" + auctionendtime + "' ");
		}
		return auctionDAO.searchAuctionList(sqlBuilder.toString());
	}

	@Override
	public List<Auction> getAll() {
		return auctionDAO.getAll();
	}

	@Override
	public String updateAuctionState(int auctionid) {
		int executeCount = auctionDAO.updateAuctionState(auctionid);
		if (executeCount == 0) {
			return AuctionEnum.AUCTION_UPDATE_FAIL.getValue();
		}
		return AuctionEnum.AUCTION_UPDATE_SUCCESS.getValue();
	}
}
