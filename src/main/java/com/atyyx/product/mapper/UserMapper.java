package com.atyyx.product.mapper;

import com.atyyx.product.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 21:59
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    Map<String,Object> selectMapById(long id);

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
