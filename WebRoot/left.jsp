<%@page import="com.qianfeng.auction.eums.AuctionEnum"%>
<%@page import="com.qianfeng.auction.eums.RoleEnum"%>
<%@page import="com.qianfeng.auction.util.AuthorizationUtil"%>
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

<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
<%
	String msg = request.getParameter("msg") == null ? "" : request
			.getParameter("msg");
	String pageindex = request.getParameter("pageindex") == null
			? ""
			: request.getParameter("pageindex");
	if (msg.equals(AuctionEnum.AUCTION_SUCCESS.getValue())) {
%>
<script type="text/javascript">
	$(function() {
        $("#auctionmanager").attr("href","AuctionListByPageServlet?pageindex=<%=pageindex%>");
		document.getElementById('auctionmanager').click();
	});
</script>
<%
	}
%>
<style type="text/css">
li {
	list-style: none;
	margin-top: 20px;
}

a {
	text-decoration: none;
	margin-left: -20px;
}
</style>
</head>

<body>
	<ol>
		<li><a id="auctionmanager" href="AuctionListByPageServlet"
			target="main">拍卖管理</a>
		</li>
		<%
			if (AuthorizationUtil.hasRole(RoleEnum.ROLE_ADMIN.getValue(),
					session)) {
		%>
		<li><a href="UserListByPageServlet" target="main">用户角色管理</a>
		</li>
		<%
			}
		%>
		<%
			if (AuthorizationUtil.hasRole(RoleEnum.ROLE_ADMIN.getValue(),
					session)) {
		%>
		<li><a href="RoleListByPageServlet" target="main">权限管理</a>
		</li>
		<%
			}
		%>
	</ol>

</body>
</html>
