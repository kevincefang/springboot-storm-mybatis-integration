package com.demo.proxy.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author: kevin.
 * @date: 2017/5/12.
 * @description: mybatis配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.demo.proxy.dal.mapper")
@ConfigurationProperties(prefix = "spring.datasource")
@PropertySource(value = "classpath:datasource-config.properties")
public class SessionFactoryConfig implements TransactionManagementConfigurer {

    /** * mybatis 配置路径 */
    private static String MYBATIS_CONFIG = "mybatis-config.xml";

    /**     * mybatis mapper resource 路径     */
    private static String MAPPER_PATH = "/mapper/**.xml";

    private static String typeAliasPackage = "com.demo.proxy.dal.model";


    private static Class<? extends DataSource> type;

    private static String driverClassName;

    private static String url;

    private static String username;

    private static String password;

    private static int initialSize;

    private static int minIdle;

    private static int maxActive;

    /**
     *创建sqlSessionFactoryBean 实例
     * 并且设置configtion 如驼峰命名.等等
     * 设置mapper 映射路径
     * 设置datasource数据源
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径 */
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        /** 添加mapper 扫描路径 */
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        /** 设置datasource */
        sqlSessionFactoryBean.setDataSource(dataSource());
        /** 设置typeAlias 包扫描路径 */
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事务管理器
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 创建数据源
     * @return
     */
    @Bean(name="dataSource", destroyMethod = "close", initMethod="init")
    //@ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        System.out.println("-------------------- start init DataSource ---------------------");
        //DataSource dataSource = DataSourceBuilder.create().type(getType()).build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        System.out.println("-------------------- init DataSource end -----------------");
        return dataSource;
    }

    public static String getDriverClassName() {
        return driverClassName;
    }

    public static void setDriverClassName(String driverClassName) {
        SessionFactoryConfig.driverClassName = driverClassName;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        SessionFactoryConfig.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionFactoryConfig.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionFactoryConfig.password = password;
    }

    public static Class<? extends DataSource> getType() {
        return type;
    }

    public static void setType(Class<? extends DataSource> type) {
        SessionFactoryConfig.type = type;
    }

    public static int getInitialSize() {
        return initialSize;
    }

    public static void setInitialSize(int initialSize) {
        SessionFactoryConfig.initialSize = initialSize;
    }

    public static int getMinIdle() {
        return minIdle;
    }

    public static void setMinIdle(int minIdle) {
        SessionFactoryConfig.minIdle = minIdle;
    }

    public static int getMaxActive() {
        return maxActive;
    }

    public static void setMaxActive(int maxActive) {
        SessionFactoryConfig.maxActive = maxActive;
    }
}
