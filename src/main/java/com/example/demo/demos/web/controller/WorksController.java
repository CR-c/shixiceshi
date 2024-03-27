package com.example.demo.demos.web.controller;
import cn.hutool.core.io.resource.InputStreamResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.demos.commons.R;
import com.example.demo.demos.web.entity.Works;
import com.example.demo.demos.web.service.WorksService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * (Works)表控制层
 *
 * @author makejava
 * @since 2024-03-27 20:43:19
 */
@Slf4j
@RestController
@RequestMapping("works")
public class WorksController {
    /**
     * 服务对象
     */
    @Autowired
    private WorksService worksService;

    //新增
    @PostMapping("/save")
    public R<String> save(@RequestBody Works works){
        works.setSubTime(new Date());
        return worksService.save(works)?R.success("新增成功"):R.error("新增失败");
    }

    //删除
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam Integer id){

        return worksService.removeById(id)?R.success("删除成功"):R.error("删除失败");
    }

    //修改
    @PostMapping("/update")
    public R<String> update(@RequestBody Works works){
        return worksService.updateById(works)?R.success("修改成功"):R.error("修改失败");
    }

    //查询
    @GetMapping("/getById")
    public R<Works> getById(@RequestParam Integer id){

        return R.success(worksService.getById(id));
    }

    @PostMapping("/list")
    public R<Page> getOrderList(@RequestBody Works works,@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "status") int status) {
        //分页查
        LambdaQueryWrapper<Works> queryWrapper = new LambdaQueryWrapper<>();
        if (works!=null){
            queryWrapper.eq(works.getWorkName()!=null,Works::getWorkName,works.getWorkName());
            queryWrapper.eq(works.getSchool()!=null,Works::getSchool,works.getSchool());
            queryWrapper.eq(works.getUserName()!=null,Works::getUserName,works.getUserName());
        }
        Page<Works> pageInfo = new Page<>(page, pageSize);
        Page<Works> paged = worksService.page(pageInfo, queryWrapper);
        return R.success(paged);
    }

    @GetMapping("/export-data")
    public ResponseEntity<InputStreamResource> exportData(@RequestBody Works works,@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "status") int status) throws IOException {
        // 假设这是从数据库分页查询得到的数据列表
        List<Works> pageData = fetchDataFromDatabase(works,page,pageSize, status);

        // 创建一个Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // 创建表头行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Column1");
        headerRow.createCell(1).setCellValue("Column2");
        // 更多列 ...

        // 填充数据行
        int rowNum = 1;
        for (Works data : pageData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getSchool());
            row.createCell(1).setCellValue(data.getGroup());
            row.createCell(2).setCellValue(data.getWorkName());
            row.createCell(3).setCellValue(data.getUserName());
            row.createCell(4).setCellValue(data.getUserPhone());
            row.createCell(5).setCellValue(data.getAverageScore());
            row.createCell(6).setCellValue(data.getSubTime());
            // 更多列 ...
        }

        // 将Excel数据写入到输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // 将输出流转换为输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        // 返回文件下载
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=export.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(inputStream));
    }

    private List<Works> fetchDataFromDatabase(Works works,int page,int pageSize,int status) {
        // 模拟数据库分页查询获取的数据
        LambdaQueryWrapper<Works> queryWrapper = new LambdaQueryWrapper<>();
        if (works!=null){
            queryWrapper.eq(works.getWorkName()!=null,Works::getWorkName,works.getWorkName());
            queryWrapper.eq(works.getSchool()!=null,Works::getSchool,works.getSchool());
            queryWrapper.eq(works.getUserName()!=null,Works::getUserName,works.getUserName());
        }
        Page<Works> pageInfo = new Page<>(page, pageSize);
        Page<Works> paged = worksService.page(pageInfo, queryWrapper);

        return paged.getRecords();
    }

}

