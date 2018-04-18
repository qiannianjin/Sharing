$(function() {
	
	//控制菜单显示
	var user = JSON.parse(decodeURIComponent($.cookie("user")));
	if (user.userid) {  //登陆
		$(".fly-nav-user .layui-nav-item:lt(5)").hide();
		$(".fly-nav-user .layui-nav-item:gt(4)").show();
		$(".nickname").text(user.nickname);
		$(".avatar").attr("src", user.avatar);
	} else {
		$(".fly-nav-user .layui-nav-item:lt(5)").show();
		$(".fly-nav-user .layui-nav-item:gt(4)").hide();
	}
	
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
})