package com.demo.dbEngine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBaseEngine {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>>selectQueryExecution (String query){
		return jdbcTemplate.queryForList(query);
	}
	 
	public <T> void singleInsertQueryExecution(T object,String query, BiConsumer<PreparedStatement, T> setter){
		jdbcTemplate.update(query, ps -> setter.accept(ps, object));
	}
	
	public <T> void bulkInsertQueryExecution(List<T> object,String query, BiConsumer<PreparedStatement, T> setter){
		jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				setter.accept(ps, object.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return object.size();
			}
		});
	}
	
	public ResultSetMetaData executeNativeQuery(String query) {
		try(Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery()){
			return resultSet.getMetaData();
		}
		catch(SQLException e) {
			
		}
		return null;
	}
}
