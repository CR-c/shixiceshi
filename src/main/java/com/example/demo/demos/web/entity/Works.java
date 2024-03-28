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
@ApiModel(value = "作品实体类")
public class Works {
 
       //作品id
       private Long id;
    
       //作品组别
       private String workGroup;
    
       //作品名称
       private String workName;
    
       //参赛姓名
       private String userName;
    
       //联系方式
       private String userPhone;
    
       //提交时间
       private Date subTime;
    
       //实时平均分
       private Double averageScore;
       //学校
       private String school;

  
}

