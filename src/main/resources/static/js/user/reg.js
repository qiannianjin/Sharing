﻿$(function() {
	
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

    //生成uuid
    function guid() {
        function S4() {
            return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
        }
        return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
    }

    //获取验证码
    var codeKey = null;
    function getCode(){
        if(codeKey==null){
            codeKey = guid();
        }
        $("#LAY-user-get-vercode").attr("src","/image/captcha?codeKey="+codeKey+"&n="+Math.random());
        $("#verkey").val(codeKey);
    }

    getCode();  //获取验证码
    $("#LAY-user-get-vercode").click(function(){
        getCode();
    });
})