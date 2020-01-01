package com.zixuan.jdbc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC util class
 */


public class JDBCUtils2 {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * get connection
     */
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
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
