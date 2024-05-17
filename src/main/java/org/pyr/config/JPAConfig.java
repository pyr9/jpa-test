package org.pyr.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("org.pyr.repository")
@EnableTransactionManagement
public class JPAConfig {

  @Bean
  public DataSource dataSource() {
      DruidDataSource dataSource = new DruidDataSource();
      dataSource.setUsername("root");
      dataSource.setPassword("19980617pyr");
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/springdata_jpa");
      return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setGenerateDdl(true);

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(vendorAdapter);
      factory.setPackagesToScan("org.pyr.entity");
      factory.setDataSource(dataSource());

      Properties jpaProperties = new Properties();
      jpaProperties.put("hibernate.generate_statistics", true);
      jpaProperties.put("hibernate.format_sql", true);

      //将这些插入操作打包成一个批处理操作，然后一次性发送到数据库执行。这样做可以减少通信成本和事务开销，提高性能。
      jpaProperties.put("hibernate.jdbc.batch_size", 100);
      jpaProperties.put("hibernate.jdbc.batch_versioned_data", true);
      //jpaProperties.put("hibernate.jdbc.order_inserts", true);
      //jpaProperties.put("hibernate.jdbc.order_updates", true);

      factory.setJpaProperties(jpaProperties);
      return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }
}