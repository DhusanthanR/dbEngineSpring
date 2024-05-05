package com.demo.dbEngine;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

//public interface DataBaseService <T>{
//
//	public List<Map<String,Object>>selectQueryExecution (String query);
//	
//	public ResultSetMetaData executeNativeQuery(String query);
//	
//	public void singleInsertQueryExecution(T object,String query, BiConsumer<PreparedStatement, T> setter);
//	
//	public void bulkInsertQueryExecution(List<T> object,String query, BiConsumer<PreparedStatement, T> setter);
//}

public interface DataBaseService {

	public List<Map<String,Object>>selectQueryExecution (String query);
	
	public ResultSetMetaData executeNativeQuery(String query);
	
	public <T>void singleInsertQueryExecution(T object,String query, BiConsumer<PreparedStatement, T> setter);
	
	public <T>void bulkInsertQueryExecution(List<T> object,String query, BiConsumer<PreparedStatement, T> setter);
}