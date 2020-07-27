package com.example.arithmetic.mapsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lgh on 2020/7/27:20:11
 * @description HashMap排序
 */
public class MapSort {

    static HashMap<Integer, User> map = new HashMap<>();

    public static void main(String[] args) {
        User user1 = new User();
        user1.setAge(12);
        user1.setName("zhangsan");
        map.put(1, user1);

        User user2 = new User();
        user2.setAge(22);
        user2.setName("lisi");
        map.put(2, user2);

        User user3 = new User();
        user3.setAge(32);
        user3.setName("wangwu");
        map.put(3, user3);

        HashMap<Integer, User> sort = sortHashMap(map);
        for (Integer integer : sort.keySet()) {
            System.out.println(sort.get(integer));
        }

    }

    private static HashMap<Integer, User> sortHashMap(HashMap<Integer, User> map) {
        LinkedHashMap<Integer, User> newHashMap = new LinkedHashMap<>();
        Set<Map.Entry<Integer, User>> entries = map.entrySet();
        ArrayList<Map.Entry<Integer, User>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o1.getValue().getAge() - o2.getValue().getAge();
            }
        });

        for (Map.Entry<Integer, User> entry : list) {
            newHashMap.put(entry.getKey(), entry.getValue());
        }

        return newHashMap;
    }
}
