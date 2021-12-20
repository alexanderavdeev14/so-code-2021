package com.company.day16;

import com.company.Utils;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int day = 16;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
        List<String> test2 = Utils.readLines("input.txt/"+day+"/test2");
//        System.out.println(part1(test.txt));
//        System.out.println(part1(input.txt));
        System.out.println(part2(test2));
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
        for (int i = 0; i < input.size(); i++) {
            System.out.println(parse(constructList(hexToBin(input.get(i)),new ArrayList<>())));
        }
        return String.valueOf("Done");
    }

    private static List<Object> constructList(String s, List<Object> list) {

        if(s.length() == 0 || s.matches("0+")) return list;
        long type = bin2Long(s.substring(3,6));
        list.add(type);
        s = s.substring(6);

        if(type == 4) {
            s = addLiteralToInstruction(s, list);
        } else {
            s = otherTypeBts(s, list);
        }
        return constructList(s,list);

    }

    private static List<Object> constructList(String s, List<Object> list, long numberOfIterations) {
        if(numberOfIterations == 0) {
            list.add(0, s);
            return list;
        }
        if(s.length() == 0 || s.matches("0+")) {
            list.add(0, s);
            return list;
        }
        long type = bin2Long(s.substring(3,6));
        list.add(type);
        s = s.substring(6);

        if(type == 4) {
            s = addLiteralToInstruction(s, list);
        } else {
            s = otherTypeBts(s, list);
        }
        return constructList(s,list,--numberOfIterations);

    }

    private static String otherTypeBts(String s,  List<Object> list) {
        if(s.charAt(0) == '0'){
            s = s.substring(1);
            long numberOfBits = bin2Long(s.substring(0, 15));
            s = s.substring(15);
            List<Object> subList = constructList(s.substring(0, (int) numberOfBits), new ArrayList<>());

//            list.add((long) subList.size());
            list.add(subList);
            s = s.substring((int)numberOfBits);
        } else {
            s = s.substring(1);
            long numberOfIterations = bin2Long(s.substring(0, 11));
            s = s.substring(11);
            List<Object> e = constructList(s, new ArrayList<>(), numberOfIterations);
            s = (String) e.remove(0);
//            list.add(numberOfIterations);
            list.add(e);
        }
        return s;
    }

    private static String addLiteralToInstruction(String s,  List<Object> list) {
        boolean lastValue;
        StringBuilder sb = new StringBuilder();
        do{
            lastValue = s.charAt(0) != '0';
            sb.append(s, 1, 5);
            s = s.substring(5);
        } while(lastValue);
        list.add(bin2Long(sb.toString()));
        return s;
    }

    private static long parse(List<Object> list){
        Long type = (Long) list.remove(0);
        if(type == 0){
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            return longs.stream().mapToLong(l->l).sum();
        } else if(type == 1) {
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            long product = 1;
            for (int i = 0; i < longs.size(); i++) {
                product*=longs.get(i);
            }
            return product;
        } else if(type ==2){
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            return longs.stream().mapToLong(l->l).min().getAsLong();
        } else if(type == 3){
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            return longs.stream().mapToLong(l->l).max().getAsLong();
        } else if(type==4){
            return (Long) list.get(0);
        } else if(type==5){
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            if(longs.get(0) > longs.get(1)) return 1;
            else return 0;
        } else if(type == 6){
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            if(longs.get(0) < longs.get(1)) return 1;
            else return 0;
        } else {
            List<Long> longs = new ArrayList<>();
            generateListOfLongs(list, longs);
            if(longs.get(0).equals(longs.get(1))) return 1;
            else return 0;
        }
    }

    private static void generateListOfLongs(List<Object> list, List<Long> longs) {
        list = (List<Object>) list.get(0);
        int size = list.size();
        for (int i = 0; i < size/2; i++) {
            List<Object> subLongList = new ArrayList<>();
            subLongList.add(list.remove(0));
            subLongList.add(list.remove(0));
            longs.add(parse(subLongList));
        }
    }

}

class Executor{

}

class Instructions{
    String instruction;
    List<Instructions> instructions;
    Long literal;

    public Instructions() {
        instructions = new ArrayList<>() ;
        literal = null;
    }

    public String getInstruction() {
        return instruction;
    }

    public List<Instructions> getInstructions() {
        return instructions;
    }

    public Long getLiteral() {
        return literal;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setInstructions(List<Instructions> instructions) {
        this.instructions = instructions;
    }

    public void setLiteral(Long literal) {
        this.literal = literal;
    }
}
