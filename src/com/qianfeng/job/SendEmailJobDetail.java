package com.qianfeng.job;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.jobs.ee.mail.SendMailJob;

import com.qianfeng.auction.biz.AuctionBIZ;
import com.qianfeng.auction.biz.AuctionRecordBIZ;
import com.qianfeng.auction.bizimpl.AuctionBIZImpl;
import com.qianfeng.auction.bizimpl.AuctionRecordBIZImpl;
import com.qianfeng.auction.dto.UserDTO;
import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.entity.AuctionRecord;
import com.qianfeng.auction.entity.User;
import com.qianfeng.auction.eums.AuctionEnum;
import com.qianfeng.auction.util.EntityCopyUtil;
import com.qianfeng.email.EmailSender;

public class SendEmailJobDetail implements Job {

	Logger logger = Logger.getLogger(SendEmailJobDetail.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		List<Auction> auctionList = auctionBIZ.getAll();
		for (Auction auction : auctionList) {
			if (auction.getAuctionendtime().getYear() == new Timestamp(
					System.currentTimeMillis()).getYear()
					&& auction.getAuctionendtime().getMonth() == new Timestamp(
							System.currentTimeMillis()).getMonth()
					&& auction.getAuctionendtime().getDate() == new Timestamp(
							System.currentTimeMillis()).getDate()
					&& auction.getState() == 1) {
				AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
				UserDTO user = auctionRecordBIZ
						.findMaxAuctionPriceUserByAuctionID(auction.getId());
				if (user != null) {
					EmailSender emailSender = new EmailSender();
					emailSender.setAuctionid(auction.getId());
					emailSender.setUserid(user.getId());
					emailSender.setAuctionprice(user.getAuctionprice());
					emailSender.setEmail(user.getEmail());
					emailSender.setUsername(user.getUsername());
					emailSender.setAuctionName(auction.getAuctionname());
					try {
						emailSender.send();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
