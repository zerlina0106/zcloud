
// 隐藏按钮图片
function signFunction() {
    // var是局部变量
    let submit = document.getElementById("sign-submit");
    if (submit.value === "登录"){
        submit.value = "登录中..."
    }

    // 拿到Input-text的对象
    let username_input = document.getElementById("username");
    let username = username_input.value;

    let password_input = document.getElementById("password");
    let password = password_input.value;
    POST("/user/sign", {
        name: username,
        password: password
    }, function (data) {
        saveUser(data[0]);
        window.location.href = "home.html";
    }, function (data) {
        alert(data);
    });
}
