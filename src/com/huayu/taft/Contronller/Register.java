package com.huayu.taft.Contronller;

import com.huayu.taft.DAO.UserDAO;
import com.huayu.taft.Utils.GetData;
import com.huayu.taft.View.FirstPage;

/**
 * Created by xueyang on 2015/10/11.
 */
public class Register {
    public void register() {
        UserDAO udao = new UserDAO();
        GetData gt = new GetData();
        boolean flag1 = true;
        while (flag1) {
            System.out.println("请输入用户名");
            String name = gt.getData();
            boolean flag2 = udao.checkName(name);
            if (flag2) {//用户名不存在
                flag1 = false;
                boolean flag = true;
                while (flag) {
                    System.out.println("请输入密码");
                    String passwd = gt.getData();
                    System.out.println("请再次输入密码");
                    String passwd1 = gt.getData();
                    if (passwd.equals(passwd1)) {
                        flag = false;
                        boolean flag3 = udao.register(name,passwd);
                        if (flag3) {
                            while (flag3) {
                                System.out.println("注册成功,需缴纳100元押金激活账户");
                                System.out.println("1.充值激活账户 \n2.登录\n3.返回首页");
                                String str = gt.getData();
                                if ("1".equals(str)) {
                                    flag3 = false;
                                    //pay();
                                } else if ("2".equals(str)) {
                                    flag3 = false;
                                    new Login().userLogin();
                                } else if ("3".equals(str)) {
                                    flag3 = false;
                                    FirstPage.firstPage();
                                } else {
                                    System.out.println("输入错误，请重新输入");
                                }
                            }
                        }else{
                            System.out.println("注册失败！！！");
                            for (int i = 0; i < 1; ) {
                                System.out.println("1.继续注册\n2.返回首页");
                                String str1 = gt.getData();
                                if ("1".equals(str1)){
                                    i = 1;
                                    new Register().register();
                                }else if ("2".equals(str1)){
                                    i = 1;
                                    FirstPage.firstPage();
                                }else{
                                    System.out.println("输入错误，请重新输入");
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < 1; ) {
                            System.out.println("两次密码不一致，1.重新输入2.返回首页");
                            String str2 = gt.getData();
                            if ("1".equals(str2)){
                                i = 1;
                            }else if ("2".equals(str2)){
                                i = 1;
                                flag = false;
                                FirstPage.firstPage();
                            }else{
                                System.out.println("输入错误，请重新输入");
                            }
                        }
                    }
                }
            } else {
                System.out.println("用户名已存在，1.重新输入 2.返回首页");
                String str2 = gt.getData();
                for (int i = 0; i < 1; ) {
                    if ("1".equals(str2)){
                        i = 1;
                    }else if ("2".equals(str2)){
                        i = 1;
                        flag1 = false;
                        FirstPage.firstPage();
                    }else{
                        System.out.println("输入错误，请重新输入");
                    }
                }
            }
        }
    }
}