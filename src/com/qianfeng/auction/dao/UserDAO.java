package com.qianfeng.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.auction.entity.User;

public interface UserDAO {
	// ����淶 ���� �� ����� ������ ���� ����Ҫ�ֶ���д����
	User userLogin(String username, String password);

	List<User> findUsersByPage(BigDecimal beginpagenumber, BigDecimal pagenumber);

	BigDecimal getAllCount();

	User findUserByID(int userid);

}
