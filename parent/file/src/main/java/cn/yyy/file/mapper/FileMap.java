package cn.yyy.file.mapper;

import cn.yyy.file.model.FileModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
@Component
@Transactional
public interface FileMap extends tk.mybatis.mapper.common.Mapper<FileModel> {

}




