function setIcon() {
    var thumbnail_area = document.getElementsByClassName('thumbnail-area');

    let pngs = ['allfile', 'picture', 'video','music','noble', 'upload', 'download', 'bin', 'classify' ];
    for (let i = 0, len = thumbnail_area.length; i < len; i++ ){
        thumbnail_area[i].src = '../images/images1/'+pngs[i]+'.png';
    }
}
function selectUserLog(){

}