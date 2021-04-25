<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>题库生成</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <div class="main-container">
                    <div class="layui-form-item">
                        <input type="hidden" name="assessLibraryId" value="${id}">
                        <label class="layui-form-label">题目类型</label>
                        <div class="layui-input-block">
                            <select name="assessType">
                                <option value="">请选择题目类型</option>
                                <option value="1">老师</option>
                                <option value="2">辅导员</option>
                                <option value="3">学生</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <input type="hidden" name="batchName" id="batchName">
                        <label class="layui-form-label">批次名称</label>
                        <div class="layui-input-block">
                            <select lay-verify="required" name="batchNo" lay-filter="LAY-Select" id="batchNameSelect">

                            </select>
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

        });

        /*表单提交*/
        form.on('submit(add)', function (data) {
            var url = BaseUrl + 'assess/generateAssess';
            //保存接口
            $.post(url, data.field, function (result) {
                if (result.success) {
                    layerCustom.greenLaughMsg(result.msg, function () {
                        //刷新父页面的表格
                        // parent.$(".layui-laypage-btn")[0].click();
                        parent.layui.table.reload('tableId')
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

        form.on('select(LAY-Select)', function(data){
           var text = data.elem[data.elem.selectedIndex].text;
           $("#batchName").val(text);
        });

    });
</script>
</body>
</html>
