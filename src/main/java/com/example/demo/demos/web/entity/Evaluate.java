package com.example.demo.demos.web.entity;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "评价实体类")
public class Evaluate {

    //评价id
    @ApiModelProperty(value = "评价id")
    private Long id;


    //评委名
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "评委名")
    private String evaluateUser;

    //作品名
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "作品名")
    private String workName;

    //学校名
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "学校名")
    private String school;

    //参赛人
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "参赛人")
    private String workUserName;

    //组别
    @NotBlank(message = "组别不能为空")
    @ApiModelProperty(value = "组别")
    private String workGroup;

    //教案
    @NotBlank(message = "教案不能为空")
    @ApiModelProperty(value = "教案")
    private String teachBook;

    //视频资料
    @NotBlank(message = "视频资料不能为空")
    @ApiModelProperty(value = "视频资料")
    private String teachVideo;

    //专业人才培养方案
    @NotBlank(message = "专业人才培养方案不能为空")
    @ApiModelProperty(value = "专业人才培养方案")
    private String professionBook;

    //课程标准
    @NotBlank(message = "课程标准不能为空")
    @ApiModelProperty(value = "课程标准")
    private String classStandard;

    //分数
    @NotBlank(message = "分数不能为空")
    @ApiModelProperty(value = "分数")
    private Integer score;

    //评价时间
    @ApiModelProperty(value = "评价时间")
    @FutureOrPresent(message = "评价时间必须是当前时间")
    private Date evaluateTime;
    //教学实施
    @ApiModelProperty(value = "教学实施")
    @NotBlank(message = "分数不能为空")
    private String teachImplement;
    //教材
    @NotBlank(message = "分数不能为空")
    @ApiModelProperty(value = "教材")
    private String teachStuBook;


}

