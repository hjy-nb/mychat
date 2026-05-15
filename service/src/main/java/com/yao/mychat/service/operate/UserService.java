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
import com.yao.mychat.utils.MapStruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * 用户服务类
 *
 * Session = 服务端的"临时记事本"，记录谁登录了
 *  session.setAttribute(String name, Object value) - 存储数据
 *  session.getAttribute(String name) - 获取数据
 *  session.invalidate() - 销毁会话
 *  request.getSession() - 获取当前会话对象
 */
@Service
public class UserService extends ServiceImpl<UserMapper,UserEntity> implements UserIService {
    private static final Logger BUSINESS_LOGGER = LoggerOffer.getBusinessLogger();

    @Autowired
    MapStruct mapstruct;


    private HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("无法获取会话");
        }
        return attributes.getRequest().getSession();
    }


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
        UserEntity userEntity = mapstruct.convertRegisterDTO2UserEntity(registerDTO);
        save(userEntity);

        BUSINESS_LOGGER.info("用户注册成功，用户ID：{}", userEntity.getUserId());

        return userEntity.getUserId();
    }

    //用户登录
    public LoginVO login(LoginDTO loginDTO) {
        if(!checkUserNameExists(loginDTO.getUsername())){
            throw new UserNameNotValid("用户名错误");
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

        return mapstruct.getLoginVO(userEntity);
    }

    // 用户登出
    public void logout(Long userId) {
        lambdaUpdate().set(UserEntity::getStatus, UserStateEnum.OFFLINE)
                .eq(UserEntity::getUserId, userId)
                .update();

        BUSINESS_LOGGER.info("用户登出成功，用户ID：{}", userId);
    }

    //用户注销
    public void deleteUser(Long userId) {}

    //修改密码



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


}
