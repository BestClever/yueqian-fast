<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>权限选择</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <div class="main-container">
                    <div class="layui-input-inline">
                        <input class="layui-input" id="searchInput" value="" placeholder="输入查询节点内容...">
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-normal" id="search_btn">查询</button>
                    </div>
                    <ul id="permission-tree" class="dtree" data-id="0" style="padding-top: 10px"></ul>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container operateTable">
            <button type="button" class="yueqian-btn yueqian-btn-primary yueqian-btn-sm" data-type="confirm">
                <i class="layui-icon layui-icon-ok"></i>
                提交
            </button>
            <button type="button" class="yueqian-btn yueqian-btn-sm" data-type="close">
                <i class="layui-icon layui-icon-close"></i>
                关闭
            </button>
        </div>
    </div>
</form>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>

    layui.use(['form', 'jquery', 'dtree', 'element', 'layer', 'laydate', 'common', 'layerCustom'], function () {
        var element = layui.element;
        $ = layui.jquery,
            dtree = layui.dtree,
            layer = layui.layer,
            layerCustom = layui.layerCustom,
            laydate = layui.laydate,
            common = layui.common,
            form = layui.form;

        var DTree = dtree.render({
            elem: "#permission-tree",
            url: BaseUrl + "sysRolePermission/getRolePermission?roleId="+"${roleId}",
            selectInputName: {nodeId: "id", context: "permissionName"},
            skin:"laySimple",
            checkbar: true,
            initLevel: "1",
            checkbarType: "no-all",
            dataStyle: "layuiStyle",
            dataFormat: "list",
            response: {
                message: "msg",
                statusCode: 0,
                treeId: "id", //节点ID（必填）
                parentId: "parentId", //父节点ID（必填）
                title: "permissionName"
            },
            done: function (data, obj, first) {  //使用异步加载回调
                var reportId = $("#parentId").val();
                dtree.dataInit("permission-tree", reportId); // 初始化值
                $("#search_btn").unbind("click");
                $("#search_btn").click(function () {
                    var value = $("#searchInput").val();
                    if (value) {
                        var flag = DTree.searchNode(value); // 内置方法查找节点
                        if (!flag) {
                            layer.msg("该名称节点不存在！", {icon: 5});
                        }
                    } else {
                        DTree.menubarMethod().refreshTree(); // 内置方法刷新树
                    }
                    return false;
                });
            }
        });

        $('.operateTable .yueqian-btn').on('click', function () {
            var type = $(this).data('type');
            switch (type) {
                case "confirm":
                    confirm();
                    break;
                case "close":
                    close();
                    break;
            }
        });

        function confirm() {
            var param = dtree.getCheckbarNodesParam("permission-tree");
            var ids =[];
            for (var i = 0; i < param.length; i++) {
                var id = param[i].nodeId;
                ids.push(id);
            }

            var url =BaseUrl + "sysRolePermission/saveRelationship";
            var param={
                ids:ids.toString(),
                roleId:"${roleId}"
            };
            common.ajax.post(url,param,function (result) {
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
                }else {
                    layerCustom.redCryMsg(result.msg)
                }
            })
        }

        function close() {
            //先得到当前iframe层的索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭弹出框
            parent.layer.close(index);
        }
    });
</script>
</body>
</html>
