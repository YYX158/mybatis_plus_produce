package com.atyyx.product;

import com.atyyx.product.Enum.Sex;
import com.atyyx.product.mapper.UserMapper;
import com.atyyx.product.pojo.User;
import com.atyyx.product.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:25
 */
@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    /**
     * 利用封装好的Service层去获取数据数量
     */
    @Test
    public void test1()
    {
        //SELECT COUNT( 1 ) FROM user
        int count = userService.count();
        System.out.println(count);
    }

    /**
     * 创建一个有序的列表，通过列表向数据库中插入数据
     */
    @Test
    public void test2()
    {
        /**
         *  Preparing: INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
         * ==> Parameters: 1558064286395211778(Long), cbz(String), 22(Integer), cbz@qq.com(String)
         * ==> Parameters: 1558064286546206722(Long), yjh(String), 23(Integer), yjh@qq.com(String)
         * ==> Parameters: 1558064286546206723(Long), qll(String), 21(Integer), qll@qq.com(String)
         * ==> Parameters: 1558064286546206724(Long), ccy(String), 23(Integer), ccy@qq.com(String)
         */
        List<User> list=new ArrayList<>();
        list.add(new User("cbz",22,"cbz@qq.com", Sex.Boy));
        list.add(new User("yjh",23,"yjh@qq.com",Sex.Boy));
        list.add(new User("qll",21,"qll@qq.com",Sex.Gril));
        list.add(new User("ccy",23,"ccy@qq.com",Sex.Boy));
        userService.saveBatch(list);
    }
}
