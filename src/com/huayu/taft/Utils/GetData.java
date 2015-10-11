package com.huayu.taft.Utils;

import java.util.Scanner;

/**
 * Created by Administrator on 15-10-11.
 */
public class GetData {
    public String getData(){
        String str = null;
        Scanner s = new Scanner(System.in);
        str = s.nextLine();
        if(null!=str&&!"".equals(str)){
            return str;
        }else{
            System.out.println("∫√∫√ ‰»Î");
            return getData();
        }
    }
}
