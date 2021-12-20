package com.company.day14;

import com.company.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        int day = 14;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
        System.out.println(part1(test,10)+","+part2(test,10));
        System.out.println(part1(input,10)+","+part2(input,10));
        System.out.println(part2(test,40));
        System.out.println(part2(input, 40));


    }

    private static String part1(List<String> input, int steps){
        if(input.size() == 0) return "error";

        StringBuilder starting = new StringBuilder(input.get(0));
        String[] cases = new String[input.size()-2];
        for (int i = 2; i < input.size(); i++) {
            String s = input.get(i);
            cases[i-2] = input.get(i);
        }

        for (int i = 0; i < steps; i++) {
            String snapshot = starting.toString();
            int startinLength = starting.length();
            for (int j = 0, virtual =0; j < startinLength-1; j++, virtual++) {
                for (String aCase : cases) {
                    String[] matchArray = aCase.split(" -> ");

//                    if(i ==0)System.out.println(matchArray[0]+" -> "+matchArray[1]);

                    if(matchArray[0].equals(snapshot.substring(j,j+2))){
                        starting.insert(virtual+1, matchArray[1]);
                        virtual++;
                    }
                }
            }
        }
        String polymere =  starting.toString();
        HashMap<String, Long> map = new HashMap<>();
        for (int i = 0; i < polymere.length(); i++) {
            String key = polymere.substring(i, i + 1);
            if(!map.containsKey(key)) map.put(key, 1L);
            else {
                Long along = map.get(key);
                map.put(key,along+1);
            }
        }
        Set<String> keySet= map.keySet();
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for (String s : keySet) {
            System.out.println(s+": "+map.get(s));
            if(map.get(s) < min) min = map.get(s);
            if(map.get(s) > max) max = map.get(s);
        }
        return String.valueOf(max-min);
    }

    private static String part2(List<String> input, int steps){
        if(input.size() == 0) return "error";

        String starting = input.get(0);
        String[] cases = new String[input.size()-2];
        for (int i = 2; i < input.size(); i++) {
            String s = input.get(i);
            cases[i-2] = input.get(i);
        }

        HashMap<String, Long> pairsMap = new HashMap<>();
        int size  = starting.length();
        for (int i = 0; i < size-1; i++) {
            String key = starting.substring(i,i+2);
            if(!pairsMap.containsKey(key)) pairsMap.put(key, 1L);
            else {
                Long aLong = pairsMap.get(key);
                pairsMap.put(key, aLong+1);
            }
        }
        HashMap<String, Long> aminoMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String key = starting.substring(i,i+1);
            if(!aminoMap.containsKey(key)) aminoMap.put(key, 1L);
            else {
                Long aLong = aminoMap.get(key);
                aminoMap.put(key, aLong+1);
            }
        }

        for (int i = 0; i < steps; i++) {
            HashMap<String, Long> tmp = pairsMap;
            pairsMap = new HashMap<>();

            for (String aCase : cases) {
                String[] aCaseArray = aCase.split(" -> ");
                String pair = aCaseArray[0];
                if(tmp.containsKey(pair)) {
                    String replaceAmino = aCaseArray[1];
                    if(aminoMap.containsKey(replaceAmino)){
                        Long aLong = aminoMap.get(replaceAmino);
                        aminoMap.put(replaceAmino, tmp.get(pair)+aLong);
                    } else aminoMap.put(replaceAmino, tmp.get(pair));

                    String key1 = pair.substring(0,1)+ replaceAmino;
                    String key2 = replaceAmino + pair.substring(1,2);
                    if(pairsMap.containsKey(key1) ){
                        Long tmpLong = pairsMap.get(key1);
                        pairsMap.put(key1, tmpLong+tmp.get(pair));
                    } else  pairsMap.put(key1, tmp.get(pair));
                    if(pairsMap.containsKey(key2) ){
                        Long tmpLong = pairsMap.get(key2);
                        pairsMap.put(key2, tmpLong+tmp.get(pair));
                    } else  pairsMap.put(key2, tmp.get(pair));
                }
            }
        }

        Set<String> keySet= aminoMap.keySet();

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for (String s : keySet) {
            System.out.println(s+": "+aminoMap.get(s));
            if(aminoMap.get(s) < min) min = aminoMap.get(s);
            if(aminoMap.get(s) > max) max = aminoMap.get(s);
        }
        return String.valueOf(max-min);
    }
}
