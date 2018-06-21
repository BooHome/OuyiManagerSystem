<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<title>欢迎页</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css" />
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/echarts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
</head>
<style>
	input {
	width: 150px;
	height: 25px;
	font-size: 1.2em;
}
</style>
<body style="height: 100%; ">
	
		<div style="margin: 2%;">
				开始时间：<input type="date" name="startTime" id="startTime"
					value="${searchR['startTime']}">&nbsp;&nbsp; 结束时间： <input
					type="date" name="endTime" id="endTime" value="${searchR['endTime']}">&nbsp;&nbsp;
					<button type="button" onclick="initData()" class="btn btn-primary">查询</button>
		</div>
		<div id="container" style="height: 70% ;margin: 2%"></div>
	
	<script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		option = {
			title : {
				text : '产品访问量统计',
			},
			tooltip : {
				trigger : 'axis',
			},
			legend : {
				data : [ 'PV', 'UV' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataZoom : {
						yAxisIndex : 'none'
					},
					dataView : {
						readOnly : false
					},
					magicType : {
						type : [ 'line', 'bar' ]
					},
					restore : {},
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : []
			},
			yAxis : {
				type : 'value',
				axisLabel : {
					formatter : '{value}'
				}
			},
			series : [ {
				name : 'PV',
				type : 'line',
				areaStyle: {},
				data : [],
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			}, {
				name : 'UV',
				type : 'line',
				areaStyle: {},
				data : [],
				markPoint : {
					data : [ {
						type : 'max',
						name : '最大值'
					}, {
						type : 'min',
						name : '最小值'
					} ]
				},
				markLine : {
					data : [ {
						type : 'average',
						name : '平均值'
					} ]
				}
			} ]
		};
		;
		 myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
		 $(function() {
				initData();
			});
		 function initData(){
			 var names =[];
			 var pvs = [];
			 var uvs = [];
			 var startTime = document.getElementById('startTime').value;
			 var endTime = document.getElementById('endTime').value;
			 $.ajax({
					url : "ajax/getChartData",
					type : "POST",
					dataType : "json",
					data : {
						startTime : startTime,
						endTime : endTime
					},
					success : function(data) {
						console.log(data);
						if(data){
							for (var i = 0; i < data.length; i++) {
								names.push(data[i].countName);
								pvs.push(data[i].PV);
								uvs.push(data[i].UV);
							}
							  myChart.hideLoading();    //隐藏加载动画
			                    myChart.setOption({        //加载数据图表
			                        xAxis: {
			                            data: names
			                        },
			                        series: [{
			                            // 根据名字对应到相应的系列
			                            name: 'PV',
			                            data: pvs
			                        },{
			                        	// 根据名字对应到相应的系列
			                            name: 'UV',
			                            data: uvs
			                        }]
			                    });
						}
					},
			         error : function(errorMsg) {
			             //请求失败时执行该函数
			         alert("图表请求数据失败!");
			         myChart.hideLoading();
			         }
				});
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		 }
		 
	</script>
</body>
</html>