$(function(){
	
	$("#btnAdd").click(function(){
		var weburl = $("#webName").val();
		var userMakeName = $("#makeName").val();
		
		//console.log(weburl);
		//console.log(userMakeName);
		
		var apiurl = "";

        $.ajax({
            type:"GET",
            url:weburl, //访问的链接
            dataType:"json",  //数据格式设置为jsonp

            success:function(data){  //成功的回调函数
                alert(data);

            },
            error: function (e) {
                alert("error");
            }
        });

        $.get()

	});
})

