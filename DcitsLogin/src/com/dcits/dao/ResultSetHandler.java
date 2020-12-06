package com.dcits.dao;

import java.sql.ResultSet;

/**
 * 把JDBC返回的结果集封装成特定类型
 */
public interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws Exception;
}
