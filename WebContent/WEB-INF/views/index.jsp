<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理系统</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<style type="text/css">
/*Global Styles*/
body {
	background: #f1f4f7;
	padding-top: 50px;
	color: #5f6468;
}

p {
	color: #777;
}

a, a:hover, a:focus {
	color: #30a5ff;
}

h1, h2, h3, h4, h5, h6 {
	color: #5f6468;
}

h1 {
	font-weight: 300;
	font-size: 40px;
}

h2 {
	font-weight: 300;
	margin-bottom: 20px;
}

h3, h4 {
	font-weight: 300;
}

.large {
	font-size: 2em;
}

.text-muted {
	color: #9fadbb;
}

.color-gray {
	color: #5f6468;
}

.color-blue {
	color: #30a5ff;
}

.color-teal {
	color: #1ebfae;
}

.color-orange {
	color: #ffb53e;
}

.color-red {
	color: #f9243f;
}

.bg-primary .glyphicon-remove {
	color: #5f6468;
}

.bg-primary .glyphicon-remove:hover {
	color: #ef4040;
}

.no-padding {
	padding: 0;
	margin: 0;
}

.glyphicon-xl {
	font-size: 6em;
}

.glyphicon-l {
	font-size: 3em;
}

.glyphicon-m {
	font-size: 1.5em;
}

.glyphicon-s {
	font-size: 0.75em;
}

.form-control {
	border: 1px solid #eee;
	box-shadow: none;
}

.form-control:focus {
	border: 1px solid #30a5ff;
	outline: 0;
	box-shadow: inset 0px 0px 0px 1px #30a5ff;
}

.navbar-header .navbar-brand {
	color: #fff;
	font-size: 16px;
	text-transform: uppercase;
	font-weight: 500;
	letter-spacing: 2px;
}

.navbar-header .navbar-brand span {
	color: #30a5ff;
}

.copyrights {
	text-indent: -9999px;
	height: 0;
	line-height: 0;
	font-size: 0;
	overflow: hidden;
}

/*Buttons*/
a.btn:hover, button:hover {
	opacity: 0.8;
}

a.btn:active, button:active {
	opacity: 1;
}

.btn-default, .btn-default:hover, .btn-default:focus, .btn-default:active,
	.btn-default.active, .open>.dropdown-toggle.btn-default, .btn-default.disabled,
	.btn-default[disabled], fieldset[disabled] .btn-default, .btn-default.disabled:hover,
	.btn-default[disabled]:hover, fieldset[disabled] .btn-default:hover,
	.btn-default.disabled:focus, .btn-default[disabled]:focus, fieldset[disabled] .btn-default:focus,
	.btn-default.disabled:active, .btn-default[disabled]:active, fieldset[disabled] .btn-default:active,
	.btn-default.disabled.active, .btn-default[disabled].active, fieldset[disabled] .btn-default.active
	{
	background-color: #e9ecf2;
	border-color: #e9ecf2;
	color: #1b3548;
}

.btn-primary, .btn-primary:hover, .btn-primary:focus, .btn-primary:active,
	.btn-primary.active, .open>.dropdown-toggle.btn-primary, .btn-primary.disabled,
	.btn-primary[disabled], fieldset[disabled] .btn-primary, .btn-primary.disabled:hover,
	.btn-primary[disabled]:hover, fieldset[disabled] .btn-primary:hover,
	.btn-primary.disabled:focus, .btn-primary[disabled]:focus, fieldset[disabled] .btn-primary:focus,
	.btn-primary.disabled:active, .btn-primary[disabled]:active, fieldset[disabled] .btn-primary:active,
	.btn-primary.disabled.active, .btn-primary[disabled].active, fieldset[disabled] .btn-primary.active
	{
	background-color: #30a5ff;
	border-color: #30a5ff;
}

.btn-success, .btn-success:hover, .btn-success:focus, .btn-success:active,
	.btn-success.active, .open>.dropdown-toggle.btn-success, .btn-success.disabled,
	.btn-success[disabled], fieldset[disabled] .btn-success, .btn-success.disabled:hover,
	.btn-success[disabled]:hover, fieldset[disabled] .btn-success:hover,
	.btn-success.disabled:focus, .btn-success[disabled]:focus, fieldset[disabled] .btn-success:focus,
	.btn-success.disabled:active, .btn-success[disabled]:active, fieldset[disabled] .btn-success:active,
	.btn-success.disabled.active, .btn-success[disabled].active, fieldset[disabled] .btn-success.active
	{
	background-color: #8ad919;
	border-color: #8ad919;
}

