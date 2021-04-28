<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>用户信息添加</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">登录名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="loginName"  autocomplete="off" placeholder="请输入登录名称" class="layui-input"  lay-verify="required|loginName">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">用户名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName"  autocomplete="off" placeholder="请输入用户名称" class="layui-input"  lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">密码</label>
                    <div class="layui-input-block">
                        <input type="text" name="password" id="password"  autocomplete="off" placeholder="请输入密码" class="layui-input"  lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">确认密码</label>
                    <div class="layui-input-block">
                        <input type="text" name="confirmPassword"  autocomplete="off" placeholder="请输入密码" class="layui-input"  lay-verify="required|confirmPassword">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="1" title="男" checked>
                        <input type="radio" name="sex" value="2" title="女">
                        <input type="radio" name="sex" value="3" title="未知">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">角色</label>
                    <div class="layui-input-block">
                        <select name="userType" lay-verify="required" lay-filter="LAY-select" id="role" placeholder="请输入用户类型">
                            <option value="1">管理员</option>
                            <option value="2">老师</option>
                            <option value="3">辅导员</option>
                            <option value="4">学生</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" placeholder="请输入邮箱" lay-verify="email"  name="email" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学院名称</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="collegeName" id="collegeName">
                        <select name="collegeId" lay-verify="required" lay-filter="LAY-Select-collegeName" id="collegeNameSelect">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="yueqian-btn yueqian-btn-primary yueqian-btn-sm" lay-submit="" lay-filter="add">
                <i class="layui-icon layui-icon-ok"></i>
                提交
            </button>
            <button type="reset" class="yueqian-btn yueqian-btn-sm">
                <i class="layui-icon layui-icon-refresh"></i>
                重置
            </button>
        </div>
    </div>
</form>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>

    layui.use(['form', 'jquery', 'upload', 'element', 'layer', 'laydate', 'common', 'layerCustom'], function () {
        var element = layui.element;
        $ = layui.jquery,
            upload = layui.upload,
            layer = layui.layer,
            layerCustom = layui.layerCustom,
            laydate = layui.laydate,
            common = layui.common,
            form = layui.form;


        /*表单验证*/
        form.verify({
            confirmPassword:function (value, item) {
                var password = $("#password").val();
                if (!common.equals(value,password)){
                    return '两次密码输入不一致';
                }
            },
            loginName: function (value) {
                var url = BaseUrl + 'sysUser/existLoginName';
                var param = {
                    loginName: value
                }
                var returnMsg = '';
                $.ajax({
                    url: url,
                    type: "get",
                    async: false,
                    data: param,
                    dataType: "json",
                    success: function (result) {
                        if (!result.success) {
                            returnMsg = result.msg;
                        }
                    }
                });
                if (returnMsg) {
                    return returnMsg;
                }
            }
        });

        /*表单提交*/
        form.on('submit(add)', function (data) {
            var url = BaseUrl + 'sysUser/save';
            //保存接口
            $.post(url, data.field, function (result) {
                if (result.success) {
                    layerCustom.greenLaughMsg(result.msg, function () {
                        //先得到当前iframe层的索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭弹出框
                        parent.layer.close(index);
                        //刷新父页面的表格
                        parent.$(".layui-laypage-btn")[0].click();
                    })
                } else {
                    layer.msg(result.msg)
                    return;
                }
            }, 'json');

            return false;
        });

        form.on('select(LAY-Select)', function(data){
           var text = data.elem[data.elem.selectedIndex].text;
           $("#roleName").val(text);
        });

        form.on('select(LAY-Select-collegeName)', function(data){
            var text = data.elem[data.elem.selectedIndex].text;
            $("#collegeName").val(text);
        });
    });
</script>
</body>
</html>
