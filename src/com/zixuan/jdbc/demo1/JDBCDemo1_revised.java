package com.zixuan.jdbc.demo1;

import org.junit.Test;

import java.sql.*;

public class JDBCDemo1_revised {

    @Test
    /**
     * Read
     */
    public void demo1() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. register Driver
//            DriverManager.registerDriver(new Driver()); // This line will make Driver to be registered twice
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. create connection
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test?" + "useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2B8",
                    "root",
                    "zzx123456");

            // 3. Create SQL statement and execute it
            // create SQL statement
            String sql = "SELECT * FROM user";
            stmt = conn.createStatement();
            // execute SQL
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int uid = rs.getInt("uid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                System.out.println(uid + " " + username + " " + password + " " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. close resources
            // it si a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { e.printStackTrace(); }
                rs = null;
            }

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
                conn = null; // manually set conn as null will trigger garbage collection earlier
            }
        }

    }

}
