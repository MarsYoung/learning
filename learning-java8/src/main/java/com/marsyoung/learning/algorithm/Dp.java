package com.marsyoung.learning.algorithm;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/*
 * 假设有35块钱要买商品，
 * 优惠券使用张数不受限制，
 * 求最多可以使用优惠券金额
 *
 * */
public class Dp {

    private final int totalMoney = 35;

    public List<Coupon> genData() {
        List<Coupon> coupons
                = Lists.newArrayList();
        coupons.add(
                Coupon.builder().name("a").full(5).cut(1).build()
        );
        coupons.add(
                Coupon.builder().name("b").full(10).cut(2).build()
        );
        coupons.add(
                Coupon.builder().name("c").full(5).cut(2).build()
        );
        coupons.add(
                Coupon.builder().name("d").full(8).cut(3).build()
        );
        coupons.add(
                Coupon.builder().name("e").full(15).cut(5).build()
        );
        coupons.add(
                Coupon.builder().name("f").full(20).cut(5).build()
        );
        return coupons;
    }

    /*
     * 表示总价为totalMoney
     * 名字为name的优惠券和他之前已经被试着装入背包的 最优结果
     * */
    @Data
    class Result {
        String name;
        int totalMoney;
        int totalCut;
    }

    public void calculate() {
        List<Coupon> coupons = genData();
        int[][] cutValue = new int[coupons.size()+1][totalMoney+1];
        for (int i = 1; i <= coupons.size(); i++) {
            Coupon coupon = coupons.get(i-1);
            for (int j = 1; j <= totalMoney; j++) {
                cutValue[i][j] = cutValue[i - 1][j];//不放入
                if(coupon.getFull() <= j){
                    int temp = cutValue[i - 1][j - coupon.getFull()] + coupon.getCut();//放入
                    if (coupon.getFull() <= j && temp > cutValue[i][j]) {
                        cutValue[i][j] = temp;
                    }
                }
            }
        }

        for(int i=1;i<cutValue.length;i++){
            for(int j=1;j<cutValue[i].length;j++){
                System.out.print(cutValue[i][j]+"\t");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        new Dp().calculate();
    }


}
