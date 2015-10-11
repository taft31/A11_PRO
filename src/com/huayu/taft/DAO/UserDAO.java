package com.huayu.taft.DAO;

import com.huayu.taft.Model.Users;
import com.huayu.taft.Utils.RandID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 15-10-11.
 */
public class UserDAO extends BaseDAO {
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement prestat = null;
    public boolean register(String userName, String userPass) {
        String sql = "insert into users(user_ID,user_Name,user_Pass) values(?,?,?)";
        conn = getConn();
        try {
            prestat = conn.prepareStatement(sql);
            prestat.setString(1, new RandID().getID(RandID.UserID));
            prestat.setString(2, userName);
            prestat.setString(3, userPass);
            int rows = prestat.executeUpdate();
            if (0 != rows) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
    public Users checkLogin(String userName,String userPass){
        conn = getConn();
        String sql = "select * from users where user_Name = ? and user_Pass = ?";
        try {
            prestat = conn.prepareStatement(sql);
            prestat.setString(1,userName);
            prestat.setString(2,userPass);
            rs = prestat.executeQuery();
            while (rs.next()){
                Users user = new Users();
                user.setUser_ID(rs.getString(1));
                user.setUser_Name(rs.getString(2));
                user.setUser_Pass(rs.getString(3));
                user.setUser_Money(rs.getDouble(4));
                user.setUser_Phone(rs.getString(5));
                user.setUser_Email(rs.getString(6));
                user.setUser_State(rs.getInt(7));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return null;
    }
}
