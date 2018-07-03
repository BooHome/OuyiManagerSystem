<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" 	%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
 <meta http-equiv="content-type" content="text/html; charset=gb2312">  
  <meta name="author" content="oscar999"> 
<title>分站管理</title>
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
	margin-top:20px;
}

.m10 {
	margin-top:10px;
}

.tableleft {
	text-align: right;
	padding-left: 5px;
	background-color: #f5f5f5;
}
</style>
</head>

<body>
<%
 if ((String)request.getAttribute("addStr") == "true") {
 %>
 <script>
 alert("分站添加成功！");
 </script>
 <%
 }
 %>
 
 <%
 if ((String)request.getAttribute("updateStr") == "true") {
 %>
 <script>
 alert("分站修改成功！");
 </script>
 <%
 }
 %>
<div style="float: right;margin-right: 50px;font-size: 1.2em;">导出为CSV文件&nbsp;<a id="test" href="downloadFile" title="导出为CSV文件"><span class="glyphicon glyphicon-download-alt" /></a>   
</div>
	<form class="form-inline definewidth m20" action="searchclassify"
		method="get">
		分站名称：
		<input type="text" name="keyName" id="keyName"
			class="abc input-default" value="${serchPc.CName}" placeholder="请输入分站名称">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>分站ID</th>
				<th>名称</th>
				
				<th>排序</th>
				<th>推广地址</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${pageInfo.list}" var="pc" varStatus="st">
			<tr>
				<td>${pc.id}</td>
				<td>${pc.CName}</td>
				<td>${pc.CSort}</td>
				<td>http://sj.ouyisms.com/WebH5/index.html?pcId=${pc.id}</td>
				<td><a href="updateClassifyJump?pc=${pc.id}" title="修改分站"><span class="glyphicon glyphicon-edit" />
				</a>&nbsp;<a class="deleteClassify" href="deleteclassify?pc=${pc.id}" title="删除分站"><span class="glyphicon glyphicon-trash" /></a></td>
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
				<li><a href="classifyManager?pageNum=1">首页</a></li>
				<c:if test="${pageInfo.hasPreviousPage}">
					<li><a href="classifyManager?pageNum=${pageInfo.pageNum-1}" aria-label="Previous"> <span
						aria-hidden="true">上一页</span>
				</a></li>
				</c:if>
				<c:if test="${pageInfo.hasNextPage}">
					<li><a href="classifyManager?pageNum=${pageInfo.pageNum+1}" aria-label="Next"> 
					<span aria-hidden="true">下一页</span>
				</a></li>
				</c:if>
				<li><a href="classifyManager?pageNum=${pageInfo.pages}">尾页</a></li>
				<li>
					<form action="classifyManager" method="get" style="float:right">
						跳转至:<input style="width: 30%; " type="number"
							name="pageNum" id="pageNum" min="1"
							value="${pageInfo.pageNum}" /> 页
						<button type="submit" class="btn btn-info btn-sm">跳转</button>
					</form>
				</li>
			</ul>
		</nav>
    </div>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".deleteClassify").click(function() {
				if (confirm("确定删除吗?")) {
					alert("删除成功!");
					return true;
				}
				return false;
			});
		});
	</script> 
</body>
</html>