package com.weka.mapper;

import com.weka.entity.KeyWord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public interface KeyWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KeyWord record);

    KeyWord selectByPrimaryKey(Integer id);

    List<KeyWord> selectAll();

    int updateByPrimaryKey(KeyWord record);

    List<KeyWord> selectByClassifyId(Integer classifyId);
}