.btn-warning, .btn-warning:hover, .btn-warning:focus, .btn-warning:active,
	.btn-warning.active, .open>.dropdown-toggle.btn-warning, .btn-warning.disabled,
	.btn-warning[disabled], fieldset[disabled] .btn-warning, .btn-warning.disabled:hover,
	.btn-warning[disabled]:hover, fieldset[disabled] .btn-warning:hover,
	.btn-warning.disabled:focus, .btn-warning[disabled]:focus, fieldset[disabled] .btn-warning:focus,
	.btn-warning.disabled:active, .btn-warning[disabled]:active, fieldset[disabled] .btn-warning:active,
	.btn-warning.disabled.active, .btn-warning[disabled].active, fieldset[disabled] .btn-warning.active
	{
	background-color: #ffb53e;
	border-color: #ffb53e;
}

.btn-danger, .btn-danger:hover, .btn-danger:focus, .btn-danger:active,
	.btn-danger.active, .open>.dropdown-toggle.btn-danger, .btn-danger.disabled,
	.btn-danger[disabled], fieldset[disabled] .btn-danger, .btn-danger.disabled:hover,
	.btn-danger[disabled]:hover, fieldset[disabled] .btn-danger:hover,
	.btn-danger.disabled:focus, .btn-danger[disabled]:focus, fieldset[disabled] .btn-danger:focus,
	.btn-danger.disabled:active, .btn-danger[disabled]:active, fieldset[disabled] .btn-danger:active,
	.btn-danger.disabled.active, .btn-danger[disabled].active, fieldset[disabled] .btn-danger.active
	{
	background-color: #f9243f;
	border-color: #f9243f;
}

/*Backgrounds*/
.bg-primary {
	color: #1b3548;
	background-color: #e9ecf2;
}

a.bg-primary:hover {
	background-color: #e9ecf2;
}

.bg-success {
	color: #fff;
	background-color: #8ad919;
}

a.bg-success:hover {
	background-color: #8ad919;
}

.bg-success a {
	color: rgba(255, 255, 255, .75);
}

.bg-info {
	color: #fff;
	background-color: #30a5ff;
}

a.bg-info:hover {
	background-color: #30a5ff;
}

.bg-info a {
	color: rgba(255, 255, 255, .75);
}

.bg-warning {
	color: #fff;
	background-color: #ffb53e;
}

a.bg-warning:hover {
	background-color: #ffb53e;
}

.bg-warning a {
	color: rgba(255, 255, 255, .75);
}

.bg-danger {
	color: #fff;
	background-color: #f9243f;
}

a.bg-danger:hover {
	background-color: #f9243f;
}

.bg-danger a {
	color: rgba(255, 255, 255, .75);
}

/*Panels*/
.panel {
	border: 0;
}

.panel-heading {
	font-size: 18px;
	font-weight: 300;
	letter-spacing: 0.025em;
	height: 66px;
	line-height: 45px;
}

.panel-default .panel-heading {
	background: #fff;
	border-bottom: 1px solid #eee;
	color: #5f6468;
}

.panel-footer {
	background: #fff;
	border-top: 1px solid #eee;
}

.panel-widget {
	padding: 0;
	position: relative;
}

.panel-widget .panel-footer {
	border: 0;
	text-align: center;
}

.panel-footer .input-group {
	padding: 0px;
	margin: 0 -5px;
}

.panel-footer .input-group-btn:last-child>.btn, .panel-footer .input-group-btn:last-child>.btn-group
	{
	margin: 0;
}

.panel-widget .panel-footer a {
	color: #999;
}

.panel-widget .panel-footer a:hover {
	color: #666;
	text-decoration: none;
}

.panel-blue {
	background: #30a5ff;
	color: #fff;
}

.panel-teal {
	background: #1ebfae;
	color: #fff;
}

.panel-orange {
	background: #ffb53e;
	color: #fff;
}

.panel-red {
	background: #f9243f;
	color: #fff;
}

