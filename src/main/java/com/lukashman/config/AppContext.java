package com.lukashman.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import com.lukashman.dao.EventDAOImpl;

@Configuration
public class AppContext {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/Event_db?useUnicode=true&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean
	@Scope("prototype")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	@Scope("prototype")
	public EventDAOImpl eventDAOImpl() {
		EventDAOImpl daoImpl = new EventDAOImpl(jdbcTemplate());
		return daoImpl;
	}
}
