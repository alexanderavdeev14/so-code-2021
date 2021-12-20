package com.company.day13;

import com.company.Utils;

import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int day = 13;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
//        System.out.println(part1(test.txt));
        System.out.println(part1(input));
        System.out.println(part2(test));
        System.out.println(part2(input));
    }

    private static String part1(List<String> input){
        boolean[][] paper = readPaper(input);
        int[][] instructions = readInstructions(input);

        for (int[] instruction : instructions) {

                if(instruction[0]!=-1){
                    paper = foldAlongX(instruction[0], paper);
                } else {
                    paper = foldAlongY(instruction[1], paper);
                }
                break;
        }


        return String.valueOf(countAmountOfDots(paper));
    }


    private static String part2(List<String> input){
        boolean[][] paper = readPaper(input);
        int[][] instructions = readInstructions(input);

        for (int[] instruction : instructions) {

            if(instruction[0]!=-1){
                paper = foldAlongX(instruction[0], paper);
            } else {
                paper = foldAlongY(instruction[1], paper);
            }
        }

        for (boolean[] booleans : paper) {
            for (boolean aBoolean : booleans) {
                if(aBoolean) System.out.print("@");
                else System.out.print(".");
            }
            System.out.println();
        }


        return "";
    }

    private static boolean[][] readPaper(List<String> input){
        int maxY=0;
        int maxX=0;
        for (String s : input) {
            if(s.equals("")) break;
            String[] tmp = s.split(",");
            if(Integer.valueOf(tmp[0]) > maxX) maxX = Integer.valueOf(tmp[0]);
            if(Integer.valueOf(tmp[1]) > maxY) maxY = Integer.valueOf(tmp[1]);
            System.out.println(tmp[0]+","+tmp[1]);
        }
        maxX++;
        maxY++;
        boolean[][] paper = new boolean[maxY][maxX];
        for (int i = 0; i < maxX; i++) {
            for (int i1 = 0; i1 < maxY; i1++) {
                paper[i1][i] = Boolean.FALSE;
            }
        }
        for (String s : input) {
            if(s.equals("")) break;
            String[] tmp = s.split(",");
            paper[Integer.valueOf(tmp[1])][Integer.valueOf(tmp[0])] = Boolean.TRUE;
        }
        return paper;
    }

    private static int[][] readInstructions(List<String> input){
        int size=0;
        for (String s : input) {
            size++;
            if(s.equals("")) break;

        }
        int[][] instructions = new int[input.size()-size][2];
        for (int i = size; i < input.size(); i++) {
            String[] instruction = input.get(i).split(" ")[2].split("=");
            if(instruction[0].equals("x")){
                instructions[i-size][0] = Integer.valueOf(instruction[1]);
                instructions[i-size][1] = -1;
            } else
            {
                instructions[i-size][1] = Integer.valueOf(instruction[1]);
                instructions[i-size][0] = -1;
            }
        }
        return instructions;
    }

    private static int countAmountOfDots(boolean[][] paper) {
        int dots =0;
        for (boolean[] booleans : paper) {
            for (boolean aBoolean : booleans) {
                if(aBoolean) dots++;
            }
        }
        return dots;
    }

    private static boolean[][] foldAlongY(int y, boolean[][] paper) {
        for (int i = 0; i < y; i++) {
            for (int x = 0; x < paper[0].length; x++) {
                if( paper[y + i + 1][x]) {
                    paper[y - i - 1][x] = paper[y + i + 1][x];
                }
            }
        }
        boolean[][] newPaper = new boolean[y][paper[0].length];
        for (int i = 0; i < y; i++) {
            for (int i1 = 0; i1 < paper[0].length; i1++) {
                newPaper[i][i1] = paper[i][i1];
            }
        }
        return newPaper;
    }

    private static boolean[][] foldAlongX(int x, boolean[][] paper) {
        for (int i = 0; i < x; i++) {
            for (int y = 0; y < paper.length; y++) {
                if( paper[y][x + i + 1]) {
                    paper[y][x - i - 1] = paper[y][x + i + 1];
                }
            }
        }
        boolean[][] newPaper = new boolean[paper.length][x];
        for (int i = 0; i < x; i++) {
            for (int i1 = 0; i1 < paper.length; i1++) {
                newPaper[i1][i] = paper[i1][i];
            }
        }
        return newPaper;
    }

}
