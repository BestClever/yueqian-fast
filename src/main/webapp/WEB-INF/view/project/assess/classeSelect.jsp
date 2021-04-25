<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>班级列表</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">班级名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="classeName" placeholder="请输入班级名称" class="layui-input">
                </div>
                <button class="yueqian-btn yueqian-btn-md yueqian-btn-primary" lay-submit lay-filter="search">
                    <i class="layui-icon layui-icon-search"></i>
                    查询
                </button>
                <button type="reset" class="yueqian-btn yueqian-btn-md">
                    <i class="layui-icon layui-icon-refresh"></i>
                    重置
                </button>
            </div>
        </form>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body">
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>
<div class="bottom">
    <div class="button-container">
        <button type="submit" class="yueqian-btn yueqian-btn-primary yueqian-btn-sm" id="LAY-submit">
            <i class="layui-icon layui-icon-ok"></i>
            确定
        </button>
    </div>
</div>


<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'common', 'layerCustom'], function () {
        var element = layui.element;
        table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            form = layui.form;
        common = layui.common;

        var myTalbe = table.render({
            elem: '#tableId'
            , url: BaseUrl + 'classe/list'
            , skin: 'line'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'classeName', title: '班级名称'}
                , {field: 'classeSize', title: '班级人数'}
                , {field: 'classeNo', title: '班级编号'}
                , {field: 'classeGrade', title: '年级'}
                , {field: 'classeTeacheName', title: '班主任名称'}
                , {field: 'assistantName', title: '辅导员名称'}
            ]]
            , page: true
            , where: {
                userType:4
            }
        });

        form.on("submit(search)", function (data) {
            myTalbe.reload({
                where: data.field,
                page: {curr: 1}
            });
            return false;
        });

        $("#LAY-submit").on('click',function () {
            var checkData = table.checkStatus("tableId").data;
            if (checkData.length === 0) {
                layerCustom.redCryMsg("至少选择一条数据");
                return;
            }
            //设值 到父页面
            parent.$('#classeId').val(checkData[0].id);
            parent.$('#classeName').val(checkData[0].classeName);
            parent.$('#classeTeacherName').val(checkData[0].classeTeacheName);
            //先得到当前iframe层的索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭弹出框
            parent.layer.close(index);
        });
    });
</script>
</body>
</html>

