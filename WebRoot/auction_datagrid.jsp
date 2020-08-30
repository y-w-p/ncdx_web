<%@page import="com.qianfeng.auction.entity.User"%>
<%@page import="com.qianfeng.auction.eums.PermissionEnum"%>
<%@page import="com.qianfeng.auction.util.AuthorizationUtil"%>
<%@page import="com.qianfeng.auction.eums.AuctionEnum"%>
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
     location.href = "AuctionListByPageServlet?pageindex="+pageindex+"&pagenumber="+pagenumber+"";
  }
  
  function addAuction() {
    location.href = "add_auction.jsp";
  }
  
  $(
   function(){
     $("#pagenumber option[value=<%=pageVO.getPagenumber().intValue()%>]").attr("selected","selected");
   }
  );
  <%String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");%>
  
  <%if(msg.equals(AuctionEnum.AUCTION_ADD_SUCCESS.getValue())){%>
    alert("<%=AuctionEnum.AUCTION_ADD_SUCCESS.getDesc()%>");
  <%}%>
    
  <%if(msg.equals(AuctionEnum.AUCTION_UPDATE_SUCCESS.getValue())){%>
    alert("<%=AuctionEnum.AUCTION_UPDATE_SUCCESS.getDesc()%>");
  <%}%>
  
  
  
  function updateAuction(arg){
    var auctionid = arg.getAttribute("auctionid");
    var auctionname = arg.getAttribute("auctionname");
    var auctionstartprice = arg.getAttribute("auctionstartprice");
    var auctionlowprice = arg.getAttribute("auctionlowprice");
    var auctionstarttime = arg.getAttribute("auctionstarttime");
    var auctionendtime = arg.getAttribute("auctionendtime");
    var auctiondesc = arg.getAttribute("auctiondesc");
    var auctionimage = arg.getAttribute("auctionimage");
    var pageindex= arg.getAttribute("pageindex");
    location.href  = "update_auction.jsp?auctionid="+auctionid+"&auctionname="+auctionname+"&auctionstartprice="+auctionstartprice+"&auctionlowprice="+auctionlowprice+"&auctionstarttime="+auctionstarttime+"&auctionendtime="+auctionendtime+"&auctiondesc="+auctiondesc+"&auctionimage="+auctionimage+"&pageindex="+pageindex+"";
  }
  
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
  
  function auction(arg) {
   var auctionid = arg.getAttribute("auctionid");
   location.href = "AuctionRecordByIDServlet?auctionid="+auctionid+"&pageindex=<%= pageVO.getpageindex().intValue()%>";
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
		<h2 style="color: blue;">拍卖品列表</h2>
	</center>
	<form action="AuctionSearchServlet" method="post">
		<table align="center" style="text-align: center;">
			<tr>
				<td>商品名<input style="margin-right: 20px" name="auctionname" />
				</td>
				<td>起拍价<input style="margin-right: 20px"
					name="auctionstartprice" /></td>
				<td>起拍时间<input style="margin-right: 20px"
					name="auctionstarttime" /></td>
				<td>结束时间<input style="margin-right: 20px" name="auctionendtime" />
				</td>
				<td><button type="submit">查询</button></td>
				<td>
					<%
						if (AuthorizationUtil.hasPermission(
								PermissionEnum.PERMISSION_ADD_AUCTION.getValue(), session)) {
					%>
					<button type="button" style="border: 1px solid red;"
						onclick="addAuction()">添加拍卖品</button>
				</td>
				<%
					}
				%>
			</tr>
		</table>
	</form>

	<table class="datagridtable" align="center" style="text-align: center;"
		cellspacing="0">
		<tr style="background-color: gray;">
			<td>拍卖品名</td>
			<td>拍卖品起始价</td>
			<td>拍卖品低价</td>
			<td>拍卖品起拍时间</td>
			<td>拍卖品结束时间</td>
			<td>拍卖品描述</td>
			<td>拍卖品图片</td>
			<td width="200px">操作</td>
		</tr>

		<%
			int temp = 0;
			for (Auction auction : (List<Auction>) pageVO.getLists()) {
				temp++;
				if (temp % 2 == 0) {
		%>
		<tr style="background-color: #00bcd4;">
			<td><%=auction.getAuctionname()%></td>
			<td><%=auction.getAuctionstartprice()%></td>
			<td><%=auction.getAuctionlowprice()%></td>
			<td><%=auction.getauctionstarttime()%></td>
			<td><%=auction.getAuctionendtime()%></td>
			<td><%=auction.getAuctiondesc()%></td>
			<td><img alt="该图片暂时无法显示" height="50px" width="150px"
				src="<%=basePath%>/upload/<%=auction.getAuctionimage()%>" />
			</td>
			<td width="200px">
				<%
					if (AuthorizationUtil
									.hasPermission(
											PermissionEnum.PERMISSION_UPDATE_AUCTION
													.getValue(), session)) {
				%>
				<button auctionid="<%=auction.getId()%>"
					auctionname="<%=auction.getAuctionname()%>"
					auctionstartprice="<%=auction.getAuctionstartprice()%>"
					auctionlowprice="<%=auction.getAuctionlowprice()%>"
					auctionstarttime="<%=auction.getauctionstarttime()%>"
					auctionendtime="<%=auction.getAuctionendtime()%>"
					auctiondesc="<%=auction.getAuctiondesc()%>"
					auctionimage="<%=auction.getAuctionimage()%>"
					pageindex="<%=pageVO.getpageindex()%>"
					onclick="updateAuction(this)">修改</button> <%
 	}
 %> <%
 	if (AuthorizationUtil
 					.hasPermission(
 							PermissionEnum.PERMISSION_DELETE_AUCTION
 									.getValue(), session)) {
 %>
				<button auctionid="<%=auction.getId()%>" onclick="delAuction(this)">删除</button>
				<%
					}
				%> <%
 	if (AuthorizationUtil.hasPermission(
 					PermissionEnum.PERMISSION_PAY_AUCTION.getValue(),
 					session)) {
 %>
				<button onclick="auction(this)" pageindex="<%= pageVO.getpageindex().intValue() %>" auctionid="<%=auction.getId()%>">竞拍</button>
				<%
					}
				%>
			</td>
		</tr>
		<%
			} else {
		%>
		<tr>
			<td><%=auction.getAuctionname()%></td>
			<td><%=auction.getAuctionstartprice()%></td>
			<td><%=auction.getAuctionlowprice()%></td>
			<td><%=auction.getauctionstarttime()%></td>
			<td><%=auction.getAuctionendtime()%></td>
			<td><%=auction.getAuctiondesc()%></td>
			<td><img alt="该图片暂时无法显示" height="50px" width="150px"
				src="<%=basePath%>/upload/<%=auction.getAuctionimage()%>" />
			</td>
			<td width="200px">
				<%
					if (AuthorizationUtil
									.hasPermission(
											PermissionEnum.PERMISSION_UPDATE_AUCTION
													.getValue(), session)) {
				%>
				<button auctionid="<%=auction.getId()%>"
					auctionname="<%=auction.getAuctionname()%>"
					auctionstartprice="<%=auction.getAuctionstartprice()%>"
					auctionlowprice="<%=auction.getAuctionlowprice()%>"
					auctionstarttime="<%=auction.getauctionstarttime()%>"
					auctionendtime="<%=auction.getAuctionendtime()%>"
					auctiondesc="<%=auction.getAuctiondesc()%>"
					auctionimage="<%=auction.getAuctionimage()%>"
					pageindex="<%=pageVO.getpageindex()%>"
					onclick="updateAuction(this)">修改</button> <%
 	}
 %> <%
 	if (AuthorizationUtil
 					.hasPermission(
 							PermissionEnum.PERMISSION_DELETE_AUCTION
 									.getValue(), session)) {
 %>
				<button auctionid="<%=auction.getId()%>" onclick="delAuction(this)">删除</button>
				<%
					}
				%> <%
 	if (AuthorizationUtil.hasPermission(
 					PermissionEnum.PERMISSION_PAY_AUCTION.getValue(),
 					session)) {
 %>
				<button onclick="auction(this)" pageindex="<%= pageVO.getpageindex().intValue() %>" auctionid="<%=auction.getId()%>">竞拍</button>
				<%
					}
				%>
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
				href="AuctionListByPageServlet?pageindex=<%=i%>&pagenumber=<%=pageVO.getPagenumber().intValue()%>"><%=i%></a>
				<%
					} else {
				%> <a
				href="AuctionListByPageServlet?pageindex=<%=i%>&pagenumber=<%=pageVO.getPagenumber().intValue()%>"><%=i%></a>
				<%
					}
					}
				%>
			</td>
		</tr>
	</table>
</body>
</html>