$(function() {
	
	//控制菜单显示
	var userid = $.cookie("userid");
	if (userid) {  //登陆
		$(".fly-nav-user .layui-nav-item:lt(5)").hide();
		$(".fly-nav-user .layui-nav-item:gt(4)").show();
	} else {
		$(".fly-nav-user .layui-nav-item:lt(5)").show();
		$(".fly-nav-user .layui-nav-item:gt(4)").hide();
	}
})