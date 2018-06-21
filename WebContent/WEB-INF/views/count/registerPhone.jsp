<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>注册手机详情</title>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<script src="../js/jquery-1.11.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}

@charset "utf-8";

select, textarea, input[type="text"], input[type="password"], input[type="datetime"],
	input[type="datetime-local"], input[type="date"], input[type="month"],
	input[type="time"], input[type="week"], input[type="number"], input[type="email"],
	input[type="url"], input[type="search"], input[type="tel"], input[type="color"],
	.uneditable-input {
	padding: 1px;
}

select {
	height: 24px;
}

.radio {
	font-size: 11px;
	margin-bottom: 4px;
}

form {
	margin: 0 0 5px;
}

.page {
	text-align: right;
	margin-right: 25px;
	margin-top: 5px;
}

.page a {
	margin-left: 5px;
}

.page .current {
	margin-left: 5px;
	color: red;
}

.table td input[type="checkbox"] {
	padding: 0;
	margin: 0;
}

.table th input[type="checkbox"] {
	padding: 0;
	margin: 0;
}

.table td, .table th {
	padding-top: 8px;
	padding-bottom: 4px;
	line-height: 20ppx;
	text-align: center;
	vertical-align: middle!important;
}

.table th {
	background-color: #eaeaea;
}

.definewidth {
	width: 96%;
	margin: auto;
}

.m20 {
	padding-top: 20px;
}

.tableleft {
	text-align: right;
	padding-left: 5px;
	background-color: #f5f5f5;
}
</style>
</head>

<body>
<div style="float: right;margin-right: 50px;margin-top: 20px;font-size: 1.2em;">导出为CSV文件&nbsp;<a id="test" href="downloadFile3" title="导出为CSV文件"><span class="glyphicon glyphicon-download-alt" /></a>   
</div>
	<table class="table table-bordered table-hover definewidth m10" style="margin-top: 40px;">
    <thead>
    <tr>
       
        <th>访问用户的手机号码</th>
        <th>时间</th>
        
       
    </tr>
    </thead>
		<c:forEach items="${pageInfo.list}" var="member" varStatus="st">
			<tr>
				<td>${member.MTel}</td>
				<td><fmt:formatDate value="${member.MAddTime}"
						pattern="yyyy年MM月dd日" /></td>
			</tr>
		</c:forEach>
	</table>
	 <div style="position: relative;top: 10px;left: 30px;">
		 <span style="font-size: 1.1em;">
         当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录  
        </span>
	</div>
	 <div style="float:right">
		 <nav>
			<ul class="pager">
				<li><a href="registerPhone?pageNum=1&pTitle=${pTitle}">首页</a></li>
				<c:if test="${pageInfo.hasPreviousPage}">
					<li><a href="registerPhone?pageNum=${pageInfo.pageNum-1}&pTitle=${pTitle}" aria-label="Previous"> <span
						aria-hidden="true">上一页</span>
				</a></li>
				</c:if>
				<c:if test="${pageInfo.hasNextPage}">
					<li><a href="registerPhone?pageNum=${pageInfo.pageNum+1}&pTitle=${pTitle}" aria-label="Next"> 
					<span aria-hidden="true">下一页</span>
				</a></li>
				</c:if>
				<li><a href="registerPhone?pageNum=${pageInfo.pages}&pTitle=${pTitle}s">尾页</a></li>
				<li>
					<form action="registerPhone" method="get" style="float:right">
						跳转至:<input style="width: 30%; " type="number"
							name="pageNum" id="pageNum" min="1"
							value="${pageInfo.pageNum}" /> 页
							<input  type="hidden" name="pageNum" id="pageNum" value="${pTitle}" />
						<button type="submit" class="btn btn-info btn-sm">跳转</button>
					</form>
				</li>
			</ul>
		</nav>
    </div>
</body>
</html>