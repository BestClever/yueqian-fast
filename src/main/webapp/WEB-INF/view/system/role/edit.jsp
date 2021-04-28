<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>角色表修改</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="" lay-filter="dataForm">
    <div class="mainBox">
        <div class="mainBox">
            <div class="main-container">
                <input type="hidden" name="id">
                <div class="main-container">
                    <%--填写想要新增的字段--%>
                    <div class="layui-form-item">
                        <label class="layui-form-label layui-required">角色名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="roleName" autocomplete="off" placeholder="请输入" class="layui-input"
                                   readonly>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label layui-required">角色编码</label>
                        <div class="layui-input-block">
                            <input type="text" name="roleCode" autocomplete="off" placeholder="请输入角色编码"
                                   class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">角色描述</label>
                        <div class="layui-input-block">
                            <input type="text" name="roleDescription" autocomplete="off" placeholder="请输入"
                                   class="layui-input">
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
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                        <textarea type="text" name="remark" autocomplete="off" placeholder="请输入"
                                  class="layui-textarea"></textarea>
                        </div>
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
            <button type="button" class="yueqian-btn yueqian-btn-sm">
                <i class="iconfont layui-icon-ali-guanbi1"></i>
                关闭
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
        window.setFormVal = function (initData) {
            form.val("dataForm", initData);
        };
        /*表单验证*/
        form.verify({});

        /*表单提交*/
        form.on('submit(add)', function (data) {
            var url = BaseUrl + 'sysRole/update';
            //保存接口
            $.post(url, data.field, function (result) {
                if (result.success) {
                    layerCustom.greenLaughMsg(result.msg, function () {
                        //先得到当前iframe层的索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭弹出框
                        parent.layer.close(index);
                        //刷新父页面的表格
                        parent.layui.table.reload('tableId');
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
            $("#").val(text);
        });

    });
</script>
</body>
</html>
