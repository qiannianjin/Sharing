$(function() {
	
	$("#L_email").blur(function(){
		var userid = $(this).val();
		$.post("/user/checkuser/" + userid, {}, function(data){
			var tip = $("#useridtip span");
			tip.text(data.msg);
			if (data.status != 0) { //userid重复
				tip.attr("style", "color:red");
				$("#L_email").focus();
			} else { //可以注册
				tip.removeAttr("style");
			}
		});
	});
})