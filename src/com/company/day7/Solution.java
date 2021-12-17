package com.company.day7;

import com.company.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Solution {
    public static void main(String[] args) {
        int day = 7;
        List<String> test = Utils.readLines("input/"+day+"/test");
        List<String> input = Utils.readLines("input/"+day+"/input");
        System.out.println(part1(test));
        System.out.println(part1(input));
        System.out.println(part2(test));
        System.out.println(part2(input));
    }

    private static String part1(List<String> input){
        String[] crab = input.get(0).split(",");
        int[] crabi = new int[crab.length];
        int i=0;
        for (var c:
             crab) {
            crabi[i] = Integer.valueOf(c);
            i++;
        }

        int maxHorizontal = Arrays.stream(crabi).max().getAsInt();
        int fuel=Integer.MAX_VALUE;
        int result=-1;
        for (int j = 0; j<= maxHorizontal; j++) {
            int tmp = 0;
            for (var c:
                 crabi) {
               tmp = tmp + (Math.abs(c - j));
//                System.out.println("move from "+c+" to ");

            }
            if(fuel> tmp){
                fuel=tmp;
                result=j;
            }

        }

        return String.valueOf(fuel);
    }

    private static String part2(List<String> input){
        String[] crab = input.get(0).split(",");
        int[] crabi = new int[crab.length];
        int i=0;
        for (var c:
                crab) {
            crabi[i] = Integer.valueOf(c);
            i++;
        }

        int maxHorizontal = Arrays.stream(crabi).max().getAsInt();
        int fuel=Integer.MAX_VALUE;
        int result=-1;
        for (int j = 0; j<= maxHorizontal; j++) {
            int tmp = 0;
            for (var c:
                    crabi) {
                    int moves= (Math.abs(c - j));
                    tmp = tmp +  (moves * (moves + 1) / 2);


//                System.out.println("move from "+c+" to ");

            }
            if(fuel> tmp){
                fuel=tmp;
                result=j;
            }

        }

        return String.valueOf(fuel);
    }
}
