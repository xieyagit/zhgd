function q() {
    var arrall = ["imprest", "checkAndAccept", "finish"];
    for (var i = 0; i < arrall.length; i++) {
        $("#" + arrall[i]).val("");
        $("#" + arrall[i] + "Money").val("");
    }
}



//只能输入数字
function k1(obj) {
    if (obj.value.length == 1) {
        obj.value = obj.value.replace(/[^1-9]/g, '')
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    } else {
        obj.value = obj.value.replace(/\D/g, '')
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    }
    if (obj.value == "") {
        obj.value = "0";
    }
}

//只能输入数字
function k2(obj) {
    if (obj.value.length == 1) {
        obj.value = obj.value.replace(/[^1-9]/g, '')
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    } else {
        obj.value = obj.value.replace(/\D/g, '')
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    }
    if (obj.value == "") {
        obj.value = "0";
    }
    //人脸设备数
    var a1 = $("#faceRecognition").val();

    //人脸设备价格
    var a2 = $("#faceRecognitionPrice").val();
    //后台管理系统数量
    var a3 = $("#managementSystem").val();
    //后台管理系统价格
    var a4 = $("#managementSystemPrice").val();
    //设备总价
    var a = document.getElementById("sbyj");
    a.value = (a1 * a2) + (a3 * a4);
    //两人服务数量
    var b1 = $("#twoPeopleService").val();
    //两人服务价格
    var b2 = $("#twoPeopleServicePrice").val();
    //一人服务数量
    var b3 = $("#onePeopleService").val();
    //一人服务价格
    var b4 = $("#onePeopleServicePrice").val();
    //人员服务总价
    var b = document.getElementById("ryfw");
    b.value = (b1 * b2) + (b3 * b4);
    //总价
    var v = document.getElementById("sum1");
    v.value = (a1 * a2) + (a3 * a4) + (b1 * b2) + (b3 * b4);
    q();
}

//只能输入数字及两位小数
function k(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
    obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
    if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        obj.value = parseFloat(obj.value);
    }
    if (obj.value == "") {
        obj.value = "0";
    }
}

//失去焦点
function f(obj) {
    k(obj);
    //人脸设备数
    var a1 = $("#faceRecognition").val();

    //人脸设备价格
    var a2 = $("#faceRecognitionPrice").val();
    //后台管理系统数量
    var a3 = $("#managementSystem").val();
    //后台管理系统价格
    var a4 = $("#managementSystemPrice").val();
    //设备总价
    var a = document.getElementById("sbyj");
    a.value = (a1 * a2) + (a3 * a4);
    //两人服务数量
    var b1 = $("#twoPeopleService").val();
    //两人服务价格
    var b2 = $("#twoPeopleServicePrice").val();
    //一人服务数量
    var b3 = $("#onePeopleService").val();
    //一人服务价格
    var b4 = $("#onePeopleServicePrice").val();
    //人员服务总价
    var b = document.getElementById("ryfw");
    b.value = (b1 * b2) + (b3 * b4);
    //总价
    var v = document.getElementById("sum1");
    v.value = (a1 * a2) + (a3 * a4) + (b1 * b2) + (b3 * b4);
    q();
}


//时间格式化函数
function getFormatTime(time) {
    var time = new Date(parseInt(time));
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
}

function add0(m) {
    return m < 10 ? '0' + m : m
}