package cn.yyy.file.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Table(name = "file")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;

    private String name;

    private Integer size;

    private String md5;


    static public final String FID = "fid";
    static public final String NAME = "name";
    static public final String SIZE = "size";
    static public final String MD5 = "md5";
    static public final String USER_ID = "userid";
    static public final String PARENT_ID = "parentid";
}