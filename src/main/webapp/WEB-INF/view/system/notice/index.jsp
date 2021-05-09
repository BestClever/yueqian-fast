<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>消息通知表列表</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="" placeholder="" class="layui-input">
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
        </div>
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>

<!--操作-->
<script type="text/html" id="tableTool">
    <a class="yueqian-btn yueqian-btn-warming yueqian-btn-md" lay-event="viewDetail">
        <i class="iconfont layui-icon-ali-kejian"></i>
        查看
    </a>
    <a class="yueqian-btn yueqian-btn-warming yueqian-btn-md" lay-event="update">
        <i class="iconfont layui-icon-ali-xiugai"></i>
        修改
    </a>
    <a class="yueqian-btn yueqian-btn-danger yueqian-btn-md" lay-event="del">
        <i class="iconfont layui-icon-ali-shanchu1"></i>
        删除
    </a>
</script>

<%--公告类型--%>
<script type="text/html" id="noticeTypeTps">
    {{# if(d.noticeType=='1'){ }}
    <span class="layui-bg-green yueqian-span">通知</span>
    {{# }else if(d.noticeType=='2'){ }}
    <span class="layui-bg-blue yueqian-span">公告</span>
    {{# } }}
</script>


<%--公告类型--%>
<script type="text/html" id="isNormalTps">
    {{# if(d.isNormal=='0'){ }}
    <span class="layui-bg-green yueqian-span">正常</span>
    {{# }else if(d.isNormal=='1'){ }}
    <span class="layui-bg-orange yueqian-span">关闭</span>
    {{# } }}
</script>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'layerCustom'], function () {
        var element = layui.element,
            table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            form = layui.form;

        var myTalbe = table.render({
            elem: '#tableId'
            , url: BaseUrl + 'sysNotice/list'
            , skin: 'line'
            , cols: [[
            	{type: 'checkbox'},
                {field: 'noticeTitle', title: '公告标题'},
                {field: 'noticeType', title: '公告类型',templet: "#noticeTypeTps"},
                {field: 'isNormal', title: '公告状态',templet: "#isNormalTps"},
                {field: 'createTime', title: '创建时间'},
				{title: '操作', templet: '#tableTool', width: 250, align: 'center'}
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
                case "viewDetail":
                    viewDetail(data);
                    break;
                case "update":
                    update(data);
                    break;
                case "del":
                    del(data);
                    break;
            }
        });

        $('.operateTable .yueqian-btn').on('click', function () {
            var type = $(this).data('type');
            switch (type) {
                case "add":
                    add();
                    break;
            }
        });
        


        function add() {
            layerCustom.open("添加公告信息", BaseUrl + 'sysNotice/add', "700px", "550px", function (layero, index) {
            })
        }

        function viewDetail(data) {
            layerCustom.open("查看公告信息", BaseUrl + 'sysNotice/viewNotice?id='+data.id, "950px", "580px", function (layero, index) {
            })
        }

        function update(data) {
            layerCustom.open("修改公告信息", BaseUrl + 'sysNotice/edit', "700px", "450px", function (layero, index) {
                //给子页面赋值
                var iframeWin = window[layero.find("iframe")[0]["name"]];
                iframeWin.initForm(data);
            })
        }

        function del(data) {
            layerCustom.confirm("是否要删除该条数据", function () {
                var loading = layer.load();
                var params = {
                    id: data.id
                }
                $.ajax({
                    url: BaseUrl + "sysNotice/remove",
                    data: params,
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layerCustom.greenLaughMsg(result.msg, function () {
                                myTalbe.reload();
                            });
                        } else {
                            layerCustom.redCryMsg(result.msg);
                        }
                    }
                })
            })
        }
    });
</script>
</body>
</html>

