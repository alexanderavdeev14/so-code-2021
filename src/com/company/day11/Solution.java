package com.company.day11;

import com.company.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        int day = 11;
        int xMax = 10;
        int yMax = 10;
        int steps = 100;
        List<String> test = Utils.readLines("input/"+day+"/test");
        List<String> input = Utils.readLines("input/"+day+"/input");
        System.out.println(part1(test, xMax  , yMax,steps));
        System.out.println(part1(input, xMax, yMax, steps));
        System.out.println(part2(test,xMax,yMax));
        System.out.println(part2(input,xMax,yMax));
    }

    private static String part1(List<String> input, int xMax, int yMax, double steps){
        int[][] octopus = new int[xMax][yMax];
        int row =0;
        for (String s : input) {
            for (int i = 0; i < s.length(); i++) {
                octopus[row][i] = Integer.parseInt(s.substring(i,i+1));
            }
            row++;
        }
        long flash = 0;
        for (int step = 0; step <steps; step++) {
            Map<String, Boolean> hashMap = new HashMap<>();
            for (int i = 0; i < xMax; i++) {
                for (int j = 0; j < yMax; j++) {
                    flash+=calculateFlashes(octopus, i, j, 0, hashMap);
                }
            }
        }
        return String.valueOf(flash);
    }

    private static long calculateFlashes(int[][] array, int i, int j, long result, Map<String, Boolean> hashMap) {
        String key = i+","+j;
        try {
            if(!hashMap.containsKey(key)) hashMap.put(key, Boolean.FALSE);
            if(!hashMap.get(key))array[i][j]++;
            if(array[i][j] <= 9) return result;
            if(hashMap.get(key)){
                return result;
            }
        } catch (IndexOutOfBoundsException e){
            return result;
        }

        result++;
        hashMap.put(key, Boolean.TRUE);
        array[i][j]=0;
        return calculateFlashes(array, i+1, j, 0, hashMap)+
                calculateFlashes(array, i, j+1,0, hashMap)+
                calculateFlashes(array,i-1,j,0, hashMap)+
                calculateFlashes(array, i, j-1, 0, hashMap)+
                calculateFlashes(array, i+1, j+1,0, hashMap)+
                calculateFlashes(array, i-1, j+1,0, hashMap)+
                calculateFlashes(array, i+1, j-1,0, hashMap)+
                calculateFlashes(array, i-1, j-1,0, hashMap)+result;

    }

    private static String part2(List<String> input, int xMax, int yMax){
        int[][] octopus = new int[xMax][yMax];
        int row =0;

        for (String s : input) {
            for (int i = 0; i < s.length(); i++) {
                octopus[row][i] = Integer.parseInt(s.substring(i, i + 1));
            }
            row++;
        }

        long step = 0;
       outer: while(true) {
            step++;
            Map<String, Boolean> hashMap = new HashMap<>();
            for (int i = 0; i < xMax; i++) {
                for (int j = 0; j < yMax; j++) {
                    calculateFlashes(octopus, i, j, 0, hashMap);
                }
            }
            for (int i = 0; i < xMax; i++) {
                for (int j = 0; j < yMax; j++) {
                    String key = i+","+j;
                    if(hashMap.containsKey(key)){
                        if(!hashMap.get(key)) continue outer;
                    } else continue outer;
                }
            }
            break;
        }
        return String.valueOf(step);
    }
}
