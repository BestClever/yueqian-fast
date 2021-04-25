<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册页</title>
    <link rel="stylesheet" href="${baseurl}/lib/layui/css/layui.css">
    <link rel="stylesheet" href="${baseurl}/css/register.css">
</head>
<body>

<div class="login-main">
    <header class="layui-elip" style="width: 82%">注册页</header>

    <!-- 表单选项 -->
    <form class="layui-form">
        <div class="layui-input-inline">
        <!-- 用户名 -->
        <div class="layui-inline" style="width: 85%">
            <input type="text" id="userNo" name="studnetNo" required  lay-verify="required" placeholder="请输入用id" autocomplete="off" class="layui-input">
        </div>
        <!-- 对号 -->
        <div class="layui-inline">
            <i class="layui-icon" id="ri" style="color: green;font-weight: bolder;" hidden></i>
        </div>
        <!-- 错号 -->
        <div class="layui-inline">
            <i class="layui-icon" id="wr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
        </div>
    </div>
        <div class="layui-input-inline">
            <!-- 用户名 -->
            <div class="layui-inline" style="width: 85%">
                <input type="text" id="name" name="studnetName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="ri1" style="color: green;font-weight: bolder;" hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="wr1" style="color: red; font-weight: bolder;" hidden>ဆ</i>
            </div>
        </div>
        <!-- 密码 -->
        <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
                <input type="password" id="pwd" name="studnetPassword" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="pri" style="color: green;font-weight: bolder;" hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="pwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
            </div>
        </div>
        <!-- 确认密码 -->
        <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
                <input type="password" id="rpwd" name="newPwd" required  lay-verify="required" placeholder="请确认密码" autocomplete="off" class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rpri" style="color: green;font-weight: bolder;" hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rpwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="studnetSex" value="0" title="男" checked>
                <input type="radio" name="studnetSex" value="1" title="女" >
            </div>
        </div>

        <div class="layui-input-inline login-btn" style="width: 85%">
            <button type="submit" lay-submit lay-filter="sub" class="layui-btn">注册</button>
        </div>
        <hr style="width: 85%" />
        <p style="width: 85%"><a href="${baseurl}/frontend/login" class="fl">已有账号？立即登录</a></p>
    </form>
</div>


<script src="${baseurl}/lib/layui/layui.js"></script>
<script type="text/javascript" src="${baseurl}/lib/layui_custom/Hlayui.js"></script>
<script type="text/javascript">
    layui.use(['form','jquery','layer','layerCustom'], function () {
        var form   = layui.form;
        var $      = layui.jquery;
        var layer  = layui.layer;
        //添加表单失焦事件
        //验证表单
        $('#userNo').blur(function() {
            var userNo = $(this).val();

            //alert(user);
            $.ajax({
                url:BaseUrl+'frontend/checkUniqueUserNo',
                type:'post',
                dataType:'json',
                data:{studnetNo:userNo},
                //验证用户名是否可用
                success:function(result){
                    if (result.success) {
                        $('#ri').removeAttr('hidden');
                        $('#wr').attr('hidden','hidden');
                    } else {
                        $('#wr').removeAttr('hidden');
                        $('#ri').attr('hidden','hidden');
                        // layerCustom.redCryMsg(result.msg)
                        layer.msg(result.msg)
                    }
                }
            })

        });

        $('#name').blur(function() {
            if(($('#name').val().length==0)){
                //layer.msg('请输入合法密码');
                $('#ri1').removeAttr('hidden');
                $('#wr1').attr('hidden','hidden');
                layer.msg('请输入用户名');
            }else {
                $('#ri1').removeAttr('hidden');
                $('#wr1').attr('hidden','hidden');
            }
        });

        // you code ...
        // 为密码添加正则验证
        $('#pwd').blur(function() {
            var reg = /^[\w]{6,12}$/;
            if(!($('#pwd').val().match(reg))){
                //layer.msg('请输入合法密码');
                $('#pwr').removeAttr('hidden');
                $('#pri').attr('hidden','hidden');
                layer.msg('请输入合法密码');
            }else {
                $('#pri').removeAttr('hidden');
                $('#pwr').attr('hidden','hidden');
            }
        });

        //验证两次密码是否一致
        $('#rpwd').blur(function() {
            if($('#pwd').val() != $('#rpwd').val()){
                $('#rpwr').removeAttr('hidden');
                $('#rpri').attr('hidden','hidden');
                layer.msg('两次输入密码不一致!');
            }else {
                $('#rpri').removeAttr('hidden');
                $('#rpwr').attr('hidden','hidden');
            };
        });

        //
        //添加表单监听事件,提交注册信息
        form.on('submit(sub)', function() {
            $.ajax({
                url:BaseUrl+'frontend/register',
                type:'post',
                dataType:'json',
                data:{
                    studnetNo:$('#userNo').val(),
                    studnetName:$('#name').val(),
                    studnetPassword:$('#pwd').val(),
                    studnetSex:$('input:radio:checked').val()
                },
                success:function(result){
                    if (result.success) {
                        layer.msg('注册成功');
                        location.href =BaseUrl+"frontend/login";
                    }else {
                        layer.msg('注册失败');
                    }
                }
            })
            //防止页面跳转
            return false;
        });

    });
</script>
</body>
</html>