.panel-blue .panel-body p, .panel-teal .panel-body p, .panel-orange .panel-body p,
	.panel-red .panel-body p {
	color: #fff;
	color: rgba(255, 255, 255, .8);
}

.panel-blue .panel-heading, .panel-teal .panel-heading, .panel-orange .panel-heading,
	.panel-red .panel-heading {
	border-bottom: 1px solid rgba(255, 255, 255, .2);
}

.panel-blue .text-muted, .panel-teal .text-muted, .panel-orange .text-muted,
	.panel-red .text-muted {
	color: rgba(255, 255, 255, .5);
}

.dark-overlay {
	background: rgba(0, 0, 0, .05);
	text-align: center;
}

.widget-left {
	height: 80px;
	padding-top: 15px;
	text-align: center;
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px;
}

.widget-right {
	text-align: left;
	line-height: 1.6em;
	margin: 0px;
	padding: 20px;
	height: 80px;
	color: #999;
	font-weight: 300;
	background: #fff;
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
}

@media ( max-width : 768px) {
	.widget-right {
		width: 100%;
		margin: 0;
		text-align: center;
		border-top-left-radius: 0px;
		border-top-right-radius: 0px;
		border-bottom-left-radius: 4px;
		border-bottom-right-radius: 4px;
	}
}

@media ( max-width : 768px) {
	.widget-left {
		border-top-left-radius: 4px;
		border-top-right-radius: 4px;
		border-bottom-left-radius: 0px;
		border-bottom-right-radius: 0px;
	}
}

.widget-right .text-muted {
	color: #9fadbb;
}

.widget-right .large {
	color: #5f6468;
}

.panel-blue .widget-left {
	background: #30a5ff;
	color: #fff;
}

.panel-teal .widget-left {
	background: #1ebfae;
	color: #fff;
}

.panel-orange .widget-left {
	background: #ffb53e;
	color: #fff;
}

.panel-red .widget-left {
	background: #f9243f;
	color: #fff;
}

.panel-widget {
	background: #fff;
}

/*Jumbotron*/
.jumbotron {
	background: #fff;
	border-bottom: 1px solid #eee;
	color: #5f6468;
}

/*Tabs*/
.panel .tabs {
	margin: 0;
	padding: 0;
}

.nav-tabs {
	background: #e9ecf2;
	border: 0;
}

.nav-tabs li a:hover {
	background: #fff;
}

.nav-tabs li a, .nav-tabs li a:hover, .nav-tabs li.active a, .nav-tabs li.active a:hover
	{
	border: 0;
	padding: 15px 20px;
}

.nav-pills {
	padding: 15px;
	padding-bottom: 0;
}

.nav-pills li a, .nav-pills li a:hover, .nav-pills li.active a,
	.nav-pills li.active a:hover {
	border: 0;
	padding: 7px 15px;
}

.nav-pills li.active a, .nav-pills li.active a:hover {
	background: #30a5ff;
}

.tab-content {
	padding: 15px;
}

/*Navbar*/
.navbar {
	border: 0;
}

.navbar input {
	border: 0;
	background: #444;
	color: #fff;
}

.navbar input:focus {
	color: #fff;
	background: #555;
	border: 1px solid #30a5ff;
	outline: 0;
	box-shadow: inset 0px 0px 0px 1px #30a5ff;
}

.user-menu {
	display: inline-block;
	margin-top: 14px;
	margin-right: 10px;
	float: right;
	list-style: none;
	padding: 0;
}

.user-menu a {
	color: #fff;
}

.user-menu a:hover, .user-menu a:focus {
	text-decoration: none;
}

/* Sidebar */
.sidebar {
	display: block;
	background-color: #fff;
	padding: 0;
	display: none;
}

.sidebar form {
	padding: 20px 15px 5px 15px;
	border-bottom: 1px solid #eee;
	margin-bottom: 20px;
}

@media ( min-width : 768px) {
	.sidebar {
		display: block;
		position: fixed;
		top: 50px;
		bottom: 0;
		left: 0;
		z-index: 1000;
		display: block;
		margin: 0;
		padding: 0;
		overflow-x: hidden;
		overflow-y: auto;
		background-color: #fff;
		box-shadow: 1px 0px 10px rgba(0, 0, 0, .05);
	}
	.navbar-header {
		width: 100%;
	}
}

