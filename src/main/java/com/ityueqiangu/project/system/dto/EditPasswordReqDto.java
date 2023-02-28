package com.ityueqiangu.project.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 编辑密码请求实体类
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.project.system.dto
 * @ClassName: EditPasswordReqDto
 * @FileName: EditPasswordReqDto.java
 * @CreateDate: 2021-11-18 20:43:07
 * @Author: FlowerStone
 * @Copyright
 */
@Data
public class EditPasswordReqDto {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

}
