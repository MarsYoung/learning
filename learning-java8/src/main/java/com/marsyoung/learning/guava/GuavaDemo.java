package com.marsyoung.learning.guava;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Ordering.natural;


/**
 * Created by mazhiyu on 2017/8/31.
 */
@Slf4j
public class GuavaDemo {

    //Null的含义是混淆的
    public void nullInMap() {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("key", null);
        log.info("tmpMap.get(\"key\") = {}", tmpMap.get("key"));
        log.info("tmpMap.get(\"notexist\") = {}", tmpMap.get("notexist"));
    }

    public void optional() {
        Optional<Integer> possible = Optional.of(5);
        log.info("possible.isPresent() = {}", possible.isPresent());
        log.info("possible.get() = {}", possible.get());
    }

    public void check() {
        checkArgument(2 > 1, "2>1 is %s");
    }

    public void ordering() {
        Ordering<String> byLength = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        byLength.natural();
    }

    public void immutableCollections() {
        Set s = new TreeSet();
        s.add(1);
        s.add(2);
        Set immutableSet = ImmutableSet.copyOf(s);
    }

    public void cache() {
        LoadingCache<Integer, Commodity> commodityCache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .removalListener(new RemovalListener() {
                    @Override
                    public void onRemoval(RemovalNotification notification) {
                        log.info("commodity {} has been removed.", notification.getKey());
                    }
                }).build(new CacheLoader<Integer, Commodity>() {
                    @Override
                    public Commodity load(Integer key) throws Exception {
                        return getCommodity(key);
                    }
                });
        try {
            commodityCache.get(1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Commodity getCommodity(Integer key) {
        return new Commodity();
    }

    public static void main(String[] args) {
        GuavaDemo demo = new GuavaDemo();
        demo.nullInMap();
        demo.optional();
        demo.cache();
    }

    @Data
    class Commodity {
        int cid;
        String name;
    }
}
