package com.qf.auction.alipay;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jspsmart.upload.Request;
import com.qianfeng.auction.eums.AuctionEnum;
import com.qianfeng.auction.util.AES;

public class AliPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AliPayServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest httpServletRequest,
			HttpServletResponse httpResponse) throws ServletException,
			IOException {
		String auctionId = httpServletRequest.getParameter("auctionid");
		String userId = httpServletRequest.getParameter("userid");
		String pageindex = httpServletRequest.getParameter("pageindex");
		String auctionPrice = httpServletRequest.getParameter("auctionprice");
		String auctionName = httpServletRequest.getParameter("auctionname");
		String APP_ID = "2016092500597170";
		String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGxLE56AKykmB9AL3zK3os60gBhjlcnMHr/2RbxPOrxUfBhc+Y2R1YhcS+P6VqDVWX/fCRXwXNQGXiQchq9qpCCQTt8p3zEltbAVKScBVcXu8P/N7gpw4tpZoQMmOKuUPllaeQvcF+h7RaPmH1nneetz+1LzwFION2Mz3rKhW3xr+s89ZKMPc1EUlGe90jJZkKYnmmGC8AM/BJ82qW9+54QOu9yUwzUYzKcyTexUKXHQPmId8N69xEeXHM9iQtJ2ZujkTdWfl/NtFR7ZIZ6bkLWb0VxLmeItO3gS+Qf8X13R7fGm8ffB3wXf/HrihhWa8DyVL8PGaqcES3CipkSTqbAgMBAAECggEAEusptedL+aVyxhmEkM5ZqFSfFxewlb+ruWVwwTE0w7g+CnyuV9ait/qPU+W7YtfcXCnZy457UgToSIrTh7B41ixN9NlKMwUwreXRoZz6Yq0xAh2NbBwHcYD9z6hDMQYw8dnAXCFHOGv0dXasab6FLFKtLtIiqtWjGMS4vHp78gBQKsjbG1Tmw3U/lD5+ndgQPpQ1UOj02c//qobf3xCml1lDXWBSrspX1fp7QaiuZBAs4jHFWUxjkVB7KmHmWuUB9YbPHInTiT7dIoR+4JlvPKmw4X10nT+PcDdY21UD92uaAoqAxvI+aWhYU2dheXXhJkfRSwo0odxKpLf6YrHyAQKBgQDLX43WFRK/4b6xsnKq1Ul2uWCU4N/lbAOGtYDZIolG83GekIZKUx1h+pf1SbW8xzpYZY32ZNIFy1pMhsSfA/LAgxxyvmD1Izc5CKjetTADUlDZVlE3khySyb4lqLjY7R3u2Oizws+/2NPcFVG0Lx58ifrD2o0vmw5TbPooIibNsQKBgQCppGj0EfIUz6Y1XcXjmkBcdIA6HBXYaqYhm2OWL9qvT2NCwIatwR8OwRl7YGpYfY9jT6QsBoxYil9a19x2jtghZaKqZ0thUwb8a/Mv6Z04cnEjjEwotKVJ1t3xygG20RtOy77GAcPQokJpRNiIpx5odDb5dYKz1JaTUgDld6mkCwKBgQCCcTh1LrEG99ErAoz/u6vSBZbIBvA0o7IjTflly/wG0RB4JrSrqWG4PVYIEw4JFjP7DLAgcIwxATxaxzoH0C18gtvVUPy5XqwF3eMKbWZtSvfmXrHbFdFT6SgX2X8uS/AeCWxAGFzN7VH94IK2Ml64fHul8WELPsXShpxw7266EQKBgFWjRchQduDCoqk2GXv4lp0VwtWS8AQ8cnlstC1RIxh0/yrnfhWg9EjCelaqKYVafVQpFDuVYqwfzltRtV20Zq8VW7SiSI9tFo2/ARki2ddxDmRfouK5rudDrO8uhwb/MMXqXPggU/Wys8vokVcK5nLcmBP+mmil3Q2pzAILEnxZAoGAHNmwVajjbt01Cr0psmQ0rN2DhCP99eeWonZK0420HsHm7JMsCwASBYbbGSuEq0BXQvKHezTy1fsLJn4Krckbui+9TZpzv5wBrPQhHaWaNfW6OFO8H3D21Kj0tgtcXIe3/Esm8EnuVXXzpGJ5mxPEHRR+lx1Lqh0CeopfMaipNr4=";
		String CHARSET = "utf-8";
		String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqOJWP2Z0mYnGegeLVQiHpyip/3RGYKEEkUtZP3yGAYl+FDvk3xdax8YfZn4zMFBls0tFiTTPfc2EA/eXQ4nj09VAbsjJy6lgE/L6Zdrz7bJNaGqQQCcJjWHHBLC0gP0ccIWflRvJeKslA9Vj5IJa1zlcc7fKlCZxDCRlIaHd/bRDIQqhZqT9hbtJtpNpHnoFXVz714npkdJe2MnV16lvwl9xEuj7WEwOic10RMdO9q+hiw1NwoOM3Eyy6gm5qS6hK/6pw4dfyvrotr54UaqAVzFPsx4PpH9Ac+j4zn3ntvS7jy+IIJj4NyMjYXfjmWy4XqPD5ubUPWWV5zpsl5kv6QIDAQAB";
		// String ALIPAY_PUBLIC_KEY =
		// "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoNTthPIOhdXMdsxZ+/81B67lFbFd8jZSUQXNn6YI5a9SB0YNZDbDopz0UwoaCqcaDDf+ZOmDU6SpUrdy2Hkk6zvXCONG7kp/zAfQ/kutGrG9YMWc9J2Wn4JG5vTd/Arc2hyi/bhmG6xd6WWdIUldvwb6uHcc7o/rUODa1Wrzd12ixhlDWXWLnbTZm/GYIy6FjdYriNZSVoAmUNYoBJP7z/TJBhZOn2xZD9PY8OOL2U3ZLISYxn1Pr9Y8mpMrfBuVZSsj2Ur2xLdFXcxWXzBueLHvGi2bA1YwfZKvFed/aEP8yXvWh4aMY+VKyHzMbEgErUlNbP8zI8vtd0kIdSGw9QIDAQAB";
		String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
		String FORMAT = "JSON";
		String SIGN_TYPE = "RSA2";
		// 这个是 支付后的同步通知地址 （目的是 阿里要知道你这一次 支付是否完成）
		String NOTIFY_URL = "http://chensiwei.natapp1.cc/ncdx_web/AliPayReturnServlet";
		// 这个是 支付成功后的跳转地址
		String RETURN_URL = "http://chensiwei.natapp1.cc/ncdx_web/AuctionRecordByIDPayReturnServlet?auctionid="
				+ auctionId
				+ "&msg="
				+ AuctionEnum.AUCTION_SUCCESS.getValue()
				+ "&pageindex=" + pageindex + "";
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL,
				APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,
				SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setReturnUrl(RETURN_URL);
		request.setNotifyUrl(NOTIFY_URL);
		// 订单号
		String out_trade_no = UUID.randomUUID().toString()
				+ new Random().nextInt(10000);
		System.out.println(out_trade_no);
		// 支付金额
		String total_amount = auctionPrice;
		// 商品名
		String subject = auctionName;
		// 这里只所有有一个BODY 是我希望 阿里巴巴把这些数据 还给我的 同步支付文件
		String sKey = "www.qianfeng.com";
		String body = null;
		try {
			body = "" + out_trade_no + "," + auctionId + "," + userId + ","
					+ total_amount + ",";
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 这里是我要向阿里发送
		try {
			System.out.println(AES.Decrypt(total_amount, sKey));
			System.out.println(AES.Decrypt(subject, sKey));

			request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
					+ "\"total_amount\":\"" + AES.Decrypt(total_amount, sKey)
					+ "\"," + "\"subject\":\"" + AES.Decrypt(subject, sKey)
					+ "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String form = "";
		try {
			form = alipayClient.pageExecute(request).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out
				.println(httpServletRequest.getSession().getAttribute("user"));
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
		System.out
				.println(httpServletRequest.getSession().getAttribute("user"));
	}

	public void init() throws ServletException {

	}
}
