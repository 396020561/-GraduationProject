//自定义函数区
function time(timestp) {
	var myDate = new Date(timestp)
	return myDate;
}

function hour(timestp) {
	var myDate = new Date(timestp)
	var hour = parseInt(myDate.getHours());
	return hour;
}

function toTimestp(time) {
	var dateTmp = time.replace(/-/g, '/') //为了兼容IOS，需先将字符串转换为'yyyy/mm/dd'格式
	var timestamp = new Date(dateTmp).getTime();
	return timestamp;
}

$(function() {
	var place = {}; //进出楼栋
	var timestp = {
		'out': [],
		'enter': []
	};
	//通行记录图表坐标数据
	var outData = []; //出寝数据
	var enterData = []; //归寝数据
	
	$.ajax({
		type: "post",
		url: "getRecentAccess",
		async: true,
		success: function(accessData) {

			accessData.forEach(function(item) {
				var tsp = toTimestp(item.createTime);
				place[tsp] = item.place;
				if(item.direction == 0) {
					timestp.out.push(tsp);
				} else {
					timestp.enter.push(tsp);
				}
			});

			timestp.out.forEach(function(item) {
				var outDataItem = [item, hour(item)];
				outData.push(outDataItem); //存入出寝数据
			});
			timestp.enter.forEach(function(item) {
				var enterDataItem = [item, hour(item)];
				enterData.push(enterDataItem); //存入归寝数据
			});

			//通行记录散点图
			var chartAcc = Highcharts.chart('container', {
				chart: {
					type: 'scatter', //图表类型：散点图
					zoomType: 'xy' //缩放方式
				},
				title: {
					text: '最近通行记录'
				},
				subtitle: {
					text: '数据来源: nchu'
				},
				credits: {
					enabled: false //隐藏版权标志
				},
				responsive: {
					rules: [{ // 在图表小于 500px 的情况下关闭图例
						condition: { // 响应条件
							maxWidth: 500
						},
						chartOptions: { // 响应内容
							legend: {
								enabled: false
							}
						}
					}]
				},
				xAxis: {
					title: {
						enabled: true,
						text: '日期'
					},
					type: 'datetime',
					dateTimeLabelFormats: {
						day: '%e of %b'
					},
					/*floor: 1,
					ceiling: 31,*/
					startOnTick: true,
					endOnTick: true,
					showLastLabel: true,
					tickInterval: 24 * 3600 * 1000, //坐标刻度显示间隔为1天
					/*labels: {
						formatter: function() {
							return time(this.value).getDate();
						},
						step: 1 //坐标刻度显示间隔
					},*/
				},
				yAxis: {
					title: {
						text: '时间'
					},
					floor: 0,
					ceiling: 24,
					max: 24,
					min: 0,
					tickInterval: 4,
					/*tickAmount: 6,*/
					/*minTickInterval:1,*/
					/*tickInterval: 2,
					tickAmount: 6,
					*/
					/*labels: {
						formatter: function() {
							if(this.value < 23) {
								return this.value;
							} else if(this.value >= 23 && this.value <= 24) {
								return this.value + "(晚归)";
							} else {
								return '';
							}
						},
						step: 1 //坐标刻度显示间隔
					}*/
				},
				legend: {
					align: 'right',
					verticalAlign: 'top',
					floating: true,
					/*layout: 'vertical',*/
					x: -40,
					/*y: 70,*/
					/*backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',*/
					/*borderWidth: 1*/
				},
				plotOptions: {
					scatter: {
						marker: {
							radius: 5,
							states: {
								hover: {
									enabled: true,
									lineColor: 'rgb(100,100,100)'
								}
							}
						},
						states: {
							hover: {
								marker: {
									enabled: false
								}
							}
						},
						tooltip: {
							/*headerFormat: '<b>{series.name}</b><br>',*/
							/*pointFormat: '时间：' + '{point.x}号, {point.y}<br>',*/
							pointFormatter: function() {
								return '时间：' + (time(this.x).getMonth() + 1) + '月' +
									time(this.x).getDate() + '日 ' +
									time(this.x).getHours() + ':' +
									time(this.x).getMinutes() + '<br/>' +
									'地点：' + place[this.x]
							},
							/* footerFormat: '地点：' + data.place */
						}
					}
				},
				series: [{
					name: '外出',
					color: 'rgba(180,238,180,1)',
					data: outData
				}, {
					name: '归寝',
					color: 'rgba(164,211,238,1)',
					data: enterData
				}]

			});

		},
		error: function() {
			alert("通行记录加载出错");
		}
	});

});

//考勤数据图表
$(function(){
	var late = 0;
	var noBack = 0;
	var noOut = 0;
	var leave = 0;
	var other = 0;
	var AttData = [];
	var cat = 0;
	$.ajax({
		type:"post",
		url:"getAccAttend",
		success:function(data){
			data.forEach(function(item){
				cat = item.category;
				switch(cat){
				case 0:
					late++;
					break;
				case 1:
					noBack++;
					break;
				case 2:
					noOut++;
					break;
				case 3:
					leave++;
					break;
				case 4:
					other++;
					break;
				case 5:
					other++;
					break;
				case 6:
					other++;
					break;
				}
			});
			AttData.push(late,noBack,noOut,leave,other);
			
			var chartYic = Highcharts.chart('yichang', {
				chart: {
					type: 'bar' //指定图表的类型，默认是折线图（line）
				},
				title: {
					text: '考勤统计' // 标题
				},
				credits: {
					enabled: false
				},
				responsive: {
					rules: [{ // 在图表小于 500px 的情况下关闭图例
						condition: { // 响应条件
							maxWidth: 500
						},
						chartOptions: { // 响应内容
							legend: {
								enabled: false
							}
						}
					}]
				},
				xAxis: {
					categories: ['晚归', '未归', '未出', '请假', '异常'] // x 轴分类
				},
				yAxis: {
					title: {
						text: '数量' // y 轴标题
					},
					tickInterval: 1,
				},
				legend: {
					align: 'right',
					verticalAlign: 'top',
					floating: true,
					/*layout: 'vertical',*/
					x: -40,
					/*y: 70,*/
					/*backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',*/
					/*borderWidth: 1*/
				},
				series: [{ // 数据列
					name: '数量', // 数据列名
					data: AttData, // 数据
					color: 'rgba(255,100,97,1)'
				}]
			});
			
		},
		error:function(){
			alert("考勤数据加载出错");
		}
	});
});

//访客数据图表
$(function(){
	var total = 1;
	var last = 1;
	
	$.ajax({
		type:"post",
		url:"getUserInfo",
		success:function(data){
			last = data.lastVisitorNum;
			total = last + data.totalVisitor;
			
			var chartVisitor = Highcharts.chart('visitor', {
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					type: 'pie'
				},
				title: {
					text: '本月录入访客情况'
				},
				credits: {
					enabled: false
				},
				tooltip: {
					/*headerFormat: '{series.name}<br>',*/
					/*pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>',*/
					/*footerFormat: ' (',*/
					pointFormatter: function() {
						return this.percentage + '%' + ' (' + (this.percentage / 100) * total + ')';
					},
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
							enabled: true,
							format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							style: {
								color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							}
						}
					}
				},
				series: [{
					name: '占比',
					colorByPoint: true,
					data: [{
						name: '剩余额度',
						y: (last / total) * 100,
						sliced: true,
						selected: true
					}, {
						name: '已用额度',
						y: ((total - last) / total) * 100,
						color: 'rgba(223, 83, 83, .5)'
					}]
				}]
			});
			
		},
		error:function(data){
			console.log(data.result);
		}
	});
	
});




//最近请假记录