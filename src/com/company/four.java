package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class four {
    public static void main(String[] args) {
//        System.out.println(prt1(Utils.readLines("input/4/test/1.txt")));
        System.out.println(prt2(Utils.readLines("input/4/test/1.txt")));
    }

    private static String prt1(List<String> input){

        String[] lsinput = new String[input.size()];
        String[] lsRow = new String[input.size()];
        String[] numbers = input.get(0).split(",");
        List<String> markedNumbers = new ArrayList<>();

        System.out.println();

        int last=0;
        int sum=0;
        int winningBoard = 0;
        int n = 3;
        String[] ls=null;
        Boolean brk = false;

        outer: for (int j = 0; j < numbers.length ; j++) {
            for (int i = 2; i < input.size() ; i++) {
                int currentBoard = (i - 2)/6;

                if (input.get(i).length()==0) {
                    continue;
                }
                String sLs = input.get(i);
                if(sLs.charAt(0)==' ') sLs = sLs.substring(1);
                ls = sLs.split("\\s+");

                int colmn=0;
                for (var s:
                     ls) {
                    int row = 6*currentBoard+colmn;
                    if (s.equals(numbers[j])) {
                        if(!markedNumbers.contains(s)) markedNumbers.add(s);

                        if (lsinput[i] == null && lsRow[row] != null) {
                            lsinput[i] = s;
                            lsRow[row] = lsRow[row] + "," + s;
                        } else if (lsinput[i] != null && lsRow[row] == null) {
                            lsinput[i] = lsinput[i] + "," + s;
                            lsRow[row] = s;
                        } else if (lsinput[i] != null && lsRow[row] != null) {
                            lsinput[i] = lsinput[i] + "," + s;
                            lsRow[row] = lsRow[row] + "," + s;
                        } else  {
                            lsinput[i] = s;
                            lsRow[row] = s;
                        }
                    }

                    if ((lsinput[i] != null && lsinput[i].split(",").length ==5)  || (lsRow[row] != null && lsRow[row].split(",").length ==5) ) {
                        winningBoard = (i-2)/6;

                        System.out.println(i+","+winningBoard+","+s);
                        last = Integer.valueOf(s);
                        for(var ss: lsinput) {
                            if(ss != null && ss.split(",").length ==5)System.out.println(ss);
                        }
                        for(var ss: lsRow) {
                            if(ss != null &&  ss.split(",").length ==5)System.out.println(ss);
                        }
                        break outer;
                    }
                    colmn++;
                }
            }
        }

        for (int i = 2+6*winningBoard; i <  2+6*winningBoard+5; i++) {

            String sLs = input.get(i);
            if (sLs.charAt(0) == ' ') sLs = sLs.substring(1);
            ls = sLs.split("\\s+");

            for (var ss : ls) {
                if (!markedNumbers.contains(ss)) {
                    sum += Integer.valueOf(ss);
//                    System.out.println("Non-marked: " + ss);
                }

            }
        }

        System.out.println(last+","+sum);
        return String.valueOf(last*sum);
    }

    private static String prt2(List<String> input){

        String[] lsinput = new String[input.size()];
        String[] lsRow = new String[input.size()];
        String[] numbers = input.get(0).split(",");
        List<String> markedNumbers = new ArrayList<>();

        System.out.println();

        int last=0;
        int sum=0;
        int n = (input.size() - 2)/6;
        int boardsStillNotWinned = n;
        int winningBoard = 0;
        List<Integer> boardsThatWinned = new ArrayList<>();
        String[] ls=null;
        Boolean brk = false;

        outer: for (int j = 0; j < numbers.length ; j++) {
            for (int i = 2; i < input.size() ; i++) {
                int currentBoard = (i - 2)/6;

                if (input.get(i).length()==0) {
                    continue;
                }
                String sLs = input.get(i);
                if(sLs.charAt(0)==' ') sLs = sLs.substring(1);
                ls = sLs.split("\\s+");

                int colmn=0;
                for (var s:
                        ls) {
                    int row = 6*currentBoard+colmn;
                    if (s.equals(numbers[j])) {
                        if(!markedNumbers.contains(s)) markedNumbers.add(s);

                        if (lsinput[i] == null && lsRow[row] != null) {
                            lsinput[i] = s;
                            lsRow[row] = lsRow[row] + "," + s;
                        } else if (lsinput[i] != null && lsRow[row] == null) {
                            lsinput[i] = lsinput[i] + "," + s;
                            lsRow[row] = s;
                        } else if (lsinput[i] != null && lsRow[row] != null) {
                            lsinput[i] = lsinput[i] + "," + s;
                            lsRow[row] = lsRow[row] + "," + s;
                        } else  {
                            lsinput[i] = s;
                            lsRow[row] = s;
                        }
                    }

                    if ((lsinput[i] != null && lsinput[i].split(",").length ==5)  || (lsRow[row] != null && lsRow[row].split(",").length ==5) ) {
                        if(boardsThatWinned.contains(currentBoard)) continue;
                        winningBoard = (i-2)/6;
                        boardsThatWinned.add(winningBoard);
                        System.out.println(i+","+winningBoard+","+"("+boardsThatWinned.toString()+")");
                        last = Integer.valueOf(s);
                        if(boardsThatWinned.size() == (n+1)) break outer;
                    }
                    colmn++;
                }
            }
        }

        for (int i = 2+6*winningBoard; i <  2+6*winningBoard+5; i++) {

            String sLs = input.get(i);
            if (sLs.charAt(0) == ' ') sLs = sLs.substring(1);
            ls = sLs.split("\\s+");

            for (var ss : ls) {
                if (!markedNumbers.contains(ss)) {
                    sum += Integer.valueOf(ss);
//                    System.out.println("Non-marked: " + ss);
                }

            }
        }

        System.out.println(last+","+sum);
        return String.valueOf(last*sum);
    }
}
