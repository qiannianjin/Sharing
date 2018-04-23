$(function() {
	
	function GetQueryString(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	
	var params = {};
	var userid = GetQueryString("userid");
	var code = GetQueryString("code");
	var email = GetQueryString("email");
	var type = GetQueryString("type")
	params.userid = userid;
	params.code = code;
	params.email = email;
	params.type = type;
	
	$.post("/email/verity", params, function(data){
		$("div.fly-none p").text(data.msg);
	});
})