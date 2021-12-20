package com.company.day17;

import com.company.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        int day = 17;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");

        part1(test);
        part1(input);
        part2(test);
        part2(input);
    }

    private static void part1(List<String> input){
        int[][] area = new int[2][2];

        for (int i = 0; i < input.size(); i++) {
            String[] coordinates = input.get(i).split(",");
            for (int j = 0; j < coordinates.length; j++) {
                area[i][j] = Integer.parseInt(coordinates[j]);
            }
        }

        int yMax = area[1][1];
        int yMin = area[1][0];


        long initialY = 0;
        long maxHeight = Long.MIN_VALUE;
        while(initialY < 1000){
            long yMaxHeight = triangleIdentity(initialY);

            if(isWithinBound(yMaxHeight,yMin, yMax)) {
                if(maxHeight < yMaxHeight) maxHeight = yMaxHeight;
            }

            initialY++;
        }
        System.out.println(maxHeight);
    }


    private static long triangleIdentity(long num){
        return num*(num+1)/2;

    }

    private static boolean isWithinBound(long yMaxHeight, int yMin, int yMax){

        long currentHeight = yMaxHeight;
        for (int i = 1; currentHeight >= yMin ; i++) {
            currentHeight-=i;
            if(currentHeight >= yMin && currentHeight <= yMax ) return true;
        }
        return false;
    }

    private static void part2(List<String> input){

        int[][] area = new int[2][2];

        for (int i = 0; i < input.size(); i++) {
            String[] coordinates = input.get(i).split(",");
            for (int j = 0; j < coordinates.length; j++) {
                area[i][j] = Integer.parseInt(coordinates[j]);
            }
        }
        int xMin = area[0][0];
        int xMax = area[0][1];
        int yMax = area[1][1];
        int yMin = area[1][0];

        int tries = 1000;
        int numberOfSolutions = 0;
        for (int vx = 0; vx < tries ; vx++) {
            for (int vy = -tries; vy <tries ; vy++) {
                if(isWithinBoundNew(vx,vy, xMin,xMax,yMin,yMax)) numberOfSolutions++;
            }
        }
        System.out.println(numberOfSolutions);
    }

    private static boolean isWithinBoundNew(int vx, int vy, int xMin, int xMax, int yMin, int yMax) {
        int currentX =0;
        int currentY =0;
        for (int i = 0; i<1000; i++) {
            currentY+=vy;
            currentX+=vx;
            if(currentX>=xMin && currentX <= xMax && currentY<=yMax && currentY>=yMin) return true;
            if(currentX > xMax || currentY < yMin) return false;
            if(vx!=0) vx--;
            vy--;
        }
        return false;

    }


}
