package com.newsoftvalley.customer;

import java.util.HashMap;
import java.util.Map;

public class OrderResource {
    long id = 0;
    Map<Long,Order> map = new HashMap<>();
    public Order get(long id){
        return map.get(id);
    }
    public void update(long id,Order order){
        order.setId(id);
        map.put(id,order);
    }
    public void delete(long id){
        map.remove(id);
    }
    public long create(Order order){
        long curId = id;
        order.setId(curId);
        map.put(curId, order);
        id++;
        return curId;
    }
}
