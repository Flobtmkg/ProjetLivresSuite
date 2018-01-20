package springContext;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import javax.sql.DataSource;
import javax.swing.*;


@ComponentScan(basePackages = {"beans","DaoInterfaces","webservice.allplatform","DaoImpl","springContext"})
@PropertySource("classpath:application.properties")
@Configuration
public class Application {
    //
    @Value("${bdd.url}")
    private String jdbcUrl;
    @Value("${bdd.class}")
    private String jdbcClass;
    @Value("${bdd.password}")
    private String jdbcPassword;
    @Value("${bdd.username}")
    private String jdbcUsername;
    //
    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        //
        BasicDataSource dataSourceConfig = new BasicDataSource();
        dataSourceConfig.setDriverClassName(jdbcClass);//org.postgresql.Driver
        dataSourceConfig.setUrl(jdbcUrl);//jdbc:postgresql://localhost:5432/livres
        dataSourceConfig.setUsername(jdbcUsername);//postgres
        dataSourceConfig.setPassword(jdbcPassword);//flosql75301
        return dataSourceConfig;
    }
    @Bean
    @Autowired
    public PlatformTransactionManager configTransaction(DataSource ds){
        DataSourceTransactionManager dstm = new DataSourceTransactionManager(ds);
        return dstm;
    }
}
