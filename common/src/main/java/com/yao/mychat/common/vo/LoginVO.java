package com.yao.mychat.common.vo;

import com.yao.mychat.common.putenum.UserStateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录视图对象
 */
@Data
@Builder
public class LoginVO {
    private Long userId;              // 用户ID
    private String username;          // 用户名
    private String nickname;          // 昵称
    private String avatar;            // 头像
    private UserStateEnum status;     // 在线状态
    private LocalDateTime lastLoginTime; // 最后登录时间
}
