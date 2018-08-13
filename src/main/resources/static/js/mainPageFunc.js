//是否添加过房间
var hasliving = 0;

//房间长度
var len = 0;

//删除按键的开关
var deleteIsOn = 0;

//存web的数组
var websiteArray = new Array();

//用户名存储
var userName;

$(function() {
    console.log('ok');

    //
    $('[data-toggle="popover"]').popover(); // 工具提示和弹出框的装载

    //需要有一个ajax取出用户名的操作 这里需要同步 因为下面要拿这个账号
    getAndSetUserInfo();

    //账号所属的直播页面预加载
    getUserCollect($("#userInfo").text(), "douyu");

    //$(getHttp()).prependTo("#mycontentblock");

    //下拉选按钮
    $("#douyuSelect").click(function() {
        $("#inputnote").text("https://www.douyu.com/");
    });

    $("#huyaSelect").click(function() {
        $("#inputnote").text("http://www.huya.com/");
    });

    if($("#btn_ConfirmAdd").text()=="添加")

    //确认添加  //确认删除 合并
        $("#btn_ConfirmAdd").click(function() {
            if(deleteIsOn==0){ //deleteIsOn 为0是确认添加房间的事件
                //获取房间号
                var roomId = $("#iptContent").val();
                //console.log($("#iptContent").val());
                //开始拼凑json
                var data = {
                    "id": "",
                    "user": $("#userInfo").text(),
                    "userCollectWebsite": "https://www.douyu.com/" + roomId,
                    "userCollectWebsiteType": "douyu",
                    "userMakeName": "",
                    "webAPI": "http://open.douyucdn.cn/api/RoomApi/room/" + roomId
                };

                //添加斗鱼网站
                $.ajax({
                    type: "post",
                    url: "WebCollectAdd",
                    async: false,
                    data: JSON.stringify(data),
                    dataType: 'json',
                    contentType: "application/json",
                    success: function(data) {
                        if(data.statusCode == 0) {
                            console.log(JSON.stringify(data)); //目前样式不好看  之后要改
                            //值清空
                            $("#iptContent").val("");
                            //这是调用了关闭
                            $("#btn_AddClose").click();

                            if(len == 0 && hasliving == 0) { //有长度说明有收藏房间分成直播中和未直播
                                hasliving = 1;
                                len = 1;
                                $("#mycontentblock").append('<h3>直播中</h3><div class="row" id="living" ></div>');
                                $("#mycontentblock").append('<h3>未开播</h3><div class="row" id="notliving" ></div>');

                            }

                            writeRoom(data.result.webSiteName, //房间链接
                                data.result.data.room_thumb, //房间缩略图
                                data.result.data.room_name, //房间名
                                data.result.data.cate_name, //房间类名
                                data.result.data.owner_name, //房间拥有者名
                                data.result.data.hn,
                                data.result.data.room_status);
                            websiteArray.push(data.result.webSiteName);
                            console.log(websiteArray);
                        } else {

                            alert(JSON.stringify(data.result));
                            $("#iptContent").val("");
                            //alert(data.result);
                        }
                    },
                    error: function() {
                        console.log("error");
                    }

                });

            }
            //删除事件
            else{ //这里是 删除窗口打开的事件
                var isAbleToDelete = 0 // 表示存在这个房间 是可以删除的
                var arrlen = websiteArray.length;
                var id = $("#iptContent").val(); //这里id可能为房间号 可能为主播id
                var webName = $("#inputnote").text()+id;
                console.log(webName);
                //这是如果输入的是房间名的情况
                for(var i = 0 ; i <arrlen;i++){
                    if(webName == websiteArray[i]){
                        isAbleToDelete = 1;
                    }
                }

                //这是输入是主播ID的情况
                var nikname = "#"+id;
                if ($(nikname).attr("href")!=null) {
                    isAbleToDelete = 2;
                    //console.log($(nikname).attr("href"));
                }else{
                    //console.log("null!");
                }

                //这是可以删除的情况
                if(isAbleToDelete ==1){
                    websiteArray.push(webName); //把要删除的web信息存入web数组
                    $('#delcfmModel').modal(); //提示确认信息！
                }else if(isAbleToDelete == 2){
                    websiteArray.push($(nikname).attr("href")); //把要删除的web信息存入web数组
                    $('#delcfmModel').modal(); //提示确认信息！
                }else{//这是不能删除的情况
                    alert("房间不存在！");
                }

            }
        });

    $("#btnCancel").click(function(){
        //取消事件点击时要把数组中的数据pop掉
        websiteArray.pop();
    });

    $("#btnConfirm").click(function(){
        var webName = websiteArray.pop(); //获取要删除的房间号
        console.log(webName);
        var jsonData ={
            "id": "",
            "user": userName,
            "userCollectWebsite":webName,
            "userCollectWebsiteType": "",
            "userMakeName": "",
            "webAPI": ""
        };

        $.ajax({
            type: "post",
            url: "deleteWeb",
            async: false,
            data: JSON.stringify(jsonData),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){
                var tipName = document.getElementById(webName);
                if(data.status=="1"){ //这是后台已经删除成功的情况
                    tipName.remove();
                }
            },
            error:function(){
                console.log("error!");
            }
        })

        $("#btn_AddClose").click();
    });

    //删除和添加合并
    //添加房间的超链接点击事件
    $("#aClick").click(function() {
        deleteIsOn = 0;
        $("#iptContent").val("");
        $("#btn_ConfirmAdd").text("添加");
        $("#exampleModalLabel").text("添加收藏");
        $("#iptContent").attr("placeholder","请输入房间号:");
    })

    //删除房间的超链接点击事件
    $("#aDelete").click(function(){
        deleteIsOn = 1;
        $("#iptContent").val("");
        $("#btn_ConfirmAdd").text("删除");
        $("#exampleModalLabel").text("选择删除");
        $("#iptContent").attr("placeholder","请输入房间号或者主播ID");
    })

    $("#btnAdd").click(function(){
        $("#aClick").click();
    })

    $("#btn_AddClose").click(function(){
        $("#iptContent").val("");
    })
});

