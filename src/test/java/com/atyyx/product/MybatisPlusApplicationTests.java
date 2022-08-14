package com.atyyx.product;

import com.atyyx.product.Enum.Sex;
import com.atyyx.product.mapper.UserMapper;
import com.atyyx.product.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:09
 */
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    /**
     * 数据库中id为BigInteger类型，所以在实体类中定义的是Long类型
     * Long类型在java中的数据要体现为：数字+L
     */
    @Test
    void contextLoads() {
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }

    /**
     * 获取所有的数据，返回为列表
     */
    @Test
    void test2() {
        // 设置查找条件为空
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    /**
     * 创建数据表对应的实体类，通过将实体类对象插入到数据表中
     * 从而完成新增功能的实现
     */
    @Test
    public void test3() {
        User yyx = new User("yyx", 23, "yyx@qq.com", Sex.Boy);
        userMapper.insert(yyx);
        // 用雪花算法来算出来Id
        System.out.println(yyx);
    }

    /**
     * 有了假删除操作以后，就直接把delete改成update了
     * ==>  Preparing: UPDATE User SET is_delete=1 WHERE name = ? AND age = ? AND is_delete=0
     * ==> Parameters: ccy(String), 23(Integer)
     * <p>
     * 查询的时候也会自动带上
     * SELECT id,name,age,email,is_delete FROM User WHERE is_delete=0
     */
    @Test
    public void test4() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "ccy");
        map.put("age", 23);
        userMapper.deleteByMap(map);
        List<User> users = userMapper.selectList(null);
        users.stream().forEach(System.out::println);
    }

    /**
     * 用Collection集合顶级接口去做查找条件，放入selectBatchIds中去查询
     */
    @Test
    public void test5() {
        Collection coll = Arrays.asList(2L, 3L);
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
        List list = userMapper.selectBatchIds(coll);
        list.stream().forEach(System.out::println);
    }

    /**
     * 返回一个map集合
     */
    @Test
    public void test6() {
        //select id,name,age,email from user where id=?
        Map<String, Object> map = userMapper.selectMapById(2L);
        System.out.println(map);
    }

    /**
     * 查询测试
     * ==>  Preparing:
     * SELECT id,name,age,email,is_delete FROM User
     * WHERE is_delete=0 AND (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
     * ==> Parameters: %王%(String), 18(Integer), 35(Integer)
     * <==    Columns: id, name, age, email, is_delete
     * <==        Row: 8, 王五, 32, wangwu@baomidou.com, 0
     * <==      Total: 1
     */
    @Test
    public void test7() {
        // 查看用户名包括“王”，年龄在18岁到35岁之间，邮箱不为空的。
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "王")
                .between("age", 18, 35).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.stream().forEach(System.out::println);
    }


    /**
     * SELECT id,name,age,email,is_delete FROM User WHERE is_delete=0 ORDER BY age DESC,id ASC
     */
    @Test
    public void test8() {
        // 查询用户信息，按照年龄的降序排序;若年龄相同，则按照id升序排列
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.stream().forEach(System.out::println);
    }

    /**
     * UPDATE User SET is_delete=1 WHERE is_delete=0 AND (email IS NULL)
     */
    @Test
    public void test9() {
        // 删除表中邮箱为空的
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        userMapper.delete(queryWrapper);
    }

    /**
     * ==>  Preparing:
     * UPDATE User SET age=?
     * WHERE is_delete=0
     * AND (age > ? AND name LIKE ? OR email IS NULL)
     * ==> Parameters: 21(Integer), 20(Integer), %王%(String)
     */
    @Test
    public void test10() {
        //将年龄＞20并且用户名中含有王  或者邮箱为null的用户的信息进行修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20).like("name", "王").or().isNull("email");
        User user = new User();
        user.setAge(21);
        userMapper.update(user, queryWrapper);
    }


    /**
     * ==>  Preparing:
     * UPDATE User SET name=?, email=?
     * WHERE is_delete=0
     * AND (name LIKE ? AND (age > ? OR email IS NULL))
     * ==> Parameters: 小红(String), xiaohong@test.com(String), %王%(String), 20(Integer)
     */
    @Test
    public void test11() {
        // 将用户名中包含有王并且（年龄大于20或者邮箱为NULL）的用户信息修改
        //lambda中条件执行的优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "王").and(s -> s.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("xiaohong@test.com");
        userMapper.update(user, queryWrapper);
    }

    /**
     * ==>  Preparing:
     * SELECT id,name,age,email,is_delete FROM User
     * WHERE is_delete=0
     * AND (
     * email IS NULL OR
     * (age < ? AND name LIKE ?)
     * )
     * ==> Parameters: 30(Integer), %张%(String)
     */
    @Test
    public void test12() {
        // 查找邮箱为空或者（年龄小于30而且姓张）的员工信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email").or(s -> s.lt("age", 30).like("name", "张"));
        List<User> users = userMapper.selectList(queryWrapper);

        //User(id=6, name=张三, age=24, email=zhangsan@baomidou.com, isDelete=0)
        //User(id=9, name=admin, age=25, email=null, isDelete=0)

        users.stream().forEach(System.out::println);
    }

    /**
     * 自己拼装查询出来的字段
     * <p>
     * ==>  Preparing: SELECT name,age FROM User WHERE is_delete=0
     */
    @Test
    public void test13() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.stream().forEach(System.out::println);
    }

    /**
     * 嵌套子查询的书写
     * <p>
     * SELECT id,name,age,email,is_delete FROM User
     * WHERE is_delete=0 AND (id IN
     * (select id from user where id <=100)
     * )
     */
    @Test
    public void test14() {
        // 查询id小于或者等于100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 定义内部的sql
        queryWrapper.inSql("id", "select id from user where id <=100");
        List<User> users = userMapper.selectList(queryWrapper);
        users.stream().forEach(System.out::println);
    }

    /**
     * 使用UpdateWrapper实现修改操作
     * <p>
     * ==>  Preparing:
     * UPDATE User SET name=?,email=?
     * WHERE is_delete=0 AND
     * (name LIKE ? AND (age > ? OR email IS NULL))
     * ==> Parameters: wsupdate(String), ws@qq.com(String), %王%(String), 20(Integer)
     */
    @Test
    public void test15() {
        // 将用户名中含有"王"和并且（年龄大于20或者邮箱为null）的用户信息的修改
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "王").and(s -> s.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "wsupdate").set("email", "ws@qq.com");
        userMapper.update(null, updateWrapper);
    }

    /**
     * 模拟实际开发
     * <p>
     * ==>  Preparing: SELECT id,name,age,email,is_delete FROM User WHERE is_delete=0 AND (age > ? AND age < ?)
     * ==> Parameters: 20(Integer), 30(Integer)
     */
    @Test
    public void test16() {
        String username = "ws";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!username.isBlank()) // 判断username不为null也不为""
        {
            queryWrapper.like("name", username);
        }
        if (ageBegin != null) {
            queryWrapper.gt("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.lt("age", ageEnd);
        }
        List<User> users = userMapper.selectList(queryWrapper);
        users.stream().forEach(System.out::println);

    }

    /**
     * 使用lambda表达式来规避字段问题
     *
     * ==>  Preparing:
     *         SELECT id,name,age,email,is_delete FROM User
     *         WHERE is_delete=0 AND (name LIKE ? AND age >= ? AND age < ?)
     * ==> Parameters: %ws%(String), 20(Integer), 30(Integer)
     * <==    Columns: id, name, age, email, is_delete
     * <==        Row: 8, ws, 21, ws@qq.com, 0
     */
    @Test
    public void test17() {
        String username = "ws";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!username.isBlank(), User::getName, username);
        queryWrapper.ge(ageBegin != null, User::getAge, ageBegin);
        queryWrapper.lt(ageEnd != null, User::getAge, ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.stream().forEach(System.out::println);

    }

    /**
     * 使用lambda表达式来规避字段问题
     *
     * ==>  Preparing: UPDATE User SET name=? WHERE is_delete=0 AND (name LIKE ? AND age >= ? AND age < ?)
     * ==> Parameters: ws1(String), %ws%(String), 20(Integer), 30(Integer)
     */
    @Test
    public void test18() {
        String username = "ws";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(!username.isBlank(), User::getName, username);
        updateWrapper.ge(ageBegin != null, User::getAge, ageBegin);
        updateWrapper.lt(ageEnd != null, User::getAge, ageEnd);
        updateWrapper.set(User::getName,"ws1");
        userMapper.update(null,updateWrapper);

    }
}
