package com.huayu.taft.View;

import com.huayu.taft.Contronller.ManagerLogin;
import com.huayu.taft.Contronller.Register;
import com.huayu.taft.Contronller.Login;
import com.huayu.taft.Utils.GetData;

/**
 * Created by xueyang on 2015/10/11.
 */
public class FirstPage {
    public static void main(String[] args) {
        firstPage();
    }
    public static void firstPage() {//首页
        System.out.println("========图书馆管理========");
        System.out.println("1.登录");//用户，管理员和超级管理员均由此口登录
        System.out.println("2.注册");//注册只提供用户注册
        System.out.println("3.退出");
        System.out.println("请选择：");
        GetData gd =new GetData();
        String str =gd.getData();
        if (str.equals("1")){
            boolean flag1 =true;
            while (flag1) {
                System.out.println("========请选择你的身份========");
                System.out.println("1.用户");
                System.out.println("2.管理员");//含超管。所以超级管理员和管理员的账户不能重复
                String str1 = gd.getData();
                if (str1.equals("1")) {
                    flag1=false;
                    new Login().userLogin();//跳转
                } else if (str1.equals("2")) {
                    flag1=false;
                    new ManagerLogin().managerLogin();
                } else {
                    System.out.println("无此选项，请重新选择：");
                }
            }
        }else if ("2".equals(str)){
            new Register().register();
        }else if ("3".equals(str)){
            System.out.println("正在为您退出，请稍后。。。。");
            System.exit(0);
        }else {
            System.out.println("无此选择，正在重返首页：");
            firstPage();
        }
    }
}
