package com.qianfeng.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.auction.entity.User;

public interface UserDAO {
	// 阿里规范 公开 的 抽象的 这两个 描述 不需要手动的写出来
	User userLogin(String username, String password);

	List<User> findUsersByPage(BigDecimal beginpagenumber, BigDecimal pagenumber);

	BigDecimal getAllCount();

	User findUserByID(int userid);

}
