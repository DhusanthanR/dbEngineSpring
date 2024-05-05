package com.demo.dbEngine;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseServiceImplementation implements DataBaseService {
	
	@Autowired
	private DataBaseEngine dbEngine;

	@Override
	public List<Map<String,Object>>selectQueryExecution (String query){
		return dbEngine.selectQueryExecution(query);
	}
	
	@Override
	public ResultSetMetaData executeNativeQuery(String query) {
		return dbEngine.executeNativeQuery(query);
	}
	
	@Override
	public <T>void singleInsertQueryExecution(T object,String query, BiConsumer<PreparedStatement, T> setter) {
		dbEngine.singleInsertQueryExecution(object, query, setter);
	}
	
	@Override
	public <T>void bulkInsertQueryExecution(List<T> object,String query, BiConsumer<PreparedStatement, T> setter) {
		dbEngine.bulkInsertQueryExecution(object, query, setter);
	}
	
}
