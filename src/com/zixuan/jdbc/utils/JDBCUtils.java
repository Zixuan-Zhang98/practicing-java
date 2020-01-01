package com.zixuan.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC util class
 */


public class JDBCUtils {
    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        // 加载属性文件并解析
        Properties props = new Properties();
        // 获得属性文件的输入流， 通常使用类的加载器的方式进行获取，而不是FileInputStream("src/jdbc.properties"), 因为web工程中没有src目录
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverClass = props.getProperty("driverClass");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
    }

    /**
     * register driver
     */
    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(driverClass);
    }

    /**
     * get connection
     */
    public static Connection getConnection() throws Exception {
        loadDriver();
        return DriverManager.getConnection(url, username, password);
    }

    /**
     *release resource
     */
    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            stmt = null;
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) { e.printStackTrace(); }
            conn = null;
        }
    }

    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { e.printStackTrace(); }
            rs = null;
        }
        release(stmt, conn);
    }



}
