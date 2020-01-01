package com.zixuan.jdbc.demo1;

import org.junit.Test;

import java.sql.*;

public class JDBCPractice1 {
    /**
     * create a goods table and insert data
     * then print out all the data
     */
    @Test
    public void demo1() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. get connection
            conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test", "root", "zzx123456");

            // 3. get statement
            stmt = conn.createStatement();

            // 4. write SQL
            String createTableSQL = "CREATE TABLE goods(" +
                    "id INT UNSIGNED AUTO_INCREMENT KEY," +
                    "name VARCHAR(20) NOT NULL," +
                    "price FLOAT(8) NOT NULL," +
                    "description VARCHAR(30) NOT NULL" +
                    ")";

            String insertStatement = "INSERT goods VALUES(NULL, 'phone', 2000.0, '32GB'), " +
                    "(NULL, 'refrigerator', 1500.0, 'silver'), " +
                    "(NULL, 'washer', 3000.0, 'excellent')";

            String updateStatement = "UPDATE goods SET description = '64GB' WHERE id = 1";

            String deleteStatement = "DELETE FROM goods WHERE id = 2";

            String queryStatement = "SELECT * FROM goods WHERE price < 3000";

            // 5. execute SQL
            // create table
            stmt.execute(createTableSQL);
            // insert data
            int linesChanged =stmt.executeUpdate(insertStatement);
            if (linesChanged > 0) {
                System.out.println("Insert success!");
            }

            linesChanged = stmt.executeUpdate(updateStatement);
            if (linesChanged > 0) {
                System.out.println("Update success!");
            }

            linesChanged = stmt.executeUpdate(deleteStatement);
            if (linesChanged > 0) {
                System.out.println("Delete success!");
            }

            // query data
            rs = stmt.executeQuery(queryStatement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                System.out.println("id: " + id + "\n" +
                        "name: " + name + "\n" +
                        "price: " + price + "\n" +
                        "description: " + description + "\n");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
