package com.company.one;

import com.company.Utils;

import java.util.List;

public class Prt1 {
    public static void main(String[] args) {
       List<String> ls = Utils.readLines("input.txt/1/1.txt");
        int tmp = -1;
        int count = 0;
        for (var s:
             ls) {
            if(Integer.valueOf(s) > tmp){
                count++;
            }
            tmp = Integer.valueOf(s);
        }
        System.out.println(count-1);
    }
}
