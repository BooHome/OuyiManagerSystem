<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" 	%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>图片管理</title>
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
<%
 if ((String)request.getAttribute("addStr") == "true") {
 %>
 <script>
 alert("幻灯添加成功！");
 </script>
 <%
 }
 %>
 
 <%
 if ((String)request.getAttribute("updateStr") == "true") {
 %>
 <script>
 alert("幻灯修改成功！");
 </script>
 <%
 }
 %>
<div style="float: right;margin-right: 50px;margin-top: 25px;font-size: 1.2em;">导出为CSV文件&nbsp;<a id="test" href="downloadFile" title="导出为CSV文件"><span class="glyphicon glyphicon-download-alt" /></a>   
</div>
	<form class="form-inline definewidth m20" action="searchslides" method="get">
    <input type="text" name="keyName" id="keyName"class="abc input-default" value="${sd.STitle}" placeholder="请输入幻灯名称">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10"  style="margin-top: 20px;">
    <thead>
    <tr>
       
       <th>所属分站</th>
        <th>名称</th>
        <th>图片</th>
        
        
        
        <th>时间</th>
        <th>操作</th>
    </tr>
    </thead>
            <c:forEach items="${pageInfo.list}" var="s" varStatus="st">
           		 <c:if test="${s.STitle != null}">
					<tr>
					<td>${s.productClass.CName}</td>
					<td>${s.STitle}</td>
					<td>${s.STitleImg}</td>
					<td><fmt:formatDate value="${s.SAddTime}" pattern="yyyy年MM月dd日"/></td>
					<td><a href="updateSlidesJump?s=${s.id}" title="修改幻灯"><span class="glyphicon glyphicon-edit"></a>&nbsp;<a class="deleteSildes" href="deleteslides?s=${s.id}" title="删除幻灯"><span class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:if>
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
				<li><a href="slidesManager?pageNum=1">首页</a></li>
				<c:if test="${pageInfo.hasPreviousPage}">
					<li><a href="slidesManager?pageNum=${pageInfo.pageNum-1}" aria-label="Previous"> <span
						aria-hidden="true">上一页</span>
				</a></li>
				</c:if>
				<c:if test="${pageInfo.hasNextPage}">
					<li><a href="slidesManager?pageNum=${pageInfo.pageNum+1}" aria-label="Next"> 
					<span aria-hidden="true">下一页</span>
				</a></li>
				</c:if>
				<li><a href="slidesManager?pageNum=${pageInfo.pages}">尾页</a></li>
				<li>
					<form action="slidesManager" method="get" style="float:right">
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
   $(document).ready(function(){  
    $(".deleteSildes").click(function(){  
        if(confirm("确定删除吗?")){  
            alert("删除成功!");  
            return true;  
        }  
        return false;  
    });  
   });  
</script>  
</body>
</html>