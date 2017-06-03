package com.marsyoung.test;
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
  
public class ListRemoveTest {  
    public static void main(String[] args) {  
        ListRemoveTest test = new ListRemoveTest();  
          
        System.out.println("-1-使用jdk5.0以后的增强for循环去remove");  
        List<String> list = test.buildList();  
        try {  
            for (String str : list) {  
                list.remove(str);  
            }  
        } catch (Exception e) {  
            // java.util.ConcurrentModificationException  
            e.printStackTrace();   
        }  
  
        System.out.println("-2-使用Iterator的remove");  
        list = test.buildList();  
        try {  
            Iterator<String> iterator = list.iterator();  
            while (iterator.hasNext()) {  
                iterator.remove();  
            }  
        } catch (Exception e) {  
            // java.lang.IllegalStateException  
            e.printStackTrace();  
        }  
  
        System.out.println("-3-iterator遍历+list的remove");  
        try {  
            list = test.buildList();  
            for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {  
                String str = (String) iterator.next();  
                list.remove(str);  
            }  
        } catch (Exception e) {  
            // java.util.ConcurrentModificationException  
            e.printStackTrace();  
        }  
          
        System.out.println("-4-使用list的remove(int)方法. [由后向前删除]");  
        list = test.buildList();  
        for (int i = list.size(); i > 0; i--) {  
            list.remove(i - 1);  
        }  
  
        System.out.println("-5-使用list的remove(int)方法. [由前向后删除]");  
        list = test.buildList();  
        for (int i = 0; i < list.size(); i++) {  
            list.remove(0);  
        }  
    }  
  
    private List<String> buildList() {  
        List<String> list = new ArrayList<String>();  
        list.add("a");  
        list.add("b");  
        list.add("c");  
        return list;  
    }  
}  