.sidebar ul.nav a:hover, .sidebar ul.nav li.parent ul li a:hover {
	text-decoration: none;
	background-color: #e9ecf2;
}

.sidebar ul.nav .active a, .sidebar ul.nav li.parent a.active, .sidebar ul.nav .active>a:hover,
	.sidebar ul.nav li.parent a.active:hover, .sidebar ul.nav .active>a:focus,
	.sidebar ul.nav li.parent a.active:focus {
	color: #fff;
	background-color: #30a5ff;
}

* /

.sidebar ul.nav ul, .sidebar ul.nav ul li {
	list-style: none;
	list-style-type: none;
}

.sidebar ul.nav ul.children {
	width: auto;
	padding: 0;
	margin: 0;
	background: #f9f9f9;
}

.sidebar ul.nav ul.children li a {
	text-decoration: none;
	height: 40px;
	background: #f9f9f9;
	color: #30a5ff !important;
}

.sidebar ul.nav li.current a {
	background-color: #30a5ff;
	color: #fff !important;
}

.sidebar ul.nav li.parent ul li a {
	border: none;
	display: block;
	padding-left: 30px;
	line-height: 40px;
}

.sidebar span.glyphicon {
	margin-right: 10px;
}

.sidebar ul.nav li.divider {
	border-bottom: 1px solid #eee;
	margin: 20px 0;
}

.sidebar .attribution {
	position: absolute;
	bottom: 0px;
	width: 100%;
	padding: 15px;
	text-align: center;
	border-top: 1px solid #eee;
	font-size: 12px;
}

@media ( max-width : 768px) {
	.sidebar .attribution {
		display: none;
	}
}

/*Breadcrumbs*/
.breadcrumb {
	border-radius: 0;
	padding: 10px 15px;
	background: #e9ecf2;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
	margin: 0;
}

h1.page-header {
	margin-top: 30px;
	border-bottom: 0;
}

/*Charts*/
.main-chart {
	background: #fff;
}

.easypiechart-panel {
	text-align: center;
	padding: 1px 0;
	margin-bottom: 20px;
}

.placeholder h2 {
	margin-bottom: 0px;
}

.donut {
	width: 100%;
}

.easypiechart {
	position: relative;
	text-align: center;
	width: 120px;
	height: 120px;
	margin: 20px auto 10px auto;
}

.easypiechart .percent {
	display: block;
	position: absolute;
	font-size: 26px;
	top: 38px;
	width: 120px;
}

#easypiechart-blue .percent {
	color: #30a5ff;
}

#easypiechart-teal .percent {
	color: #1ebfae;
}

#easypiechart-orange .percent {
	color: #ffb53e;
}

#easypiechart-red .percent {
	color: #ef4040;
}

/*Calendar Widget*/
#calendar, .datepicker-inline {
	width: 100%;
}

#calendar table {
	width: 100%;
}

.datepicker table tr td.old, .datepicker table tr td.new {
	color: rgba(255, 255, 255, .50);
}

.datepicker table tr td.active, .datepicker table tr td.active:hover,
	.datepicker table tr td.active.disabled, .datepicker table tr td.active.disabled:hover,
	.datepicker table tr td.active:hover, .datepicker table tr td.active:hover:hover,
	.datepicker table tr td.active.disabled:hover, .datepicker table tr td.active.disabled:hover:hover,
	.datepicker table tr td.active:focus, .datepicker table tr td.active:hover:focus,
	.datepicker table tr td.active.disabled:focus, .datepicker table tr td.active.disabled:hover:focus,
	.datepicker table tr td.active:active, .datepicker table tr td.active:hover:active,
	.datepicker table tr td.active.disabled:active, .datepicker table tr td.active.disabled:hover:active,
	.datepicker table tr td.active.active, .datepicker table tr td.active:hover.active,
	.datepicker table tr td.active.disabled.active, .datepicker table tr td.active.disabled:hover.active,
	.open .dropdown-toggle.datepicker table tr td.active, .open .dropdown-toggle.datepicker table tr td.active:hover,
	.open .dropdown-toggle.datepicker table tr td.active.disabled, .open .dropdown-toggle.datepicker table tr td.active.disabled:hover
	{
	color: #ffffff;
	background-color: rgba(0, 0, 0, .5);
	border-color: #285e8e;
}

