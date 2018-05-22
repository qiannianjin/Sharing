layui.use('layer', function(){
    var layer = layui.layer;

    $(function() {
        $.get("/type/common?status=1", function(data){
            var list = data.dataList || {};
            if (data.status == 0) {
                var sourceNode = document.getElementById("model");
                $.each(list, function(i, n) {
                    var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.informationtypeid, "style":"", "id":"li_" + n.informationtypeid});
                    li.find("a").attr("href", "/jie/index?informationtypeid=" + n.informationtypeid).text(n.name);
                    $("div.layui-container>ul.layui-clear li#model").before(li);
                });
            }
        });

        function findInformation(params, container, sourceNode) {

            $.post("/jie/list", params, function(data){
                var list = data.dataList || {};
                if (list.length == 0) {
                    layer.msg("没有更多数据了!", {time: 3 * 1000});
                    return;
                }
                if (data.status == 0) {
//				var sourceNode = document.getElementById("info_model");
                    $.each(list, function(i, n) {
                        var li = $(sourceNode.cloneNode(true)).attr({"class":"li_" + n.id, "style":"", "id":"li_" + n.id});
                        li.find("a.fly-avatar img").attr("src", n.avatar).attr("alt", n.nickname);
                        li.find("h2 a:eq(1)").attr("href", "/jie/detail?informationid=" + n.informationid)
                            .text(n.name);
                        li.find("div.fly-list-info a cite").text(n.nickname);
                        var createtime = new Date(n.createtime);
                        var now = new Date();
                        if ((now.getTime() - createtime.getTime()) < 10 * 60 * 1000) {
                            var showtime  = "刚刚"
                        } else {
                            var showtime = createtime.getFullYear() + "-" + (createtime.getMonth()+1) + "-" + createtime.getDate();
                        }
                        li.find("div.fly-list-info span:eq(0)").text(showtime);
                        li.find("div.fly-list-info>span.fly-list-kiss").html('<i class="iconfont icon-kiss"></i> ' + n.price);
                        if (n.price!=null && n.price!=undefined && n.price == 0) {
//						li.find("a.wealth").attr("class", li.find("a.wealth").attr("class") + " layui-bg-red").text("免费");
                            li.find("a.wealth").text("免费");
                        }
                        if (n.important == 1) { //置顶
                            li.find("div.fly-list-badge span.layui-badge:eq(0)").show();
                        } else if (n.important == 2) { //推荐
                            li.find("div.fly-list-badge span.layui-badge:eq(1)").show();
                        } else if (n.important == 3) { //精华
                            li.find("div.fly-list-badge span.layui-badge:eq(2)").show();
                        }
//					$("ul.fly-list:eq(0)").append(li);
//					$("ul.fly-list:eq(1)").append(li);
                        container.append(li);
                    });
                }
            });
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

        function checkUserSign() {
            var userCookie = $.cookie("user");
            if (userCookie) {
                var user = JSON.parse(decodeURIComponent(userCookie));
                if (user.userid) {  //登陆
                    $.post("/sign/check", {userid: user.userid}, function(data){
                        if (data && data.status == 0) {
                            data = data.data;
                            //设置 连续签到 天数 和 今日应该获取 S币值
                            $("span.fly-signin-days cite").text(data.days);  //连续签到 天数
                            $("div.fly-signin-main>span>cite").text(data.points);
                            if (data.signed) { //已经签到过了
                                $("#LAY_signin").addClass('layui-btn-disabled');
                            } else { //今日还没有签到
                                $("#LAY_signin").removeClass('layui-btn-disabled');
                            }
                        }
                    });
                }
                return;
            }
        }

        //首页加载 置顶数据
        var params = {status:1, important:1};
        findInformation(params, $("ul.fly-list:eq(0)"), document.getElementById("info_model"));

        //综合
        var comprehensiveParams = {status:1};
        var pageNum = 1;
        var pageSize = 8;
        comprehensiveParams.pageNum = pageNum;
        comprehensiveParams.pageSize = pageSize;
        findInformation(comprehensiveParams, $("ul.fly-list:eq(1)"), document.getElementById("info_model"));

        //加载公告  温馨通道
        findAnnouncement({status:4, important:4}, $("#announcementcontainer"), document.getElementById("announcement_model"));

        //检测是否已经签到
        checkUserSign();


        //更多求解
        $(".laypage-next").click(function(){
            pageNum = pageNum + 1;
            var params = {};
            // layui-this
            var importantText = $('#comprehensive[class="layui-this"], #recommend[class="layui-this"], #essence[class="layui-this"]').text();
            if (importantText == '综合') {

            } else if (importantText == '推荐') {
                params.important = 2;
            } else if (importantText == '精华') {
                params.important = 3;
            }
            params.pageNum = pageNum;
            params.pageSize = pageSize;
            findInformation(params, $("ul.fly-list:eq(1)"), document.getElementById("info_model"));
        });

        //给链接添加 点击事件
        //综合链接
        $("#comprehensive").click(function(){
            //清除原有li
            $("ul.fly-list:eq(1)").empty();
            pageNum = 1;
            findInformation({status:1, pageNum:pageNum, pageSize:pageSize}, $("ul.fly-list:eq(1)"), document.getElementById("info_model"));
            //添加链接选中效果
            $("#recommend, #essence").removeAttr("class");
            $("#comprehensive").attr("class", "layui-this");
        });
        //推荐链接
        $("#recommend").click(function(){
            //清除原有li
            $("ul.fly-list:eq(1)").empty();
            pageNum = 1;
            findInformation({status:1, important:2, pageNum:pageNum, pageSize:pageSize}, $("ul.fly-list:eq(1)"), document.getElementById("info_model"));
            //添加链接选中效果
            $("#comprehensive, #essence").removeAttr("class");
            $("#recommend").attr("class", "layui-this");
        });
        //精华链接
        $("#essence").click(function(){
            //清除原有li
            $("ul.fly-list:eq(1)").empty();
            pageNum = 1;
            findInformation({status:1, important:3, pageNum:pageNum, pageSize:pageSize}, $("ul.fly-list:eq(1)"), document.getElementById("info_model"));
            //添加链接选中效果
            $("#comprehensive, #recommend").removeAttr("class");
            $("#essence").attr("class", "layui-this");
        });
    })
});