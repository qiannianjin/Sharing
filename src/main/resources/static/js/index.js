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
})