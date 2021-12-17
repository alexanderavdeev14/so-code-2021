package com.company.day8;

import com.company.Utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        int day = 8;
        List<String> test = Utils.readLines("input/"+day+"/test");
        List<String> input = Utils.readLines("input/"+day+"/input");
//        System.out.println(part1(test,  new int[]{1,4,7,8}));
//        System.out.println(part1(input,  new int[]{1,4,7,8}));
        System.out.println(part2(test));
//        System.out.println(part2(input));
    }

    private static String part1(List<String> input, int[] numbers){
        // two sigmnets is l
        // 7 uses dab
        ArrayList<Integer> segments =new ArrayList<>();


        segments.add(2); //1
        segments.add(4);//4
        segments.add(3);//7
        segments.add(7);//8


        ArrayList<Integer> solArray = new ArrayList<>();
        int count = 0;
        for (var num:
            input) {
            String[] nums = num.split("\\|")[1].split(" ");

            for(var numm: nums){
                if(segments.contains(numm.length())) count++;
            }
        }


        return String.valueOf(count);
    }

    private static String part2(List<String> input){
        List<Character> possibleChars = new ArrayList<>();
        String possibleCharsString = "abcdefg";

        for (int i = 0; i <possibleCharsString.length() ; i++) {
            possibleChars.add(possibleCharsString.charAt(i));
        }

        List<String> allPossibleCombinations = generateAllPossibleCombinations(new ArrayList<String>(),possibleChars, "");
        long result = 0;
        for (var line:
             input) {
            String[] uniqueSignalPattern = line.split("\\|")[0].split(" ");
            String[] digitOutPutValue = line.split("\\|")[1].split(" ");
            List<String> mergedLists = Stream.concat(Arrays.stream(uniqueSignalPattern), Arrays.stream(digitOutPutValue)).collect(Collectors.toList());
            String[] mappingStrings = new String[]{"1110111", "0010010", "1011101", "1011011", "0111010",
                    "1101011", "1101111", "1010010", "1111111", "1111011"};

            char[] combinationArray = findPossibleCombination(allPossibleCombinations, uniqueSignalPattern, mappingStrings);

            for (char c : combinationArray) {
                System.out.print(String.valueOf(c));
            }
            System.out.println();



            StringBuilder resultString = new StringBuilder();
            for (var digit:
                    digitOutPutValue) {
                String digitBinary = "";
                for (int i = 0; i < 7; i++) {
                    if (digit.contains(String.valueOf(combinationArray[i]))) {
                        digitBinary += "1";
                    } else {
                        digitBinary += "0";
                    }
                }
                for (int i = 0; i < mappingStrings.length; i++) {
                    if(mappingStrings[i].equals(digitBinary)) resultString.append(i);
                }
            }
            result+=Long.parseLong(resultString.toString());
        }
        return String.valueOf(result);
    }

    private static char[] findPossibleCombination(List<String> allPossibleCombinations, String[] uniqueSignalPattern, String[] mappingStrings) {
        for (var possibleCombination:
                allPossibleCombinations) {
            char[] possibleCombinationArray = new char[7];
            List<String> mappingStringsCopy = Arrays.stream(mappingStrings).collect(Collectors.toList());

            for (int i = 0; i < possibleCombination.length(); i++) {
                possibleCombinationArray[i] = possibleCombination.charAt(i);
            }

            for (var digit:
                 uniqueSignalPattern) {
                String digitBinary = "";
                for (int i = 0; i < 7; i++) {
                    if (digit.contains(String.valueOf(possibleCombinationArray[i]))){
                        digitBinary+="1";
                    }else {
                        digitBinary+="0";
                    }
                }
                mappingStringsCopy.remove(digitBinary);
            }

            if(mappingStringsCopy.size() == 0) return possibleCombinationArray;




        }
        throw new RuntimeException("error");
    }

    private static List<String> generateAllPossibleCombinations(ArrayList<String> strings, List<Character> possibleChars, String string) {
        if (possibleChars.size() == 0) {
            strings.add(string);
            return strings;
        }

        for (var c : possibleChars) {
            List<Character> newPossibleChars = new ArrayList<>();
            for (var cc : possibleChars) {
                if (c.equals(cc)) continue;
                newPossibleChars.add(cc);
            }
            generateAllPossibleCombinations(strings, newPossibleChars, string + c);
        }
        return strings;
    }

}
