package dao;

import pojo.Users;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    public int insert(Users users) {
        Connection conn = DBUtil.getConn();
        PreparedStatement st = null;

        try {
            String sql = " insert users(username, password, fullname, email) "
                    + " values(?,?,?,?) ";

            conn.setAutoCommit(false);
            st = conn.prepareStatement(sql);
            st.setString(1, users.getUserName());
            st.setString(2, users.getPassword());
            st.setString(3, users.getFullName());
            st.setString(4, users.getEmail());

            int num = st.executeUpdate();
            conn.commit();

            return num;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtil.closeConn(null, st, conn);
        }

        return -1;
    }

    public List<Users> select() {

        List<Users> list = null;

        Connection conn = DBUtil.getConn();
        Statement st = null;
        ResultSet rs = null;

        try {

            String sql = " select * from users ";

            conn.setAutoCommit(false);
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            conn.commit();

            if (rs.next()) {
                list = new ArrayList<Users>();
            }

            while (rs.next()) {
                int userId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                String email = rs.getString(5);

                Users users = new Users(userId, userName, password, fullName, email);
                System.out.println(users.toString());
                if (list != null) {
                    list.add(users);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtil.closeConn(rs, st, conn);
        }

        return list;
    }

    public static void main(String[] args) {
        UsersDao dao = new UsersDao();

//        dao.insert();
        dao.select();
    }

}
