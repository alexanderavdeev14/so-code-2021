package com.company.day5;

import com.company.Utils;

import java.util.Arrays;
import java.util.List;

public class Solution {

    static int  gridSize = 2000;
    public static void main(String[] args) {
        int day = 256;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
        System.out.println(part1(test));
//        System.out.println(part1(input.txt));
//        //System.out.println(part2(test.txt));
//        //System.out.println(part2(input.txt));
    }

    private static int part1(List<String> input){
        int[][] grid = new int[gridSize][gridSize];
        for (int i = 0; i <gridSize ; i++) {
            for (int j = 0; j <gridSize ; j++) {
                grid[i][j] = 0;
            }
        }

        for (var line:
             input) {
            grid = drawLine(grid,line);
//            for (int i = 0; i <gridSize ; i++) {
//                for (int j = 0; j <gridSize ; j++) {
//                    //System.out.print(grid[i][j]);
//                }
//                //System.out.println();
//            }
//            //System.out.println();
        }

//        for (int i = 0; i <gridSize ; i++) {
//            for (int j = 0; j <gridSize ; j++) {
//                if(grid[i][j]!=0)//System.out.print(grid[i][j]);
//                else //System.out.print(".");
//
//            }
//            //System.out.println();

//        }


        return solPart1(grid);
    }

    private static String part2(List<String> input){
        return "";
    }

    private static int[][] drawLine(int[][] grid, String  cords){
        //System.out.println("--------------\n"+cords);
        String[] coords = cords.strip().split("->");
        String[] x =new String[2];
        x[0] = coords[0].strip().split(",")[0];
        x[1] = coords[1].strip().split(",")[0];
        String[] y =new String[2];
        y[0] = coords[0].strip().split(",")[1];
        y[1] = coords[1].strip().split(",")[1];

        if(x[0].equals(x[1]))  x = new String[]{x[0]};
        if(y[0].equals(y[1]))  y = new String[]{y[0]};
        int[] xi = new int[x.length];
        for (int i = 0; i < x.length ; i++) {
            xi[i]=Integer.parseInt(x[i]);
        }
        int[] yi = new int[y.length];
        for (int i = 0; i < y.length ; i++) {
            yi[i]=Integer.parseInt(y[i]);
        }





//        Arrays.sort(xi);
//        Arrays.sort(yi);
//        for (var xx:
//                xi) {
//            //System.out.print(xx);
//            //System.out.print(",");
//        }
//        //System.out.println();
//        for (var yy:
//                yi) {
//            //System.out.print(yy);
//            //System.out.print(",");
//        }
//        //System.out.println();


//        if(xi.length == 1 && yi.length == 1) {
//            grid[xi[0]][yi[0]]++;
//        } else
        if(xi.length ==1 && yi.length > 1){
            Arrays.sort(yi);
            for (int yy = yi[0]; yy <=yi[1] ; yy++) {
                //System.out.println(cords+" :("+xi[0]+","+yy+ ")");
                grid[yy][xi[0]]++;
            }
        } else if(xi.length >1  && yi.length ==1){
            Arrays.sort(xi);
            for (int xx =xi[0]; xx <=xi[1] ; xx++) {
                //System.out.println(cords+" :("+xx+","+yi[0]+ ")");
                grid[yi[0]][xx]++;
            }
        } else {
            int xx=xi[0];
            int yy=yi[0];
            //System.out.println(cords+" :("+xx+","+yy+ ")");
            grid[yy][xx]++;
           while(xx != xi[1]){
               if(xi[0] > xi[1])xx--;
               else xx++;
               if(yi[0] > yi[1])yy--;
               else yy++;
               //System.out.println(cords+" :("+xx+","+yy+ ")");
               grid[yy][xx]++;
           }
        }
        //System.out.println("xxxxxxx");
        return grid;
    }



    private static int solPart1(int grid[][]){
        int count=0;
        for (int i = 0; i <gridSize ; i++) {
            for (int j = 0; j <gridSize ; j++) {
                if(grid[i][j] >= 2) count++;
            }
        }
        return count;
    }
}
