package com.example.demo.demos.web.service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.demos.web.entity.Works;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * (Works)表服务接口
 *
 * @author makejava
 * @since 2024-03-27 20:43:19
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface WorksService extends IService<Works> {

}
