package com.qianfeng.auction.bizimpl;

import java.util.List;

import com.qianfeng.auction.biz.SMSBIZ;
import com.qianfeng.auction.dao.SMSDAO;
import com.qianfeng.auction.daoimpl.SMSDAOImpl;
import com.qianfeng.auction.entity.SMS;
import com.qianfeng.auction.eums.SMSEnum;

public class SMSBIZImpl implements SMSBIZ {

	SMSDAO smsdao = new SMSDAOImpl();

	@Override
	public List<SMS> findTimeOverSMS() {
		return smsdao.findTimeOverSMS();
	}

	@Override
	public String addSMS(SMS sms) {
		int executeCount = smsdao.addSMS(sms);
		if (executeCount == 0) {
			return SMSEnum.SMS_ADD_FAIL.getValue();
		}
		return SMSEnum.SMS_ADD_SUCCESS.getValue();
	}

	@Override
	public String delSMSByID(int id) {
		int executeCount = smsdao.delSMSByID(id);
		if (executeCount == 0) {
			return SMSEnum.SMS_DELETE_FAIL.getValue();
		}
		return SMSEnum.SMS_DELETE_SUCCESS.getValue();
	}

	@Override
	public SMS findSMSByMaxCreateTime() {
		return smsdao.findSMSByMaxCreateTime();
	}

}
