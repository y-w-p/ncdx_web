<%@page import="com.qianfeng.auction.eums.UserEnum"%>
<%@page import="com.qianfeng.auction.entity.Role"%>
<%@page import="com.qianfeng.auction.entity.User"%>
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

<title>My JSP 'save_user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
td {
	border: solid #d0d0d0 1px;
	text-align: center;
	height: 50px;
}

input {
	width: 200px;
}
</style>
<%
	List<Role> sysroles = (List<Role>) application.getAttribute("roles");
	List<Role> usersroles = (List<Role>) request.getAttribute("roles");
	User user = (User) request.getAttribute("user");
%>

<script type="text/javascript">
<%String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");%>
  
  <%if(msg.equals(UserEnum.USER_UPDATE_ROLE_FAIL.getValue())){%>
    alert("<%=UserEnum.USER_UPDATE_ROLE_FAIL.getDesc()%>");
<%}%>
	
</script>
</head>

<body>
	<center>
		<h1>用户角色修改</h1>
	</center>
	<form action="UserRoleUpdateServlet" method="post">
		<table align="center" width="70%" cellspacing="0">
			<tr style="display: none;">
				<td>userid:</td>
				<td><input name="userid" value="<%=user.getId()%>" /></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input disabled="disabled" name="username"
					value="<%=user.getUsername()%>" />
				</td>
			</tr>
			<tr>
				<td>用户角色:</td>
				<td>
					<%
						for (Role sysrole : sysroles) {
							boolean temp = false;
							for (Role userrole : usersroles) {
								if (sysrole.getId() == userrole.getId()) {
									temp = true;
								}
							}
					%> <%
 	if (temp) {
 %> <%=sysrole.getRoledesc()%>: <input name="role"
					value="<%=sysrole.getId()%>" type="checkbox"  checked="checked">

					<%
						} else {
					%> <%=sysrole.getRoledesc()%>: <input name="role"
					value="<%=sysrole.getId()%>" type="checkbox"> <%
 	}
 	}
 %>
				</td>
			</tr>
			<tr>
				<td colspan="2"><center>
						<button>修改用户</button>
					</center></td>
			</tr>
		</table>
	</form>
</body>
</html>
