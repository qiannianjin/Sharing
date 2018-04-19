$(function() {
	
	//控制编译此信息按钮
	var userCookie = $.cookie("user");
	if (!userCookie) { //没有登陆
		$("#LAY_jieAdmin span.layui-btn").hide();
		return;
	}
	
})