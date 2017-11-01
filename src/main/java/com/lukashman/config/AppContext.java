package com.lukashman.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.lukashman.dao.EventDAOImpl;

@Configuration
public class AppContext {
	
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase embeddedDatabase = (EmbeddedDatabase) builder.setName("Memory_list_db")
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:sql/db.sql")
				.build();
		return embeddedDatabase;
	}
	
	@Bean
	@Scope("prototype")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	@Scope("prototype")
	public EventDAOImpl eventDAOImpl(JdbcTemplate jdbcTemplate) {
		EventDAOImpl daoImpl = new EventDAOImpl(jdbcTemplate);
		return daoImpl;
	}
}
