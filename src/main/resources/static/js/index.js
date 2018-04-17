$(function() {
	
	//控制菜单显示
	var user = JSON.parse(decodeURIComponent($.cookie("user")));
	if (user.userid) {  //登陆
		$(".fly-nav-user .layui-nav-item:lt(5)").hide();
		$(".fly-nav-user .layui-nav-item:gt(4)").show();
		$(".nickname").text(user.nickname);
		$(".avatar").attr("src", user.avatar);
	} else {
		$(".fly-nav-user .layui-nav-item:lt(5)").show();
		$(".fly-nav-user .layui-nav-item:gt(4)").hide();
	}
})