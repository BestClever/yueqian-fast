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
                    <input type="text" name="classeName" placeholder="" class="layui-input">
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
        <div class="layui-btn-group operateTable" style="margin: 5px">
            <button class="yueqian-btn yueqian-btn-primary yueqian-btn-md" data-type="add">
                <i class="layui-icon layui-icon-add-1"></i>
                新增
            </button>
            <button class="yueqian-btn yueqian-btn-success yueqian-btn-md" data-type="studentAdd">
                <i class="layui-icon layui-icon-add-1"></i>
                添加学生
            </button>
        </div>
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>

<!--操作-->
<script type="text/html" id="tableTool">
    <a class="yueqian-btn yueqian-btn-md yueqian-btn-warming"  lay-event="studentDetail">学生详情</a>
    <a class="yueqian-btn yueqian-btn-md yueqian-btn-warming"  lay-event="update">修改</a>
    <a class="yueqian-btn yueqian-btn-md yueqian-btn-danger"  lay-event="del">删除</a>
</script>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery','common', 'layer', 'layerCustom'], function () {
        var element = layui.element;
        table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            common = layui.common,
            form = layui.form;

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
                , {title: '操作', toolbar: '#tableTool', fixed: "right", width: 250}
            ]]
            , page: true

        });

        form.on("submit(search)", function (data) {
            myTalbe.reload({
                where: data.field,
                page: {curr: 1}
            });
            return false;
        });

        table.on("tool(tableFilter)", function (obj) {
            var data = obj.data;
            switch (obj.event) {
                case "update":
                    update(data);
                    break;
                case "del":
                    del(data);
                break;
                case "studentDetail":
                    studentDetail(data);
                break;
            }
        });

        $('.operateTable .yueqian-btn').on('click', function () {
            var type = $(this).data('type');
            switch (type) {
                case "add":
                    add();
                    break;
                case "studentAdd":
                    studentAdd();
                    break;
            }
        });

        function add() {
            layerCustom.openFull("添加用户", BaseUrl + 'classe/add', "700px", "550px", function (layero, index) {
                //给子页面赋值
                var body = layer.getChildFrame('body', index);
                //初始化学院选择下拉框
                var url =BaseUrl+"college/list";
                var param ={
                    limit:9999,
                    page:0
                }
                $.ajax({
                    url: url,
                    type: "get",
                    async: false,
                    data: param,
                    dataType: "json",
                    success: function (result) {
                        body.find("#collegeNameSelect").append("<option  value=>请选择学院</option>");
                        for (var i=0;i < result.data.length;i++) {
                            var item = result.data[i];
                            body.find("#collegeNameSelect").append("<option  value=" + item.id + ">" + item.collegeName + "</option>");
                        }
                    }
                });
            })
        }

        /**
         * 添加 班级的学生
         */
        function studentAdd() {
            var checkData = table.checkStatus("tableId").data;
            if (checkData.length === 0) {
                layerCustom.redCryMsg("至少选择一条数据");
                return;
            }
            layerCustom.open("添加学生",BaseUrl + 'classe/studentAdd?id='+checkData[0].id, "700px", "550px",function () {

            });
        }

        function update(data) {
            layerCustom.open("修改用户信息", BaseUrl + 'classe/edit', "700px", "450px", function (layero, index) {
                //给子页面赋值
                var body = layer.getChildFrame('body', index);
                //初始化学院选择下拉框
                var url =BaseUrl+"college/list";
                var param ={
                    limit:9999,
                    page:0
                }
                $.ajax({
                    url: url,
                    type: "get",
                    async: false,
                    data: param,
                    dataType: "json",
                    success: function (result) {
                        for (var i=0;i < result.data.length;i++) {
                            var item = result.data[i];
                            body.find("#collegeNameSelect").append("<option  value=" + item.id + ">" + item.collegeName + "</option>");
                        }
                    }
                });
                //给子页面赋值
                var iframeWin = window[layero.find("iframe")[0]["name"]];
                iframeWin.initForm(data);
            })
        }

        function del(data){
            layerCustom.confirm("是否要删除"+data.classeName,function () {
               var loading = layer.load();
                var params ={
                    id:data.id
                }
                $.ajax({
                    url: BaseUrl + "classe/remove",
                    data:params,
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layerCustom.greenLaughMsg(result.msg,function () {
                                myTalbe.reload();
                            });
                        } else {
                            layerCustom.redCryMsg(result.msg);
                        }
                    }
                })
            })
        }

        function studentDetail(data) {
            layerCustom.open("学生详情",BaseUrl + 'classe/studentDetail?classeId='+data.id, "700px", "550px",function () {

            });
        }


    });
</script>
</body>
</html>

