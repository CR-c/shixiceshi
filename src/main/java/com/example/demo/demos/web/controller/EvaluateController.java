package com.example.demo.demos.web.controller;

import cn.hutool.core.io.resource.InputStreamResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.demos.web.entity.Evaluate;
import com.example.demo.demos.web.entity.Works;
import com.example.demo.demos.web.service.EvaluateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.demos.commons.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * (Evaluate)表控制层
 *
 * @author makejava
 * @since 2024-03-28 00:53:02
 */
@Slf4j
@RestController
@Api(value = "评价服务接口")
@RequestMapping("evaluate")
public class EvaluateController {
    /**
     * 服务对象
     */
    @Autowired
    private EvaluateService evaluateService;

    static final int INDEX_OF_WorkGroup = 0;
    static final int Index_Of_School = 1;
    static final int Index_Of_WorkName = 2;
    static final int Index_Of_EvaluateUser = 3;
    static final int Index_Of_TeachImplement = 4;
    static final int Index_Of_TeachBook = 5;
    static final int Index_Of_TeachVideo = 6;
    static final int Index_Of_ProfessionBook = 7;
    static final int Index_Of_ClassStandard = 8;

    static final int Index_Of_TeachStuBook = 9;
    static final int Index_Of_Score= 10;
    static final int Index_Of_EvaluateTime = 11;


    //新增
    @ApiOperation("新增评价")
    @PostMapping("/save")
    public R<String> save(@Validated @RequestBody Evaluate evaluate){

        evaluateService.save(evaluate);
        return R.success("新增成功");
    }

    //删除
    @ApiOperation("删除评价")
    @DeleteMapping("/delete")
    public R<String> delete(@Validated @RequestParam("id") Integer id){

        evaluateService.removeById(id);
        return R.success("删除成功");
    }

    //修改
    @ApiOperation("修改评价")
    @PutMapping("/update")
    public R<String> update(@Validated @RequestBody Evaluate evaluate){

        evaluateService.updateById(evaluate);
        return R.success("修改成功");
    }

    //查询
    @ApiOperation("查询评价")
    @GetMapping("/find")
    public R<Evaluate> findById(@Validated @RequestParam("id") Integer id){

        Evaluate evaluate = evaluateService.getById(id);
        return R.success(evaluate);
    }

    //分页查询
    @ApiOperation("分页查询评价")
    @PostMapping("/findPage")
    public R<Page<Evaluate>> findPage(@Validated @RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize,
                                      @RequestBody Evaluate evaluate){
        Page<Evaluate> paged = fetchDataFromDatabase(evaluate,page, pageSize);
        return R.success(paged);
    }

    //导出分页查询的数据
    @ApiOperation("导出分页查询的数据")
    @GetMapping("/export-data")
    public ResponseEntity<InputStreamResource> exportData(@Validated @RequestBody Evaluate evaluate, @RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize) throws IOException {
        // 假设这是从数据库分页查询得到的数据列表
        List<Evaluate> pageData = fetchDataFromDatabase(evaluate,page,pageSize).getRecords();

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
        for (Evaluate data : pageData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(INDEX_OF_WorkGroup).setCellValue(data.getWorkGroup());
            row.createCell(Index_Of_School).setCellValue(data.getSchool());
            row.createCell(Index_Of_WorkName).setCellValue(data.getWorkName());
            row.createCell(Index_Of_EvaluateUser).setCellValue(data.getEvaluateUser());
            row.createCell(Index_Of_TeachImplement).setCellValue(data.getTeachImplement());
            row.createCell(Index_Of_TeachBook).setCellValue(data.getTeachBook());
            row.createCell(Index_Of_TeachVideo).setCellValue(data.getTeachVideo());
            row.createCell(Index_Of_ProfessionBook).setCellValue(data.getProfessionBook());
            row.createCell(Index_Of_ClassStandard).setCellValue(data.getClassStandard());
            row.createCell(Index_Of_TeachStuBook).setCellValue(data.getTeachStuBook());
            row.createCell(Index_Of_Score).setCellValue(data.getScore());
            row.createCell(Index_Of_EvaluateTime).setCellValue(data.getEvaluateTime());

            // 更多列 ...
        }

        // 将Excel数据写入到输出流
        ByteArrayInputStream inputStream;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();

            // 将输出流转换为输入流
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        }

        // 返回文件下载
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=export.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(inputStream));
    }

    private Page<Evaluate> fetchDataFromDatabase(@Validated Evaluate evaluate,int page,int pageSize) {
        // 模拟数据库分页查询获取的数据
        LambdaQueryWrapper<Evaluate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(evaluate.getEvaluateUser() != null, Evaluate::getEvaluateUser, evaluate.getEvaluateUser());
        queryWrapper.eq(evaluate.getWorkName() != null, Evaluate::getWorkName, evaluate.getWorkName());
        queryWrapper.eq(evaluate.getSchool() != null, Evaluate::getSchool, evaluate.getSchool());
        queryWrapper.eq(evaluate.getWorkUserName() != null, Evaluate::getWorkUserName, evaluate.getWorkUserName());
        queryWrapper.eq(evaluate.getWorkGroup() != null, Evaluate::getWorkGroup, evaluate.getWorkGroup());
        Page<Evaluate> pageInfo = new Page<>(page, pageSize);

        return evaluateService.page(pageInfo, queryWrapper);
    }

}

