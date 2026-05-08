package com.yao.mychat.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yao.mychat.common.putenum.UserStateEnum;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@TableName("user")
public class UserEntity {
    @TableId(value = "user_id")
    private Long userId; // 用户ID
    @TableField(value = "username")
    private String username; // 用户名
    @TableField(value = "password")
    private String password; // 密码
    @TableField(value = "nickname")
    private String nickname; // 昵称
    @TableField(value = "avatar")
    private String avatar; // 头像URL
    @TableField(value = "phone")
    private String phone; // 手机号
    @TableField(value = "email")
    private String email; // 邮箱
    @TableField(value = "gender")
    private Integer gender; // 性别
    @TableField(value = "birthday")
    private LocalDateTime birthday; // 生日
    @TableField(value = "signature")
    private String signature; // 个性签名
    @TableField(value = "status")
    private UserStateEnum status; // 状态(0:离线,1:在线,2:忙碌)
    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime; // 最后登录时间
    @TableField(value = "last_login_ip")
    private String lastLoginIp; // 最后登录IP
    @TableField(value = "create_time")
    private LocalDateTime createTime; // 创建时间
    @TableField(value = "update_time")
    private LocalDateTime updateTime; // 更新时间
}
