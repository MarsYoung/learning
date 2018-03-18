package com.marsyoung.learning.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mazhiyu on 2016/11/19.
 */
public class Sort {

    public static void main(String[] args) {


    }

    public void sort(){
        Integer[] array= {1,2,3,4};
        Arrays.sort(array, Integer::compareTo);
    }
}
