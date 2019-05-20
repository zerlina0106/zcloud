
function chooseEvery() {
    let dir_checkbox = document.getElementsByClassName('dir-checkbox');
    for(let i = 0; i<dir_checkbox.length; i++){
        dir_checkbox[i].checked = true;
    }
    let file_checkbox = document.getElementsByClassName('file-checkbox');
    for(let i = 0; i<file_checkbox.length; i++){
        file_checkbox[i].checked = true;
    }
}

function cancelChooseEvery() {
    let dir_checkbox = document.getElementsByClassName('dir-checkbox');
    for(let i = 0; i<dir_checkbox.length; i++){
        dir_checkbox[i].checked = false;
    }
    let file_checkbox = document.getElementsByClassName('file-checkbox');
    for(let i = 0; i<file_checkbox.length; i++){
        file_checkbox[i].checked = false;
    }
}
// 全选按钮
function chooseAll(checkbox){
    if ( checkbox.checked === true){
        chooseEvery();
    }else{
        cancelChooseEvery();
    }
}
// 下载按钮
function downloadClick() {
    let download_list = [];
    let dir_checkbox = document.getElementsByClassName('dir-checkbox');
    let file_checkbox = document.getElementsByClassName('file-checkbox');
    let dir_name = document.getElementsByClassName('dir-name');
    let file_name = document.getElementsByClassName('file-name');
    for(i = 0; i<dir_checkbox.length; i++){
        if(dir_checkbox[i].checked === true){
            download_list.push(dir_name[i].innerHTML);
        }
    }
    for(i = 0; i<file_checkbox.length; i++){
        if(file_checkbox[i].checked === true){
            download_list.push(file_name[i].innerHTML);
        }
    }
    if(download_list.length === 0){
        alert('请选择文件夹或文件下载');
    }
    else {
        alert(download_list);
    }

}
// 本地文件夹上传路径
function getUploadDirPath() {
    var upload_dir = document.getElementById('upload-dir');
    alert('要上传的文件夹是' + upload_dir.value);

}

// 新建文件夹
function createNewFolder() {
    let dir_name = prompt("请输入文件夹名","新建文件夹");
    if (dir_name != null){
        POST("file/createDir",
            getDirInfo(getFID(),dir_name),
            function(data){
                createDiv(data, true);
            },
            function(data){
                alert(data);
            }
        )
    }
}

// 确认搜索
function beginSearch(event) {
    let e = event || window.event;
    if(e.keyCode === 13){
        let text = $("#search-text").val();
        POST(
            "file/searchMyFiles",
            {
                name: text
            },
            function(data){
                createDiv(data);
            }
        )

    }
}

// 返回上级目录按钮
function lastCatalogue() {
    beginInterface(lastId.pop(), true);
}
