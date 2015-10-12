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
            System.out.println("��¼�ɹ�����ӭ" + user.getUser_Name() + "�ĵ�����");
            System.out.println("1.�鿴���޸���Ϣ\n2.����\n3.������Ϣ\n4.������ʷ\n5.��ֵ\n6.����");
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
                    System.out.println("����������");
                }
            }
        }


    }
    public boolean seeMessage(Users user) {
        System.out.println("=========�鿴���޸�����========");
        System.out.println("�û�����" + user.getUser_Name());
        System.out.println("���䣺" + user.getUser_Email());
        System.out.println("�ֻ����룺" + user.getUser_Phone());
        System.out.println("1.�޸�����\n2.�޸��ֻ���\n3.�޸�����");
        System.out.println("ѡ���Ӧ��Ž����޸ģ�����Ķ�������һҳ�����롱back��");
        boolean flag = true;
        while (flag) {
            GetData gd = new GetData();
            String c = gd.getData();
            if ("1".equals(c)) {
                flag = false;
                System.out.println("�������µ����䣺");
                String nemail = gd.getData();

            } else if ("2".equals(c)) {
                flag = false;
                System.out.println("���������ֻ����룺");

                boolean flag2 = true;
                while (flag2) {
                    String str1 = gd.getData();
                    if (str1.matches("1[34578]\\d{9}")) {
                        flag2 = false;
                        String sql = "update Users set user_Phone = ? where user_ID = ?";
                        boolean flag1 = udao.doUpdate(sql, str1, user.getUser_ID());
                        if (flag1) {
                            for (int i = 0; i < 1; ) {
                                System.out.println("�ֻ������޸ĳɹ�\n1.�����鿴���޸�����\n2.������һ��\n3.������ҳ");
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
                                    System.out.println("����������������룡����");
                                }
                            }
                        } else {
                            flag2 = false;
                            System.out.println("�޸�ʧ��,����������һ��");
                            return true;
                        }
                    } else {
                        System.out.println("��������ֻ����벻����Ҫ�������䣨11λ����");
                    }
                }
            }else if ("3".equals(c)) {
                flag = false;
                return changePass(user);
            } else if ("back".equals(c)) {
                flag = false;
                System.out.println("���ڷ�����һ��");
                return true;
            } else {
                System.out.println("�޴�ѡ������������");
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
        System.out.println("========�޸�����========");
        System.out.println("������ԭ���������֤��");
        GetData gd= new GetData();
        UserDAO udao = new UserDAO();
        String oldpass =gd.getData();
        if (oldpass.equals(user.getUser_Pass())){
            System.out.println("ԭ������֤�ɹ���");
            boolean flag =true;
            while (flag) {
                System.out.println("�������µ����룺");
                String newpass1 = gd.getData();
                System.out.println("���ٴ�����������");
                String newpass2 = gd.getData();
                if (newpass1.equals(newpass2)) {
                    flag =false;
                    String sql = "update Users set user_Pass = ? where user_ID = ?";
                    boolean flag1 = udao.doUpdate(sql, newpass1, user.getUser_ID());
                    if (flag1){
                        System.out.println("�����޸ĳɹ�����Ҫ���µ�¼������Ϊ��������ҳ��������");
                        FirstPage.firstPage();
                    }else{
                        System.out.println("�����޸�ʧ�ܣ����������û�����");
                        return true;
                    }

                } else {
                    System.out.println("�������벻һ�£����������룺");
                }
            }
        }else {
            System.out.println("ԭ������֤ʧ�ܣ����������������֤�������޸ķ�����һ�������롰back��");
            String c =gd.getData();
            if ("back".equals(c)){
                System.out.println("���ڷ�����һ����������");
                return true;
            }else {
                changePass(user);
            }
        }
        return false;
    }
    public boolean borrowBooks(){
        System.out.println("��ʼ����");
        BookDAO bd = new BookDAO();
        ArrayList<String> bts = bd.findBookType();//�õ�ͼ������
        System.out.println("����ͼ������Ϊ�����б��밴���ͽ���");

        for (int i = 0;i<bts.size();i++) {
            System.out.println((i+1)+"."+bts.get(i));
        }
        GetData  gd = new GetData();
        System.out.println("������������ĵ�����");

        for(int i=0;i<1;){
            String str = gd.getData();
            if(str.matches("\\d+")){
                int a = Integer.parseInt(str);
                if(a<=0||a>bts.size()){
                    System.out.println("������������������");
                }else{
                   String type = bts.get(a-1);
                    ArrayList<Books> books = bd.findBooks(type, 1);
                    System.out.println("������ͼ���ţ��Ա����");
                    for(int ndy = 0;ndy<books.size();ndy++){
                        Books bk = books.get(ndy);
                        System.out.println((ndy+1)+"."+bk.getBk_Name() +"\t"+bk.getBk_Author()+"\t"+bk.getBk_Price()+ " Ԫ\t"+bk.getBk_Count()+" ��");
                    }
                    String bh = gd.getData();
                    //TODO ���������������ж�
                    int sb = Integer.parseInt(bh);

                    Books borrowBook = books.get(sb - 1);//����Ľ�������һ���飬
                    //TODO �õ�ͼ��ID��������ͼ�����-1���ڽ��ı�������һ����¼���ڽ��������������һ����¼

                }
            }else{
                System.out.println("������������������");
            }
        }
        return false;
    }
}
