layui.use('layer', function(){ 
	var layer = layui.layer;

	$(function(){
		
		//find information
		function findInformation(url, params, container, sourceNode) {
			$.post(url, params, function(data){
				var list = data.dataList || {};
				if (data.status == 0) {
//					var sourceNode = document.getElementById("pub_model");
					if (url.indexOf("/jie/list") != -1){ //my pub information
						$("#pubCount").text(list.length);
					} else { //my buy information
						$("#buyCount").text(list.length);
					}
					$.each(list, function(i, n) {
						var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
						li.find("a.jie-title").attr("href", "/jie/detail?informationid=" + n.informationid).text(n.name);
						if (url.indexOf("/jie/list") != -1){ //my pub information
							li.find("a.mine-edit").attr("href", "/jie/add?informationid=" + n.informationid);
						}
						var createtime = new Date(n.createtime);
						var now = new Date();
						if ((now.getTime() - createtime.getTime()) < 10 * 60 * 1000) { //10M
							var showtime  = "刚刚"
						} else {
							//2017/3/14 上午8:30:00
							var showtime = createtime.getFullYear() + "/" + (createtime.getMonth()+1) + "/" + createtime.getDate();
							if ((createtime.getHours()+1) < 6) {
								showtime = " 凌晨";
							} else if ((createtime.getHours()+1) < 12) {
								showtime += " 上午";
							} else if ((createtime.getHours()+1) < 19) {
								showtime += " 下午";
							} else {
								showtime += " 晚上";
							}
							showtime += (createtime.getHours()+1-12) + ":" + createtime.getMinutes() + ":" + createtime.getSeconds();
						}
						li.find("i").text(showtime);
						container.append(li);
					})
				} else {
					
				}
			})
		}
		
		var params = {};
		var userCookie = $.cookie("user");
		if (userCookie) {
			var user = JSON.parse(decodeURIComponent(userCookie));
			params.userid = user.userid;
		}
		
		//get my pub information  /jie/list
		findInformation("/jie/list", params, $("#pub_container"), document.getElementById("pub_model"));		
		
		//get my buy information  /jie/buylist
		findInformation("/jie/buylist", params, $("#buy_container"), document.getElementById("buy_model"));

	})
})
	