package com.saberking.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
  private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/test01_db?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";

  private static final String USER = "root";
  private static final String PASS = "admin123";

  public static void main(String[] args) throws Exception {
    Connection conn = null;
    Statement stmt = null;

    Class.forName(JDBC_DRIVER);
    conn = DriverManager.getConnection(DB_URL, USER, PASS);
    stmt = conn.createStatement();

    conn = DriverManager.getConnection(DB_URL, USER, PASS);

    stmt = conn.createStatement();

    String sql1 = "CREATE TABLE `emp` ("
    		+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
    		+ "  `dept` varchar(45) NOT NULL,"
    		+ "  `name` varchar(45) NOT NULL,"
    		+ "  PRIMARY KEY (`id`)"
    		+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    String sql2 = "CREATE TABLE `record` ("
    		+ "  `id` int(11) NOT NULL,"
    		+ "  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,"
    		+ "  PRIMARY KEY (`id`)"
    		+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    stmt.executeUpdate(sql1);
    stmt.executeUpdate(sql2);
    System.out.println("创建表成功...");
    stmt.close();
    conn.close();
  }
}
