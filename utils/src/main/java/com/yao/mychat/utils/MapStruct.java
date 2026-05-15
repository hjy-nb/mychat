package com.yao.mychat.utils;

import com.yao.mychat.common.dto.RegisterDTO;
import com.yao.mychat.common.entity.UserEntity;
import com.yao.mychat.common.vo.LoginVO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 对象转换接口
 */
@Mapper(componentModel = "spring")
public interface MapStruct {
    // 将用户实体转换为登录视图对象
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "avatar", source = "avatar")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "lastLoginTime", source = "lastLoginTime")
    public LoginVO getLoginVO(UserEntity userEntity);

    // 将注册信息转换为用户实体
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "nickname", source = "nickname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "lastLoginIp", source = "loginIp")
    public UserEntity convertRegisterDTO2UserEntity(RegisterDTO registerDTO) ;
}
