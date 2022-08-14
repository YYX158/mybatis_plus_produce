package com.atyyx.product.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:00
 */
@TableName("t_stu")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sid;

    private String sname;

    private Integer age;

    private String email;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "sid = " + sid +
                ", sname = " + sname +
                ", age = " + age +
                ", email = " + email +
                "}";
    }
}
