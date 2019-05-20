package com.weka.mapper;

import com.weka.entity.Classify;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public interface ClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classify record);

    Classify selectByPrimaryKey(Integer id);

    List<Classify> selectAll();

    int updateByPrimaryKey(Classify record);
}