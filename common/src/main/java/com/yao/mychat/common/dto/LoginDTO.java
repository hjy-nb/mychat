package com.yao.mychat.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * 登录DTO
 */
@Data
@Builder
public class LoginDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;   // 用户名（必填）
    @NotBlank(message = "密码不能为空")
    private String password;   // 密码（必填）
    private String LoginIp;
}
