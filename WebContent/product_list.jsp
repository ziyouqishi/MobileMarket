<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有商品</title>
</head>
<body>
<table border="1" align="center" width="88%">

<tr>
<td colspan="8"> 
<form action="${pageContext.request.contextPath }/findProductByCondition" method="post">
商品名称：<input name="name">&nbsp;&nbsp;&nbsp;&nbsp;
关键词：<input name="kw">&nbsp;&nbsp;&nbsp;&nbsp;

<input type="submit" value="查询">
</form>
</td>
</tr>

<tr>
<td><input type="checkbox"> </td>
<td> pid</td>
<td>商品图片 </td>
<td> 商品名称</td>
<td> 市场价</td>
<td>商城价 </td>
<td>商品描述 </td>
<td>操作 </td>
</tr>

<c:forEach items="${list }" var="p">
<tr>
<td><input width="3%" type="checkbox" name="pid" value="${p.pid }"></td>
<td width="8%"> ${p.pid }</td>
<td width="8%"><img alt="" src="${pageContext.request.contextPath }/${p.pimage }" width="80"></td>
<td width="8%">${p.pname } </td>
<td width="8%">${p.market_price } </td>
<td width="8%">${p.shop_price } </td>
<td>${p.pdesc} </td>
<td width="8%">
<a href="${pageContext.request.contextPath }/getProductById?pid=${p.pid}">修改</a>
|

<a href="javascript:void(0)"  onclick="deleteP('${p.pid}')">删除 </a>
</td>
</tr>
</c:forEach>
</table>

</body>

<script type="text/javascript">
function deleteP(obj){
	
	if(confirm("真得要删除吗")){
		location.href="${pageContext.request.contextPath }/deletetProductById?pid="+obj;
	}
}

</script>
</html>