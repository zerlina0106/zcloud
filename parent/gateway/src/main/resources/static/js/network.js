function POST(url, data, success, error) {
    if (url === null){
        alert("url 不能为空");
        return;
    }
    if(data === null){
        data = {};
    }
    $.ajax({
        type: 'post',
        url: url,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data),

        success: function(data) {
            if (data.messages !== null && data.messages[0].code === '1000' ){
                if (data.data){
                    success(data.data.rows);
                } else {
                    success(null);
                }
            } else {
                debugger;
                error(data.messages[0].message);
            }

        },
        fail: function(data){
            debugger;
            error(data);
        },
        error: function(data){
            debugger;
            error(data);
        }
    });
}
function POST_FILE(url, data) {
    if (url === null){
        alert("url 不能为空");
        return;
    }
    if(data === null){
        data = {};
    }
    window.open('/file/downloadFile?fid='+data.fid);
}
function UPLOAD_FILE(formData, success, error) {

    $.ajax({
        url: "file/uploadFile0",
        type: "POST",
        data: formData,
        /**
         *必须false才会自动加上正确的Content-Type
         */
        contentType: false,
        /**
         * 必须false才会避开jQuery对 formdata 的默认处理
         * XMLHttpRequest会对 formdata 进行正确的处理
         */
        processData: false,
        success: function (data) {
            if (data.messages !== null && data.messages[0].code === '1000' ){
                success(data.data.rows);
            } else {
                debugger;
                error(data.messages[0].message);
            }
        },
        error: function () {
            alert("上传失败！");
            $("#imgWait").hide();
        }
    });
}
let userID = "userID";
let userName = "name";
let userPhone = "phone";
let fid = 0;

function getUserID() {
    return localStorage.getItem(userID);
}
function getUserName() {
    return localStorage.getItem(userName);
}
function getUserPhone() {
    return localStorage.getItem(userPhone);
}
function setFID(fid0){
    fid = fid0;
}
function getFID(){
    return fid;
}
function getDirInfo(superid, name, fid) {
    let dic = {};
    dic['uid'] = getUserID();
    if (superid === null){
        superid = getFID();
    }
    dic['superid'] = superid;
    if (name !== null){
        dic['name'] = name;
    }
    if (fid !== null){
        dic['id'] = fid;
    }
    return dic;
}

function saveUser(user) {
    localStorage.setItem(userName,user.name);
    localStorage.setItem(userID,user.id);
    localStorage.setItem(userPhone,user.phone);
}