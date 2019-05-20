package cn.yyy.common.entity;

import cn.yyy.common.controller.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class ResponseData<T>  implements Serializable {

    /**
     * Message List
     */
    private List<Message> messages;

    private T data;

    public ResponseData() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage()));
        this.messages = messages;
    }

    public String getCode(){
        return this.getMessages().get(0).getCode();
    }

    public ResponseData(List<Message> messages) {
        this.messages = messages;
    }

    public ResponseData(String code, String message, T data) {
        List messages = new ArrayList();
        messages.add(new Message(code,message));
        this.messages = messages;
        this.data =data;
    }

    public ResponseData(T data) {
        this.data =data;
    }

    public ResponseData(String code, String message) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(code,message));
        this.messages = messages;
    }

}
