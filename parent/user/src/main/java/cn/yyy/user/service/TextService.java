package cn.yyy.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class TextService {
    private final String ApplicatioID = "b7dd7e700fad966202c3854d76fd0342";
    private final String RESTAPIKey = "133487dd14cbcb2bd0ceea1173fca360";
    private final String sendUrl = "https://api2.bmob.cn/1/requestSmsCode";
    private final String checkUrl = "https://api2.bmob.cn/1/verifySmsCode/";

    class Message{
        private String mobilePhoneNumber;
        // templates 备用属性
        private String template;
        Message(String phone){
            this.mobilePhoneNumber = phone;
        }
        Message(String phone, String template){
            this.mobilePhoneNumber = phone;
            this.template = template;
        }
        public String getMobilePhoneNumber() {
            return mobilePhoneNumber;
        }

        public String getTemplate() {
            return template;
        }

        public void setMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
        }

        public void setTemplate(String template) {
            this.template = template;
        }
    }
    public String sendMessage(String phone){
        Message mes = new Message(phone);
        String param = JSON.toJSON(mes).toString();
        return POST(param, sendUrl);
    }

    public Boolean checkMessage(String phone, String verifyCode){
        String param = JSON.toJSONString(new Message(phone));
        String post = POST(param, checkUrl+verifyCode);
        JSONObject obj = JSON.parseObject(post);
        if (obj==null){
            return false;
        }
        Object msg = obj.get("msg");
        if (msg==null){
            return false;
        } else {
            return msg.equals("ok");
        }
    }

    private String POST(String param, String postUrl){
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("X-Bmob-Application-Id", ApplicatioID);
            conn.setRequestProperty("X-Bmob-REST-API-Key", RESTAPIKey);
            conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            return "";
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