.datepicker table tr td span:hover, .datepicker thead tr:first-child th:hover,
	.datepicker tfoot tr th:hover, .datepicker table tr td.day:hover,
	.datepicker table tr td.day.focused {
	background: rgba(0, 0, 0, 0.25);
}

.panel-heading .glyphicon {
	margin-right: 10px;
}

/*Todo List Widget*/
.todo-list-item .glyphicon {
	margin-right: 5px;
	color: #9fadbb;
}

.todo-list-item .glyphicon:hover {
	margin-right: 5px;
	color: #1b3548;
}

.todo-list {
	padding: 0;
	margin: -15px;
	background: #fff;
	color: #5f6468;
}

#checkbox {
	margin: 0;
}

.todo-list .checkbox {
	display: inline-block;
	margin: 0px;
}

.panel-body input[type=checkbox]:checked+label {
	text-decoration: line-through;
	color: #777;
}

.todo-list-item {
	list-style: none;
	line-height: 0.9;
	padding: 14px 15px 8px 15px;
}

.todo-list-item:hover, a.todo-list-item:focus {
	text-decoration: none;
	background-color: #f6f6f6;
}

.todo-list-item a.trash .glyphicon:hover {
	color: #ef4040;
}

/*Chat Widget*/
.chat ul {
	list-style: none;
	margin: -15px;
	padding: 15px;
}

.chat ul li {
	margin-bottom: 10px;
	padding: 15px 5px;
	border-bottom: 1px solid #eee;
}

.chat ul li.left .chat-body {
	margin-left: 100px;
}

.chat ul li.right .chat-body {
	margin-right: 100px;
}

.chat ul li .chat-body p {
	margin: 0;
}

.chat ul .glyphicon {
	margin-right: 5px;
}

.chat .panel-body {
	overflow-y: scroll;
	height: 300px;
}

.chat-body small {
	margin-left: 5px;
}

/*Tables*/
.table {
	background: #fff;
}

.fixed-table-container {
	border: 1px solid #eee;
}

.fixed-table-container thead th {
	background: #f7f7f8;
	border-color: #ddd;
	color: #5f6468;
	font-weight: 300;
	font-size: 16px;
}

.fixed-table-container tbody td {
	border: 1px solid #eee;
}

.fixed-table-container tbody td:last-child {
	border-right: none;
}

.table>thead>tr>th {
	border-bottom: 1px solid #e6e7e8;
	vertical-align: middle;
	height: 50px;
}

.fixed-table-pagination .pagination {
	margin-top: 10px;
	margin-bottom: 0px;
}

.fixed-table-pagination .pagination-detail {
	margin-top: 20px;
}
</style>


