package cn.yyy.file.util;

import cn.yyy.common.YYYException;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class FileUtil {
    private static final int ErrCode_io = -3;
    private static final int ErrCode_fileNotFound = -4;
    private static final int ErrCode_fileReName = -4;
    private static final String ioError = "IOException";
    private static final String fileNotFoundError = "没有找到文件";
    private static final String fileReNameError = "文件转换名字失败";

    private static final String basePath = "/Users/yangyangyang/Documents/大作业2/files/";
    // 返回是否支持ipfs
//    static public boolean ifIPFS(){
//        return true;
//    }


//    static private String getMD5AndChangeName(File file) throws IOException, NoSuchAlgorithmException {
//
//        FileInputStream fis = new FileInputStream(file);
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = fis.read(buffer, 0, 1024)) != -1) {
//            md.update(buffer, 0, length);
//        }
//        BigInteger bigInt = new BigInteger(1, md.digest());
//        String md5 = bigInt.toString(16);
//        File newFile = new File(basePath +md5);
//        boolean success = file.renameTo(newFile);
//        if (success == false){
//            throw new YYYException(ErrCode_fileReName,fileReNameError);
//        }
//        return md5;
//
//    }

//
//    // 根据md5值返回文件
//    static public File getFile(String fileName) throws YYYException {
//        String path = basePath + fileName;
//        File file = new File(path);
//        if (file.exists()){
//            return file;
//        } else {
//            return null;
//        }
//    }


    // 返回hash值
    static public String saveFile(MultipartFile file) throws YYYException {
        try {
            // 传统方法
//            File serverFile = new File(basePath + fileName);
//            file.transferTo(serverFile);
//            upload(file.getBytes());
//            return getMD5AndChangeName(serverFile);

            // IPFS方法
            String hash = upload(file.getBytes());
            log.info("文件存储成功, hash值是"+hash);
            return hash;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            throw new YYYException(ErrCode_fileNotFound, fileNotFoundError);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new YYYException(ErrCode_io, ioError);
        }
    }

    static public byte[] getFile0(String hash) throws YYYException {
        try {
            return download(hash);
        } catch (IOException e) {
            e.printStackTrace();
            throw new YYYException(ErrCode_io, ioError);
        }
    }


    static private IPFS ipfs;// ipfs的服务器地址和端口
    private static IPFS getIPFS(){
        if (ipfs == null){
            ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        }
        return ipfs;
    }
    private static String upload(byte[] file0) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(file0);
        MerkleNode addResult = getIPFS().add(file).get(0);
        return addResult.hash.toString();
    }
    private static byte[] download(String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        return getIPFS().cat(filePointer);
    }
}
