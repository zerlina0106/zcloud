package cn.yyy.common;


public class YYYException extends RuntimeException {
    public int errorCode;
    public String result;
    public YYYException(int errorCode, String result){
        this.errorCode = errorCode;
        this.result = result;
    }
}
