package com.ssm.util;

import java.util.*;

/**
 * @author 13979
 */

public class Tools {
    public static void main(String[] args) {
        String s1 = "hello,world,nihao,";
        List<String> list = segmentString(s1);
        for(String str : list) {
            System.out.println(str);
        }
    }

    //分割字符串

    public static List<String> segmentString(String str) {
        int index = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '\0') {
                String substr = str.substring(index, i);
                index = i + 1;
                result.add(substr);
            }
        }
        return result;
    }

    //拼接字符串


    //计算项目有多少候选人

    public static int calculateCandidates(String str) {
        int i = 0;
        Queue<Character> queue = new LinkedList<>();
        for(char c : str.toCharArray()) {
            if(c == '[') {
                queue.add(c);
            }
            if(c == ']') {
                queue.add(c);
            }
        }
        while(!queue.isEmpty()) {
            char z = queue.poll();
            if(z == '[') {
                i++;
            }
            if(z == ']') {
                i++;
            }
        }
        int x = 2;
        if(i % x == 0) {
            return i / x;
        } else {
            return 0;
        }
    }

    //查询候选人及倾向分

    public static List<List<Integer>> queryStates(String selected) {
        List<List<Integer>> list = new ArrayList<>();
        String[] selectedArr = selected.split(",");
        for(String arr : selectedArr) {
            int idl = 0, idr = 0, scorel = 0, scorer = 0;
            for(int i = 0; i < arr.length(); i++) {
                if(arr.charAt(i) == '[') {
                    idl = i+1;
                }
                if(arr.charAt(i) == '-') {
                    idr = i;
                    scorel = i+1;
                }
                if(arr.charAt(i) == ']') {
                    scorer = i;
                }
            }
            list.add(Arrays.asList(Integer.parseInt(arr.substring(idl, idr)), Integer.parseInt(arr.substring(scorel, scorer))));
        }
        return list;
    }

}
