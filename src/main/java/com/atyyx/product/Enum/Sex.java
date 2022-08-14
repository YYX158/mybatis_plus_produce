package com.atyyx.product.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 20:29
 */
public enum Sex {
    Gril(0,"女"),
    Boy(1,"男");

    @EnumValue  // 将注解所表示的属性的值存储到数据库中
    private Integer sex;
    private String sexName;

    Sex(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
}
