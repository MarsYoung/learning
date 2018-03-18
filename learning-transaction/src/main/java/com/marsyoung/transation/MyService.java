package com.marsyoung.transation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mazhiyu on 16/7/15.
 */
@Service("myService")
public class MyService {

    @Autowired
    MyDao myDao;


    //应该不回插入数据才对
    @Transactional
    public void doSomeThing() {
        MyBean myBean = new MyBean();
        myBean.setStatus(1);
        myBean.setName("doSomeThing");
        myDao.insert(myBean);
        throw new RuntimeException();
    }


    public void doSomeThing2() {
        MyBean myBean = new MyBean();
        myBean.setStatus(1);
        myBean.setName("doSomeThing2");
        myDao.insert(myBean);
        throw new RuntimeException();
    }


    @Transactional
    public void doSomeThing3() {
        MyBean myBean = new MyBean();
        myBean.setStatus(1);
        myBean.setName("doSomeThing3");
        long id=myDao.insert(myBean);

        myDao.update(100,(int)id);
        //throw new RuntimeException();
    }

}

