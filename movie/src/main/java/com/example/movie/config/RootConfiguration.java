package com.example.movie.config;

import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.dozer.DozerBeanMapper;
import org.hibernate.dialect.Oracle10gDialect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.java.Log;

@Configuration
/**
 * @EnableTransactionManagement : XML의 <tx:annotation-driven/>와 동일, Spring의 선언적 트랜잭션 처리 기능 활성화
 * 해당 어노테이션을 사용하면 DataSourceTransactionManager로 구성되기 때문에 @Scheduled가 동작하지 않는 이슈가
 * 발생한다. 그래서 트랜잭션 매니저를 JpaTransactionManage로 구현한다
 * @Primary 를 사용해 우선적으로 등록할 트랜잭션 매니져 Bean을 지정
 */
@EnableTransactionManagement // DataSourceTransactionManager 찾아서 트랜잭션 등록해줌
@EnableJpaRepositories(basePackages = {"com.example.movie.repository", "com.example.movie.sample"})
@MapperScan(basePackages =  {"com.example.movie.mapper", "com.example.movie.sample"} , markerInterface = MybatisMapper.class) // mapper 인터페이스 위치 
@Log
public class RootConfiguration   {

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverClassName;
	@Value("${spring.datasource.url}")
	private String dbJdbcUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Bean(name= "dataSource") 
	public DataSource dataSource() {

		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(dbDriverClassName);
		dataSourceBuilder.url(dbJdbcUrl);
		dataSourceBuilder.username(dbUsername);
		dataSourceBuilder.password(dbPassword);
		return dataSourceBuilder.build();
	}

	/**
	 * LocalContainerEntityManagerFactoryBean
	 * EntityManager를 생성하는 팩토리
	 * SessionFactoryBean과 동일한 역할, Datasource와 mapper를 스캔할 .xml 경로를 지정하듯이
	 * datasource와 엔티티가 저장된 폴더 경로를 매핑해주면 된다.
	 * @param builder
	 * @param dataSource
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.ORACLE);
		vendorAdapter.setDatabasePlatform(Oracle10gDialect.class.getName());	

		final String[] packageLocation = {"com.example.movie.entity","com.example.movie.dto","com.example.movie.sample"};
		entityManagerFactory.setDataSource(dataSource());		
		entityManagerFactory.setPackagesToScan(packageLocation);
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(additionalProperties());

		return entityManagerFactory;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.use_sql_comments", "true");
		return properties; 
	}

	@Bean
	public SpringManagedTransactionFactory managedTransactionFactory() {
		SpringManagedTransactionFactory managedTransactionFactory = new SpringManagedTransactionFactory();
		return managedTransactionFactory;
	}

	@Bean
	public SqlSessionFactory sessionFactory(SpringManagedTransactionFactory springManagedTransactionFactory) throws Exception {
		//System.out.println(dataSource);
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*/**-mapper.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*-mapper.xml"));
		sqlSessionFactoryBean.setTransactionFactory(springManagedTransactionFactory);
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * SqlSessionTemplate : SqlSession을 구현하고 코드에서 SqlSession을 대체하는 역할을 한다. 마이바티스 예외처리나 세션의 생명주기 관리
	 * @param testSqlSessionFactory
	 */
	//sqlTemplate 사용시 추가 SqlSessionTemplate 방식은 3.0이전 버전에 사용
	//interface Mapper 방식을 사용하면 필요없음
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "transactionManager")
	@DependsOn(value = {"dataSource", "entityManagerFactory"}) // 빈 등록순서
	public PlatformTransactionManager platformTransactionManager() throws Exception {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		jpaTransactionManager.afterPropertiesSet();
		//DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		//권장안함 요즘안쓰는듯 대체는 뭘로?
		//ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(jpaTransactionManager,dataSourceTransactionManager );
		//return chainedTransactionManager;
		return jpaTransactionManager;
	}


	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper;
	}



	// mybatis만 트랜잭션 설정시
	//@Bean
	//public DataSourceTransactionManager transactionManager() {
	//	return new DataSourceTransactionManager(dataSource());
	//}


}
