package com.demo.dbEngine;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataBaseConfiguration {
	
	private final DataSource dataSource;
	
	public DataBaseConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource);
	}
}
