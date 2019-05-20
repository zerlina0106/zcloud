package cn.yyy.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 短信消息模板
 */
@Data
@AllArgsConstructor
@ToString
public class SmsMessage implements Serializable {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 文本
     */
    private String text;
    /**
     * 类型（通道）
     */
    private String type;

}
