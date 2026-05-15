package com.yao.mychat.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

/**
 * 注册信息DTO
 */
@Data
@Builder
public class RegisterDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;   // 用户名（必填）
    @NotBlank(message = "密码不能为空")
    private String password;   // 密码（必填）
    @NotBlank(message = "呢称不能为空")
    private String nickname;   // 昵称（必填）
    @Email(message = "邮箱格式错误")
    private String email;      // 邮箱（可选）
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String phone;      // 手机号（可选）
    private String loginIp;  // 登录IP
}
