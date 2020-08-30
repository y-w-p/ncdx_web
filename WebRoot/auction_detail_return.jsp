<%@page import="com.qianfeng.auction.eums.AuctionEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>拍卖商品详情</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="https://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
<script>
	$(function() {
		$("#showError").hide();

		$("#saleForm").submit(
				function() {
					//每次竞拍最大价格
					var maxPrice = $("#details ul:first li:eq(1)").text();
					//竞拍价格
					var inputPrice = $("#sale").val();
					//起拍价格
					var startPrice = $("#startPrice").text();
					if (window.parseFloat(inputPrice) <= window
							.parseFloat(startPrice)) {
						$("#showError").html("不能低于起拍价");
						$("#showError").show();
						return false;
					} else {
						$("#showError").hide();
					}
					if (window.parseFloat(inputPrice) <= window
							.parseFloat(maxPrice)) {
						$("#showError").html("不能低于最高竞拍价");
						$("#showError").show();
						return false;
					} else {
						$("#showError").hide();
					}
					return true;
				});
	});
</script>
<script type="text/javascript">
	
<%String pageindex = request.getParameter("pageindex");
			String msg = request.getParameter("msg");
			if (AuctionEnum.AUCTION_SUCCESS.getValue().equals(msg)) {
				out.print("alert('" + AuctionEnum.AUCTION_SUCCESS.getDesc()
						+ "');");
			}
			if (AuctionEnum.AUCTION_FAIL.getValue().equals(msg)) {
				out.print("alert('" + AuctionEnum.AUCTION_FAIL.getDesc()
						+ "');");
			}%>
	
</script>

</head>

<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="sale">
			<h1 class="lf">在线拍卖系统</h1>
			<div class="logout right">
				<a href="AuctionLogoutServlet" title="注销">注销</a>
			</div>
			<div class="logout right">
				<a href="login.jsp" title="登录">登录</a>
			</div>
		</div>
		<div class="items sg-font lf">
			<ul class="rows">
				<li>名称：</li>
				<li class="borderno">${AuctionObj.auctionname }</li>
			</ul>
			<ul class="rows">
				<li>描述：</li>
				<li class="borderno">${AuctionObj.auctiondesc }</li>
			</ul>
			<ul class="rows">
				<li>开始时间：</li>
				<li class="borderno">${AuctionObj.auctionstarttime }</li>
			</ul>
			<ul class="rows">
				<li>结束时间：</li>
				<li class="borderno">${AuctionObj.auctionendtime }</li>
			</ul>
			<ul class="rows border-no">
				<li>起拍价：</li>
				<li class="borderno" id="startPrice">${AuctionObj.auctionstartprice
					}</li>
			</ul>
		</div>
		<div class="rg borders">
			<img src="<%=basePath%>upload/${AuctionObj.auctionimage }"
				width="270" alt="" />
		</div>
		<div class="cl"></div>
		<div class="top10 salebd">
			<form action="AuctiobPayServlet" method="post" id="saleForm">
				<p>
					<label for="sale">出价：</label> <input name="auctionprice"
						type="text" class="inputwd" id="sale" value="" /> <input
						type="submit" value="竞 拍" style="color: red;border: 1px solid;"
						class="spbg buttombg f14  sale-buttom" /> <input name="auctionid"
						value="${AuctionObj.id}" style="display: none;"> <input
						name="userid" value="${sessionScope.user.id}"
						style="display: none;"> <input name="pageindex"
						value="<%=pageindex%>" style="display: none;" />
				</p>
			</form>
			<p class="f14 red" id="showError">不能低于最高竞拍价</p>

		</div>
		<div class="top10">
			<input name="" type="button" value="刷 新" class="spbg buttombg f14"
				onclick="javascript:location='AuctionRecordServlet?auctionId=${AuctionObj.id }&pageindex=<%=pageindex%>'" />
			<input name="" type="button" value="返回列表" class="spbg buttombg f14"
				onclick="javascript:location='AuctionListByPageServlet?pageindex=<%=pageindex%>'" />
		</div>
	</div>
</body>
</html>
