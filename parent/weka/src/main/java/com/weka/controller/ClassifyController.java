package com.weka.controller;

import com.weka.entity.Result;
import com.weka.service.ClassifyService;
import com.weka.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shan on 2017/12/14.
 */
@Slf4j
@RestController
public class ClassifyController {
    @Autowired
    private ClassifyService classifyService;

    @GetMapping("/exerciseModel")
    public Result<String> modelExercise() {
        try {
            classifyService.createWekaModel();
            return ResultUtil.success("训练模型成功");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return ResultUtil.error(500, "训练模型失败");
    }

    @GetMapping("/getResult/{word}")
    public Result<String> modelExercise(@PathVariable("word")String word) {
        try {
            String result = classifyService.getResultByExecuteParticipleAndClassify(word);
            return ResultUtil.success(result);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return ResultUtil.error(500, "训练模型失败");
    }

}