//获取斗鱼的直播房间并加载
function getUserCollect(user, type) {

    var data = {
        "accountName": $("#userInfo").text(), //这里明天要盖掉 $()
        "webType": "douyu"
    };

    $.ajax({
        type: "post",
        url: "getWebPage",
        async: false,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            //console.log(JSON.stringify(data));

            if(data.result != null) {
                len = data.result.length;
            } else {
                len = 0;
            }

            if(len > 0 && hasliving == 0) { //有长度说明有收藏房间分成直播中和未直播
                hasliving = 1;
                $("#mycontentblock").append('<h3>直播中</h3><div class="row" id="living" ></div>');
                $("#mycontentblock").append('<h3>未开播</h3><div class="row" id="notliving" ></div>');

            }

            for(var i = 0; i < len; i++) {

                writeRoom(data.result[i].webSiteName, //房间链接
                    data.result[i].data.room_thumb, //房间缩略图
                    data.result[i].data.room_name, //房间名
                    data.result[i].data.cate_name, //房间类名
                    data.result[i].data.owner_name, //房间拥有者名
                    data.result[i].data.hn, //房间热度
                    data.result[i].data.room_status); //房间状态
                websiteArray.push(data.result[i].webSiteName);
            }
            console.log(len);

        },
        error: function() {
            console.log("error");
        }

    });
}

//写开播中的房间 参数依次为 房间链接 房间缩略图  房间名称 房间所属类型  主播名称 热度 房间直播状态
//
function writeRoom(webSiteName, room_thumb, room_name, cate_name, owner_name, hn, room_status) {

    var id;

    if(room_status == "1") {
        id = "#living";
    } else {
        id = "#notliving";
    }

    var httpstr = '<div class="col-md-3" id='+
        webSiteName +//这个框的id根据删除用
        '><a href=' +
        webSiteName + //"https://www.douyu.com/9999"
        ' id=' +
        owner_name +
        '><!-- 这里可以插入地址  --><img src="' +
        room_thumb + //"https://rpic.douyucdn.cn/asrpic/180808/9999_2242.jpg"
        '" width="250px" height="160px"><div class="boxa"><p class="boxa-l" style="color: rgb(49,49,49);">' +
        room_name + //有了凌仕 这波我很强！
        '</p><p class="boxa-r" style="color: rgb(255, 102, 0);">' +
        cate_name + //DOTA2
        '</p></div><div class="boxa" style="color: rgb(120,120,120);"><p class="boxa-l"'+
        ' style="width: 170px;"><span class="glyphicon glyphicon-user id='+
        owner_name+'1'  +  //删除用的id
        '"></span>' +
        owner_name + //yyfyyf
        '</p><p class="boxa-r" style="width: 80px;" style="color: rgb(120,120,120);"><span class="glyphicon glyphicon-fire"></span>' +
        (parseFloat(hn) / 10000).toFixed(1) + //140
        '万</p></div></a></div>';
    $(id).append(httpstr);
}

//获取账号信息 并且放在页面上
function getAndSetUserInfo() {

    $.ajax({
        type: "get",
        url: "getUserInfo",
        async: false,
        dataType: "json",

        success: function(data) {
            if(data.status == "1") {
                console.log("无用户")
            } else {
                userName = data.result;
                $("#userInfo").text(data.result);

            }
        }
    });

}