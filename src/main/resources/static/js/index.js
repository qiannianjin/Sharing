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
				li.find("h2 a:eq(1)").attr("href", "/jie/detail?informationid=" + n.id)
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
				$("ul.fly-list:eq(0)").append(li);
			});
		}
	});
})