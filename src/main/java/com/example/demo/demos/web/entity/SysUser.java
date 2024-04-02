package com.example.demo.demos.web.entity;

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
public class SysUser {
 
       //用户id
      @ApiModelProperty(value="用户id")
//   @NotBlank(message="id不能为空")
   private Long id;
    
       //用户姓名
      @ApiModelProperty(value="用户姓名")
   @NotBlank(message="userName不能为空")
   private String userName;
    
       //用户身份
      @ApiModelProperty(value="用户身份")
   @NotBlank(message="userStatus不能为空")
   private String userStatus;
    
       //用户账号
      @ApiModelProperty(value="用户账号")
   @NotBlank(message="userAccount不能为空")
   private String userAccount;
    
       //用户密码
      @ApiModelProperty(value="用户密码")
   @NotBlank(message="userPassword不能为空")
   private String userPassword;
    
       //报名组别
      @ApiModelProperty(value="报名组别")
   @NotBlank(message="workGroup不能为空")
   private String workGroup;
    
       //学校
      @ApiModelProperty(value="学校")
   @NotBlank(message="school不能为空")
   private String school;
    

  
}

