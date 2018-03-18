package com.marsyoung.learning.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mazhiyu on 2017/6/20.
 */
//parallelStream和foreach一起用会异步。
public class RemoveTest {

    public void unsafe(){
        Set<String> notCached = Arrays.asList(1,2,3,4,5,6,7,8,9,10).parallelStream().map(n->n+"").collect(Collectors.toSet());
        List<Integer> resultFromCache = Arrays.asList(1,2,3,4,5);
        if (resultFromCache != null && resultFromCache.size() != 0) {
            resultFromCache.parallelStream().forEach(n -> {
                if (n != null) {
                    notCached.remove(n+"");
                }
            });
        }
        System.out.println("unsafe:"+notCached.size());
    }

    public void safe(){
        Set<String> notCached = Arrays.asList(1,2,3,4,5,6,7,8,9,10).parallelStream().map(n->n+"").collect(Collectors.toSet());
        List<Integer> resultFromCache = Arrays.asList(1,2,3,4,5);
        if (resultFromCache != null && resultFromCache.size() != 0) {
            resultFromCache.stream().forEach(n -> {
                if (n != null) {
                    notCached.remove(n+"");
                }
            });
        }
        System.out.println("safe:"+notCached.size());
    }

    public static void main(String[] args) {
        RemoveTest test=new RemoveTest();
        for(int i=0;i<100;i++){
            test.unsafe();
            test.safe();
        }


    }
}
