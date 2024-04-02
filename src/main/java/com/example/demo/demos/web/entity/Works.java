package com.example.demo.demos.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Works {

    //作品id
    @ApiModelProperty(value = "作品id")
    private Long id;

    //作品组别
    @ApiModelProperty(value = "作品组别")
    @NotBlank(message = "workGroup不能为空")
    private String workGroup;

    //作品名称
    @ApiModelProperty(value = "作品名称")
    @NotBlank(message = "workName不能为空")
    private String workName;

    //参赛姓名
    @ApiModelProperty(value = "参赛姓名")
    @NotBlank(message = "userName不能为空")
    private String userName;

    //联系方式
    @ApiModelProperty(value = "联系方式")
    @NotBlank(message = "userPhone不能为空")
    private String userPhone;

    //提交时间
    @ApiModelProperty(value = "提交时间")
    @NotBlank(message = "subTime不能为空")
    private Date subTime;

    //实时平均分
    @ApiModelProperty(value = "实时平均分")
    @NotBlank(message = "averageScore不能为空")
    private Double averageScore;

    //学校
    @ApiModelProperty(value = "学校")
    @NotBlank(message = "school不能为空")
    private String school;


}

