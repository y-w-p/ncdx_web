<%@page import="com.qianfeng.auction.eums.AuctionEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("utf-8");
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
	String addmsg = request.getParameter("msg") == null ? "" : request
			.getParameter("msg");
%>
<script type="text/javascript">
<%if (addmsg.equals(AuctionEnum.AUCTION_UPDATE_FAIL.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_UPDATE_FAIL.getDesc()%>");
<%}%>

<%if (addmsg.equals(AuctionEnum.AUCTION_NAME_IS_NULL.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_NAME_IS_NULL.getDesc()%>");
<%}%>

<%if (addmsg.equals(AuctionEnum.AUCTION_START_PRICE_IS_FAIL
					.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_START_PRICE_IS_FAIL.getDesc()%>");
<%}%>

<%if (addmsg.equals(AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_LOW_PRICE_IS_FAIL.getDesc()%>");
<%}%>

<%if (addmsg
					.equals(AuctionEnum.AUCTION_START_TIME_IS_FAIL.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_START_TIME_IS_FAIL.getDesc()%>");
<%}%>

<%if (addmsg.equals(AuctionEnum.AUCTION_END_TIME_IS_FAIL.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_END_TIME_IS_FAIL.getDesc()%>");
<%}%>

<%if (addmsg.equals(AuctionEnum.AUCTION_DESC_IS_FAIL.getValue())) {%>
   alert("<%=AuctionEnum.AUCTION_DESC_IS_FAIL.getDesc()%>");
<%}%>
	
</script>

</head>

<body
	style="background-image: url('https://sothebys-com.brightspotcdn.com/dims4/default/286326b/2147483647/strip/true/crop/1440x330+0+0/resize/1440x330!/quality/90/?url=http%3A%2F%2Fsothebys-brightspot.s3.amazonaws.com%2Fdotcom%2F03%2Fba%2F8fc9d4d646bc94e9a0bf8511b9dc%2Fhk-1440x330-a.jpg');background-repeat: no-repeat;background-size:100% 100%">

	<form
		action="AuctionUpdateServlet?pageindex=<%=request.getParameter("pageindex")%>&auctionid=<%=request.getParameter("auctionid")%>&auctionimage=<%=request.getParameter("auctionimage")%>&auctionname=<%=request.getParameter("auctionname")%>&auctionstartprice=<%=request.getParameter("auctionstartprice")%>&auctionlowprice=<%=request.getParameter("auctionlowprice")%>&auctionstarttime=<%=request.getParameter("auctionstarttime")%>&auctionendtime=<%=request.getParameter("auctionendtime")%>&auctiondesc=<%=request.getParameter("auctiondesc")%>"
		enctype="multipart/form-data" method="post">
		<center>
			<h1 style="color: red">拍卖品修改</h1>
		</center>
		<table align="center">
			<tr style="display: none;">
				<td><input name="auctionid"
					value="<%=request.getParameter("auctionid")%>" /> <%-- 				<input name="pageindex" value="<%=request.getParameter("pageindex")%>" />
 --%> <input name="beforeimage"
					value="<%=request.getParameter("auctionimage")%>"></td>
			</tr>
			<tr>
				<td>拍卖品名</td>
				<td><input name="auctionname"
					value="<%=request.getParameter("auctionname")%>" /></td>
			</tr>
			<tr>
				<td>拍卖品起始价</td>
				<td><input name="auctionstartprice"
					value="<%=request.getParameter("auctionstartprice")%>" /></td>
			</tr>
			<tr>
				<td>拍卖品底价</td>
				<td><input name="auctionlowprice"
					value="<%=request.getParameter("auctionlowprice")%>" /></td>
			</tr>
			<tr>
				<td>拍卖品起拍时间</td>
				<td><input name="auctionstarttime"
					value="<%=request.getParameter("auctionstarttime")%>" /></td>
			</tr>
			<tr>
				<td>拍卖品结束时间</td>
				<td><input name="auctionendtime"
					value="<%=request.getParameter("auctionendtime")%>" /></td>
			</tr>
			<tr>
				<td>拍卖品描述</td>
				<td><textarea rows="" cols="" name="auctiondesc"><%=request.getParameter("auctiondesc")%></textarea>
				</td>
			</tr>
			<tr>
				<td>图片展示:</td>
				<td><img height="200px" width="200px" alt="该拍卖品没有图片"
					src="<%=basePath%>upload/<%=request.getParameter("auctionimage")%>" />
				</td>
			</tr>
			<tr>
				<td>拍卖图片</td>
				<td><input name="auctionimage" type="file" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button>修改</button></td>
			</tr>
		</table>

	</form>

</body>
</html>
