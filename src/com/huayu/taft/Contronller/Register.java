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
            System.out.println("�������û���");
            String name = gt.getData();
            boolean flag2 = udao.checkName(name);
            if (flag2) {//�û���������
                flag1 = false;
                boolean flag = true;
                while (flag) {
                    System.out.println("����������");
                    String passwd = gt.getData();
                    System.out.println("���ٴ���������");
                    String passwd1 = gt.getData();
                    if (passwd.equals(passwd1)) {
                        flag = false;
                        boolean flag3 = udao.register(name,passwd);
                        if (flag3) {
                            while (flag3) {
                                System.out.println("ע��ɹ�,�����100ԪѺ�𼤻��˻�");
                                System.out.println("1.��ֵ�����˻� \n2.��¼\n3.������ҳ");
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
                                    System.out.println("�����������������");
                                }
                            }
                        }else{
                            System.out.println("ע��ʧ�ܣ�����");
                            for (int i = 0; i < 1; ) {
                                System.out.println("1.����ע��\n2.������ҳ");
                                String str1 = gt.getData();
                                if ("1".equals(str1)){
                                    i = 1;
                                    new Register().register();
                                }else if ("2".equals(str1)){
                                    i = 1;
                                    FirstPage.firstPage();
                                }else{
                                    System.out.println("�����������������");
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < 1; ) {
                            System.out.println("�������벻һ�£�1.��������2.������ҳ");
                            String str2 = gt.getData();
                            if ("1".equals(str2)){
                                i = 1;
                            }else if ("2".equals(str2)){
                                i = 1;
                                flag = false;
                                FirstPage.firstPage();
                            }else{
                                System.out.println("�����������������");
                            }
                        }
                    }
                }
            } else {
                System.out.println("�û����Ѵ��ڣ�1.�������� 2.������ҳ");
                String str2 = gt.getData();
                for (int i = 0; i < 1; ) {
                    if ("1".equals(str2)){
                        i = 1;
                    }else if ("2".equals(str2)){
                        i = 1;
                        flag1 = false;
                        FirstPage.firstPage();
                    }else{
                        System.out.println("�����������������");
                    }
                }
            }
        }
    }
}