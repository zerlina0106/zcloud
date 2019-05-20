package cn.yyy.file.mapper;

import cn.yyy.file.model.Dir;
import cn.yyy.file.model.FileModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
@Component
@Transactional
public interface DirMap extends tk.mybatis.mapper.common.Mapper<Dir> {
    List<Dir> getDirWithUserIdAndDirId(Integer userId, Integer dirId);

    List<Dir> searchFileWithNameAndUser(FileModel fileModel);

}




