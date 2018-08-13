$(function() {
    var url;
    //console.log(hex_md5(hex_md5("admin")))

    $("#btnlogin").click(function() {
        //alert("ok");

        var $btn = $(this).button('loading');

        var data = {
            "accountName": $("#inputAccountName").val(),
            "password": hex_md5(hex_md5( $("#inputPassword").val())),
            "userEmailAddress":  $("#inputAccountName").val(),
            "accountStatus": ""
        };
        var flag = 0;

        $.ajax({
            type: "post",
            url: "LoginController",
            async: false,
            data:JSON.stringify(data),
            dataType:'json',
            contentType: "application/json",
            success:function(data){
                //alert(JSON.stringify(data));
                if(data.statusCode == 1){
                    alert("账号不存在");
                    //bootbox.alert("账号不存在！","no msg!");
                    $btn.button('reset');
                    location.href="Login";
                }
                if(data.statusCode==2){
                    alert("密码错误");
                    $btn.button('reset');
                    location.href="Login";
                }
                if(data.statusCode==0){
                    flag=1;
                    //alert("匹配成功");
                    $btn.button('reset');
                    location.href="mainpage";
                }
            },
            error:function(){
                $btn.button('reset');
                console.log("error");
            }

        });

    });


})