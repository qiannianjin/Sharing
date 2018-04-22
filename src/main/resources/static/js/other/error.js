$(function() {
	
	$.get("/type/common?status=1", function(data){
		var list = data.dataList || {};
		if (data.status == 0) {
			var sourceNode = document.getElementById("model");
			$.each(list, function(i, n) {
				var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.informationtypeid, "style":"", "id":"li_" + n.informationtypeid});
				li.find("a").attr("href", "/jie/index?informationtypeid=" + n.informationtypeid).text(n.name);
				$("div.layui-container>ul.layui-clear li#model").before(li);
			});
		}
	});
})