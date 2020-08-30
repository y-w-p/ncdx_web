package com.qianfeng.auction.api.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class TestNetDemo {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置您申请的KEY
	/*
	 * public static void main(String[] args) { HttpURLConnection conn = null;
	 * BufferedReader reader = null; String strUrl =
	 * "http://dfi38p.natappfree.cc/qianfeng_auction/UserSendNote?phoneNumber=15016478699"
	 * ; try { StringBuffer sb = new StringBuffer(); URL url = new URL(strUrl);
	 * conn = (HttpURLConnection) url.openConnection();
	 * conn.setRequestMethod("POST"); conn.setDoOutput(true);
	 * conn.setRequestProperty("User-agent", userAgent);
	 * conn.setUseCaches(false); conn.setConnectTimeout(DEF_CONN_TIMEOUT);
	 * conn.setReadTimeout(DEF_READ_TIMEOUT);
	 * conn.setInstanceFollowRedirects(false); conn.connect(); InputStream is =
	 * conn.getInputStream(); reader = new BufferedReader(new
	 * InputStreamReader(is, DEF_CHATSET)); String strRead = null; while
	 * ((strRead = reader.readLine()) != null) { sb.append(strRead); }
	 * System.out.println(sb.toString()); } catch (IOException e) {
	 * e.printStackTrace(); } finally { try { if (reader != null) {
	 * reader.close(); } if (conn != null) { conn.disconnect(); } } catch
	 * (IOException e) { e.printStackTrace(); } } }
	 */

	public static void main(String[] args) {

		try {
			URL url = new URL(
					"http://chensiwei.natapp1.cc/ncdx_web/UserSendNote?phoneNumber=15016478699");
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
