package com.marsyoung.learning.java8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mazhiyu on 2016/11/24.
 */
public class Test {

    public static void main(String[] args) {
//
//        List<Integer> integerList= Arrays.asList(90,99,100,490,500,990,1000,1500,1990,2000,2990,3000,5880,5990,8800,9800,9990);
//        for(int x:integerList){
//            String s="";
//            if(x<100){
//                s=0+"."+x;
//            }else{
//                s=x+"";
//                int l=s.length();
//                String tmp=s.substring(0,l-2)+"."+s.substring(l-2,l);
//                s=tmp;
//            }
//            check(x,s);
//        }


    }

    static void  check(int intV,String sV){
        System.out.println(Double.valueOf(sV).doubleValue()*100);
        System.out.println(Double.valueOf(sV).doubleValue()*100!=intV);
        System.out.println(new BigDecimal(sV).multiply(new BigDecimal("100")).doubleValue()!=intV);

    }
}
