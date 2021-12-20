package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourPretty {

    public static void main(String[] args) {
        List<String> test = Utils.readLines("input.txt/4/" +
                "testInput");
        List<String> input =  Utils.readLines("input.txt/4/" +
                "puzzleInput");
        System.out.println(solution1(test));
        System.out.println(solution2(test));
        System.out.println(solution1(input));
        System.out.println(solution2(input));

    }

    private static int solution1(List<String> ls){
        String[] numbers = ls.get(0).strip().split(",");
        int numberOfBoards = (ls.size()-1)/6;
        String[][][] boards = new String[numberOfBoards][5][5];
        Boolean[][][] boardsMarking = new Boolean[numberOfBoards][5][5];

        for (int i = 0; i < numberOfBoards ; i++) {
            int begin = (i)*6+2;
            int end = begin+5;
            for (int j = begin, row = 0; j < end ; j++, row++) {
                Boolean[] boolInit = new Boolean[5];
                Arrays.fill(boolInit, false);
                boards[i][row] = ls.get(j).strip().split("\\s+");
                boardsMarking[i][row] = boolInit;
            }
        }

        for (String number : numbers) {
            for (int i = 0; i < numberOfBoards; i++) {
                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        if (boards[i][x][y].equals(number)) {
                            boardsMarking[i][x][y]=true;
                            if(checkIfWon(boardsMarking[i],x,y)) {
                                return returnSolutionPt1(boardsMarking[i], boards[i], Integer.parseInt(number));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static int solution2(List<String> ls){
        String[] numbers = ls.get(0).strip().split(",");
        int numberOfBoards = (ls.size()-1)/6;
        ArrayList<Integer> boardsStillPlaying = new ArrayList<>();
        for (int i = 0; i < numberOfBoards ; i++) {
            boardsStillPlaying.add(i);
        }
        String[][][] boards = new String[numberOfBoards][5][5];

        Boolean[][][] boardsMarking = new Boolean[numberOfBoards][5][5];

        for (int i = 0; i < numberOfBoards ; i++) {
            int begin = (i)*6+2;
            int end = begin+5;
            for (int j = begin, row = 0; j < end ; j++, row++) {
                Boolean[] boolInit = new Boolean[5];
                Arrays.fill(boolInit, false);
                boards[i][row] = ls.get(j).strip().split("\\s+");
                boardsMarking[i][row] = boolInit;
            }
        }

        for (String number : numbers) {
            for (int i = 0; i < numberOfBoards; i++) {
                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        if (boards[i][x][y].equals(number)) {
                            boardsMarking[i][x][y]=true;
                            if(checkIfWon(boardsMarking[i],x,y)) {
                                if(boardsStillPlaying.contains(i)) {
                                    boardsStillPlaying.remove(Integer.valueOf(i));
                                };
                                if( boardsStillPlaying.size() == 0) {
                                    return returnSolutionPt1(boardsMarking[i],
                                            boards[i],
                                            Integer.parseInt(number));
                                }

                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
    private static boolean checkIfWon(Boolean[][] board, int x, int y){
        for (int i = 0; i < 5 ; i++) {
            if(!board[x][i]) break;
            if(i==4) return true;
        }
        for (int i = 0; i < 5 ; i++) {
            if(!board[i][y]) break;
            if(i==4) return true;
        }
        return false;
    }

    private static int returnSolutionPt1(Boolean[][] boardMarkings, String[][] board, int last){
        int sum = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5 ; y++) {
                if(!boardMarkings[x][y]) {
                    sum+=Integer.parseInt(board[x][y]);
                }
            }
        }
        return sum*last;
    }
}
