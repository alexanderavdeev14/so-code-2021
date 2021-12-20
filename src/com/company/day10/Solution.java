package com.company.day10;

import com.company.Utils;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int day = 10;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
        System.out.println(part1(test));
//        System.out.println(part1(input.txt));
        System.out.println(part2(test));
        System.out.println(part2(input));
    }

    private static String part1(List<String> input){
        Stack<String> brackets = new Stack<>();
        Map<String, Integer> scores = new HashMap<>();

        scores.put(")", 3);
        scores.put("]", 57);
        scores.put("}", 1197);
        scores.put(">", 25137);
        long result = 0;
        for (var line:
             input) {
            long tmp = 0;
            for (int i = 0; i < line.length(); i++) {
                String bracket = line.substring(i, i+1);
                if("([{<".contains(bracket)){
                    brackets.push(bracket);
                } else {
                    if(brackets.isEmpty()) {
                        tmp =0;
                        break;
                    } else {
                        String bracketRev = brackets.pop();

                        if (bracket.equals(")") && "(".equals(bracketRev)) {
                        }
                        else if(bracket.equals("]") && "[".equals(bracketRev)){
                        }
                        else if(bracket.equals("}") && "{".equals(bracketRev)){
                        }
                        else if(bracket.equals(">") && "<".equals(bracketRev)){
                        }
                        else {
                            tmp += scores.get(bracket);
                        }
                    }
                }
            }
            result+=tmp;
        }

        return String.valueOf(result);
    }

    private static String part2(List<String> input){
        Map<String, Integer> scores = new HashMap<>();
        List<Long> numbers = new ArrayList<>();
        scores.put("(", 1);
        scores.put("[", 2);
        scores.put("{", 3);
        scores.put("<", 4);
        long result = 0;
        for (var line:
                input) {
            long tmp = 0;
            Stack<String> brackets = new Stack<>();
            tmp = getResult(brackets, scores, tmp, line);
            if(tmp != -1){
                tmp =0;
                while(!brackets.isEmpty()){
                    tmp = tmp*5+scores.get(brackets.pop());
                }
                numbers.add(tmp);
            }
        }
        Collections.sort(numbers);
        System.out.println(numbers);
        int size= (numbers.size()-1)/2;
        result = numbers.get(size);
        return String.valueOf(result);
    }

    private static long getResult(Stack<String> brackets, Map<String, Integer> scores, long result, String line) {
        if(line.length() == 0) return result;

        String bracket = line.substring(0, 1);
        if ("([{<".contains(bracket)) {
            brackets.push(bracket);
            return getResult(brackets,scores,result,line.substring(1));

        } else {
            if (brackets.isEmpty()) {
                return -1;
            } else {
                String bracketRev = brackets.pop();

                if (bracket.equals(")") && "(".equals(bracketRev)) {
                    return getResult(brackets,scores,result,line.substring(1));
                } else if (bracket.equals("]") && "[".equals(bracketRev)) {
                    return getResult(brackets,scores,result,line.substring(1));
                } else if (bracket.equals("}") && "{".equals(bracketRev)) {
                    return getResult(brackets,scores,result,line.substring(1));
                } else if (bracket.equals(">") && "<".equals(bracketRev)) {
                    return getResult(brackets,scores,result,line.substring(1));
                } else {
                    return -1;

                }
            }
        }
    }
}
