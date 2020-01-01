package com.zixuan.jdbc.demo1;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo1 {

    @Test
    public void demo1() {
        try {
            // 1. register Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. create connection
            Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc_test?" + "useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2B8",
                    "root",
                    "zzx123456");

            // 3. Create SQL statement and execute it
            // create SQL statement
            String sql = "SELECT * FROM user";
            Statement stmt = conn.createStatement();
            // execute SQL
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int uid = resultSet.getInt("uid");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                System.out.println(uid + " " + username + " " + password + " " + name);
            }

            // 4. close resources
            resultSet.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
