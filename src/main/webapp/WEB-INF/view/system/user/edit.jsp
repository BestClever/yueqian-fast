<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>用户信息修改</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="" lay-filter="dataForm">
    <div class="mainBox">
        <div class="main-container">
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" autocomplete="off" placeholder="请输入用户名称" class="layui-input"
                           lay-verify="required">
                </div>
                <label class="layui-form-label layui-required">登录名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginName" autocomplete="off" placeholder="请输入登录名称" class="layui-input"
                           lay-verify="required|loginName">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">部门名称</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="deptId" id="deptId">
                    <input type="text" name="deptName" id="deptName" autocomplete="off" placeholder="请输入部门名称" class="layui-input"
                           lay-verify="required">
                </div>
                <label class="layui-form-label layui-required">密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="password" id="password" autocomplete="off" placeholder="请输入密码"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="2" title="女">
                </div>
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-inline">
                    <input type="text" name="contactInformation" id="contactInformation" autocomplete="off" placeholder="请输入联系方式"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">
                    <input type="number" name="sortNum" autocomplete="off" placeholder="请输入排序" class="layui-input" >
                </div>
                <label class="layui-form-label">是否有效</label>
                <div class="layui-input-inline">
                    <input type="checkbox" checked="" value="0" name="isAvailable" id="isAvailable" lay-skin="switch" lay-filter="switchTest" lay-text="有效|无效">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block" id="roleDiv">
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

    function initForm(data) {
        var jsonString = JSON.stringify(data);
        var initData = JSON.parse(jsonString);
        setFormVal(initData);
    }

    layui.use(['form', 'jquery', 'upload', 'element', 'layer', 'laydate', 'common', 'layerCustom'], function () {
        var element = layui.element;
        $ = layui.jquery,
            upload = layui.upload,
            layer = layui.layer,
            layerCustom = layui.layerCustom,
            laydate = layui.laydate,
            common = layui.common,
            form = layui.form;

        /*初始化 表单*/
        window.setFormVal = function(initData){
            form.val("dataForm", initData);
        }

        window.formRender = function(){
            form.render('checkbox');
        };

        /*表单验证*/
        form.verify({});

        /*表单提交*/
        form.on('submit(add)', function (data) {
            var url = BaseUrl + 'sysUser/update';
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
                    layerCustom.redCryMsg(result.msg)
                    return;
                }
            }, 'json');

            return false;
        });

        form.on('select(LAY-Select)', function (data) {
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
