package com.qianfeng.auction.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.auction.biz.SMSBIZ;
import com.qianfeng.auction.bizimpl.SMSBIZImpl;
import com.qianfeng.auction.entity.SMS;
import com.qianfeng.auction.eums.SMSEnum;

import net.sf.json.JSONObject;

public class SMSSenderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String phonenumber = req.getParameter("phonenumber");
		URL url = new URL(
				"http://chensiwei.natapp1.cc/ncdx_web/UserSendNote?phonenumber="
						+ phonenumber + "");
		URLConnection urlconnection = url.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlconnection;
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection
				.setRequestProperty(
						"user-agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
		httpURLConnection.setConnectTimeout(3000);
		httpURLConnection.setReadTimeout(3000);
		httpURLConnection.setInstanceFollowRedirects(true);
		httpURLConnection.connect();
		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, "utf-8"));
		String temp = "";
		temp = bufferedReader.readLine();
		System.out.println(temp);
		JSONObject jsonObject = JSONObject.fromObject(temp);
		System.out.println(jsonObject.get("message"));
		System.out.println(jsonObject.get("state"));
		System.out.println(jsonObject.get("data"));
		JSONObject smsJsonObject = (JSONObject) jsonObject.get("data");
		System.out.println(smsJsonObject.get("mobilenumber"));
		System.out.println(smsJsonObject.get("validatecode"));
		System.out.println(((JSONObject) smsJsonObject.get("createtime"))
				.get("time"));
		SMSBIZ smsbiz = new SMSBIZImpl();
		SMS sms = new SMS();
		sms.setMobilenumber((String) smsJsonObject.get("mobilenumber"));
		sms.setValidatecode((String) smsJsonObject.get("validatecode"));
		sms.setCreatetime(new Timestamp(Long
				.parseLong(((JSONObject) (smsJsonObject.get("createtime")))
						.get("time").toString())));
		String result = smsbiz.addSMS(sms);
		if (result.equals(SMSEnum.SMS_ADD_SUCCESS.getValue())) {
			resp.getWriter().print(true);
		} else {
			resp.getWriter().print(false);
		}
	}
}
