package com.zixuan.jdbc.demo1;

import org.junit.Test;

import java.sql.*;

public class JDBCDemo2 {

    @Test
    /**
     * Create, 保存操作
     */
    public void demo1() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1. register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. get connection
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test?", "root", "zzx123456");

            // 3. get statement
            stmt = conn.createStatement();

            // 4. write SQL
            String sql = "INSERT INTO user VALUES(NULL, 'ee', '123', 'Yiyi')";

            // 5. execute SQL
            int i = stmt.executeUpdate(sql);
            if (i > 0) {
                System.out.println("Saved successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }

    @Test
    /**
     * Update, 修改操作
     */
    public void demo2() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 1. register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. get connection
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test", "root", "zzx123456");

            // 3. create SQL statement
            stmt = conn.createStatement();

            // 4. write SQL
            String sql = "UPDATE user SET username = 'qq', password = 456, name = 'wenqi' WHERE uid = 4 ";

            // 5. execute SQL
            int i = stmt.executeUpdate(sql);
            if (i > 0) {
                System.out.println("Updated!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // release resources
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
    }

    @Test
    /**
     * Delete
     */
    public void demo3() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 1. register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. get connection
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test", "root", "zzx123456");

            // 3. create SQL statement
            stmt = conn.createStatement();

            // 4. write SQL
            String sql = "DELETE FROM user WHERE uid = 4";

            // 5. execute SQL
            int i = stmt.executeUpdate(sql);
            if (i > 0) {
                System.out.println("Deleted!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // release resources
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
    }


    @Test
    /**
     * Select
     */
    public void demo4() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1. register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. get connection
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test", "root", "zzx123456");

            // 3. create SQL statement
            stmt = conn.createStatement();

            // 4. write SQL
            String sql = "SELECT * FROM user";

            // 5. execute SQL
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("uid") + " " +
                        rs.getString("username") + "" +
                        rs.getString("password") + "" +
                        rs.getString("name") + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // release resources
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
                conn = null;
            }
        }
    }

}
