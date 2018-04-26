layui.use('layer', function() {
    var layer = layui.layer;

    $(function() {

        function getDate(date) {
            date = new Date(date);
            return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
        }

        var changeStatus = function(cid, status){

            $.post('/comments/change', {commentid:cid, status:status}, function(data){
                if (data && data.status == 0) {
                    layer.msg(data.msg, {time: 3 * 1000,
                        end: function(){
                            window.location.href = window.location.href;
                        }
                    });
                } else {
					layer.msg(data.msg, {time: 3 * 1000,
						end: function(){
							window.location.href = window.location.href;
						}
					});
                }
            });
        };

        //控制编译此信息按钮
        var userCookie = $.cookie("user");
        if (!userCookie) { //没有登陆
            $("#LAY_jieAdmin span.layui-btn").hide();
            return;
        }

        //加载评论
        var params = {};
        params.informationid = $("#informationid").val();
        params.status = 1;

        $.post("/comments/list", params, function(data){
            var list = data.dataList || {};
            if (data.status == 0) { //success
                if (list.length == 0) { //没有评论
                    $("#comment_none").show();
                    return;
                }
                var sourceNode = document.getElementById("comment_model");
                var userCookie = $.cookie("user");
                if (userCookie) {
                    var user = JSON.parse(decodeURIComponent(userCookie));
                }
                $.each(list, function(i, n) {
                    var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
                    li.find("a.fly-avatar img").attr("src", n.avatar).attr("alt", n.avatar);
                    li.find("div.fly-detail-user a.fly-link cite").text(n.nickname);
                    li.find("div.detail-hits span").text(getDate(n.createtime));
                    li.find("div.comment_content p").text(n.content);
                    li.find("input.comments_id").val(n.commentid);
                    if ($("#userid").val() == n.userid) { //是作者的回复
                        li.find(".louzhu").show();
                    } else {
                        li.find(".normaluser").show();
                    }
                    //判断浏览者的身份 如果是自己发的帖子 则有删除和编辑的功能
                    if (user && user.userid == n.userid) {
                        li.find("div.jieda-admin span[type=edit]").show();
                        li.find("div.jieda-admin span[type=del]").show();
                    } else if (user && user.userid == $("#userid").val()) {//是作者
                        li.find("div.jieda-admin span[type=accept]").show();
                    }
                    $("#jieda").append(li);
                });

                $('li[id^=li_] .edit').click(function(){ //编辑
                    var comments_id = $(this).parents("li[id^=li_]").find('.comments_id').val();
                    //获取评论内容
					var comment_content = $(this).parents('li[id^=li_]').find('div.comment_content p').text();

					//设置到底部的评论内容框中
					$("#L_content").text(comment_content);

					var input_comments_id = $('<input />').attr("type", "hidden")
						.attr("name", "commentid").attr("value", comments_id);
					alert(input_comments_id.val());
					$("#informationid").before(input_comments_id);
                });
                $('li[id^=li_] .del').click(function(){ //删除
                    var comments_id = $(this).parents("li[id^=li_]").find('.comments_id').val();
                   layer.confirm('删除提示', {btn: ['确认删除', '取消']}, function(index, layero){
                       changeStatus(comments_id, 9);
				   });
                });
                $('li[id^=li_] .jieda-accept').click(function(){

                });

                $('tr[id^=tr_] .btn_stop').click(function(){
                    var wid = $(this).parents('tr[id^=tr_]').find('.salevirtual_id').val();
                    $.messager.confirm('停用提示：','确认停用？',function(r){
                        if (r){
                            changeStatus(wid, 0, '停用成功');
                        }
                    });
                });
            } else { //加载评论失败

            }
        });
    })
})