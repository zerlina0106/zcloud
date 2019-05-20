package cn.yyy.common.controller;

public enum ResponseCode {

    SUCCESS("1000", "success."),
    ERROR("1001", "error."),
    PARA_ERROR("1002", "parameters error.");

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
