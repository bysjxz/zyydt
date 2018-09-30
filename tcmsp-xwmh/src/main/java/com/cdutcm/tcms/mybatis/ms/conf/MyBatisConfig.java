package com.cdutcm.tcms.mybatis.ms.conf;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.cdutcm.core.database.MultipleDataSource;
import com.cdutcm.core.page.PagePlugin;



@Configuration
public class MyBatisConfig{
	
	
	@Primary
	@Bean(name="dsDh")
	@ConfigurationProperties(prefix = "spring.datasourceDh")
	public DataSource dataSourceDh() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}
	

	/**
	  * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	  */
	 @Bean
	 public MultipleDataSource mds(@Qualifier("dsDh") DataSource dataSourceDh) {
	     Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
	     targetDataSources.put("dsDh", dataSourceDh);
	
	     MultipleDataSource ds = new MultipleDataSource();
	     ds.setTargetDataSources(targetDataSources);
	     ds.setDefaultTargetDataSource(dataSourceDh);// 默认加载CWS数据源
	
	     return ds;
	 }
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		sqlSessionFactoryBean.setDataSource(mds(dataSourceDh()));

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setMapperLocations(resolver
				.getResources("classpath:/*/*.xml"));
		
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{new PagePlugin()});

		return sqlSessionFactoryBean.getObject();
	}

//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return new DataSourceTransactionManager(mds(dataSourceCws(),dataSourceKb(),dataSourceKbbd()));
//	}
}
