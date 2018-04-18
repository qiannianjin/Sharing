$(function() {
	
	//控制导航栏用户名称和头像显示
	var user = JSON.parse(decodeURIComponent($.cookie("user")));
	$(".nickname").text(user.nickname);
	$(".avatar").attr("src", user.avatar);
})