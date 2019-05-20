package cn.yyy.common.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message implements Serializable {
    /**
     * Code
     */
    private String code;

    /**
     * Message
     */
    private String message;

}
