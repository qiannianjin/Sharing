layui.use('layer', function() {
    var layer = layui.layer;

    $(function() {

        function getDate(date) {
            date = new Date(date);
            return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
        }

        function findAnnouncement(params, container, sourceNode) {
            $.post("/jie/list", params, function(data){
                var list = data.dataList || {};
                if (list.length == 0) {
                    layer.msg("没有更多数据了!", {time: 3 * 1000});
                    return;
                }
                if (data.status == 0) {
                    $.each(list, function(i, n) {
                        var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
                        li.find("a").attr("href", "/jie/detail?informationid=" + n.informationid);
                        li.find("a").text(n.name);
                        container.append(li);
                    })
                }
            });
        }

        var changeStatus = function(url, params){

            $.post(url, params, function(data){
                if (data && data.status == 0) {
                    layer.msg(data.msg, {time: 3 * 1000,
                        end: function(){
                            if (data.action) {
                                window.location.href = data.action;
                            } else {
                                window.location.href = window.location.href;
                            }
                        }
                    });
                } else {
					layer.msg(data.msg, {time: 3 * 1000,
						end: function(){
					        if (data.action) {
                                window.location.href = data.action;
                            } else {
							    window.location.href = window.location.href;
                            }
						}
					});
                }
            });
        };

        //控制信息状态  重要程度，0普通; 1置顶; 2推荐; 3精华
        var important = $("#important").val();
        if (important == 0) { //普通
            $("#common").show();
        } else if (important == 1) { //置顶
            $("#stick").show();
        } else if (important == 2) { //推荐
            $("#recommend").show();
        } else if (important == 3) { //精华
            $("#essence").show();
        }
        var price = $("#price");
        if (price && price > 0) {//付费
            $("#fee").show();
        } else { //免费
            $("#free").show();
        }

        //控制编译和删除按钮
        var userCookie = $.cookie("user");
        if (userCookie && JSON.parse(decodeURIComponent(userCookie)).userid == $("#userid").val()) { //登陆 并且是作者 才显示编辑信息
            $("#LAY_jieAdmin span.layui-btn a").attr("href", "/jie/add?informationid=" + $("#informationid").val());
            $("#LAY_jieAdmin span.layui-btn").show();
            $("#delInfo").show();
            //给按钮添加点击事件
            $("#delInfo").click(function(){
                layer.confirm('删除提示', {btn: ['确认删除', '取消']}, function(index, layero){
                    var params = {};
                    params.informationid = $("#informationid").val();
                    params.status = 9;
                    changeStatus("/jie/change", params);
                });
            });
           //return;
        }

        //加载公告  温馨通道
        findAnnouncement({status:4, important:4}, $("#announcementcontainer"), document.getElementById("announcement_model"));

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
					$("#informationid").before(input_comments_id);
                });
                $('li[id^=li_] .del').click(function(){ //删除
                    var comments_id = $(this).parents("li[id^=li_]").find('.comments_id').val();
                   layer.confirm('删除提示', {btn: ['确认删除', '取消']}, function(index, layero){
                       var params = {commentid:comments_id, status:9}
                       changeStatus("/comments/change", params);
				   });
                });
                $('li[id^=li_] .jieda-accept').click(function(){

                });
            } else { //加载评论失败

            }
        });
    })
})