package com.zixuan.jdbc.demo3;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zixuan.jdbc.utils.JDBCUtils;
import com.zixuan.jdbc.utils.JDBCUtils2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 连接池测试类
 */

public class DataSourceDemo1 {

    @Test
    /**
     * 手动配置连接池
     */
    public void demo1() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 0. 创建连接池：
            ComboPooledDataSource dateSource = new ComboPooledDataSource();
            // 0. 设置连接池的参数：
            dateSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dateSource.setJdbcUrl("jdbc:mysql:///jdbc_test");
            dateSource.setUser("root");
            dateSource.setPassword("zzx123456");
            dateSource.setMaxPoolSize(20);
            dateSource.setInitialPoolSize(3);

            // 1. 获得连接
            conn = dateSource.getConnection();

            // 2. 编写SQL
            String sql = "SELECT * FROM user";

            // 3. 预编译SQL
            pstmt = conn.prepareStatement(sql);

            // 4. 设置参数

            // 5. 执行SQL
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("uid") + "\n" +
                        "username: " + rs.getString("username") + "\n" +
                        "password: " + rs.getString("password") + "\n" +
                        "name: " + rs.getString("name") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
    }

    @Test
    /**
     * 使用配置文件的方式
     */
    public void demo2() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 0. 创建连接池：
//            ComboPooledDataSource dateSource = new ComboPooledDataSource();
            // 0. 设置连接池的参数：自动根据被指文件设置

            // 1. 获得连接
            conn = JDBCUtils2.getConnection();

            // 2. 编写SQL
            String sql = "SELECT * FROM user";

            // 3. 预编译SQL
            pstmt = conn.prepareStatement(sql);

            // 4. 设置参数

            // 5. 执行SQL
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("uid") + "\n" +
                        "username: " + rs.getString("username") + "\n" +
                        "password: " + rs.getString("password") + "\n" +
                        "name: " + rs.getString("name") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.release(rs, pstmt, conn);
        }
    }


}
