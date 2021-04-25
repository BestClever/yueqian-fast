<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>学院信息修改</title>

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
                        <label class="layui-form-label layui-required">批次名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="batchName"  autocomplete="off" placeholder="请输入登录名称" class="layui-input"  readonly>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">开始时间</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="startTime" id="startTime" placeholder="yyyy-MM-dd HH:mm:ss">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">结束时间</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="endTime" id="endTime" placeholder="yyyy-MM-dd HH:mm:ss">
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

        //开始日期时间选择器
        laydate.render({
            elem: '#startTime'
            ,type: 'datetime'
        });

        //结束日期时间选择器
        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
        });
        /*表单验证*/
        form.verify({

        });

        /*表单提交*/
        form.on('submit(add)', function (data) {
            var url = BaseUrl + 'assessBatch/update';
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

        form.on('select(LAY-Select)', function(data){
           var text = data.elem[data.elem.selectedIndex].text;
           $("#roleName").val(text);
        });

    });
</script>
</body>
</html>
