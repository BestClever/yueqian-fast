<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>消息通知表添加</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="white-bg">
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
            	<%--填写想要新增的字段--%>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-required">公告标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="noticeTitle"  autocomplete="off" placeholder="请输入" class="layui-input"  lay-verify="required|Name">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">公告类型</label>
                    <div class="layui-input-block">
                        <select name="noticeType" lay-verify="required" lay-filter="LAY-select" id="noticeType" placeholder="请输入公告类型">
                            <option value="1">通知</option>
                            <option value="2">公告</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">公告内容</label>
                    <div class="layui-input-block">
                        <textarea name="noticeContent" id="content" cols="30" rows="10"></textarea>
                    </div>
                </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">公告状态</label>
                        <div class="layui-input-block">
                            <input type="radio" name="isNormal" value="0" title="正常" checked>
                            <input type="radio" name="isNormal" value="1" title="关闭">
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

<script type="text/javascript" src="${baseurl}/static/js/jquery.min.js"></script>
<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>

    layui.use(['form', 'jquery', 'upload', 'element', 'layer', 'laydate', 'common', 'layerCustom','tinymce','layedit'], function () {
        var element = layui.element,
            $ = layui.jquery,
            upload = layui.upload,
            layer = layui.layer,
            layerCustom = layui.layerCustom,
            laydate = layui.laydate,
            common = layui.common,
            form = layui.form,
            layedit = layui.layedit,
            tinymce = layui.tinymce;


        /*表单验证*/
        form.verify({
            Name: function (value) {
                var url = BaseUrl + 'sysNotice/exist';
                var param = {
                    Name: value
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

        var editIndex = layedit.build('content',{
            uploadImage: {url: BaseUrl + 'file/uploadImage', type: 'post'},
            height: 300
        });

        /*表单提交*/
        form.on('submit(add)', function (data) {
            //同步富文本和textarea里面的内容
            // layedit.sync(editIndex);
            data.field.noticeContent = layedit.getContent(editIndex);
            var url = BaseUrl + 'sysNotice/save';
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
           $("#roleName").val(text);
        });

    });
</script>
</body>
</html>