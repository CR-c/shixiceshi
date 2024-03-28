package com.example.demo.demos.web.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
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
    private Long id;

    //评委名
    private String evaluateUser;

    //作品名
    private String workName;

    //学校名
    private String school;

    //参赛人
    private String workUserName;

    //组别
    private String workGroup;

    //教案
    private String teachBook;

    //视频资料
    private String teachVideo;

    //专业人才培养方案
    private String professionBook;

    //课程标准
    private String classStandard;

    //分数
    private Integer score;

    //评价时间
    private Date evaluateTime;
    //教学实施
    private String teachImplement;
    //教材
    private String teachStuBook;


}

