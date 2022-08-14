package com.atyyx.product.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 20:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@TableName("t_product")
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //用来标识乐观锁版本号
    private Integer version;
}
