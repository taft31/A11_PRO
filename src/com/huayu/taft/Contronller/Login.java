package com.huayu.taft.Contronller;

import com.huayu.taft.DAO.UserDAO;
import com.huayu.taft.Model.Users;
import com.huayu.taft.Utils.GetData;
import com.huayu.taft.View.FirstPage;
import com.huayu.taft.View.LoginPage;


/**
 * Created by Administrator on 15-10-11.
 */
public class Login {
    UserDAO udao = new UserDAO();
    public void userLogin() {
        GetData gd = new GetData();
        System.out.println("========用户登录========");
        System.out.println("请输入用户名：");
        String name = gd.getData();
        System.out.println("请输入密码");
        String pass = gd.getData();
        Users user = new UserDAO().checkLogin(name, pass);//检查

        if (null != user ) {
            new LoginPage().loginPage(name,pass,user);
        } else {
            System.out.println("用户名或者密码错误~返回首页输入“back”，重新登录请输入任意键：");
            String c = gd.getData();
            if ("back".equals(c)) {
                System.out.println("正在返回首页，请稍后：");
                FirstPage.firstPage();
            } else {
                userLogin();
            }
        }
    }




}

