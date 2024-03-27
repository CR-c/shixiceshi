package com.example.demo.demos.web.service;

import org.springframework.transaction.annotation.Transactional;
import com.example.demo.demos.web.entity.Evaluate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (Evaluate)表服务接口
 *
 * @author makejava
 * @since 2024-03-28 00:53:02
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface EvaluateService extends IService<Evaluate> {

}
