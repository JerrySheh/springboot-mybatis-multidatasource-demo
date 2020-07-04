package me.jerrysheh.demo.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author jerrysheh
 * @date 2020/7/4
 */
@MapperScan(basePackages = {"me.jerrysheh.demo.mapper"})
public class DataSource1Config {

    @Bean(name = "DS_1")
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Qualifier("DS_1")
    private DataSource ds1;

    @Bean(name = "sqlSessionFactoryMysqlBeanDS1")
    @Primary
    public SqlSessionFactoryBean SqlSessionFactoryBeanDS1() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds1);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/*.xml"));
        return bean;
    }

}
