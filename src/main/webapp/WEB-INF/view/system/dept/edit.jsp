<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>部门修改</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="" lay-filter="dataForm">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <div class="layui-form-item">
                    <input type="hidden" name="id">
                    <label class="layui-form-label layui-required">上级部门</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="parentId" id="parentId">
                        <input type="text" name="parentName" id="parentName" autocomplete="off" placeholder="请输入上级部门名称" class="layui-input" lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="deptName" autocomplete="off" placeholder="请输入部门名称" class="layui-input layui-disabled" lay-verify="required" readonly>
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
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">部门描述</label>
                    <div class="layui-input-block">
                        <textarea name="deptDescription" placeholder="请输入部门描述" class="layui-textarea"></textarea>
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
            var url = BaseUrl + 'sysDept/update';
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

        form.on('select(LAY-Select)', function (data) {
            var text = data.elem[data.elem.selectedIndex].text;
            $("#roleName").val(text);
        });

        //部门选择
        $("#parentName").click(function () {
            layer.open({
                type: 1,  //type:0 也行
                title: "选择树",
                area: ["400px", "80%"],
                content: '<div style="padding: 20px;"><div class="layui-input-inline">\n' +
                    '  <input class="layui-input" id="searchInput" value="" placeholder="输入查询节点内容...">\n' +
                    '</div>\n' +
                    '<div class="layui-input-inline">\n' +
                    '  <button class="layui-btn layui-btn-normal" id="search_btn">查询</button>\n' +
                    '</div>' +
                    '<ul style="margin-top: 10px;" id="dept-tree" class="dtree" data-id="0"></ul></div>',
                btn: ['确认选择'],
                success: function (layero, index) {
                    var DTree = dtree.render({
                         obj: $(layero).find("#dept-tree"),  //  如果直接用elem加载不出来，则可以使用这个方式加载jquery的DOM
                        // elem: "#dept-tree",
                        url: BaseUrl + "sysDept/list",
                        selectInputName: {nodeId: "id", context: "deptName"},
                        dataStyle: "layuiStyle",
                        dataFormat: "list",
                        response: {
                            message: "msg",
                            statusCode: 0,
                            treeId: "id", //节点ID（必填）
                            parentId: "parentId", //父节点ID（必填）
                            title: "deptName"
                        },
                        done: function (data, obj, first) {  //使用异步加载回调
                            var reportId = $("#parentId").val();
                            dtree.dataInit("dept-tree", reportId); // 初始化值
                            $("#search_btn").unbind("click");
                            $("#search_btn").click(function(){
                                var value = $("#searchInput").val();
                                if(value){
                                    var flag = DTree.searchNode(value); // 内置方法查找节点
                                    if (!flag) {layer.msg("该名称节点不存在！", {icon:5});}
                                } else {
                                    DTree.menubarMethod().refreshTree(); // 内置方法刷新树
                                }
                            });
                        }
                    });

                    // 绑定节点的双击
                    dtree.on("nodedblclick('dept-tree')", function (obj) {
                        $("#parentId").val(obj.param.nodeId);
                        $("#parentName").val(obj.param.context);
                        layer.close(index);
                    });
                },
                yes: function (index, layero) {
                    var param = dtree.getNowParam("dept-tree"); // 获取当前选中节点
                    $("#parentId").val(param.nodeId);
                    $("#parentName").val(param.context);
                    layer.close(index);
                }
            });
        });
    });
</script>
</body>
</html>
