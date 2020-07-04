package me.jerrysheh.demo.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author jerrysheh
 * @date 2020/7/4
 */
@Configuration
@MapperScan(basePackages = {"me.jerrysheh.demo.mapper"},
        annotationClass = me.jerrysheh.demo.annotation.DS2Mapper.class)
public class DataSource2Config {

    @Bean(name = "DS_2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Qualifier("DS_2")
    private DataSource ds2;

    @Bean(name = "sqlSessionFactoryBeanDS2")
    public SqlSessionFactoryBean SqlSessionFactoryBeanDS2() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds2);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper*.xml"));
        return bean;
    }

}
