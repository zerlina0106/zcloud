package cn.yyy.file.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "dir")
public class Dir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uid;

    private Integer fid;

    private Integer superid;

    private String name;

    private Date createtime;

    @Column(name = "TYPE_1")
    private String type_1;
    @Column(name = "TYPE_2")
    private String type_2;

    @Transient
    private Integer size;
}