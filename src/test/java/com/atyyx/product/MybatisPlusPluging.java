package com.atyyx.product;

import com.atyyx.product.mapper.ProductMapper;
import com.atyyx.product.mapper.UserMapper;
import com.atyyx.product.pojo.Product;
import com.atyyx.product.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:24
 */
@SpringBootTest
public class MybatisPlusPluging {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;

    /**
     * 分页模式
     * <p>
     * Preparing: SELECT COUNT(*) FROM User WHERE is_delete = 0
     * ==> Parameters:
     * <==    Columns: COUNT(*)
     * <==        Row: 11
     * <==      Total: 1
     * ==>  Preparing: SELECT id,name,age,email,is_delete FROM User WHERE is_delete=0 LIMIT ?
     * ==> Parameters: 3(Long)
     * <==    Columns: id, name, age, email, is_delete
     * <==        Row: 2, Jack, 20, Jack@baomidou.com, 0
     * <==        Row: 3, Tom, 28, Tom@baomidou.com, 0
     * <==        Row: 4, Sandy, 21, Sandy@baomidou.com, 0
     * <==      Total: 3
     */
    @Test
    public void test1() {
        Page<User> page = new Page<User>(1, 3);
        userMapper.selectPage(page, null);
        System.out.println(page);
    }

    /**
     * 分页数据信息获取
     * <p>
     * ==>  Preparing: SELECT COUNT(*) FROM User WHERE is_delete = 0
     * ==> Parameters:
     * <==    Columns: COUNT(*)
     * <==        Row: 11
     * <==      Total: 1
     * ==>  Preparing: SELECT id,name,age,email,is_delete FROM User WHERE is_delete=0 LIMIT ?,?
     * ==> Parameters: 9(Long), 3(Long)
     * <==    Columns: id, name, age, email, is_delete
     * <==        Row: 1558064286546206722, yjh, 23, yjh@qq.com, 0
     * <==        Row: 1558064286546206723, qll, 21, qll@qq.com, 0
     * <==      Total: 2
     */
    @Test
    public void test2() {
        Page<User> page = new Page<User>(4, 3);
        userMapper.selectPage(page, null);
        // 展示该页面中的所有数据
        System.out.println(page.getRecords());
        // 获取总的页面数量
        System.out.println(page.getPages());
        // 获取数据的总量
        System.out.println(page.getTotal());
        // 判断是否还有下一页
        System.out.println(page.hasNext());
        // 判断是否还有前一页
        System.out.println(page.hasPrevious());
    }

    /**
     * ==>  Preparing: SELECT COUNT(*) FROM user WHERE age > ?
     * ==> Parameters: 18(Integer)
     * <==    Columns: COUNT(*)
     * <==        Row: 12
     * <==      Total: 1
     * Cache Hit Ratio [com.atyyx.mybatis_plus.mapper.UserMapper]: 0.0
     * ==>  Preparing: select id,name,age ,email from user where age>? LIMIT ?,?
     * ==> Parameters: 18(Integer), 3(Long), 3(Long)
     */
    @Test
    public void test3() {
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVo(page, 18);
        // 展示该页面中的所有数据
        System.out.println(page.getRecords());
        // 获取总的页面数量
        System.out.println(page.getPages());
        // 获取数据的总量
        System.out.println(page.getTotal());
        // 判断是否还有下一页
        System.out.println(page.hasNext());
        // 判断是否还有前一页
        System.out.println(page.hasPrevious());
    }

    @Test
    public void test4() {
        //小李查询商品价格
        Product product = productMapper.selectById(1L);
        System.out.println("小李查询到的商品价格为" + product.getPrice());

        //小王查询商品价格
        Product product1 = productMapper.selectById(1L);
        System.out.println("小王查询到的商品价格为" + product1.getPrice());

        // 小李将商品阿济格+50
        product.setPrice(product.getPrice() + 50);
        productMapper.updateById(product);

        // 小李将商品阿济格-30
        product1.setPrice(product1.getPrice() - 30);
        int i = productMapper.updateById(product1);
        if(i==0) // 操作失败了
        {
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice()-30);
            productMapper.updateById(productNew);
        }

        //老板查询商品价格
        Product product2 = productMapper.selectById(1L);
        System.out.println("老板查询到的商品价格为" + product2.getPrice());
    }
}
