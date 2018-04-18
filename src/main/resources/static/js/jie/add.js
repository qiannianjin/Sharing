$(function() {
		
	$.get("/type/common?status=1", function(data){
		var list = data.dataList || {};
		if (data.status == 0) {
			var sourceNode = document.getElementById("model");
			$.each(list, function(i, n) {
				var option = $('<option value="' + n.id + '">' + n.name + '</option>');
				$("select[name=typeid]").append(option);
				var dd = $('<dd class="" lay-value="' + n.id + '">' + n.name + '</dd>');
				$("div.layui-unselect dl.layui-anim").append(dd);
			});
		}
	});
	
layui.define('fly', function(exports){
	var form = layui.form;
	//监听专栏选择
	form.on('select(column)', function(obj){
	    var value = obj.value
	    ,elemQuiz = $('#LAY_quiz')
	    ,tips = {
	      tips: 1
	      ,maxWidth: 250
	      ,time: 10000
	    };
	    elemQuiz.addClass('layui-hide');
	    if(value === '0'){
	      layer.tips('下面的信息将便于您获得更好的答案', obj.othis, tips);
	      elemQuiz.removeClass('layui-hide');
	    } else if(value === '99'){
	      layer.tips('系统会对【分享】类型的帖子予以飞吻奖励，但我们需要审核，通过后方可展示', obj.othis, tips);
	    }
	  });
	})
	exports('jie', null);
});