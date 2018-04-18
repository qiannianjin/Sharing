$(function() {
	
	var typeid = $("#typeidhide").val();
	
	$.get("/type/common?status=1", function(data){
		var list = data.dataList || {};
		if (data.status == 0) {
			var sourceNode = document.getElementById("model");
			$.each(list, function(i, n) {
				var option = $('<option value="' + n.id + '">' + n.name + '</option>');
				$("select[name=typeid]").append(option);
			});
			//设置默认选中
			if (typeid) {
				$("#typeid").val(typeid)
			}
			layui.use('form', function(){
				// 动态添加内容 layui 自动渲染失败，需要手动渲染
				var form = layui.form;
			    form.render();
			});
		}
	});
	
	
	
})