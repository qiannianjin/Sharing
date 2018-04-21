$(function() {
	
	$.get("/type/common?status=1", function(data){
		var list = data.dataList || {};
		if (data.status == 0) {
			var sourceNode = document.getElementById("model");
			$.each(list, function(i, n) {
				var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
				li.find("a").attr("href", "jie/index.html").text(n.name);
				$("div.layui-container>ul.layui-clear li#model").before(li);
			});
		}
	});
	
	$.post("/jie/list", {status:1}, function(data){
		var list = data.dataList || {};
		if (data.status == 0) {
			var sourceNode = document.getElementById("info_model");
			$.each(list, function(i, n) {
				var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
				li.find("a.fly-avatar img").attr("src", n.avatar).attr("alt", n.nickname);
				li.find("h2 a:eq(1)").attr("href", "/jie/detail?informationid=" + n.informationid)
					.text(n.name);
				li.find("div.fly-list-info a cite").text(n.nickname);
				var createtime = new Date(n.createtime);
				var now = new Date();
				if ((now.getTime() - createtime.getTime()) < 10 * 60 * 1000) {
					var showtime  = "刚刚"
				} else {
					var showtime = createtime.getFullYear() + "-" + createtime.getMonth()+1 + "-" + createtime.getDate();
				}
				li.find("div.fly-list-info span:eq(0)").text(showtime);
				li.find("div.fly-list-info span.fly-list-kill").html('<i class="iconfont icon-kiss"></i> ' + n.price);
				if (n.important == 1) { //置顶
					li.find("div.fly-list-badge span.layui-badge:eq(0)").show();
				} else if (n.important == 2) { //推荐
					li.find("div.fly-list-badge span.layui-badge:eq(1)").show();
				} else if (n.important == 3) { //精华
					li.find("div.fly-list-badge span.layui-badge:eq(2)").show();
				}
//				$("ul.fly-list:eq(0)").append(li);
				$("ul.fly-list:eq(1)").append(li);
			});
		}
	});
})