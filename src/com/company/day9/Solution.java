package com.company.day9;

import com.company.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        int day = 9;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
//        System.out.println(part1(test.txt));
//        System.out.println(part1(input.txt));
//        System.out.println(part2(test.txt));
        System.out.println(part2(input));
    }

    private static String part1(List<String> input){
        List<Integer> riskLevel = new ArrayList<>();

        int xMax = 100;
        int yMAx = 100;
        int map[][] = new int[xMax][yMAx];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                map[i][j] = Integer.parseInt(input.get(i).substring(j,j+1));
            }
        }

        findRiskLevels(riskLevel, xMax, yMAx, map);
        long result=0;
        for (var kk:
             riskLevel) {
            result += kk;
        }

        String safo = ""+"";
        return String.valueOf(result);
    }

    private static void findRiskLevels(List<Integer> riskLevel, int xMax, int yMAx, int[][] map) {
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMAx; j++) {
                ArrayList<Integer> minArray = new ArrayList<>();

                if(i == 0 && j == 0){
                    minArray.add(map[i+1][j]);
                    minArray.add(map[i][j+1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }

                if(i == 0 && j == yMAx -1){
                    minArray.add(map[i+1][j]);
                    minArray.add(map[i][j-1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }

                if(i == xMax -1 && j ==0 ){
                    minArray.add(map[i-1][j]);
                    minArray.add(map[i][j+1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;

                }
                if(i == xMax -1 & j == yMAx -1){
                    minArray.add(map[i-1][j]);
                    minArray.add(map[i][j-1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }
                if(j==0 && i>0){
                    minArray.add(map[i+1][j]);
                    minArray.add(map[i-1][j]);
                    minArray.add(map[i][j+1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }

                if(j== yMAx -1 && i>0){
                    minArray.add(map[i+1][j]);
                    minArray.add(map[i-1][j]);
                    minArray.add(map[i][j-1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }
                if(i == 0 &&  j>0 && j< yMAx -1){
                    minArray.add(map[i+1][j]);
                    minArray.add(map[i][j-1]);
                    minArray.add(map[i][j+1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }

                if(i== xMax -1 && j>0 && j< yMAx -1){
                    minArray.add(map[i-1][j]);
                    minArray.add(map[i][j+1]);
                    minArray.add(map[i][j-1]);
                    calcRiskLevel(riskLevel, map, i, j, minArray);
                    continue;
                }



                minArray.add(map[i+1][j]);
                minArray.add(map[i-1][j]);
                minArray.add(map[i][j+1]);
                minArray.add(map[i][j-1]);
                calcRiskLevel(riskLevel, map, i, j, minArray);


            }


        }
    }

    private static void findLowLevelPoints(boolean[][] mapLowLevels, int xMax, int yMAx, int[][] map) {
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMAx; j++) {
                ArrayList<Integer> minArray = new ArrayList<>();

                if (i == 0 && j == 0) {
                    minArray.add(map[i + 1][j]);
                    minArray.add(map[i][j + 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }

                if (i == 0 && j == yMAx - 1) {
                    minArray.add(map[i + 1][j]);
                    minArray.add(map[i][j - 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }

                if (i == xMax - 1 && j == 0) {
                    minArray.add(map[i - 1][j]);
                    minArray.add(map[i][j + 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;

                }
                if (i == xMax - 1 & j == yMAx - 1) {
                    minArray.add(map[i - 1][j]);
                    minArray.add(map[i][j - 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }
                if (j == 0 && i > 0) {
                    minArray.add(map[i + 1][j]);
                    minArray.add(map[i - 1][j]);
                    minArray.add(map[i][j + 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }

                if (j == yMAx - 1 && i > 0) {
                    minArray.add(map[i + 1][j]);
                    minArray.add(map[i - 1][j]);
                    minArray.add(map[i][j - 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }
                if (i == 0 && j > 0 && j < yMAx - 1) {
                    minArray.add(map[i + 1][j]);
                    minArray.add(map[i][j - 1]);
                    minArray.add(map[i][j + 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }

                if (i == xMax - 1 && j > 0 && j < yMAx - 1) {
                    minArray.add(map[i - 1][j]);
                    minArray.add(map[i][j + 1]);
                    minArray.add(map[i][j - 1]);
                    insertLowPoints(mapLowLevels, map, i, j, minArray);
                    continue;
                }


                minArray.add(map[i + 1][j]);
                minArray.add(map[i - 1][j]);
                minArray.add(map[i][j + 1]);
                minArray.add(map[i][j - 1]);
                insertLowPoints(mapLowLevels, map, i, j, minArray);


            }
        }
    }

    private static void calcRiskLevel(List<Integer> riskLevel, int[][] map, int i, int j, ArrayList<Integer> minArray) {
        int min = Integer.MAX_VALUE;
        for (var k :
                minArray) {
            if (k < min) min = k;

        }

        if (min > map[i][j]) riskLevel.add(map[i][j] + 1);
    }

    private static void insertLowPoints(boolean[][] lowLevels, int[][] map, int i, int j, ArrayList<Integer> minArray) {
        int min = Integer.MAX_VALUE;
        for (var k :
                minArray) {
            if (k < min) min = k;

        }
        if (min > map[i][j]) lowLevels[i][j] = Boolean.TRUE;
        else lowLevels[i][j] = Boolean.FALSE;
    }

    private static String part2(List<String> input){



        int xMax = 100;
        int yMAx = 100;
        boolean mapLowLevels[][] = new boolean[xMax][yMAx];
        int map[][] = new int[xMax][yMAx];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                map[i][j] = Integer.parseInt(input.get(i).substring(j,j+1));
            }
        }
        findLowLevelPoints(mapLowLevels,xMax,yMAx,map);

        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMAx; j++) {
                if(mapLowLevels[i][j]) System.out.print(1);
                else System.out.print(0);
            }
            System.out.println();
        }
        ArrayList<Long> bassinSizes = new ArrayList<>();
        for (int i = 0; i <xMax ; i++) {
            for (int j = 0; j <yMAx ; j++) {
                if(mapLowLevels[i][j]) {
                    bassinSizes.add(findBasinSizeRecrusive(map, i, j, Integer.MIN_VALUE, 0, new HashMap<>()));
                    System.out.println("--------");
                }
            }
        }
        bassinSizes = (ArrayList<Long>) bassinSizes.stream().sorted().collect(Collectors.toList());
        System.out.println(bassinSizes);
        long result = 1;
        for (int i = bassinSizes.size()-1; i > bassinSizes.size()-1-3 ; i--) {
            result*=bassinSizes.get(i);
        }


        return String.valueOf(result);
    }

    private static Long findBasinSizeRecrusive(int[][] map, int i, int j, int priveous, int basinSize, Map<String, Boolean> hashMap) {
        try {
            if(hashMap.containsKey(i+","+j)) return (long) basinSize;
            if (map[i][j] == 9) return (long) basinSize;
            if(map[i][j] < priveous ) return (long) basinSize;
        } catch (Exception e){
            return  (long)basinSize;
        }
        hashMap.put(i+","+j, Boolean.TRUE);
        System.out.println(i+","+j);


        return findBasinSizeRecrusive(map, i+1, j, map[i][j], 0,hashMap) +
                findBasinSizeRecrusive(map, i-1, j, map[i][j], 0,hashMap) +
                findBasinSizeRecrusive(map, i, j-1, map[i][j], 0,hashMap) +
                findBasinSizeRecrusive(map, i, j+1, map[i][j],0,hashMap)+1+basinSize;
    }

}
