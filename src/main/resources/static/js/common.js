$(function() {
	
	//控制菜单显示 用户名称和头像显示
	var userCookie = $.cookie("user");
	if (!userCookie) {
		$(".fly-nav-user .layui-nav-item:lt(5)").show();
		$(".fly-nav-user .layui-nav-item:gt(4)").hide();
		return;
	}
	
	var user = JSON.parse(decodeURIComponent(userCookie));
	if (user.userid) {  //登陆
		$(".fly-nav-user .layui-nav-item:lt(5)").hide();
		$(".fly-nav-user .layui-nav-item:gt(4)").show();
		$(".nickname").text(user.nickname);
		$(".avatar").attr("src", user.avatar);
	} 
})