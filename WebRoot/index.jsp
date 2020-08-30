<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%
	String msg = request.getParameter("msg") == null ? "" : request
			.getParameter("msg");
	String pageindex = request.getParameter("pageindex") == null ? "" : request
			.getParameter("pageindex");
%>
</head>
<frameset  cols="10%,*">
	<frame src="left.jsp?msg=<%=msg%>&pageindex=<%=pageindex %>" name="left">
	<frameset rows="10%,*">
		<frame src="top.jsp">
		<frame src="main.jsp" name="main" sandbox="allow-popups">
	</frameset>
</frameset>
</html>
