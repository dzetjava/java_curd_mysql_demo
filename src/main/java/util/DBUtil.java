package util;

import java.sql.*;

public class DBUtil {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/sampledb";
    private static final String user = "root";
    private static final String password = "root!Zet007";

    private static Connection conn;

    public static Connection getConn() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("=========conn open...=========");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(ResultSet rs, Statement st, Connection conn){
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("===========conn close...==============");
        }
    }

//    public static void main(String[] args) {
//        getConn();
//    }
}
