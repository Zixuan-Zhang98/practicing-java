package com.zixuan.jdbc.demo1;

import com.zixuan.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

public class JDBCDemo3 {

    @Test
    /**
     * 保存记录
     */
    public void demo1() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1. register driver
            JDBCUtils.loadDriver();

            // 2. get connection
            conn = JDBCUtils.getConnection();

            // 3. create statement
            stmt = conn.createStatement();

            // 4. write SQL
            String sql = "INSERT user VALUES(NULL, 'nn', '610', 'yining')";

            // 5. execute SQL
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(stmt, conn);
        }

    }
}
