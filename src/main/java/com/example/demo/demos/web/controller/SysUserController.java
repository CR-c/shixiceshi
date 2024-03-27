package com.example.demo.demos.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.demos.commons.R;
import com.example.demo.demos.web.entity.SysUser;
import com.example.demo.demos.web.entity.Works;
import com.example.demo.demos.web.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2024-03-27 20:42:23
 */
@Slf4j
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;

    private static final String UPLOAD_DIR = "path/static"; // 更改为您希望保存上传文件的目录

    @PostMapping("/save")
    public R<String> save(@RequestBody SysUser sysUser){
        boolean save = sysUserService.save(sysUser);
        if(save){
            return R.success("保存成功");
        }
        return R.error("保存时报");
    }

    //删除SysUser
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam Integer id){
        boolean delete = sysUserService.removeById(id);
        if(delete){
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }

    //修改
    @PostMapping("/update")
    public R<String> update(@RequestBody SysUser sysUser){
        boolean update = sysUserService.updateById(sysUser);
        if(update){
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

    //查询
    @PostMapping("/getById")
    public R<SysUser> getById(@RequestParam Integer id){
        SysUser sysUser = sysUserService.getById(id);
        if(sysUser != null){
            return R.success(sysUser);
        }
        return R.error("查询失败");
    }
    @RequestMapping("/list")
    public R<Page> getOrderList(@RequestBody SysUser sysUser, @RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "status") int status) {
        //分页查
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(sysUser.getUserStatus()!=null, SysUser::getUserStatus, sysUser.getUserStatus());
        queryWrapper.eq(sysUser.getUserName()!=null, SysUser::getUserName, sysUser.getUserName());
        queryWrapper.eq(sysUser.getSchool()!=null, SysUser::getSchool, sysUser.getSchool());
        Page<SysUser> pageInfo = new Page<>(page, pageSize);
        Page<SysUser> paged = sysUserService.page(pageInfo, queryWrapper);
        return R.success(paged);
    }

    //下载模板
    @GetMapping("/download-file")
    public ResponseEntity<Resource> downloadFile() {
        // 这里填入你想下载的文件的路径
        Path filePath = Paths.get("path/static/user.xlsx");
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                // 确保设置正确的content type
                String contentType = "application/octet-stream";
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                // 文件不存在或者不可读，则返回错误信息
                throw new RuntimeException("Could not read the file!");
            }
        } catch (IOException ex) {
            // 处理文件访问异常
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected to upload!");
        }
        try {
            // 保存文件到服务器
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            return ResponseEntity.ok().body("You successfully uploaded " + file.getOriginalFilename() + "!");

        } catch (IOException e) {
            // 处理文件保存错误
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Server Error: Couldn't upload the file!");
        }
    }
}