</head>
<%
out.println(request.getAttribute("tip"));
%> 
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index">后台管理系统</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<p style="color: white;">
							您好，<span class="glyphicon glyphicon-user"></span>&nbsp;管理员 <a
								href="loginout" style="margin-left: 0px;">【注销退出】</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
			<li class="parent "><a class="navBar" data-toggle="collapse"
				href="#sub-item-1"> <i class="glyphicon glyphicon-home"></i>&nbsp;&nbsp;&nbsp;系统管理
					<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="children collapse" id="sub-item-1">
				<!-- 	
					<li><a class="mItem" href="#"> <span
							class="glyphicon glyphicon-cog"></span> 系统配置
					</a></li>
					<li><a class="mItem" href="#"> <span
							class="glyphicon glyphicon-user"></span> 管理员管理
					</a></li>
					<li><a class="mItem" href="#"> <span
							class="glyphicon glyphicon-plus"></span> 管理员添加
					</a></li>  
					-->
					<li><a class="mItem" href="loginout"> <span
							class="glyphicon glyphicon-edit"></span> 退出登录
					</a></li>
				</ul></li>
			<li class="parent "><a class="navBar" data-toggle="collapse"
				href="#sub-item-2"> <i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;&nbsp;分站管理
					<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="children collapse" id="sub-item-2">
					<li><a class="mItem" href="productClass/classifyManager?pageNum=1"
						target="commentFrame"> <span class="glyphicon glyphicon-list"></span>
							分站管理
					</a></li>
					<li><a class="mItem" href="productClass/addClassifyPage"
						target="commentFrame"> <span class="glyphicon glyphicon-plus"></span>
							分站添加
					</a></li>
				</ul></li>
			<li class="parent "><a class="navBar" data-toggle="collapse"
				href="#sub-item-3"> <i class="glyphicon glyphicon-tags"></i>&nbsp;&nbsp;&nbsp;产品管理
					<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="children collapse" id="sub-item-3">
					<li><a class="mItem" href="product/productManager?pageNum=1"
						target="commentFrame"> <span class="glyphicon glyphicon-tag"></span>
							产品管理
					</a></li>
					<li><a class="mItem" href="product/addProductPage"
						target="commentFrame"> <span class="glyphicon glyphicon-plus"></span>
							产品添加
					</a></li>
				</ul></li>
			<li class="parent "><a class="navBar" data-toggle="collapse"
				href="#sub-item-4"> <i class="glyphicon glyphicon-picture"></i>&nbsp;&nbsp;&nbsp;幻灯管理
					<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="children collapse" id="sub-item-4">
					<li><a class="mItem" href="slides/slidesManager?pageNum=1"
						target="commentFrame"> <span
							class="glyphicon glyphicon-picture"></span> 幻灯管理
					</a></li>
					<li><a class="mItem" href="slides/addSlidesPage"
						target="commentFrame"> <span
							class="glyphicon glyphicon-plus"></span> 幻灯添加
					</a></li>
				</ul></li>
			<li class="parent "><a class="navBar" data-toggle="collapse"
				href="#sub-item-5"> <i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;统计查询
					<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="children collapse" id="sub-item-5">
				<!--  <li><a class="mItem" href="#"> <span
							class="glyphicon glyphicon-search"></span> 点击查询
					</a></li>  -->
					<li><a class="mItem" href="count/userCount?pageNum=1" 
						target="commentFrame"> <span
							class="glyphicon glyphicon-phone"></span> 用户统计
					</a></li>
					<li><a class="mItem" href="count/registerCount?pageNum=1"
						target="commentFrame"> <span
							class="glyphicon glyphicon-registration-mark"></span> 产品统计
					</a></li>
			 <!-- <li><a class="mItem" href="#"> <span
							class="glyphicon glyphicon-search"></span> 注册查询
					</a></li>  -->	
				</ul></li>
				<li class="parent "><a class="navBar" data-toggle="collapse"
				href="#sub-item-6"><i class="glyphicon glyphicon-tags"></i>&nbsp;&nbsp;&nbsp;独立产品管理
					<span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="children collapse" id="sub-item-6">
					<li><a class="mItem" href="saproduct/saproductManager?pageNum=1"
						target="commentFrame"> <span class="glyphicon glyphicon-tag"></span>
							独立产品管理
					</a></li>
					<li><a class="mItem" href="saproduct/addSaproductPage"
						target="commentFrame"> <span class="glyphicon glyphicon-plus"></span>
							独立产品添加
					</a></li>
					<li><a class="mItem" href="saproduct/standProductCount?pageNum=1"
						target="commentFrame"> <span
							class="glyphicon glyphicon-registration-mark"></span> 独立产品统计
					</a></li>
				</ul></li>
		<div class="attribution">
			CopyRight@2018-Collect from<br /> <a href="http://www.ouyisms.com/"
				title="广东欧一控股有限公司" target="_blank">广东欧一控股有限公司</a>
		</div>
		</ul>
	</div>
	<!--/左侧菜单栏-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">首页</li>
			</ol>
		</div>
		<!--/.右侧首页行-->


		<div class="row" style="margin-top: 20px;">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<iframe name="commentFrame" src="welcome" frameborder="0"
						scrolling="no" width="100%" height="900px"></iframe>
				</div>
			</div>
		</div>
		<!--/右侧主要内容->	
	</div>	<!--/右侧全部内容-->

		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="js/bootstrap-datepicker.js"></script>
		<script>
		$(function(){
	        $(".navBar").click(function(e){
	        	/*根据菜单点击切换上下图标*/
	        	$(this).find("span").toggleClass("glyphicon-chevron-down");
		        $(this).find("span").toggleClass("glyphicon-chevron-up");
	        });
	    });
		$(function(){
	        $(".mItem").click(function(e){
	        	/*根据菜单点击切换标题*/
	        	$(".active").text($(this).text());
	        });
	    });
		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>
	</body>
</html>