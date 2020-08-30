package com.qianfeng.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alipay.api.msg.Message;
import com.qianfeng.auction.util.AES;

public class EmailSender {

	private int userid;
	private int auctionid;
	private int auctionprice;
	private String username;
	private String email;
	private String auctionName;

	public EmailSender(int userid, int auctionid, int auctionprice,
			String username, String email, String auctionName) {
		this.userid = userid;
		this.auctionid = auctionid;
		this.auctionprice = auctionprice;
		this.username = username;
		this.email = email;
		this.auctionName = auctionName;
	}

	public EmailSender() {
	}

	public void send() throws Exception {
		Properties emailProperties = new Properties();
		emailProperties.load(EmailSender.class
				.getResourceAsStream("/email.properties"));
		Session session = Session.getInstance(emailProperties);
		// 开启邮箱调试功能
		session.setDebug(true);
		Transport transport = session.getTransport();
		// 链接邮箱服务器
		transport.connect(emailProperties.getProperty("mail.host"),
				emailProperties.getProperty("username"),
				emailProperties.getProperty("password"));
		// 实例化mimeMessage 这个文件 主要用来设置内容
		MimeMessage mimeMessage = new MimeMessage(session);
		// 标题
		String sKey = "www.qianfeng.com";
		String auctionid02 = AES.Encrypt(String.valueOf(auctionid), sKey);
		String userid02 = AES.Encrypt(String.valueOf(userid), sKey);
		String auctionprice02 = AES.Encrypt(String.valueOf(auctionprice), sKey);
		String auctionname02 = AES.Encrypt(String.valueOf(auctionName), sKey);
		System.out.println(auctionid02);
		System.out.println(userid02);
		String userParameter = "?auctionid=" + auctionid02 + "&userid="
				+ userid02 + "&auctionprice=" + auctionprice02
				+ "&auctionname=" + auctionname02;
		// 内容
		mimeMessage.setContent(
				"恭喜您 " + username + "以" + auctionprice + "的价格" + "获得宝物:"
						+ auctionName + "付款地址:"
						+ "chensiwei.natapp1.cc/ncdx_web/AliPayServlet"
						+ userParameter.replaceAll("[+]", "%2B") + "",
				"text/plain;charset=utf-8");
		// 发件人
		mimeMessage.setFrom(new InternetAddress(emailProperties
				.getProperty("username")));
		// 收件人
		mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO,
				new InternetAddress("1766723331@qq.com"));
		// 发送邮件
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		// 关闭
		transport.close();
	}

	public static void main(String[] args) {
		EmailSender emailSender = new EmailSender();
		emailSender.setAuctionprice(1);
		emailSender.setEmail("1766723331@qq.com");
		emailSender.setUserid(1);
		emailSender.setAuctionid(3);
		emailSender.setAuctionName("佛跳墙");
		emailSender.setUsername("haha");
		try {
			emailSender.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAuctionid() {
		return auctionid;
	}

	public void setAuctionid(int auctionid) {
		this.auctionid = auctionid;
	}

	public int getAuctionprice() {
		return auctionprice;
	}

	public void setAuctionprice(int auctionprice) {
		this.auctionprice = auctionprice;
	}

}
