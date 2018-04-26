$(function() {

    function getDate(date) {
    	date = new Date(date);
        return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
    }
	
	//控制编译此信息按钮
	var userCookie = $.cookie("user");
	if (!userCookie) { //没有登陆
		$("#LAY_jieAdmin span.layui-btn").hide();
		return;
	}

	//加载评论
	var params = {};
    params.informationid = $("#informationid").val();
    params.status = 1;

	$.post("/comments/list", params, function(data){
		var list = data.dataList || {};
		if (data.status == 0) { //success
			if (list.length == 0) { //没有评论
				$("#comment_none").show();
				return;
			}
			var sourceNode = document.getElementById("comment_model");
            var userCookie = $.cookie("user");
            if (userCookie) {
                var user = JSON.parse(decodeURIComponent(userCookie));
			}
			$.each(list, function(i, n) {
				var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
				li.find("a.fly-avatar img").attr("src", n.avatar).attr("alt", n.avatar);
				li.find("div.fly-detail-user a.fly-link cite").text(n.nickname);
				li.find("div.detail-hits span").text(getDate(n.createtime));
				li.find("div.comment_content p").text(n.content);
				if ($("#userid").val() == n.userid) { //是作者的回复
					li.find(".louzhu").show();
				} else {
					li.find(".normaluser").show();
				}
				//判断浏览者的身份 如果是自己发的帖子 则有删除和编辑的功能
				if (user && user.userid == n.userid) {
					li.find("div.jieda-admin span[type=edit]").show();
					li.find("div.jieda-admin span[type=del]").show();
				} else if (user && user.userid == $("#userid").val()) {//是作者
                    li.find("div.jieda-admin span[type=accept]").show();
                }

				$("#jieda").append(li);
			})
		} else { //加载评论失败

		}
	});
})