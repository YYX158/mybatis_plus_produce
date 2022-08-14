package com.atyyx.product.mapper;

import com.atyyx.product.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yyx
 * @version 1.0
 * @date 2022/8/14 22:02
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
