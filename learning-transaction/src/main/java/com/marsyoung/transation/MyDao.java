package com.marsyoung.transation;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mazhiyu on 16/7/15.
 */
@Repository
public class MyDao extends  BaseDao{


    public long insert(MyBean myBean){
        Map<String,Object > map=new HashMap<String,Object>();
        map.put("id",myBean.getId());
        map.put("status",myBean.getStatus());
        map.put("name",myBean.getName());
        return this.insert("mx_marsyoung_test",map);
    }

    public int update(int status,int id){
        return this.update("update mx_marsyoung_test set status = ? where id = ?" ,status,id);
    }
}
