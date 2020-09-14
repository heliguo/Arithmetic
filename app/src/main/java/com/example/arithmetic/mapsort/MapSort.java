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

        char c1 = '你';
        char c2 = 'a';
        char c3 = 'ぁ';
        System.out.println(Integer.toHexString(c1));
        System.out.println(Integer.toHexString(c2));
        System.out.println(Integer.toHexString(c3));

        System.out.println(">>>>>>>>"+Character.toChars(covert("4F60"))[0]);

        System.out.println("=======" + (1 << 3));
        System.out.println("=======" + (3 << 3));
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

    private static int covert(String content){
        int number=0;
        String [] HighLetter = {"A","B","C","D","E","F"};
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i <= 9;i++){
            map.put(i+"",i);
        }
        for(int j= 10;j<HighLetter.length+10;j++){
            map.put(HighLetter[j-10],j);
        }
        String[]str = new String[content.length()];
        for(int i = 0; i < str.length; i++){
            str[i] = content.substring(i,i+1);
        }
        for(int i = 0; i < str.length; i++){
            number += map.get(str[i])*Math.pow(16,str.length-1-i);
        }
        return number;
    }
}
