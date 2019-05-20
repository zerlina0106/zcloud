package cn.yyy.file.controller;


import cn.yyy.common.YYYException;
import cn.yyy.common.controller.BaseController;
import cn.yyy.common.entity.ResponseData;
import cn.yyy.file.util.FileUtil;
import cn.yyy.file.model.Dir;
import cn.yyy.file.model.FileModel;
import cn.yyy.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class FileController extends BaseController {
    private final String SUCCESS_UPLOAD = "上传成功";
    private final String ERROR_UPLOAD = "文件不存在";
//    private final String SUCCESS_UPLOAD = "上传成功";


    @Autowired
    FileService fileService;
    // ----------------- Dir操作相关 -----------------

    // 根据关键词查询当前网盘公开资源的文件信息
    @RequestMapping("searchFiles")
    public ResponseData searchFiles(@RequestBody FileModel fileModel) {
        List list = fileService.searchFiles(fileModel);
        return getTableDataWithList(list);
    }

    // 根据关键词查询自己的文件
    @RequestMapping("searchMyFiles")
    public ResponseData searchMyFiles(@RequestBody FileModel fileModel) {
        List list = fileService.searchMyFiles(fileModel);
        return getTableDataWithList(list);
    }

    @RequestMapping("createDir")
    public ResponseData createDir(@RequestBody Dir dir){
        dir.setFid(0);
        dir.setSize(null);
        try {
            fileService.createDir(dir);
        } catch (YYYException e){
            return getTableData(e);
        }
        return getTableData(dir);
    }

    // 查看自己文件目录
    @RequestMapping("getDir")
    public ResponseData getDir(@RequestBody Dir dir) {
        if (dir.getSuperid()!=null &&dir.getSuperid() < 0){
            if (dir.getSuperid() > -10){
                return getWeka(dir);
            } else {
                return getWeka0(dir.getSuperid(), dir.getUid());
            }
        }
        List<Dir> list = fileService.getDir(dir.getUid(), dir.getSuperid());
        return getTableDataWithList(list);
    }

    // ----------------- 文件操作相关 -----------------
    // 下载文件
    @RequestMapping("downloadFile")
    public ResponseData downloadFile(HttpServletResponse response, String fid) {
        try {
            fileService.downloadFile(fid, response);
        } catch (YYYException e) {
            return getTableData(e);
        }
        return null;
    }

    @PostMapping("deleteFile")
    public ResponseData deleteFile(@RequestBody Dir dir){
        fileService.deleteFile(dir);
        return getTableData();
    }

    // 上传文件
    @PostMapping("uploadFile0")
    public ResponseData uploadFile0(Integer userid, Integer dirId, MultipartHttpServletRequest request) {
        MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
        Collection<List<MultipartFile>> values = map.values();
        Dir dir = null;
        for (List<MultipartFile> files : values) {
            for (MultipartFile file : files) {
                String md5;
                String fileName;
                try {
                    fileName = file.getOriginalFilename();
                    md5 = FileUtil.saveFile(file);
                } catch (YYYException exception) {
                    return getTableData(exception);
                }
                FileModel fileModel = new FileModel();
                fileModel.setName(fileName);
                fileModel.setMd5(md5);
                fileModel.setSize((int) file.getSize());
                dir = fileService.uploadFile0(userid, fileModel, dirId);
            }
        }
        return getTableData(dir);
    }

    // 上传文件  保留方法,暂时用不到
    @RequestMapping("uploadFile")
    public ResponseData uploadFile(@RequestBody FileModel fileModel) {
        if (fileService.uploadFile(fileModel) == true) {
            return getTableDataWithMessage(CODE_FAIL, ERROR_UPLOAD);
        } else {
            return getTableData();
        }
    }

    // 获取自己的分类
    @RequestMapping("getWeka")
    public ResponseData getWeka(@RequestBody Dir dir) {
        Integer i = dir.getSuperid();
        ArrayList<Dir> list = new ArrayList<>();
        Dir d1 = new Dir();
        d1.setName("生活");
        d1.setSuperid(0);
        d1.setCreatetime(new Date());
        d1.setUid(-1);
        Dir d2 = new Dir();
        d2.setName("业务");
        d2.setSuperid(0);
        d2.setCreatetime(new Date());
        d2.setUid(-1);
        switch (i){
            case -1:
                d1.setFid(-11);
                d2.setFid(-12);
                d1.setId(-11);
                d2.setId(-12);
                break;
            case -2:
                d1.setFid(-21);
                d2.setFid(-22);
                d1.setId(-21);
                d2.setId(-22);
                break;
            case -3:
                d1.setFid(-31);
                d2.setFid(-32);
                d1.setId(-31);
                d2.setId(-32);
                break;
            case -4:
                d1.setFid(-41);
                d2.setFid(-42);
                d1.setId(-41);
                d2.setId(-42);
                break;
            case -5:
                d1.setFid(-50);
                d1.setId(-50);
                list.add(d1);
                return getTableData(list);
            default:
                return getTableData();
        }
        list.add(d1);
        list.add(d2);
        return getTableData(list);
    }
    // 获取自己的分类
    @RequestMapping("getWeka1")
    public ResponseData getWeka1(@RequestBody Dir dir) {
        ArrayList<Dir> list = new ArrayList<>();
        String[] ss = {"图片","视频","音乐","文档","其它"};
        for (int j = 0; j < 5; j++) {
            Dir d = new Dir();
            d.setId(-1-j);
            if (j == 4){
                d.setId(-50);
            }
            d.setName(ss[j]);
            d.setSuperid(0);
            list.add(d);
        }
        return getTableData(list);
    }

    private ResponseData getWeka0(Integer fid, int uid){
        List list = fileService.getWeka(fid, uid);
        return getTableData(list);
    }
}
