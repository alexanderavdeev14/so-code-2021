package com.company.day6;

import com.company.Utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int day = 6;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
        System.out.println("Test 1: "+part1(test,18));
        System.out.println("Solution 1: "+part1(input,80));
        System.out.println("Test 2: "+part2(test));
        System.out.println("Solution 2: "+part2(input));
    }

    private static String part1(List<String> input, int days){
        List<Integer> fishes = new ArrayList<>();

        String[] initState = input.get(0).split(",");
        for (int i = 0; i < initState.length; i++) {
            fishes.add(Integer.valueOf(initState[i]));
        }

//        System.out.println("Initial state: "+fishes);
        for (int day = 0; day < days; day++) {
            int size = fishes.size();
            Double numberOfSpawnFish=0d;
            for (int i = 0; i <size ; i++) {
               int currentCount =  fishes.get(i);
               if(currentCount == 0) {
                   fishes.set(i, Integer.valueOf(6));
                   currentCount = fishes.get(i);
                   fishes.add(8);
                   continue;
               }
                fishes.set(i, Integer.valueOf(--currentCount));
            }
        }

        return String.valueOf(fishes.size());
    }

    private static String part2(List<String> input){
        List<Integer> fishes = new ArrayList<>();
        List<Long> zip = new ArrayList<>();
        int days = 256;

        String[] initState = input.get(0).split(",");
        for (int i = 0; i < initState.length; i++) {
            fishes.add(Integer.valueOf(initState[i]));
        }
        for (var s:
                fishes) {
            zip.add(1L);
        }
//        System.out.println("Initial state: "+fishes);
//        System.out.println("Initial state: "+zip);
        for (int day = 0; day < days; day++) {
            int size = fishes.size();
            Double numberOfSpawnFish=0d;
            for (int i = 0; i <size ; i++) {
                int currentCount =  fishes.get(i);
                if(currentCount == 0) {
                    fishes.set(i, Integer.valueOf(6));
                    currentCount = fishes.get(i);
                    numberOfSpawnFish=numberOfSpawnFish+zip.get(i);
                    continue;
                }
                fishes.set(i, Integer.valueOf(--currentCount));
            }
            if(numberOfSpawnFish >0) {
                fishes.add(8);
                zip.add(numberOfSpawnFish.longValue());
//                System.out.println(fishes.size()+", "+ zip.size()+numberOfSpawnFish);
            }
//            System.out.println("After "+day+" day(s): "+fishes);
        }
        long lanternfish = 0L;
        for (int i = 0; i < zip.size(); i++) {
            lanternfish+=(long)zip.get(i);
        }

        return String.valueOf(lanternfish);
    }
}
