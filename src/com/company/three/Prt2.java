package com.company.three;

import com.company.Utils;

import java.util.ArrayList;
import java.util.List;

public class Prt2 {

    public static void main(String[] args) {

        List<String> input1 = Utils.readLines("input/3/2.txt");
        List<String> input2 = Utils.readLines("input/3/2.txt");

        int i=0;
        int one=0;
        int two =0;
        while(true){
            List<String> lssOne = new ArrayList<>();
            List<String> lssZero = new ArrayList<>();

            for (var s:
                    input1) {
                if(s.charAt(i) == '0') {
                    lssZero.add(s);
                } else
                {
                    lssOne.add(s);
                }
            }
            if(lssOne.size() >= lssZero.size()) {
                if(lssOne.size() == 1){
                    System.out.println(one =Integer.parseInt(lssOne.get(0),2));
                    break;
                }
                input1 = lssOne;
            } else
            {
                if(lssZero.size() == 1){
                    System.out.println(one = Integer.parseInt(lssZero.get(0),2));
                    break;
                }
                input1 = lssZero;
            }

            i++;

        }
        i=0;
        input1 = Utils.readLines("input/3/2.txt");
        input2 = Utils.readLines("input/3/2.txt");
        while(true){
            List<String> lssOne = new ArrayList<>();
            List<String> lssZero = new ArrayList<>();

            for (var s:
                    input1) {
                if(s.charAt(i) == '0') {
                    lssZero.add(s);
                } else
                {
                    lssOne.add(s);
                }
            }
            if(lssOne.size() < lssZero.size()) {
                if(lssOne.size() == 1){
                    System.out.println(two = Integer.parseInt(lssOne.get(0),2));
                    break;
                }
                input1 = lssOne;
            } else
            {
                if(lssZero.size() == 1){
                    System.out.println(two = Integer.parseInt(lssZero.get(0),2));
                    break;
                }
                input1 = lssZero;
            }

            i++;

        }

        System.out.println(one*two);

    }

}
