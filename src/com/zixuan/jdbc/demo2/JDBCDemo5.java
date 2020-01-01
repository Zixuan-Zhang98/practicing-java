package com.zixuan.jdbc.demo2;

import com.zixuan.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * PreparedStatement的使用
 */
public class JDBCDemo5 {
    @Test
    /**
     * 保存数据
     */
    public void demo1() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. 获得连接
            conn = JDBCUtils.getConnection();
            // 2. 编写SQL
            String insertSQL = "INSERT user VALUES(NULL, ?, ?, ?)";
            // 3. 预编译SQL
            pstmt = conn.prepareStatement(insertSQL);
            // 4. 设置参数的值
            pstmt.setString(1, "qqq");
            pstmt.setString(2, "123");
            pstmt.setString(3, "wenqi");
            // 5. 执行SQL
            int linesChanged= pstmt.executeUpdate();
            if (linesChanged > 0) {
                System.out.println("Insert success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Test
    /**
     * 修改数据
     */
    public void demo2() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. 获得连接
            conn = JDBCUtils.getConnection();
            // 2. 编写SQL
            String updateSQL = "UPDATE user SET username = ?, password = ?, name = ? WHERE uid = ?";
            // 3. 预编译SQL
            pstmt = conn.prepareStatement(updateSQL);
            // 4. 设置参数的值
            pstmt.setString(1, "www");
            pstmt.setString(2, "666666");
            pstmt.setString(3, "Curious");
            pstmt.setInt(4, 6);
            // 5. 执行SQL
            int linesChanged= pstmt.executeUpdate();
            if (linesChanged > 0) {
                System.out.println("Update success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Test
    /**
     * 删除数据
     */
    public void demo3() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. 获得连接
            conn = JDBCUtils.getConnection();
            // 2. 编写SQL
            String deleteSQL = "DELETE FROM user WHERE uid = ?";
            // 3. 预编译SQL
            pstmt = conn.prepareStatement(deleteSQL);
            // 4. 设置参数的值
            pstmt.setInt(1, 6);

            // 5. 执行SQL
            int linesChanged= pstmt.executeUpdate();
            if (linesChanged > 0) {
                System.out.println("Delete success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Test
    /**
     * 查询数据
     */
    public void demo4() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1. 获得连接
            conn = JDBCUtils.getConnection();
            // 2. 编写SQL
            String querySQL = "SELECT * FROM user WHERE uid = ?";
            // 3. 预编译SQL
            pstmt = conn.prepareStatement(querySQL);
            // 4. 设置参数的值
            // you can also use setObject
            pstmt.setInt(1, 9);

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


}
