package com.company.one;

import com.company.Utils;

import java.util.List;

public class Prt2 {
    public static void main(String[] args) {
        List<String> ls = Utils.readLines("input.txt/1/2.txt");
        int tmp = -1;
        int count = 0;
        int previousSum = 0;
        for (int i=0; i<ls.size(); i++) {
            int sum =0;
            for(int j=i; j<i+3; j++){
                if(j >= ls.size()){
                    sum=-1;
                    break;
                }
                int value = Integer.valueOf(ls.get(j));
                sum += value;
            }
            if(sum>previousSum){
                count++;
            }
            previousSum = sum;

        }
        System.out.println(count-1);
    }
}

