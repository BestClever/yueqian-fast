<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${baseurl}/css/public.css" media="all"/>
    <link rel="stylesheet" href="${baseurl}/css/index.css" media="all"/>
    <link rel="stylesheet" href="${baseurl}/lib/layui/css/layui.css" media="all"/>
</head>
<body >
<div class="layui-card">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px; margin-left:200px">
        <legend>个人资料</legend>
    </fieldset>
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="width: 600px; height: 500px;margin: 20px auto">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="hidden" value="${user.id}" name="userid">
                <input type="text" value="${user.name}" name="name" placeholder="请输姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="1" title="男"
                       <c:if test="${user.sex=='1'}">checked</c:if>>
                <input type="radio" name="sex" value="0" title="女" <c:if test="${user.sex=='0'}">checked</c:if> >
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">保存</button>
                <button type="button" id="pwd" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >修改密码</button>
            </div>
        </div>
    </form>
</div>



    <!-- 修改的弹出层开始 -->
    <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
        <form class="layui-form"  lay-filter="dataFrm" id="dataPwd">
            <div class="layui-form-item">
                <label class="layui-form-label">输入旧密码</label>
                <div class="layui-input-block">
                    <input type="hidden" id="id" value="${user.id}" name="id"/>
                    <input type="password" id="jpwd" name="pwd" lay-verify="title" autocomplete="off" placeholder="请输入旧密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">输入新密码</label>
                <div class="layui-input-block">
                    <input type="password" id="pwd1" name="pwd1" lay-verify="title" autocomplete="off" placeholder="请输入新密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" id="pwd2" name="pwd2" lay-verify="title" autocomplete="off" placeholder="确认密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center; margin-left: 0">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" id="qwe">保存</button>
                    <button type="button" id="guanbi" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-close" >取消修改</button>
                </div>
            </div>
        </form>
    </div>
    <!-- 修改的弹出层结束 -->
<script type="text/javascript" src="${baseurl}/lib/layui/layui.js"></script>
<script type="text/javascript" src="${baseurl}/lib/layui_custom/Hlayui.js"></script>

<script type="text/javascript">
    layui.use(['form','layerCustom','jquery','table'], function(){
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var layerCustom = layui.layerCustom;


        var op;
        $("#pwd").click(function () {
            op=layer.open({
                type: 1,
                title:"修改密码",
                area: ['500px', '300px'],
                content:$("#saveOrUpdateDiv") //这里content是一个普通的String
            });
        });

        //关闭窗口
        $("#guanbi").click(function(){
            layer.close(op)
        })
        $("#qwe").click(function(){

            var id=$("#id").val();
            var pwd=$("#jpwd").val();
            var pwd1=$("#pwd1").val();
            var pwd2=$("#pwd2").val();
            if(pwd==""||id==""||pwd1==""){
                layer.msg("不能为空")
                return false;
            }else if(pwd1!=pwd2){
                layer.msg("密码不一致")
                return false;
            }
            $.post(BaseUrl+"admin/updatePwd",{id:id,pwd:pwd,newPwd:pwd1},function(result){
                if(result.success){
                    // setTimeout(function(){
                    //     window.parent.location.href="../sel/toLogin";//#设定跳转的链接地址
                    // },3000)
                    layerCustom.greenLaughMsg("修改成功");  // 显示倒计时

                    //layer.close(op);
                    return false;
                }else{
                    layer.msg("旧密码错误");
                    return false;
                }

            })

        })
        //保存
        form.on("submit(doSubmit)",function(obj){
            layer.confirm('确定修改吗', function(index){
                //序列化表单数据
                var params=$("#dataFrm").serialize();

                $.post(BaseUrl+"admin/update",params,function(result){
                    if(result.success){
                        layerCustom.greenLaughMsg(result.msg,function () {
                            location.reload();
                        })
                    }
                })
            });
        })
    })

</script>
</body>
</html>
