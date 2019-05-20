
window.local_choose_area = document.getElementById('DFile-area');
homepath = '/home';
nowpath = homepath;
nowname = '';
let type_file = 'file';
let type_dir = 'dir';
// 基类：名字，路径，类型，大小，修改时间
function DFile(filename, filepath, file_type, file_size, change_time, dirId) {
    this.name = filename;
    this.path = filepath;
    this.type = file_type;
    if (file_size !== '-'){
        if (file_size < 1000){
            file_size = Math.ceil(file_size) + 'B';
        }  else if (file_size < 1000*1000){
            file_size = Math.ceil(file_size/1000) + 'K';
        } else {
            file_size = Math.ceil(file_size/1000/1000) + 'M';
        }
    }
    this.size = file_size;
    if (change_time){
        this.change_time = change_time.slice(0,10);
    } else {
        this.change_time = '-';
    }
    this.fid = dirId;
}

function beginInterface2(){
    lastId.push(0);
    setFID(999);
    $("#last-back").show();
    POST(
        "file/getWeka1",
        {
            superid: getFID(),
            uid: getUserID()
        },
        function (data) {
            createDiv(data);
        }
    );
}
// 开始创建文件
let lastId = [];
function beginInterface(superId, back) {
    console.log(superId);
    if (superId === 999){
        beginInterface2();
        if (back){
            lastId.pop();
        }
        return;
    }

    setIcon();
    $("#username-log-area").text(getUserName());
    if (superId){
        if (superId !== 0){
            lastId.push(getFID());
            $("#last-back").show();
        } else {
            lastId = 0;
            $("#last-back").hide();
        }
        setFID(superId);
    } else {
        setFID(0);
        lastId = [];
        $("#last-back").hide();
    }
    if (back){
        lastId.pop();
    }
    POST(
        "file/getDir",
        getDirInfo(superId),
        function (data) {
            createDiv(data);
        }
    );
}
// 创建所有单行域
function createDiv(data, bool) {
    if (!bool){
        deleteDiv();
    }
    let df_list = getDFList(data);
    for(let i=0; i < df_list.length; i++){
        if (df_list[i].type !== type_file){
            createDirArea(df_list[i]);
        }
        else {
            createFileArea(df_list[i]);
        }
    }
}
// 删除所有单行域
function deleteDiv() {
    $("#DFile-area").empty();
}


// fid 和 id 属性互相切换
function idToFid(id){
    return 'ID_FID_'+id;
}
function fidToId(fid){
    return fid.slice(7);
}
// 创建文件夹域
function createDirArea(dfile) {
    let
        dir_name = dfile.name,
        dir_date = dfile.change_time,
        dir_id = dfile.fid;

    let area_dir = document.createElement('div');
    area_dir.className = 'dir-area';
    area_dir.id = idToFid(dir_id);
    // 绑定双击打开事件
    area_dir.setAttribute('ondblclick', 'dbClickOpen(this)');
    // 绑定右键菜单
    area_dir.setAttribute('onmousedown', 'rightMenu(this)');

    // 复选框
    let checkbox_area_dir = document.createElement('div');
    checkbox_area_dir.className = 'dir-checkbox-area';

    let checkbox_dir = document.createElement('input');
    checkbox_dir.className = 'dir-checkbox';
    checkbox_dir.setAttribute('type', 'checkbox');
    checkbox_area_dir.appendChild(checkbox_dir);

    // 文件夹图片
    let img_dir = document.createElement('div');
    img_dir.className = 'dir-img';

    // 文件夹名
    let name_dir = document.createElement('div');
    name_dir.className = 'dir-name';
    name_dir.innerText = dir_name;

    // 下载处
    let quick_download_dir = document.createElement('div');
    quick_download_dir.className = 'dir-quick-download';
    quick_download_dir.setAttribute('onclick','quickDownloadClick(this)');

    // 大小
    let size_dir = document.createElement('div');
    size_dir.className = 'dir-size';
    size_dir.innerText = '-';

    // 修改日期
    let change_time_dir = document.createElement('div');
    change_time_dir.className = 'dir-change-time';
    change_time_dir.innerText = dir_date;

    area_dir.appendChild(checkbox_area_dir);
    area_dir.appendChild(img_dir);
    area_dir.appendChild(name_dir);
    area_dir.appendChild(quick_download_dir);
    area_dir.appendChild(size_dir);
    area_dir.appendChild(change_time_dir);

    // 赋值到主域中
    let DFile_area = document.getElementById('DFile-area');
    DFile_area.appendChild(area_dir);
}

