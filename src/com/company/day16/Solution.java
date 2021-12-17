package com.company.day16;

import com.company.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int day = 16;
        List<String> test = Utils.readLines("input/"+day+"/test");
        List<String> input = Utils.readLines("input/"+day+"/input");
        System.out.println(part1(test));
        System.out.println(part1(input));
        System.out.println(part2(test));
        System.out.println(part2(input));
    }

    private static String part1(List<String> input){
        //Binary MSB

        // 3 bits version
        // 3 bit type ID
        ArrayList<Long> resultList = new ArrayList<>();
        for (String s : input) { resultList.add(sumVersions(0L, hexToBin(s)));
        }
        return resultList.toString();
    }

    private static long sumVersions(long sum, String s) {
        if(s.length() == 0 || s.matches("0+")) return sum;
        long version = bin2Long(s.substring(0,3));
        sum+= version;
        long type = bin2Long(s.substring(3,6));
        s = s.substring(6);
        if(type == 4) {
            s = addLiteralToInstruction(s);
        } else {
            s = otherTypeBts(s);
        }
        return sumVersions(sum,s);

    }

    private static String otherTypeBts(String s) {
        int numberOfBits;
        if(s.charAt(0) == '0'){
            s = s.substring(1);
            numberOfBits = (int) bin2Long(s.substring(0, 15));
            s = s.substring(15+numberOfBits);
        } else {
            s = s.substring(1);

            numberOfBits = (int) bin2Long(s.substring(0, 11));
            s = s.substring(11+numberOfBits);
        }
        return s;
    }

    private static String addLiteralToInstruction(String s) {
        boolean lastValue;
        do{
            lastValue = s.charAt(0) != '0';
            s = s.substring(5);
        } while(lastValue);
        return s;
    }


    private static String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    private static long bin2Long(String bin){
        return Long.valueOf(bin, 2);
    }



    private static String part2(List<String> input){
        return null;
    }

    private void constructList(String s, Instructions instructions){
        if(s.length() == 0 || s.matches("0+")) return;
        String type = s.substring(3,6);
        instructions.setInstruction(type);
        s = s.substring(6);
        if(type == 4) {
            s = addLiteralToInstruction(s, instructions);
        } else {
            s = otherTypeBts(s);
        }
        return sumVersions(s, instructions);
    }

    private String addLiteralToInstruction(String s, Instructions instructions) {
        boolean lastValue;
        do{
            lastValue = s.charAt(0) != '0';
            String value =
            s = s.substring(5);
        } while(lastValue);
        return s;
    }
}

class Executor{
    String instructions;


    public long execute(long result){
        if(instructions.length() == 0 || instructions.matches("0+")) return result;
        long version = bin2Long(instructions.substring(0,3));
        int operation = (int)bin2Long(instructions.substring(3,6));
        instructions = instructions.substring(6);
        LinkedList<String>

        switch (operation){
            case 0:  //sum
                char typeLengthId = instructions.charAt(0);
                int sum;
                    if(typeLengthId == '0'){
                        caculate
                    } else {}

                break;
            case 1: break; //product
            case 2: break; //minimum
            case 3: break; //maximum
            case 4:
                return calculateLiteral();
                break; // literal

            case 5: break; //greater than
            case 6: break; //less than
            case 7: break; //equal
        }

    }

    private long calculateLiteral() {
        boolean lastValue;
        StringBuilder value  = new StringBuilder();
        do{
            lastValue = instructions.charAt(0) != '0';
            value.append(instructions.substring(1,5));
            instructions = instructions.substring(5);
        } while(lastValue);
        return bin2Long(value.toString());


    }

    private static long bin2Long(String bin){
        return Long.valueOf(bin, 2);
    }


}

class Instructions{
    String instruction;
    List<Instructions> instructions;

    public Instructions(List<Instructions> instructions) {
        this.instructions = instructions;
    }

    public String getInstruction() {
        return instruction;
    }

    public List<Instructions> getInstructions() {
        return instructions;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setInstructions(List<Instructions> instructions) {
        this.instructions = instructions;
    }
}
