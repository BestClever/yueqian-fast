$(function(){
	loginVerCode();//初始化生成随机数
});


//生成随机数
function loginVerCode(len){
    len = len || 4;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';//默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1
    var maxPos = $chars.length;
    var loginCode = '';
    for (i = 0; i < len; i++) {
        loginCode += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    $(".loginVerCode").html(loginCode);
}