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