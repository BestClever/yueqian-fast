<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>学院信息添加</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action=""  lay-filter="dataForm">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <div class="layui-form-item">
                    <label class="layui-form-label">学院名称</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="collegeName" id="collegeName">
                        <select name="collegeId" lay-verify="required" lay-filter="LAY-Select" id="collegeNameSelect">
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班级名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="classeName"  id="classeName" autocomplete="off" placeholder="请输入班级名称" class="layui-input"  lay-verify="required|collegeName">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班级号</label>
                    <div class="layui-input-block">
                        <input type="text" name="classeNo" id="classeNo" autocomplete="off" placeholder="请输入班级号" class="layui-input"  lay-verify="required|classeVerify">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班级人数</label>
                    <div class="layui-input-block">
                        <input type="text" name="classeSize" id="classeSize" autocomplete="off" placeholder="请输入班级人数" class="layui-input"  lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">年级</label>
                    <div class="layui-input-block">
                        <select name="classeGrade" lay-verify="required" lay-filter="classeGradeSelect" id="classeGradeSelect">
                            <option value="1">大一</option>
                            <option value="2">大二</option>
                            <option value="3">大三</option>
                            <option value="4">大四</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班主任名称</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="classeTeacheId" id="classeTeacheId">
                        <input type="text" name="classeTeacheName" id="classeTeacheName" autocomplete="off" placeholder="请输入班主任名称" class="layui-input" readonly  lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">辅导员名称</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="assistantId" id="assistantId">
                        <input type="text" name="assistantName" id="assistantName" autocomplete="off" placeholder="请输入辅导员名称" class="layui-input" readonly  lay-verify="required">
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
            classeVerify: function (value) {
                var url = BaseUrl + 'classe/existClasseName';
                var param = {
                    classeName: $("#classeName").val(),
                    classeNo:value
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
            var url = BaseUrl + 'classe/save';
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
           $("#collegeName").val(text);
        });

        $("#classeTeacheName").on('click',function () {
            layerCustom.open("教师选择",BaseUrl + 'classe/teacherSelect', "700px", "550px",function () {
                
            });
        });

        $("#assistantName").on('click',function () {
            layerCustom.open("教师选择",BaseUrl + 'classe/assistantSelect', "700px", "550px",function () {

            });
        });

    });
</script>
</body>
</html>
