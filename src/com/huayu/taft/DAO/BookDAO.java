package com.huayu.taft.DAO;

import com.huayu.taft.Model.Books;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by xueyang on 2015/10/11.
 */
public class BookDAO extends BaseDAO{
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement prestat = null;

    public ArrayList<Books> checkBook(String userSearch){
        conn = getConn();
        String sql = "select * from books where bk_Name = ? or bk_Author = ?";
        ArrayList<Books> ayyBook = new ArrayList<>();
        try {
            prestat = conn.prepareStatement(sql);
            prestat.setString(1,userSearch);
            prestat.setString(2,userSearch);
            rs = prestat.executeQuery();

            while (rs.next()){
                Books book = new Books();
                book.setBk_ID(rs.getString(1));
                book.setBk_Name(rs.getString(2));
                book.setBk_Author(rs.getString(3));
                book.setBk_Price(rs.getDouble(4));
                book.setBk_Count(rs.getInt(5));
                book.setBt_ID(rs.getString(6));
                book.setBk_State(rs.getInt(7));
                ayyBook.add(book);
            }
            return ayyBook;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return null;
    }

    public Books findBook(String bk_ID){
        conn = getConn();
        String sql = "select * from books where bk_ID = ?";
        try {
            prestat = conn.prepareStatement(sql);
            prestat.setString(1,bk_ID);
            rs = prestat.executeQuery();
            while (rs.next()){
                Books book = new Books();
                book.setBk_ID(rs.getString(1));
                book.setBk_Name(rs.getString(2));
                book.setBk_Author(rs.getString(3));
                book.setBk_Price(rs.getDouble(4));
                book.setBk_Count(rs.getInt(5));
                book.setBt_ID(rs.getString(6));
                book.setBk_State(rs.getInt(7));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return null;
    }
    public ArrayList<String> findBookType(){
        String sql = "select * from bookType where bt_rid = 0";
        conn = getConn();
        try {
            prestat = conn.prepareStatement(sql);
            rs= prestat.executeQuery();
            ArrayList<String> bookTypes = new ArrayList<>();
            boolean flag = false;//如果进入while则表示有结果，再返回结果，如果没有返回null
            while (rs.next()){
                flag = true;
                bookTypes.add(rs.getString("bt_Name"));
            }
            if (flag){
                return bookTypes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

        return null;
    }
    public ArrayList<Books> findBooks(String type,int currentPage){
        String sql = "select * from (select t.*,rownum rn from(select * from books where bt_ID = (select bt_Id from BOOKTYPE where BT_NAME = ?))t)where rn>=? and rn <=?";
        int start = (currentPage-1)*5+1;//当前页的起始值
        int end = currentPage*5;//当前页的结束值
        conn = getConn();
        try {
            prestat = conn.prepareStatement(sql);
            prestat.setString(1, type);
            prestat.setInt(2, start);
            prestat.setInt(3,end);
            rs = prestat.executeQuery();
            ArrayList<Books> books = new ArrayList<>();
            boolean flag = false;
            while (rs.next()){
                flag = true;
                Books book = new Books();
                book.setBk_ID(rs.getString(1));
                book.setBk_Name(rs.getString(2));
                book.setBk_Author(rs.getString(3));
                book.setBk_Price(rs.getDouble(4));
                book.setBk_Count(rs.getInt(5));
                book.setBk_State(rs.getInt(6));
                book.setBt_ID(rs.getString(7));
                books.add(book);
            }
            if(flag){
                //TODO 这是逗你的
                return books;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return null;
    }
}
