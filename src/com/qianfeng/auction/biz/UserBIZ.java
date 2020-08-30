package com.qianfeng.auction.biz;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.qianfeng.auction.entity.Auction;
import com.qianfeng.auction.entity.User;

public interface UserBIZ {

	String userLogin(String username, String password, String validatecode,
			String syscode, HttpServletRequest request);

	void userAuthorizition(int userid, HttpSession httpSession);

	List<User> findUsersByPage(BigDecimal pageindex, BigDecimal pagenumber);

	BigDecimal getAllCount();

	User findUserByID(int userid);

}
