<%@page import="com.qianfeng.auction.vo.RoleVO"%>
<%@page import="com.qianfeng.auction.eums.UserEnum"%>
<%@page import="com.qianfeng.auction.vo.UserVO"%>
<%@page import="com.qianfeng.auction.entity.User"%>
<%@page import="com.qianfeng.auction.eums.PermissionEnum"%>
<%@page import="com.qianfeng.auction.util.AuthorizationUtil"%>
<%@page import="com.qianfeng.auction.entity.Auction"%>
<%@page import="com.qianfeng.auction.vo.PageVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	// 在請求报文中抓取 拍卖品分页VO
	PageVO pageVO = (PageVO) request.getAttribute("pagevo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'auction_datagrid.jsp' starting page</title>

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
	width: 120px;
}
</style>
<script src="https://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
  function changePageNumber(pagenumber,pageindex) {
     location.href = "UserListByPageServlet?pageindex="+pageindex+"&pagenumber="+pagenumber+"";
  }
  
  $(
   function(){
     $("#pagenumber option[value=<%=pageVO.getPagenumber().intValue()%>]").attr("selected","selected");
   }
  );
  <%String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");%>
  
  <%if(msg.equals(UserEnum.USER_UPDATE_SUCCESS.getValue())){%>
    alert("<%=UserEnum.USER_UPDATE_SUCCESS.getDesc()%>");
  <%}%>
    
  
  
  
</script>

<script type="text/javascript">
  // ajax
  function delAuction(arg) {
    if(window.confirm("是否确认删除该数据?")) {
      var auctionid = arg.getAttribute("auctionid");
      $userarg = $(arg);
      var userdata = {"auctionid":auctionid};
            $.ajax({
        url:"AuctionDelByIDServlet",
        type:"post",
        data:userdata,
        /* 成功回调函数的数据的 处理方式  也可以理解为响应报文的处理方式*/
        dataType:"json",
        success: function(data){
          $userarg.parent().parent().remove();
        },
        error: function(){
          alert("网络异常请稍后再试");
        }
      });
    }
  }
  
  function roleUpdate(arg){
      var roleid = arg.getAttribute("roleid");
      location.href = "RolePermissionFindByroleidServlet?roleid="+roleid+"";
  }
</script>

<style type="text/css">
.datagridtable {
	table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}

td {
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/
}
</style>
</head>

<body>
	<center>
		<h2 style="color: blue;">用户列表</h2>
	</center>

	<table class="datagridtable" align="center" style="text-align: center;"
		cellspacing="0">
		<tr style="background-color: gray;">
			<td>角色名</td>
			<td>权限</td>
			<td>操作</td>
		</tr>
		<%
			int temp = 0;
			for (RoleVO role : (List<RoleVO>) pageVO.getLists()) {
				temp++;
				if (temp % 2 == 0) {
		%>
		<tr style="background-color: #00bcd4;">
			<td><%=role.getRolename()%></td>
			<td><%=role.getPermissionnname()%></td>
			<td>
				<button roleid="<%=role.getId()%>" onclick="roleUpdate(this)">修改</button>
			</td>
		</tr>
		<%
			} else {
		%>
		<tr>
			<td><%=role.getRolename()%></td>
			<td><%=role.getPermissionnname()%></td>
			<td>
				<button roleid="<%=role.getId()%>" onclick="roleUpdate(this)">修改</button>
			</td>
		</tr>
		<%
			}
			}
		%>

		<tr>
			<td colspan="8"><select id="pagenumber"
				onchange="changePageNumber(this.value,<%=pageVO.getpageindex().intValue()%>)">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
			</select> <%
 	for (int i = 1; i <= pageVO.getEndpage().intValue(); i++) {
 		if (i == pageVO.getpageindex().intValue()) {
 %> <a style="color: red;"
				href="UserListByPageServlet?pageindex=<%=i%>&pagenumber=<%=pageVO.getPagenumber().intValue()%>"><%=i%></a>
				<%
					} else {
				%> <a
				href="UserListByPageServlet?pageindex=<%=i%>&pagenumber=<%=pageVO.getPagenumber().intValue()%>"><%=i%></a>
				<%
					}
					}
				%>
			</td>
		</tr>
	</table>

</body>
</html>