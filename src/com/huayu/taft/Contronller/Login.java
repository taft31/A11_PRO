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
        System.out.println("========�û���¼========");
        System.out.println("�������û�����");
        String name = gd.getData();
        System.out.println("����������");
        String pass = gd.getData();
        Users user = new UserDAO().checkLogin(name, pass);//���

        if (null != user ) {
            new LoginPage().loginPage(name,pass,user);
        } else {
            System.out.println("�û��������������~������ҳ���롰back�������µ�¼�������������");
            String c = gd.getData();
            if ("back".equals(c)) {
                System.out.println("���ڷ�����ҳ�����Ժ�");
                FirstPage.firstPage();
            } else {
                userLogin();
            }
        }
    }




}

