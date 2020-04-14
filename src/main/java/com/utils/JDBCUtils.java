package com.utils;

import java.sql.*;

public class JDBCUtils {

    //关闭jdbc的链接
    public static void release(PreparedStatement ps, Connection conn){
        try{
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void release(ResultSet result, PreparedStatement ps, Connection conn){
        try {
            if(result != null){
                result.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally{
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(conn != null){
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 获取jdbc链接
    public static Connection getConnection(){
        String  driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://47.100.10.67:3306/disease?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
