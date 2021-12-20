package com.company;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static BufferedReader getReader(String path){
        Path p = Path.of(path);
        BufferedReader reader;
        try {
             reader = Files.newBufferedReader(p);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return reader;
    }

    public static List<String> readLines(String path){

        try(BufferedReader reader = getReader(path)) {
            String line;
            List<String> ls = new ArrayList<>();
            while((line = reader.readLine()) != null){
                ls.add(line);
            }
            return ls;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }


}
