$(function() {
	
	function GetQueryString(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}

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

    function findInformation(url, params, container, sourceNode) {
		
		$.post(url, params, function(data){
			var list = data.dataList || {};
			if (data.status == 0) {
//				var sourceNode = document.getElementById("info_model");
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
					li.find("div.fly-list-info span.fly-list-kiss").html('<i class="iconfont icon-kiss"></i> ' + n.price);
					if (n.price && n.price == 0) {
						li.find("a.wealth").text("免费");
					}
					if (n.important == 1) { //置顶
						li.find("div.fly-list-badge span.layui-badge:eq(0)").show();
					} else if (n.important == 2) { //推荐
						li.find("div.fly-list-badge span.layui-badge:eq(1)").show();
					} else if (n.important == 3) { //精华
						li.find("div.fly-list-badge span.layui-badge:eq(2)").show();
					}
//					$("ul.fly-list:eq(0)").append(li);
//					$("ul.fly-list:eq(1)").append(li);
					container.append(li);
				});
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
			
			//控制页面显示内容，在查出分类后再操作
			var informationtypeid = GetQueryString("informationtypeid");
			var type = GetQueryString("type");
			var title = $("div.fly-panel-title>a.layui-this");
			if (informationtypeid) { //分类查询共享信息
				var params = {status:1, informationtypeid:informationtypeid};
				title.text($("#li_" + informationtypeid + " a").text() + " 信息");
				findInformation("/jie/list", params, $("ul.fly-list:eq(0)"), document.getElementById("info_model"));
			} 
			if (type != null && type == 'pub') { //查询我发布的共享信息
				var params = {status:1};
				var userCookie = $.cookie("user");
				if (userCookie) {
					var user = JSON.parse(decodeURIComponent(userCookie));
					params.userid = user.userid;
				}
				title.text("我发布的共享信息");
				findInformation("/jie/list", params, $("ul.fly-list:eq(0)"), document.getElementById("info_model"));
			} else if (type != null && type == 'buy') {
				var params = {status:1};
				title.text("我购买的共享信息");
				findInformation("/jie/buylist", params, $("ul.fly-list:eq(0)"), document.getElementById("info_model"));
			}
		}
	});
})