// 创建文件域
function createFileArea(dfile) {
    let
        filename = dfile.name,
        file_size = dfile.size,
        file_date = dfile.change_time,
        id = dfile.fid;

    let area_file = document.createElement('div');
    area_file.className = 'file-area';
    area_file.id = idToFid(id);
    area_file.setAttribute('ondblclick', 'dbClickDownload(this)');
    area_file.setAttribute('onmousedown', 'rightMenu(this)');

    let checkbox_area_file = document.createElement('div');
    checkbox_area_file.className = 'file-checkbox-area';

    let checkbox_file = document.createElement('input');
    checkbox_file.className = 'file-checkbox';
    checkbox_file.setAttribute('type', 'checkbox');
    checkbox_area_file.appendChild(checkbox_file);

    let img_file = document.createElement('div');
    img_file.className = 'file-img';

    let name_file = document.createElement('div');
    name_file.className = 'file-name';
    name_file.innerText = filename;

    let quick_download_file = document.createElement('div');
    quick_download_file.className = 'file-quick-download';
    quick_download_file.setAttribute('onclick','quickDownloadClick(this)');

    let size_file = document.createElement('div');
    size_file.className = 'file-size';
    size_file.innerText = file_size;

    let change_time_file = document.createElement('div');
    change_time_file.className = 'file-change-time';
    change_time_file.innerText = file_date;

    area_file.appendChild(checkbox_area_file);
    area_file.appendChild(img_file);
    area_file.appendChild(name_file);
    area_file.appendChild(quick_download_file);
    area_file.appendChild(size_file);
    area_file.appendChild(change_time_file);
    // 赋值到主域中
    let DFile_area = document.getElementById('DFile-area');
    DFile_area.appendChild(area_file);
}

// window完成加载函数
window.onload = function() {
    let dir_area = document.getElementsByClassName('dir-area');
    if(dir_area.length > 1)
        rightMenu(dir_area);
    else if(dir_area.length === 1){
        rightMenu(dir_area[0]);
    }
    let file_area = document.getElementsByClassName('file-area');
    if(file_area.length > 1)
        rightMenu(file_area);
    else if(file_area.length === 1){
        rightMenu(file_area[0]);
    }
    if (dir_area.length + file_area.length === 0){
        rightMenu("");
    }
};
// 右键菜单函数
//-------------------------------------------------------
// 打开
function rightOpen() {
    dbClickOpen(window.local_choose_area);
}
// 下载
function rightDownload() {
    let id = window.local_choose_area.id;
    download(id);
    // 并开启下载功能
}
// 重命名
function rightRename() {
    let new_name = prompt("请输入新名字",window.local_choose_area.childNodes[2].innerText);
    if (new_name != null){
        window.local_choose_area.childNodes[2].innerText = new_name;
        // 并向后端发送一条修改名字的数据
    }
}
// 右键移动
function rightMove() {

}
// 右键删除
function rightDelete() {
    Loading();
    let id = window.local_choose_area.id;
    $("#menu").hide();
    POST("file/deleteFile",
        getDirInfo(null,null,fidToId(id)),
        function () {
            window.local_choose_area.remove();
            stopLoading();
        }
        )
}

//-------------------------------------------------------
// 右键菜单显示
function rightMenu(df_area) {
    let menu = document.getElementById('menu');

    let winWidth = function() {
        return document.documentElement.clientWidth || document.body.clientWidth;
    };
    let winHeight = function() {
        return document.documentElement.clientHeight || document.body.clientHeight;
    };

    // 关闭默认显示
    menu.style.display = 'none';
    document.addEventListener('click', function() {
        menu.style.display = 'none';
    });
    menu.addEventListener('click', function(event) {
        var event = event || window.event;
        event.cancelBubble = true;
    });

    // 右键显示函数
    df_area.oncontextmenu = function(event) {
        var event = event || window.event;
        menu.style.display = 'block';
        let x, y;
        x = event.clientX;
        y = event.clientY;
        if( x >= (winWidth() - menu.offsetWidth) ) {
            x  = winWidth() - menu.offsetWidth;
        }
        if(y > winHeight() - menu.offsetHeight  ) {
            y = winHeight() - menu.offsetHeight;
        }
        menu.style.left = x + 'px';
        menu.style.top = y + 'px';
        return false;
    };

    window.local_choose_area = df_area;
}

// 单击下载
function quickDownloadClick(select){
    download(select.parentNode.id);
}
function download(fid){
    fid = fidToId(fid);
    POST_FILE(
        "file/downloadFile",
        {
            fid: fid
        }
    )
}
// 上传文件
function uploadFile(){
    let formData = new FormData();
    let file = $("#upload-file")[0].files[0];
    formData.append("files", file);
    formData.append("userid", getUserID());
    formData.append("dirId", getFID()+'');
    UPLOAD_FILE(formData,
        function(data){
            createDiv(data, true);
        });
}
// 双击打开文件夹
function dbClickOpen(select) {
    beginInterface(fidToId(select.id));
}
// 双击文件下载
function dbClickDownload(select) {
    download(select.id);
}

// 加载函数
function Loading() {
    document.getElementById('loading-area').style.display =  "block";
}
function stopLoading() {
    document.getElementById('loading-area').style.display = "none"
}

// 后端获取文件信息
// 名字，路径，类型，大小，修改时间
// 要求返回的列表文件夹在前，文件在后
function getDFList(data) {
    let list = [];
    for (let i = 0,len = data.length; i < len; i++ ){
        let f = data[i];
        let type = f.fid>0?type_file:type_dir;
        let size = f.fid===0?'-':f.size;
        let file = new DFile(f.name, ''+f.superid, type, size, f.createtime, f.id);
        list.push(file);
    }
    return list;
}
