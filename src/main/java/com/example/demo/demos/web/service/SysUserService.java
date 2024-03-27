package com.example.demo.demos.web.service;

import org.springframework.transaction.annotation.Transactional;
import com.example.demo.demos.web.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2024-03-27 20:42:24
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface SysUserService extends IService<SysUser> {

}
