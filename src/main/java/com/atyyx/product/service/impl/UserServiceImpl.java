package com.atyyx.product.service.impl;

import com.atyyx.product.mapper.UserMapper;
import com.atyyx.product.pojo.User;
import com.atyyx.product.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 21:59
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
