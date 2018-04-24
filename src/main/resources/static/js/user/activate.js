layui.use('layer', function(){ 
	var layer = layui.layer;

	$(function(){
		//检测当前登陆的用户的邮箱是否激活
		$.post("/user/checkUserEmail", {}, function(data){
			
			if (data.status == 0) {
				data = data.data;
				$("#email").text(data.email);
				if (data.status == 1) { //已经激活
					$("#enable").show();
					$("#disable").hide();
				} else { //为激活
					$("#enable").hide();
					$("#disable").show();
				}
			} else {
	       	 	layer.msg(data.msg, {
	       	 		time: 3 * 1000,
	       	 		end: function(){
	       	 			window.location.href = data.action;
	       	 		}
	       	 	});
			}
			
		});
	})
})
	