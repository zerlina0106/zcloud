package cn.yyy.common.controller;

import cn.yyy.common.YYYException;
import cn.yyy.common.entity.Message;
import cn.yyy.common.entity.ResponseData;
import cn.yyy.common.entity.TableData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


abstract public class BaseController {
    @Autowired
    protected MessageSource messageSource;

    protected final String CODE_SUCCESS = "1000";
    protected final String CODE_FAIL = "-1";
    protected final String USER_ID = "userId";
    protected final String USER_NAME = "userName";

    /**
     * 测试是否启动成功的网址
     */
    @RequestMapping("test")
    public String test(){
        return "test";
    }

    protected ResponseData getTableData() {
        return new ResponseData();
    }
    protected ResponseData getTableData(Object o) {
        if (o instanceof List){
            return getTableDataWithList((List) o);
        }
        if (o instanceof YYYException){
            return getTableDataWithException((YYYException) o);
        }
        if (o == null){
            return getTableData();
        }
        return getTableDataWithObject(o);
    }
    protected ResponseData getTableDataWithList(List list) {

        TableData<Object> data = new TableData<>();
        data.setTotal((long) list.size());
        data.setRows(list);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage()));

        return new ResponseData<>(messages, data);
    }
    protected ResponseData getTableDataWithException(YYYException exception) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(exception.errorCode+"", exception.result));

        return new ResponseData<>(messages);
    }

    protected ResponseData getTableDataWithObject(Object o) {

        List<Object> list = new LinkedList<>();
        list.add(o);
        return getTableDataWithList(list);
    }
    protected ResponseData getTableDataWithMessage(String code, String str) {

        return new ResponseData<>(code, str);
    }
}
