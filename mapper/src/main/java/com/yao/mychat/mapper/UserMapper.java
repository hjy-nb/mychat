package com.yao.mychat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yao.mychat.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper类接口
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
