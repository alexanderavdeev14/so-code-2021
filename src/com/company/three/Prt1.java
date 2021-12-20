package com.company.three;

import com.company.Utils;

import java.util.List;

public class Prt1 {
    public static void main(String[] args) {
        List<String> input = Utils.readLines("input.txt/3/1.txt");
        List<String> inputTest = Utils.readLines("input.txt/3/test.txt/1.txt");
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < input.get(0).length() ; i++) {
            int one=0;
            int zero=0;
            int oneE=0;
            int zeroE=0;
            for (var s:
                    input) {
                if(s.charAt(i) == '0') {
                    zero++;
                    oneE++;
                }
                if(s.charAt(i) == '1') {
                    one++;
                    zeroE++;
                }
            }
            if(zero>one) stringBuilder.append('0');
            else stringBuilder.append('1');
            if(zeroE>oneE) stringBuilder2.append('0');
            else stringBuilder2.append('1');

        }
        System.out.println(stringBuilder.toString());
        System.out.println(stringBuilder2.toString());

        System.out.println(3808*287);



        }


}
