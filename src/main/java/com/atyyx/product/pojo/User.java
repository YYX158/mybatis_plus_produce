package com.atyyx.product.pojo;

import com.atyyx.product.Enum.Sex;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 20:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("User")

public class User {

    @TableId(value = "id",type = IdType.AUTO) //设置成根据数据库意义上的主键自增
    private Long id;
    @TableField("name")
    private String name;

    private Integer age;

    private String email;

    @TableLogic(value = "0",delval = "1") // 标识是逻辑删除的字段
    private Integer isDelete;

    private Sex sex;

    public User(String name, Integer age, String email,Sex sex) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.sex=sex;
    }
}
