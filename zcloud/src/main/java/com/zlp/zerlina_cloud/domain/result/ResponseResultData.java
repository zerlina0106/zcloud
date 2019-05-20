package com.zlp.zerlina_cloud.domain.result;

public class ResponseResultData extends ResponseResult {
    private Object data;

    public ResponseResultData(Object data) {
        this.data = data;
    }
    
    public ResponseResultData(ExceptionMsg msg) {
    	  super(msg);
    }
    
    public ResponseResultData(String rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

    public ResponseResultData(String rspCode, String rspMsg, Object data) {
        super(rspCode, rspMsg);
        this.data = data;
    }

    public ResponseResultData(ExceptionMsg msg, Object data) {
        super(msg);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResultData{" +
                "data=" + data +
                "} " + super.toString();
    }
}
