package com.qianfeng.auction.biz;

import java.util.List;

import com.qianfeng.auction.entity.SMS;

public interface SMSBIZ {

	List<SMS> findTimeOverSMS();

	String addSMS(SMS sms);

	String delSMSByID(int id);

	SMS findSMSByMaxCreateTime();

}
