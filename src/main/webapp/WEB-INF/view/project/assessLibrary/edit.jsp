<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>考核题库修改</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="" lay-filter="dataForm">
    <div class="mainBox">
        <div class="mainBox">
            <div class="main-container">
                <input type="hidden" name="id">
                <div class="main-container">
                    <div class="layui-form-item">
                        <label class="layui-form-label">考核内容</label>
                        <div class="layui-input-block">
                            <input type="text" name="assessContent"  autocomplete="off" placeholder="请输入考核内容" class="layui-input"  lay-verify="required" disabled>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">考核分数</label>
                        <div class="layui-input-block">
                            <input type="number" name="contentScore"  autocomplete="off" placeholder="请输入考核分数" class="layui-input"  lay-verify="required|number">
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

    var initData;

    function initForm(data) {
        var jsonString = JSON.stringify(data);
        initData = JSON.parse(jsonString);
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

        form.val("dataForm", initData);
        /*表单验证*/
        form.verify({

        });

        /*表单提交*/
        form.on('submit(add)', function (data) {
            var url = BaseUrl + 'assessLibrary/update';
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
    });
</script>
</body>
</html>
