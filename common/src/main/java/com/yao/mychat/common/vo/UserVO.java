package com.yao.mychat.common.vo;

import com.yao.mychat.common.putenum.UserStateEnum;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户视图对象
 */
@Data
public class UserVO {
    private Long userId; // 用户ID
    private String nickname; // 昵称
    private String avatar; // 头像
    private UserStateEnum onlineStatus; // 在线状态
    private LocalDateTime lastActiveTime; // 最后活跃时间
}
