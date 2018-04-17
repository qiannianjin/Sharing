$(function() {
	
	//控制菜单显示
	var user = JSON.parse(decodeURIComponent($.cookie("user")));
	$(".avatar-add img").attr("src", user.avatar);
	$("#L_email").val(user.userid);
	$("#L_username").val(user.nickname);
	$("#L_city").val(user.city);
	$(L_sign).text(user.summary);
	//性别 城市
})