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
    public static void firstPage() {//��ҳ
        System.out.println("========ͼ��ݹ���========");
        System.out.println("1.��¼");//�û�������Ա�ͳ�������Ա���ɴ˿ڵ�¼
        System.out.println("2.ע��");//ע��ֻ�ṩ�û�ע��
        System.out.println("3.�˳�");
        System.out.println("��ѡ��");
        GetData gd =new GetData();
        String str =gd.getData();
        if (str.equals("1")){
            boolean flag1 =true;
            while (flag1) {
                System.out.println("========��ѡ��������========");
                System.out.println("1.�û�");
                System.out.println("2.����Ա");//�����ܡ����Գ�������Ա�͹���Ա���˻������ظ�
                String str1 = gd.getData();
                if (str1.equals("1")) {
                    flag1=false;
                    new Login().userLogin();//��ת
                } else if (str1.equals("2")) {
                    flag1=false;
                    new ManagerLogin().managerLogin();
                } else {
                    System.out.println("�޴�ѡ�������ѡ��");
                }
            }
        }else if ("2".equals(str)){
            new Register().register();
        }else if ("3".equals(str)){
            System.out.println("����Ϊ���˳������Ժ󡣡�����");
            System.exit(0);
        }else {
            System.out.println("�޴�ѡ�������ط���ҳ��");
            firstPage();
        }
    }
}
