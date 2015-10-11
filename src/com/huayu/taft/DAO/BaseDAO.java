package com.huayu.taft.DAO;

import java.sql.*;

/**
 * Created by Qiyeah on 2015/10/4.
 */
public class BaseDAO {
    private final String Driver = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin://@192.168.0.208/orcl";
    private final String User = "taft";
    private final String Pass = "taft123";

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement prestat = null;
    public void closeAll(){
        if (null!=prestat){
            try {
                prestat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null!=rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null!=conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Connection getConn(){
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(URL,User,Pass);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //����������ѯ����
    /*
    1.������ѯ��������ȷ���Ƿ��н�������Է���ֵ��boolean;
    2.��ѯ������SQL��ѯ��������Դ�����Ա��봫���������
     */
    public ResultSet doQuery(String sql,Object... para){
        //��ѯ��Ҫ�Ƚ��������ݿ������
        conn = getConn();
        try {
            prestat = conn.prepareStatement(sql);
            for (int i = 0; i < para.length; i++) {
                prestat.setObject((i+1),para[i]);
            }
            return prestat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //���û�в�ѯ������ͷ���false;
        return null;
    }
    public boolean doUpdate(String sql,Object... para){
        conn = getConn();
        try {
            prestat = conn.prepareStatement(sql);
            for (int i = 0; i < para.length; i++) {
                prestat.setObject((i+1),para[i]);
            }
            int rows = prestat.executeUpdate();
            if (1<=rows){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        //���û�и��³ɹ����ͷ���false;
        return false;
    }
}
