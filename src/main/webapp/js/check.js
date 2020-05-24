//校验汉字
function checkWord(node){
    var tname = $(node).val();
    var reg = /\W+/;
    var flag = reg.test(tname);
    if(flag){
        $(node).css({"border":"1px solid green","color":"green"});
    }else{
        $(node).css({"border":"1px solid red","color":"red"});
    }
    return flag;
}
//校验数字
function checkNumber(node){
    var tdetail = $(node).val();
    var reg = /\d+/;
    var flag = reg.test(tdetail);
    if(flag){
        $(node).css({"border":"1px solid green","color":"green"});
    }else{
        $(node).css({"border":"1px solid red","color":"red"});
    }
    return flag;
}
/**
 * Map转json
 * @param m
 * @returns String
 */
function MapTOJson(m) {
    var str = '{';
    var i = 1;
    m.forEach(function (item, key, mapObj) {
    	if(mapObj.size == i){
    		str += '"'+ key+'":"'+ item + '"';
    	}else{
    		str += '"'+ key+'":"'+ item + '",';
    	}
    	i++;
    });
    str +='}';
    //console.log(str);
    return str;
}

//校验用户名
        function checkUserName(node){
            //1.获取用户名
            var userName=$(node).val();
            //2.定义正则表达式
            var reg_username=/^\w{8,20}$/;
            //3.判断匹配信息
            var flag=reg_username.test(userName);
            //4.给出提示信息
            if(flag){
                $(node).css({"border":"1px solid green","color":"green"});
            }else{
                $(node).css({"border":"1px solid red","color":"red"});
            }
            // alert("校验用户名");
            return flag;
        }
        //校验密码
        function checkPassword(node){
            //1.获取密码
            var password=$(node).val();
            //2.定义正则表达式
            var reg_password=/^\w{8,20}$/;
            //3.判断匹配信息
            var flag=reg_password.test(password);
            //4.给出提示信息
            if(flag){
                $(node).css({"border":"1px solid green","color":"green"});
            }else{
                $(node).css({"border":"1px solid red","color":"red"})
            }
            // alert("校验密码");
            return flag;
        }
        //校验邮箱
        function checkEmail(node){
            //1.获取邮箱
            var email=$(node).val();
            //2.定义正则    2925235864@qq.com
            var reg_email=/\w+@\w+\.\w+/;
            //3.判断匹配
            var flag=reg_email.test(email);
            if(flag){
                $(node).css({"border":"1px solid green","color":"green"});
            }else{
                $(node).css({"border":"1px solid red","color":"red"})
            }
            return flag;
        }
        //校验姓名
        function checkName(node){
            //1.获取姓名
            var name=$(node).val();
            //2.定义正则    张三
            var reg_name=/\W+/;
            //3.判断匹配
            var flag=reg_name.test(name);
            if(flag){
                $(node).css({"border":"1px solid green","color":"green"});
            }else{
                $(node).css({"border":"1px solid red","color":"red"})
            }
            return flag;
        }
        //校验手机号
        function checkTel(node){
            //1.获取手机号
            var tel=$(node).val();
            //2.定义正则    15938377569
            var reg_tel=/\d{11}/;
            //3.判断匹配
            var flag=reg_tel.test(tel);
            if(flag){
                $(node).css({"border":"1px solid green","color":"green"});
            }else{
                $(node).css({"border":"1px solid red","color":"red"})
            }
            return flag;
        }
        //校验出生日期
        function checkBirthday(node){
            //1.获取出生日期
            var birthday = $(node).val();
            //alert(birthday);
            //2.定义正则表达式
            var reg_birthday=/\d{4}\-\d{2}\-\d{2}/;
            //3.判断匹配结果
            var flag = reg_birthday.test(birthday);
            if(flag){
                $(node).css({"border":"1px solid green","color":"green"});
                //alert(flag);
            }else{
                $(node).css({"border":"1px solid red","color":"red"}).html("请输入出生日期");
                //alert(flag);
            }
            return flag;
        }
//图片预览路径
function getObjectURL(file) {
    var url = null;
    if(window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if(window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if(window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

function readFileFirefox(fileBrowser) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        }
        catch (e) {
            alert('无法访问本地文件，由于浏览器安全设置。为了克服这一点，请按照下列步骤操作：(1)在地址栏输入"about:config";(2) 右键点击并选择 New->Boolean; (3) 输入"signed.applets.codebase_principal_support" （不含引号）作为一个新的首选项的名称;(4) 点击OK并试着重新加载文件');
            return;
        }
        var fileName=fileBrowser.value; //这一步就能得到客户端完整路径。下面的是否判断的太复杂，还有下面得到ie的也很复杂。
        var file = Components.classes["@mozilla.org/file/local;1"]
            .createInstance(Components.interfaces.nsILocalFile);
        try {
            // Back slashes for windows
            file.initWithPath( fileName.replace(/\//g, "\\\\") );
        }
        catch(e) {
            if (e.result!=Components.results.NS_ERROR_FILE_UNRECOGNIZED_PATH) throw e;
            alert("File '" + fileName + "' cannot be loaded: relative paths are not allowed. Please provide an absolute path to this file.");
            return;
        }
        if ( file.exists() == false ) {
            alert("File '" + fileName + "' not found.");
            return;
        }


        return file.path;
    }


    //根据不同浏览器获取路径
    function getvl(obj){
//判断浏览器
        var Sys = {};
        var ua = navigator.userAgent.toLowerCase();
        var s;
        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
            (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
                (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
                    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
                        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
        var file_url="";
        if(Sys.ie<="6.0"){
            //ie5.5,ie6.0
            file_url = obj.value;
        }else if(Sys.ie>="7.0"){
            //ie7,ie8
            obj.select();
            file_url = document.selection.createRange().text;
        }else if(Sys.firefox){
            //fx
            //file_url = document.getElementById("file").files[0].getAsDataURL();//获取的路径为FF识别的加密字符串
            file_url = readFileFirefox(obj);
        }else if(Sys.chrome){
            file_url = obj.value;
        }
        alert("获取文件域完整路径为："+file_url);
        //document.getElementById("text").innerHTML=file_url;
    }
    
             function selectImage(file){
                 var image = '';
                 if(!file.files||!file.files[0]){
                     return;
                 }
                 var reader = new FileReader();
                 reader.onload = function(evt){
                     document.getElementById('previewImg').src = evt.target.result;
                     image = evt.target.result;
                 }
                 reader.readAsDataURL(file.files[0]);
                 return image;
             }
function upload(obj){
var files = obj.files ;
var formData = new FormData();
    for(var i = 0;i<files.length;i++){                
        formData.append("upfile[]", files[i]);
        
    }
    $.ajax({
          url: "1.php",
          type: "POST",
          data:formData,
          cache:false,         //不设置缓存
          processData: false,  // 不处理数据
          contentType: false   // 不设置内容类型
    });    
}
//校验管理员身份
        function checkAdmin(){
            $.post("../admin/findOne",function(user){
                if(user == null){
                    location.href="adminLogin.html";
                }
            })
        }
