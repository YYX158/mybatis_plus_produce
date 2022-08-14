package com.atyyx.product;

import com.atyyx.product.Enum.Sex;
import com.atyyx.product.mapper.UserMapper;
import com.atyyx.product.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:23
 */
@SpringBootTest
public class MybatisPlusEnumTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test1()
    {
        User user=new User("yyx",22, "yyx@qq.com", Sex.Boy);
        userMapper.insert(user);
        List<User> users = userMapper.selectList(null);
        users.stream().forEach(System.out::println);
    }
}
