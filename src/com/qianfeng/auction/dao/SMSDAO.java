package com.qianfeng.auction.dao;

import java.util.List;

import com.qianfeng.auction.entity.SMS;

public interface SMSDAO {

	List<SMS> findTimeOverSMS();

	int addSMS(SMS sms);

	int delSMSByID(int id);

	SMS findSMSByMaxCreateTime();

}