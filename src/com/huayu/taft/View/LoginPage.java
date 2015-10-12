package com.huayu.taft.View;

import com.huayu.taft.DAO.BookDAO;
import com.huayu.taft.DAO.UserDAO;
import com.huayu.taft.Model.Books;
import com.huayu.taft.Model.Users;
import com.huayu.taft.Utils.GetData;

import java.util.ArrayList;

/**
 * Created by Administrator on 15-10-12.
 */
public class LoginPage {
    UserDAO udao = new UserDAO();
    public void loginPage(String name,String pass,Users user){
        Boolean falg = true;
        while (falg) {
            GetData gd = new GetData();
            System.out.println("登录成功！欢迎" + user.getUser_Name() + "的到来！");
            System.out.println("1.查看或修改信息\n2.还书\n3.借阅信息\n4.借阅历史\n5.充值\n6.借阅");
            String str = gd.getData();
            for (int i = 0; i < 1; ) {
                if ("1".equals(str)) {
                    i = 1;
                    falg = seeMessage(user);
                } else if ("2".equals(str)) {
                    i = 1;
                    falg =  sendBook();
                } else if ("3".equals(str)) {
                    i = 1;
                    falg = findBorrowBook();
                } else if ("4".equals(str)) {
                    i = 1;
                    falg = findBorrowHistory();
                } else if ("5".equals(str)) {
                    i = 1;
                    falg = payMoney();
                }else if("6".equals(str)){
                    i = 1;
                    falg = borrowBooks();
                } else {
                    System.out.println("请认真输入");
                }
            }
        }


    }
    public boolean seeMessage(Users user) {
        System.out.println("=========查看与修改资料========");
        System.out.println("用户名：" + user.getUser_Name());
        System.out.println("邮箱：" + user.getUser_Email());
        System.out.println("手机号码：" + user.getUser_Phone());
        System.out.println("1.修改邮箱\n2.修改手机号\n3.修改密码");
        System.out.println("选择对应序号进行修改，无需改动返回上一页请输入”back“");
        boolean flag = true;
        while (flag) {
            GetData gd = new GetData();
            String c = gd.getData();
            if ("1".equals(c)) {
                flag = false;
                System.out.println("请输入新的邮箱：");
                String nemail = gd.getData();

            } else if ("2".equals(c)) {
                flag = false;
                System.out.println("请输入新手机号码：");

                boolean flag2 = true;
                while (flag2) {
                    String str1 = gd.getData();
                    if (str1.matches("1[34578]\\d{9}")) {
                        flag2 = false;
                        String sql = "update Users set user_Phone = ? where user_ID = ?";
                        boolean flag1 = udao.doUpdate(sql, str1, user.getUser_ID());
                        if (flag1) {
                            for (int i = 0; i < 1; ) {
                                System.out.println("手机号码修改成功\n1.继续查看与修改资料\n2.返回上一层\n3.返回首页");
                                String str2 = gd.getData();
                                if ("1".equals(str2)) {
                                    i = 1;
                                    user = udao.findUser(user.getUser_ID());
                                    seeMessage(user);
                                } else if ("2".equals(str2)) {
                                    i = 1;
                                    return true;
                                } else if ("3".equals(str2)) {
                                    i = 1;
                                    FirstPage.firstPage();
                                } else {
                                    System.out.println("输入错误请认真输入！！！");
                                }
                            }
                        } else {
                            flag2 = false;
                            System.out.println("修改失败,即将返回上一层");
                            return true;
                        }
                    } else {
                        System.out.println("您输入的手机号码不符合要求，请重输（11位）：");
                    }
                }
            }else if ("3".equals(c)) {
                flag = false;
                return changePass(user);
            } else if ("back".equals(c)) {
                flag = false;
                System.out.println("正在返回上一步");
                return true;
            } else {
                System.out.println("无此选择，请重新输入");
            }
        }
        return false;
    }
    public boolean sendBook(){
        return false;
    }

    public boolean findBorrowHistory(){
        return false;
    }

    public boolean findBorrowBook(){
        return false;
    }

    public boolean payMoney(){
        return false;
    }

    public  boolean changePass(Users user){
        System.out.println("========修改密码========");
        System.out.println("请输入原密码进行验证：");
        GetData gd= new GetData();
        UserDAO udao = new UserDAO();
        String oldpass =gd.getData();
        if (oldpass.equals(user.getUser_Pass())){
            System.out.println("原密码验证成功！");
            boolean flag =true;
            while (flag) {
                System.out.println("请输入新的密码：");
                String newpass1 = gd.getData();
                System.out.println("请再次输入新密码");
                String newpass2 = gd.getData();
                if (newpass1.equals(newpass2)) {
                    flag =false;
                    String sql = "update Users set user_Pass = ? where user_ID = ?";
                    boolean flag1 = udao.doUpdate(sql, newpass1, user.getUser_ID());
                    if (flag1){
                        System.out.println("密码修改成功，需要重新登录，正在为您返回首页。。。。");
                        FirstPage.firstPage();
                    }else{
                        System.out.println("密码修改失败，即将返回用户中心");
                        return true;
                    }

                } else {
                    System.out.println("两次密码不一致，请重新输入：");
                }
            }
        }else {
            System.out.println("原密码验证失败，输入任意键重新验证，放弃修改返回上一步请输入“back”");
            String c =gd.getData();
            if ("back".equals(c)){
                System.out.println("正在返回上一步。。。。");
                return true;
            }else {
                changePass(user);
            }
        }
        return false;
    }
    public boolean borrowBooks(){
        System.out.println("开始借书");
        BookDAO bd = new BookDAO();
        ArrayList<String> bts = bd.findBookType();//得到图书类型
        System.out.println("现有图书类型为以下列表，请按类型借阅");

        for (int i = 0;i<bts.size();i++) {
            System.out.println((i+1)+"."+bts.get(i));
        }
        GetData  gd = new GetData();
        System.out.println("请输入您想借阅的类型");

        for(int i=0;i<1;){
            String str = gd.getData();
            if(str.matches("\\d+")){
                int a = Integer.parseInt(str);
                if(a<=0||a>bts.size()){
                    System.out.println("输入有误请重新输入");
                }else{
                   String type = bts.get(a-1);
                    ArrayList<Books> books = bd.findBooks(type, 1);
                    System.out.println("请输入图书编号，以便借阅");
                    for(int ndy = 0;ndy<books.size();ndy++){
                        Books bk = books.get(ndy);
                        System.out.println((ndy+1)+"."+bk.getBk_Name() +"\t"+bk.getBk_Author()+"\t"+bk.getBk_Price()+ " 元\t"+bk.getBk_Count()+" 本");
                    }
                    String bh = gd.getData();
                    //TODO 请在这里做输入判断
                    int sb = Integer.parseInt(bh);

                    Books borrowBook = books.get(sb - 1);//具体的借阅了哪一本书，
                    //TODO 拿到图书ID，首先在图书表中-1，在借阅表中新增一条记录，在借阅详情表中新增一条记录

                }
            }else{
                System.out.println("输入有误请重新输入");
            }
        }
        return false;
    }
}
