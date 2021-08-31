package com.huawei.kunpengimsystem.config.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 扫描Mapper接口并容器管理
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    // 精确打master目录，以便和其他数据源隔离
    static final String PACKAGE = "";
    static final String MAPPER_LOCATION = "";

}
