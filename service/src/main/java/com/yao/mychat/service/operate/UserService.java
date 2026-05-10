package com.yao.mychat.service.operate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.mychat.common.dto.LoginDTO;
import com.yao.mychat.common.dto.RegisterDTO;
import com.yao.mychat.common.entity.UserEntity;
import com.yao.mychat.common.putenum.UserStateEnum;
import com.yao.mychat.common.vo.LoginVO;
import com.yao.mychat.config.LoggerOffer;
import com.yao.mychat.mapper.UserMapper;
import com.yao.mychat.service.Tinterface.UserIService;
import com.yao.mychat.service.except.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * 用户服务类
 */
@Service
public class UserService extends ServiceImpl<UserMapper,UserEntity> implements UserIService {
    private static final Logger BUSINESS_LOGGER = LoggerOffer.getBusinessLogger();
    // 用户注册
    //不符合条件建议抛异常
    public Long register(RegisterDTO registerDTO) {
        if (checkUserNameExists(registerDTO.getUsername())) {
            throw new UserNameExist("用户名已存在");
        }

        if (registerDTO.getEmail() != null && !registerDTO.getEmail().isEmpty() && checkEmailExists(registerDTO.getEmail())) {
            throw new EmailExist("邮箱已存在");
        }

        if (registerDTO.getPhone() != null && !registerDTO.getPhone().isEmpty() && checkPhoneExists(registerDTO.getPhone())) {
            throw new PhoneExist("手机号已存在");
        }

        //保存用户
        UserEntity userEntity = convertRegisterDTO2UserEntity(registerDTO);
        save(userEntity);

        BUSINESS_LOGGER.info("用户注册成功，用户ID：{}", userEntity.getUserId());

        return userEntity.getUserId();
    }

    //用户登录
    public LoginVO login(LoginDTO loginDTO) {
        if(!checkUserNameExists(loginDTO.getUsername())){
            throw new UserNameNotValid("用户名不存在,请注册");
        }

        UserEntity userEntity= lambdaQuery().eq(UserEntity::getUsername, loginDTO.getUsername())
                .one();

        if(!userEntity.getPassword().equals(loginDTO.getPassword())){
            throw new PasswordError("密码错误");
        }

        //更新用户状态、最后登录时间、最后登录IP
        lambdaUpdate().set(UserEntity::getStatus, UserStateEnum.ONLINE)
                .set(UserEntity::getLastLoginTime, LocalDateTime.now())
                .set(UserEntity::getLastLoginIp, loginDTO.getLoginIp())
                .eq(UserEntity::getUserId, userEntity.getUserId())
                .update();

        BUSINESS_LOGGER.info("用户登录成功，用户ID：{},登录时间：{},登录ip：{}", userEntity.getUserId(),
                userEntity.getLastLoginTime().toString(), userEntity.getLastLoginIp());

        return getLoginVO(userEntity);
    }

    // 用户登出
    public void logout(Long userId) {
        lambdaUpdate().set(UserEntity::getStatus, UserStateEnum.OFFLINE)
                .eq(UserEntity::getUserId, userId)
                .update();

        BUSINESS_LOGGER.info("用户登出成功，用户ID：{}", userId);
    }




    // 检查用户名是否已存在
    private boolean checkUserNameExists(String username) {
        return lambdaQuery().eq(UserEntity::getUsername, username)
                .exists();
    }

    // 检查邮箱是否已存在
    private boolean checkEmailExists(String email) {
        return lambdaQuery().eq(UserEntity::getEmail, email)
                .exists();
    }

    // 检查手机号是否已存在
    private boolean checkPhoneExists(String phone) {
        return lambdaQuery().eq(UserEntity::getPhone, phone)
                .exists();
    }


    // 将注册信息转换为用户实体
    private UserEntity convertRegisterDTO2UserEntity(RegisterDTO registerDTO) {
        LocalDateTime time = LocalDateTime.now();

        return UserEntity.builder()
                .username(registerDTO.getUsername()) //更新用户名
                .password(registerDTO.getPassword()) //更新密码
                .nickname(registerDTO.getNickname()) //更新昵称
                .email(registerDTO.getEmail()) //更新邮箱
                .phone(registerDTO.getPhone()) //更新手机号
                .status(UserStateEnum.ONLINE) //更新状态
                .lastLoginTime(time)    //更新最后登录时间
                .lastLoginIp("127.0.0.1")   //更新最后登录IP
                .createTime(time)   //更新创建时间
                .updateTime(time) //更新更新时间
                .build(); //构建用户实体
    }

    //返回loginVO视图
    private LoginVO getLoginVO(UserEntity userEntity) {
        return  LoginVO.builder()
                .userId(userEntity.getUserId()) //返回用户ID
                .username(userEntity.getUsername())  //返回用户名
                .nickname(userEntity.getNickname()) //返回昵称
                .avatar(userEntity.getAvatar()) //返回头像
                .status(userEntity.getStatus())  //返回状态
                .lastLoginTime(userEntity.getLastLoginTime()) //返回最后登录时间
                .build(); //构建登录视图

    }

}
