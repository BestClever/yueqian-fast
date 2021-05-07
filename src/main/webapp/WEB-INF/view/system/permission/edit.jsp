<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>权限修改</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="" lay-filter="dataForm">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <div class="layui-form-item">
                    <input type="hidden" name="id">
                    <label class="layui-form-label layui-required">上级菜单</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="parentId" id="parentId">
                        <input type="text" name="parentName" id="parentName" autocomplete="off" placeholder="请输入上级部门名称" class="layui-input layui-disabled" lay-verify="required" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">权限类型</label>
                    <div class="layui-input-block">
                        <input type="radio" lay-filter="permissionType" name="permissionType" value="0" title="目录">
                        <input type="radio" lay-filter="permissionType" name="permissionType" value="1" title="菜单" checked>
                        <input type="radio" lay-filter="permissionType" name="permissionType" value="2" title="按钮">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">菜单名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="permissionName" autocomplete="off" placeholder="请输入菜单名称" class="layui-input" lay-verify="required" disabled>
                    </div>
                </div>
                <div class="layui-form-item" id="permissionUrlItem">
                    <label class="layui-form-label layui-required">请求地址</label>
                    <div class="layui-input-block">
                        <input type="text" name="permissionUrl" autocomplete="off" placeholder="请输入请求地址" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" id="permissionCodeItem">
                    <label class="layui-form-label">权限标识</label>
                    <div class="layui-input-block">
                        <input type="text" name="permissionCode" autocomplete="off" placeholder="请输入权限标识" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">图标</label>
                    <div class="layui-input-block">
                        <input type="text" name="icon" autocomplete="off" placeholder="请输入图标" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-block">
                        <input type="radio" name="isAvailable" value="0" title="正常" checked>
                        <input type="radio" name="isAvailable" value="1" title="停用">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-block">
                        <input type="number" name="sortNum" lay-verify="title" autocomplete="off" placeholder="请输入排序号"
                               class="layui-input">
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

    function initForm(data) {
        var jsonString = JSON.stringify(data);
        var initData = JSON.parse(jsonString);
        setFormVal(initData);
        (
            function () {
                if (initData.permissionType == '0') {
                    $("#permissionUrlItem").hide();
                    $("#permissionCodeItem").hide();
                    $("#permissionUrl").val("");
                    $("#permissionCode").val("");
                } else if (initData.permissionType == '1') {
                    $("#permissionUrlItem").show();
                    $("#permissionCodeItem").show();
                    $("#openTypeItem").show();
                } else if (initData.permissionType == '2') {
                    $("#permissionUrlItem").hide();
                    $("#permissionCodeItem").show();
                    $("#permissionUrl").val("");
                }
            }
        )();
    }

    layui.use(['form', 'jquery', 'dtree', 'element', 'layer', 'laydate', 'common', 'layerCustom'], function () {
        var element = layui.element;
        $ = layui.jquery,
            dtree = layui.dtree,
            layer = layui.layer,
            layerCustom = layui.layerCustom,
            laydate = layui.laydate,
            common = layui.common,
            form = layui.form;

        window.setFormVal = function(initData){
            form.val("dataForm", initData);
        }
        /*表单验证*/
        form.verify({
            collegeName: function (value) {
                var url = BaseUrl + 'college/existCollegeName';
                var param = {
                    collegeName: value
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
            var url = BaseUrl + 'sysPermission/update';
            //保存接口
            $.post(url, data.field, function (result) {
                if (result.success) {
                    layerCustom.greenLaughMsg(result.msg, function () {
                        //刷新父页面的表格
                        // parent.$(".layui-laypage-btn")[0].click();
                        parent.renderTreeTalbe();
                        //先得到当前iframe层的索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭弹出框
                        parent.layer.close(index);
                    })
                } else {
                    layer.msg(result.msg)
                    return;
                }
            }, 'json');
            return false;
        });

        form.on("radio(permissionType)", function () {
            if (this.value == '0') {
                $("#permissionUrlItem").hide();
                $("#permissionCodeItem").hide();
                $("#permissionUrl").val("");
                $("#permissionCode").val("");
            } else if (this.value == '1') {
                $("#permissionUrlItem").show();
                $("#permissionCodeItem").show();
                $("#openTypeItem").show();
            } else if (this.value == '2') {
                $("#permissionUrlItem").hide();
                $("#permissionCodeItem").show();
                $("#permissionUrl").val("");
            }
        });

        form.on('select(LAY-Select)', function (data) {
            var text = data.elem[data.elem.selectedIndex].text;
            $("#roleName").val(text);
        });

        //部门选择
        $("#parentName").click(function () {
            if ($("#parentId").val()==="0") {
                layerCustom.redCryMsg("主菜单不能选择");
                return
            }
            layerCustom.open("权限选择", BaseUrl + 'sysPermission/permissionselect', "400px", "80%", function (layero, index) {
            });
        });
    });
</script>
</body>
</html>
