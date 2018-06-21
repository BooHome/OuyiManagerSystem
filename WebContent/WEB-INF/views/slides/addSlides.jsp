<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>添加图片</title>
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
 if ((String)request.getAttribute("addStr") == "false") {
 %>
 <script>
 alert("幻灯添加失败！请重新添加。");
 </script>
 <%
 }
 %>
	<form id="addSlides_form" action="addslides" method="post"  class="definewidth m20" enctype="multipart/form-data">
<input type="hidden" name="id" id="id" value=""/>
<input type="hidden" name="orgUrl" id="orgUrl" value=""/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="tableleft">所属分站</td>
            <td><span style="color:darkgary;">选择分站：</span><select id="scid" name="scid">
                      <c:forEach items="${pClasses}" var="pc" varStatus="st">
							<option value="${pc.id}">${pc.CName}</option>
						</c:forEach>
                      </select></td>
        </tr>
        <tr>
            <td class="tableleft">名称</td>
            <td><input type="text" name="sTitle" id="sTitle" value=""/></td>
        </tr>
        <tr>
            <td class="tableleft">图片</td>
            <td><input name="sTitleImg" id="sTitleImg" type="file" accept="image/*" /></td>
        </tr>
        
        <tr>
            <td class="tableleft">排序</td>
            <td><input type="text" name="sSort" id="sSort" value=""/></td>
        </tr>
        
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit"  name="saveid"  id="saveid" class="btn btn-primary">保存</button>
            </td>
        </tr>
    </table>
</form>
<script>
$(function(){
	 $('#addSlides_form').on('submit', function(e) {
	    	if ($('input[name="sTitleImg"]').val().length==0) {
		    	alert("请选择图片！");
               return false;
	    	}
	    	$(this).submit();
	    }); 
});
</script>
</body>
</html>