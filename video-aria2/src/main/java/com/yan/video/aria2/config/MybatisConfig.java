package com.yan.video.aria2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author likaiyan
 * @date 2020/6/10 6:17 下午
 */
@Configuration
@MapperScan({"com.yan.video.mapper", "com.yan.video.aria2.dao"})
public class MybatisConfig {

}
