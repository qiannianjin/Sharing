package top.arexstorm.sharing.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceComponent {

    @Resource
    MasterDataSourceConfig masterDataSourceConfig;
    @Resource
    SlaveDataSourceConfig slaveDataSourceConfig;

    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(masterDataSourceConfig.getUrl());
        dataSource.setUsername(masterDataSourceConfig.getUsername());
        dataSource.setPassword(masterDataSourceConfig.getPassword());
        dataSource.setDriverClassName(masterDataSourceConfig.getDriverClassName());

        return dataSource;
    }

    public DataSource slaveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(slaveDataSourceConfig.getUrl());
        dataSource.setUsername(slaveDataSourceConfig.getUsername());
        dataSource.setPassword(slaveDataSourceConfig.getPassword());
        dataSource.setDriverClassName(slaveDataSourceConfig.getDriverClassName());

        return dataSource;
    }

    @Bean(name = "multiDataSource")
    public MultiRouteDataSource exampleRouteDataSource() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("master",masterDataSource());
        targetDataSource.put("slave", slaveDataSource());

        MultiRouteDataSource multiRouteDataSource = new MultiRouteDataSource();
        multiRouteDataSource.setTargetDataSources(targetDataSource);
        multiRouteDataSource.setDefaultTargetDataSource(masterDataSource());

        return multiRouteDataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(exampleRouteDataSource());
        return manager;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(exampleRouteDataSource());
        return sessionFactoryBean;
    }
}
