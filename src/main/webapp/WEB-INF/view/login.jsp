<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="${baseurl}/static/lib/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${baseurl}/static/css/login.css" media="all"/>
    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian-module/alIcon/iconfont.css" media="all"/>
</head>
<body>
<!-- Head -->
<div class="layui-row" style="height: 5%;">
    <div class="layui-col-sm12 layui-col-md12 login_center login_mar_01"></div>
</div>
<!-- Head End -->

<!-- Carousel -->
<div class="layui-row">
    <div class="layui-col-sm12 layui-col-md12">
        <div class="layui-carousel login_login_height" id="loginlogin" lay-filter="loginlogin">
            <div carousel-item="">
                <div>
                    <div class="login_login_cont"></div>
                </div>
                <div>
                    <img src="${baseurl}/static/images/carousel/01.jpg"/>
                </div>
                <div>
                    <div class="background">
                        <span></span><span></span><span></span>
                        <span></span><span></span><span></span>
                        <span></span><span></span><span></span>
                        <span></span><span></span><span></span>
                    </div>
                </div>
                <div>
                    <img src="${baseurl}/static/images/carousel/03.jpg"/>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Carousel End -->

<!-- Footer -->
<div class="layui-row">
    <div class="layui-col-sm12 layui-col-md12 login_center login_mar_01">

    </div>
</div>
<!-- Footer End -->

<!-- LoginForm -->
<div class="login_lofo_main">
    <fieldset class="layui-elem-field layui-field-title login_mar_02">
        <legend>欢迎登陆 - 后台管理系统</legend>
    </fieldset>
    <div class="layui-row layui-col-space15">
        <form class="layui-form login_pad_01" action="">
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-form-item">
                    <input type="text" name="loginName" lay-verify="required" autocomplete="off" placeholder="账号"
                           class="layui-input">
                    <i class="layui-icon layui-icon-username login_lofo_icon"></i>
                </div>
            </div>
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-form-item">
                    <input type="password" name="password" lay-verify="required|pass" autocomplete="off" placeholder="密码" class="layui-input">
                    <i class="layui-icon layui-icon-password login_lofo_icon"></i>
                </div>
            </div>
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-form-item" style="display: flex;">
                    <div class="layui-input-inline">
                        <input type="text" name="verifyCode" lay-verify="required" placeholder="请输入验证码"
                               autocomplete="off"
                               class="layui-input">
                        <i class="iconfont layui-icon-ali-yanzhengma login_lofo_icon"></i>
                    </div>
                    <div class="layui-input-inline">
                        <a href="javascript:void(0);" title="点击更换验证码">
                            <img src="${baseurl}/getVerifiyCode" class="imgcode"
                                 style="width: 110px;height: 38px"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm12 layui-col-md12">
                <button id="login" class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">立即登录</button>
            </div>
        </form>
    </div>
</div>
<!-- LoginForm End -->


<!-- Jquery Js -->
<script type="text/javascript" src="${baseurl}/static/js/jquery.min.js"></script>
<!-- Layui Js -->
<script type="text/javascript" src="${baseurl}/static/lib/layui/layui.js"></script>
<script type="text/javascript" src="${baseurl}/static/lib/yueqain/yueqian.all.js"></script>
<!-- Jqarticle Js -->
<script type="text/javascript" src="${baseurl}/static/lib/assembly/jqarticle/jparticle.min.js"></script>
<script>
    layui.use(['carousel', 'form', 'common','popup','element'], function () {
        var carousel = layui.carousel;
        var element = layui.element;
        var form = layui.form;
        var common = layui.common;
        var popup = layui.popup;

        //自定义验证规则
        form.verify({
            // userName: function(value){
            //     // if(value.length < 5){
            //     //     return '账号至少得5个字符';
            //     // }
            //     return true;
            // },
            pass: [/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格']
        });

        //监听提交
        form.on('submit(login)', function (data) {
            $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
            var url = BaseUrl + "login";

            // $.post(url,data.field,function (result) {
            //     if(result.success){
            //         window.location.href = BaseUrl+'index';
            //     }else {
            //         //设置登录
            //         $("#login").text("登录").removeAttr("disabled").removeClass("layui-disabled");
            //         //刷新验证码
            //         var url = BaseUrl + "getVerifiyCode?s=" + Math.random();
            //         $(".imgcode").attr("src", url);
            //         layerCustom.redCryMsg(result.msg);
            //     }
            // },'json');
            common.ajax.post(url, data.field, function (result) {
                if (result.success) {
                    window.location.href = BaseUrl + 'index';
                } else {
                    $("#login").text("立即登录").removeAttr("disabled").removeClass("layui-disabled");
                    var url = BaseUrl + "getVerifiyCode?s=" + Math.random();
                    $(".imgcode").attr("src", url);
                    popup.failure(result.msg);
                }
            });

            return false;
        });


        //设置轮播主体高度
        var login_login_height = $(window).height() / 1.3;
        var login_car_height = $(".login_login_height").css("cssText", "height:" + login_login_height + "px!important");


        //Login轮播主体
        carousel.render({
            elem: '#loginlogin'//指向容器选择器
            , width: '100%' //设置容器宽度
            , height: 'login_car_height'
            , arrow: 'always' //始终显示箭头
            , anim: 'fade' //切换动画方式
            , autoplay: true //是否自动切换false true
            , arrow: 'hover' //切换箭头默认显示状态||不显示：none||悬停显示：hover||始终显示：always
            , indicator: 'none' //指示器位置||外部：outside||内部：inside||不显示：none
            , interval: '5000' //自动切换时间:单位：ms（毫秒）
        });

        //监听轮播--案例暂未使用
        carousel.on('change(loginlogin)', function (obj) {
            var loginCarousel = obj.index;
        });

        //验证码切换
        $('.imgcode').click(function () {
            var url = BaseUrl + "getVerifiyCode?s=" + Math.random();
            $(".imgcode").attr("src", url);
        });

        //粒子线条
        $(".login_login_cont").jParticle({
            background: "rgba(0,0,0,0)",//背景颜色
            color: "#fff",//粒子和连线的颜色
            particlesNumber: 100,//粒子数量
            //disableLinks:true,//禁止粒子间连线
            //disableMouse:true,//禁止粒子间连线(鼠标)
            particle: {
                minSize: 1,//最小粒子
                maxSize: 3,//最大粒子
                speed: 30,//粒子的动画速度
            }
        });

    });

</script>
</body>
</html>

