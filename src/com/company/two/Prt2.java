package com.company.two;

import com.company.Utils;

import java.util.List;

public class Prt2 {

    public static void main(String[] args) {
        List<String> input = Utils.readLines("input.txt/2/2.txt");
        List<String> inputTest = Utils.readLines("input.txt/2/test.txt/2.txt");

        int horizontal=0;
        int aim=0;
        int depth=0;
        for (var s:
                input) {
            String[] com = s.split(" ");
            switch (com[0]){
                case "up":
                    aim -= Integer.valueOf(com[1]);
                    break;
                case "down":
                    aim += Integer.valueOf(com[1]);
                    break;
                case "forward":
                    horizontal += Integer.valueOf(com[1]);
                    depth=depth+aim*Integer.valueOf(com[1]);
                    break;
            }

        }
        System.out.println(horizontal*depth);
    }

}
