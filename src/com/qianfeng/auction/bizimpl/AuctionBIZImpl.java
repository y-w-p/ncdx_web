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
		// ��ҳ�Ĺ�ʽ�� (pageindex-1)*pagenum (�ӵڼ�����ʼ)
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
		// ʵ����smartupload
		SmartUpload smartUpload = new SmartUpload();
		try {
			// ��ʼ�� ����������֮�� �Ϳ��Ի�ȡ�� �û��ύ����������
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
		// ������ϺϷ���ȫ��ͨ�� ��ô�ͻ�ȡ �û��ϴ����ļ�
		com.jspsmart.upload.File usersFile = smartUpload.getFiles().getFile(0);
		String fileName = null;
		if (usersFile.getSize() > 0) {
			// ����ܽ���˵���û� ���ϴ��ļ�
			// ץȡ�ļ��ĺ�׺
			String fileEXT = usersFile.getFileExt();
			// ͨ����ǰʱ��+�ļ���׺�����ļ���
			fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date()) + "." + fileEXT;
			// ��ȡ����������·�� ���ļ���ŵ�ַ��
			// ϵͳ��ص���Դ �ʺ� �洢�� ��ϵ�����ݿ��� (�ô�����������ά�� �������ٶȻ����)
			// �û���ص���Դ �ʺ� �洢�� �������� (�ô����ٶȿ� ������ά�������ɱ���һ��)
			String hostPath = request.getRealPath("upload");
			// �ϴ��ļ���������
			try {
				// File.separator ���� ��ƽ̨������
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
		// ʵ����smartupload
		SmartUpload smartUpload = new SmartUpload();
		try {
			// ��ʼ�� ����������֮�� �Ϳ��Ի�ȡ�� �û��ύ����������
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
		// ������ϺϷ���ȫ��ͨ�� ��ô�ͻ�ȡ �û��ϴ����ļ�
		com.jspsmart.upload.File usersFile = smartUpload.getFiles().getFile(0);
		String fileName = null;
		if (usersFile.getSize() > 0) {
			// ����ܽ���˵���û� ���ϴ��ļ�
			// ץȡ�ļ��ĺ�׺
			String fileEXT = usersFile.getFileExt();
			// ͨ����ǰʱ��+�ļ���׺�����ļ���
			fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date()) + "." + fileEXT;
			// ��ȡ����������·�� ���ļ���ŵ�ַ��
			// ϵͳ��ص���Դ �ʺ� �洢�� ��ϵ�����ݿ��� (�ô�����������ά�� �������ٶȻ����)
			// �û���ص���Դ �ʺ� �洢�� �������� (�ô����ٶȿ� ������ά�������ɱ���һ��)
			String hostPath = request.getRealPath("upload");
			// �ϴ��ļ���������
			try {
				// File.separator ���� ��ƽ̨������
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
			// �ϴ��ͳ־ò���ӳɹ��� ��֮ǰ���ļ�ɾ��
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
		// sql��̬ һ��Ҫ����һ���������
		// ��SQL���ƴ�ӵ�ʱ�� ��Ҫע�����Ҫ��һ���ո�
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
