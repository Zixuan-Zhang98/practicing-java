package com.zixuan.jdbc.demo2;

import com.zixuan.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 演示JDBC的SQL注入漏洞
 */

public class JDBCDemo4 {
    /**
     * 避免产生SQL注入漏洞的方法
     */
    public static boolean login2(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            // 1. 获得连接
            conn = JDBCUtils.getConnection();
            // 2. 编写SQL
            String sql = "SELECT * FROM user WHERE username = ? AND password = ? ";
            // 3. 预编译SQL
            pstmt = conn.prepareStatement(sql);
            // 4. 设置具体参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 5. 执行SQL, 因为已经预编译好了，所以不用再传入SQL语句了
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }

        return result;

    }

    /**
     * 会产生SQL注入漏洞的方法
     * @param username
     * @param password
     * @return
     */
    public static boolean login(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        boolean res = false;

        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            // SQL注入漏洞的根本问题就在SQL语句的字符串拼接上
            String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
            rs = stmt.executeQuery(sql);
            // 判断结果集中是否有数据
            if (rs.next()) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }

        return res;
    }

    @Test
    /**
     * 测试SQL注入漏洞
     */
    public void demo1() {
        // 通过在用户名中写入SQL关键字影响SQL执行的结果
        boolean flag = JDBCDemo4.login2("z", "111"); // 通过OR使整个结果为真
//        boolean flag = JDBCDemo4.login("z' -- ", "askdmasds"); //将密码注释掉
        if (flag) {
            System.out.println("Login success!");
        } else {
            System.out.println("Login fail.");
        }

    }


}
