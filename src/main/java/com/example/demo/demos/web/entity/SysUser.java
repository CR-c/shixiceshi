package com.example.demo.demos.web.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体类")
public class SysUser {

    //用户id
    private Long id;

    //用户姓名
    private String userName;

    //用户身份
    private String userStatus;

    //用户账号
    private String userAccount;

    //用户密码
    private String userPassword;

    //报名组别
    private String workGroup;

    //学校
    private String school;


}

