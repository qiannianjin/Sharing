$(function() {
	
	$("#email_id").click(function(){
		$.post("/email/send", {}, function(data){
			layer.msg(data.msg, {icon:16, shade: 0.1, time:0});
			window.location.href = data.action;
		});
	});
})