
// 隐藏按钮图片
function registerFunction() {
    // var是局部变量
    let submit = document.getElementById("register-submit");
    if (submit.value === "注册"){
        submit.value = "注册中..."
    }

    // 判断密码是否相同
    let password_input = document.getElementById("password-input");
    let password = password_input.value;

    let password_again_input = document.getElementById("password-input");
    let password_again = password_again_input.value;

    if (password !== password_again){
        // 拿到Input-text的对象
        return;
    }
    let username_input = document.getElementById("username-input");
    let username = username_input.value;

    // 拿到电话号码
    let telephone_input = document.getElementById("telephone-input");
    let telephone = telephone_input.value;

    let verifyCode = $("#verificationCode-input").val();

    POST("user/register", {
        name: username,
        phone: telephone,
        password: password,
        verifyCode: verifyCode
    }, function () {
        window.location.href = "loginZ.html";
    }, function (data) {
        alert(data);
    });
}

let countdown=0;
function setTime(button) {
    // 发送一个验证码

    if (countdown === 0) {
        button.removeAttribute("disabled");
        if (button.value === "点击获取"){
            countdown = 60;
            setTime(button);
            let telephone_input = document.getElementById("telephone-input");
            let telephone = telephone_input.value;
            POST("../user/sendMessage", {
                phone: telephone
            }, function (data) {

            }, function (data) {
                alert("发送失败");
                countdown = 1;
            });
        } else {
            button.value="点击获取";
        }

    } else {
        button.setAttribute("disabled", true);
        button.value="重新发送(" + countdown + ")";
        button.border = "1px solid black";
        countdown--;
        setTimeout(function() {
            setTime(button)
        },1000);

    }
}