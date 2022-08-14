package com.atyyx.product;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:06
 */
@SpringBootTest
public class FastAutoGeneratorTest {

    /**
     * Mybatis代码生成器
     * 通过此段代码可以生成控制层、业务逻辑层、持久层、mapper.xml、对应的实体类
     */
    @Test
    @DisplayName("Mybatis逆向工程")
    public void test1() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("yyx") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://jdkNew//mybatis_plus//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.atyyx") // 设置父包名
                            .moduleName("mybatis_plus") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://jdkNew//mybatis_plus//src//main//java")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_stu") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
