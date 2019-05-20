package cn.yyy.file.service;

import cn.yyy.common.YYYException;
import cn.yyy.common.service.BaseService;
import cn.yyy.file.util.FileUtil;
import cn.yyy.file.mapper.DirMap;
import cn.yyy.file.mapper.FileMap;
import cn.yyy.file.model.Dir;
import cn.yyy.file.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Service
public class FileService extends BaseService {
    final private int CODE_ERROR = -1001;
    final private String ERROR_FILE_NOT_FOUND = "没有找到文件";
    final private String ERROR_IO = "文件读取出现错误,可能文件已经不存在";
    final private String ERROR_NAME = "文件名格式转换错误";
    final private String ERROR_CREATE_DIR = "创建文件夹错误:文件夹名已存在";


    @Autowired
    DirMap dirMap;

    @Autowired
    FileMap fileMap;
    // ------------------------ dir相关 ------------------------
    // 根据关键词查询当前网盘公开资源的文件信息
    public List<Dir> searchFiles(FileModel fileModel){
        return dirMap.searchFileWithNameAndUser(fileModel);
    }

    // 根据关键词查询自己的文件
    public List searchMyFiles(FileModel fileModel){
        return dirMap.searchFileWithNameAndUser(fileModel);
    }

    //创建一个新的文件夹
    public void createDir(Dir dir){
        if (dirMap.select(dir).size() > 0){
            throw new YYYException(CODE_ERROR, ERROR_CREATE_DIR);
        }
        Integer ID = dirMap.insertSelective(dir);
        if (dir.getId() == null){
            dir.setId(ID);
        }
        if (dir.getCreatetime() == null){
            dir.setCreatetime(new Date());
        }
    }

    // 查看自己文件目录
    public List<Dir> getDir(Integer userId, Integer dirId){
        if (dirId == null){
            dirId = 0;
        }
        return dirMap.getDirWithUserIdAndDirId(userId, dirId);
    }

    // 删除自己的一个文件
    public void deleteFile(Dir dir) {
        dirMap.delete(dir);
    }

    // ------------------------ dir相关结束 ------------------------

    // ------------------------ 文件相关 ------------------------

    // 下载文件
    public Object downloadFile(String fileId, HttpServletResponse response){
        Dir dir = dirMap.selectByPrimaryKey(fileId);
        FileModel fileModel= fileMap.selectByPrimaryKey(dir.getFid());
        if (fileModel == null){
            return null;
        }

//        File file = FileUtil.getFile(fileModel.getMd5());
//        if (file == null){
//            throw new YYYException(CODE_ERROR, ERROR_FILE_NOT_FOUND);
//        }
//        downloadFile0(fileModel.getName(), file,response);
        byte[] bytes = FileUtil.getFile0(fileModel.getMd5());
        downloadFile0(fileModel.getName(), bytes, response);
        return null;
    }

    // downloadFile 的辅助方法
    private void downloadFile0(String fileName, Object o, HttpServletResponse response){
        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            // 实现文件下载
            byte[] buffer = new byte[1024];
            InputStream fis = null;
            BufferedInputStream bis = null;
            if (o instanceof byte[]){
                fis = new ByteArrayInputStream((byte[])o);
            } else if (o instanceof File){
                fis = new FileInputStream((File)o);
            } else {
                throw new RuntimeException();
            }
            bis = new BufferedInputStream(fis);

            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            throw new YYYException(CODE_ERROR, ERROR_NAME);
        } catch (IOException e){
            e.printStackTrace();
            throw new YYYException(CODE_ERROR, ERROR_IO);
        }
    }

    // 新建dir和File
    public Dir uploadFile0(Integer userId, FileModel fileModel, Integer dirId){
        FileModel fm = new FileModel();
        fm.setMd5(fileModel.getMd5());
        List<FileModel> fileModels = fileMap.select(fm);
        if (fileModels.size() == 0){


            Integer fid =  fileMap.insertSelective(fileModel);
            if (fileModel.getFid() == null){
                fileModel.setFid(fid);
            } else {
                System.out.println("148"+fileModel);
            }
        } else if (fileModels.size() == 1){
            fileModel = fileModels.get(0);
        } else {
            throw new RuntimeException();
        }

        if (dirId == null){
            dirId = 0;
        }

        Dir dir = new Dir();
        dir.setFid(fileModel.getFid());
        dir.setName(fileModel.getName());
        dir.setSuperid(dirId);
        dir.setUid(userId);
        dir.setSize(fileModel.getSize());

        String ans = debugInstence(fileModel.getName());
        String[] ss = ans.split("&");
        if (ans.split("&").length == 2){
            dir.setType_1(ss[0]);
            dir.setType_2(ss[1]);
        } else {
            dir.setType_1("other");
            dir.setType_2("other");
        }
        Integer ID = dirMap.insertSelective(dir);


        if (dir.getId() == null){
            dir.setId(ID);
        }
        if (dir.getCreatetime() == null){
            dir.setCreatetime(new Date());
        }
        return dir;
    }
    /*
     * 测试阶段使用
     */
    private String debugInstence(String word){
        boolean isEr = word.matches(".*[\u4e00-\u9fa5].*");
        String erString = isEr? "1":"2";
        // 图片 视频 音频 文本
        String[] imgs = {"pcx","emf","gif" , "bmp" , "tga" , "jpg" , "tif" , "jpeg" , "png","rle"};
        String[] videos = {"mp4","avi","mov","qt","asf","rm","navi","divx","mpeg"};
        String[] musics = {"mp3","wma","wav","midi","ape","flac"};
        String[] words = {"md","txt","js","word","docx","css","doc","ppt"};
        String answer;
        String[][] strs = {imgs,videos,musics,words};
        String[] types = {"图片","视频","音乐","文档"};
        for (int i = 0; i < 4; i++) {
            if (check(strs[i],word)){
                answer = types[i]+"&"+erString;
                return answer;
            }
        }
        return "other&other";
    }
    private Boolean check(String[] strs, String word){
        String newS = word.toLowerCase();
        for (int i = 0, len = strs.length; i < len; i++) {
            if (newS.contains(strs[i])){
                return true;
            }
        }
        return false;
    }
    // 上传文件 这个url没用到,作为保留
    public boolean uploadFile(FileModel fileModel){
        FileModel fileModel0 = new FileModel();
        fileModel0.setMd5(fileModel.getMd5());
        List<FileModel> list = fileMap.select(fileModel0);
        if (list.isEmpty() == true){
            return false;
        } else {
            fileMap.insertSelective(fileModel);
            return true;
        }
    }

    // getweka
    public List getWeka(Integer fid, int uid){
        fid = -fid;
        int m = fid/10-1;
        int n = fid%10;
        String[] types = {"图片","视频","音乐","文档","other"};
        Example exa = new Example(Dir.class);
        if (n!=0){
            exa.and().andEqualTo("type_1",types[m]).andEqualTo("uid",uid).andEqualTo("type_2",n+"");
        } else {
            exa.and().andEqualTo("type_1",types[m]).andEqualTo("uid",uid);
        }
        return dirMap.selectByExample(exa);
    }
    // ------------------------ 文件相关结束 ------------------------


}
