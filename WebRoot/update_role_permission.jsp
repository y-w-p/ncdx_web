<%@page import="com.qianfeng.auction.entity.Role"%>
<%@page import="com.qianfeng.auction.eums.UserEnum"%>
<%@page import="com.qianfeng.auction.entity.Permission"%>
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
	List<Permission> syspermissions = (List<Permission>) application.getAttribute("permissions");
	List<Permission> permissionroles = (List<Permission>) request.getAttribute("permissions");
	Role role = (Role) request.getAttribute("role");
%>

<script type="text/javascript">
<%String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");%>
  
  <%if(msg.equals(UserEnum.USER_UPDATE_PERMISSION_FAIL.getValue())){%>
    alert("<%=UserEnum.USER_UPDATE_PERMISSION_FAIL.getDesc()%>");
<%}%>
	
</script>
</head>

<body>
	<center>
		<h1>角色权限修改</h1>
	</center>
	<form action="RolePermissionUpdateServlet" method="post">
		<table align="center" width="70%" cellspacing="0">
			<tr style="display: none;">
				<td>roleid:</td>
				<td><input name="roleid" value="<%=role.getId()%>" /></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input disabled="disabled" name="username"
					value="<%=role.getRolename()%>" />
				</td>
			</tr>
			<tr>
				<td>用户角色:</td>
				<td>
					<%
						for (Permission sysrole : syspermissions) {
							boolean temp = false;
							for (Permission rolePermission : permissionroles) {
								if (sysrole.getId() == rolePermission.getId()) {
									temp = true;
								}
							}
					%> <%
 	if (temp) {
 %> <%=sysrole.getPermissiondesc()%>: <input name="permission"
					value="<%=sysrole.getId()%>" type="checkbox" checked="checked">

					<%
						} else {
					%> <%=sysrole.getPermissiondesc()%>: <input name="permission"
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
