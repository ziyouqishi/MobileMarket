<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1" align="center" width="88%">

<tr>
<td> pid</td>
<td>商品图片 </td>
<td> 商品名称</td>
<td> 市场价</td>
<td>商城价 </td>
<td>商品描述 </td>
</tr>

<c:forEach items="${pb.list }" var="p">
<tr>
 <td width="8%"> ${p.pid }</td>
<td width="8%"><img alt="" src="${pageContext.request.contextPath }/${p.pimage }" width="80"></td>
<td width="8%">${p.pname } </td>
<td width="8%">${p.market_price } </td>
<td width="8%">${p.shop_price } </td>
<td>${p.pdesc} </td>
</td> 
</tr>
</c:forEach>
</table>

<center>
<c:if test="${pb.currPage!=1 }">
<a href="${pageContext.request.contextPath }/showProByPage?currPage=1">[首页]</a>
<a href="${pageContext.request.contextPath }/showProByPage?currPage=${pb.currPage-1}">[上一页]</a>
</c:if>

<c:if test="${pb.currPage!=pb.totalPage }">
<a href="${pageContext.request.contextPath }/showProByPage?currPage=${pb.currPage+1}">[下一页]</a>
<a href="${pageContext.request.contextPath }/showProByPage?currPage=${pb.totalPage}">[尾页]</a>
</c:if>

第${pb.currPage}页/共${pb.totalPage}页

</center> 

</body>
</html>