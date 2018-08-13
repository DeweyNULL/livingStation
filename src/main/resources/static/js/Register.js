$(function(){
    //注册传参数
    $("#btnreg").click(function(){
        //先做校验！

        //获取参数
        var datajson ={
            "invited":{
                "invitedCode":$("#inviteCode").val(),
                "isUsed":1
            },
            "account":{
                "accountName":$("#inputName").val(),
                "password":hex_md5(hex_md5($("#inputConfirmPassword").val())),
                "userEmailAddress":$("#inputEmail").val(),
                "accountStatus":0
            }
        };

        //ajax传输数据
        $.ajax({
            type:"post",
            url:"registerController",
            async:false,
            data:JSON.stringify(datajson),
            dataType:'json',
            contentType: "application/json",
            success:function(data){
                alert(JSON.stringify(data.result));
                //这里注册成功的情况要跳转到登录界面
                if (data.statusCode==0) {

                    location.href="Login";

                } else{//不成功的情况应该刷新页面
                    location.href="register";
                }

            },
            error:function(){
                alert("error!");
            }
        });
    });

    //确认密码框框 以后框框
    $("#inputConfirmPassword").focus(function(){

    }).blur(function(){
        var password1 = $("#inputPassword").val();
        var password2 = $("#inputConfirmPassword").val();

        if(password1!=password2){
            console.log("into compare!");
            //这里在后面加把×
            showPopover($("#inputConfirmPassword"),"前后输入的密码不一致！");
            //$(this).popover('show');

        }else{
            //removeAttrPopover("#inputConfirmPassword");
            //$(this).popover("hide");

        }

    });
    //用户名框移开事件 这里做一个 输入内容校验
    $("#inputName").blur(function(){
        var inputName = $("#inputName").val();
        if (inputName.match("^[a-zA-Z0-9]{6,20}$")) {
            console.log("匹配");
        }else{
            console.log("不匹配");
            showPopover($("#inputName"),"请输入6-20位的字母或小数");
        }
    })

    //密码的校验
    $("#inputPassword").blur(function(){
        var inputName = $("#inputPassword").val();
        if (inputName.match("^[a-zA-Z0-9]{6,20}$")) {
            console.log("匹配");
        }else{
            console.log("不匹配");
            showPopover($("#inputPassword"),"请输入6-20位的字母或小数");
        }
    })

})


//自制提示框 目前已经废弃
function addAttrPopover(id,content){
    $(id).attr({
        "data-toggle":"popover",
        "data-placement":"top",
        "data-content":content
    });
}

//自制提示框的移除 目前已经废弃
function removeAttrPopover(id){
    $(id).removeAttr("data-toggle");
    $(id).removeAttr("data-trigger");
    $(id).removeAttr("data-content");

}

//显示提示框！
function showPopover(target, msg) {
    target.attr("data-original-title", msg);
    $('[data-toggle="tooltip"]').tooltip();
    target.tooltip('show');
    target.focus();

    //2秒后消失提示框
    var id = setTimeout(
        function () {
            target.attr("data-original-title", "");
            target.tooltip('hide');
        }, 2000
    );
}