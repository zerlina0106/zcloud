package cn.yyy.common.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class BaseEntity implements Serializable {
    //页码
    @Transient
    private Integer pageNum;
    //每页显示记录数
    @Transient
    private Integer pageSize;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @Column(name = "CREATE_TIMESTAMP")
    private Date createTimestamp;

    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;

    @Column(name = "UPDATE_TIMESTAMP")
    private Date updateTimestamp;
}
