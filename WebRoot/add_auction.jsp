<%@page import="com.qianfeng.auction.eums.AuctionEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'add_auction.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	color: red;
}
</style>

<%
	String addmsg = request.getParameter("msg") == null ? ""
			: request.getParameter("msg");
%>
<script type="text/javascript">
<%
  if(addmsg.equals(AuctionEnum.AUCTION_ADD_FAIL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_ADD_FAIL.getDesc()%>");
<%}%>

<%
  if(addmsg.equals(AuctionEnum.AUCTION_NAME_IS_NULL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_NAME_IS_NULL.getDesc()%>");
<%}%>

<%
  if(addmsg.equals(AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getDesc()%>");
<%}%>

<%
  if(addmsg.equals(AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getDesc()%>");
<%}%>

<%
  if(addmsg.equals(AuctionEnum.AUCTION_START_TIME_IS_FAIL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_START_TIME_IS_FAIL.getDesc()%>");
<%}%>

<%
  if(addmsg.equals(AuctionEnum.AUCTION_END_TIME_IS_FAIL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_END_TIME_IS_FAIL.getDesc()%>");
<%}%>

<%
  if(addmsg.equals(AuctionEnum.AUCTION_DESC_IS_FAIL.getValue())){
 %>
   alert("<%= AuctionEnum.AUCTION_DESC_IS_FAIL.getDesc()%>");
<%}%>


</script>

</head>

<body
	style="padding-top: 200px;background-image: url('https://sothebys-com.brightspotcdn.com/dims4/default/286326b/2147483647/strip/true/crop/1440x330+0+0/resize/1440x330!/quality/90/?url=http%3A%2F%2Fsothebys-brightspot.s3.amazonaws.com%2Fdotcom%2F03%2Fba%2F8fc9d4d646bc94e9a0bf8511b9dc%2Fhk-1440x330-a.jpg');background-repeat: no-repeat;background-size:100% 100%">

	<form action="AuctionAddServlet" enctype="multipart/form-data"
		method="post">
		<center>
			<h1 style="color: red">拍卖品添加</h1>
		</center>
		<table align="center">
			<tr>
				<td>拍卖品名</td>
				<td><input name="auctionname" />
				</td>
			</tr>
			<tr>
				<td>拍卖品起始价</td>
				<td><input name="auctionstartprice" />
				</td>
			</tr>
			<tr>
				<td>拍卖品底价</td>
				<td><input name="auctionlowprice" />
				</td>
			</tr>
			<tr>
				<td>拍卖品起拍时间</td>
				<td><input name="auctionstarttime" />
				</td>
			</tr>
			<tr>
				<td>拍卖品结束时间</td>
				<td><input name="auctionendtime" />
				</td>
			</tr>
			<tr>
				<td>拍卖品描述</td>
				<td><textarea rows="" cols="" name="auctiondesc"></textarea>
				</td>
			</tr>
			<tr>
				<td>拍卖图片</td>
				<td><input name="auctionimage" type="file" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button>添加</button>
				</td>
			</tr>
		</table>

	</form>

</body>
</html>
