<%@page import="com.qianfeng.auction.util.ValidateUtil"%>
<%@page import="com.qianfeng.auction.eums.UserLoginEnum"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>拍卖网</title>
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
<script src="https://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
  function sendSMS() {
        var phonenumber = $("#phonenumber").val();
      var userdata = {"phonenumber":phonenumber};
            $.ajax({
        url:"SMSSenderServlet",
        type:"post",
        data:userdata,
        /* 成功回调函数的数据的 处理方式  也可以理解为响应报文的处理方式*/
        dataType:"json",
        success: function(data){
         if(data) {
           $("#smsmsg").html("短信发送成功");
         }
        },
        error: function(){
           $("#smsmsg").html("短信发送失败，请稍后再试");
        }
      });
  }
</script>
<script type="text/javascript">

	<%String msg = request.getParameter("msg") == null ? "" : request
					.getParameter("msg");%>
					
	<%if (msg.equals(UserLoginEnum.USER_VALIDATE_CODE_IS_FAIL.getValue())) {%>
	 alert("<%=UserLoginEnum.USER_VALIDATE_CODE_IS_FAIL.getDesc()%>");
	<%}%>
					
	<%if (msg.equals(UserLoginEnum.USER_NAME_IS_NUll.getValue())) {%>
	 alert("<%=UserLoginEnum.USER_NAME_IS_NUll.getDesc()%>");
	<%}%>
	
	<%if (msg.equals(UserLoginEnum.USER_PASSWORD_IS_NULL.getValue())) {%>
	 alert("<%=UserLoginEnum.USER_PASSWORD_IS_NULL.getDesc()%>");
	<%}%>
	
	<%if (msg.equals(UserLoginEnum.USER_NAME_OR_PASSWORD_IS_FAIL
					.getValue())) {%>
	 alert("<%=UserLoginEnum.USER_NAME_OR_PASSWORD_IS_FAIL.getDesc()%>");
	<%}%>
	
	<%if (msg.equals(UserLoginEnum.USER_VALIDATE_CODE_IS_FAIL.getValue())) {%>
	 alert("<%=UserLoginEnum.USER_VALIDATE_CODE_IS_FAIL.getDesc()%>");
	<%}%>
	
	<%if (msg.equals(UserLoginEnum.USER_LOGIN_SUCCESS.getValue())) {%>
	 alert("<%=UserLoginEnum.USER_LOGIN_SUCCESS.getDesc()%>");
<%}%>
	
</script>
</head>
<body>
	<div
		style="position: absolute;top:0;bottom: 0;left: 0;right: 0;height: 300px;width: 500px;margin:auto;">
		<form action="UserLoginServlet" method="post">
			<table style="text-align: center;margin: auto;">
				<tr>
					<td>账号</td>
					<td><input name="username"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input name="password"></td>
				</tr>
				<tr>
					<td>验证码:</td>
					<td><input name="validatecode"></td>
				</tr>
				<tr>
					<%
						String validateCode = ValidateUtil.createValidateCode();
						session.setAttribute("syscode", validateCode);
					%>
					<td>手机号</td>
					<td><input id="phonenumber"></td>
					<td><button onclick="sendSMS()" type="button">发送短信</button>
					</td>
					<td><p id="smsmsg" style="color: red;"></p>
					</td>
				</tr>
				<tr>
					<td><button type="submit">登录</button></td>
					<td><a href="user_register.jsp">用户注册</a>
					</td>
				</tr>
			</table>
		</form>


	</div>
</body>
</html>