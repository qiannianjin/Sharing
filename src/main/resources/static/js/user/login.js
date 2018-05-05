$(function() {

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