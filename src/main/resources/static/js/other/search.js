$(function() {
    function findAnnouncement(params, container, sourceNode) {
        $.post("/jie/list", params, function(data){
            var list = data.dataList || {};
            if (list.length == 0) {
                layer.msg("没有更多数据了!", {time: 3 * 1000});
                return;
            }
            if (data.status == 0) {
                $.each(list, function(i, n) {
                    var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
                    li.find("a").attr("href", "/jie/detail?informationid=" + n.informationid);
                    li.find("a").text(n.name);
                    container.append(li);
                })
            }
        });
    }

    //加载公告  温馨通道
    findAnnouncement({status:4, important:4}, $("#announcementcontainer"), document.getElementById("announcement_model"));

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