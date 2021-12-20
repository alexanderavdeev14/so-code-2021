package com.company.day15;

import com.company.Utils;

import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        int day = 15;
        List<String> test = Utils.readLines("input.txt/"+day+"/test.txt");
        List<String> input = Utils.readLines("input.txt/"+day+"/input.txt");
//        System.out.println(part1(test.txt));
//        System.out.println(part1(input.txt));
//        System.out.println(part2(test.txt));
        System.out.println(part2(input));
    }



    private static String part1(List<String> input) {

        int yMax = input.size();
        int xMax = input.get(0).length();
        long[][] weights = new long[yMax][xMax];

        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                weights[y][x] = Long.parseLong(input.get(y).substring(x,x+1));
            }
        }

        return dijkstra(yMax, xMax, weights);

    }

    private static String dijkstra(int yMax, int xMax, long[][] weights) {
        Queue<Vertex> binaryHeap = new PriorityQueue<>();
        int maxSize = xMax * yMax;
        for (int i = 0; i < maxSize; i++) {
            if(i==0) binaryHeap.add(new Vertex(i, 0L));
            else binaryHeap.add(new Vertex(i,Long.MAX_VALUE));
        }

        List<Long> vLength = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            if(i==0) vLength.add( 0L);
            else vLength.add(Long.MAX_VALUE);
        }

        List<Integer> visited = new ArrayList<>();

        while(visited.size() != maxSize) {
            Vertex currentVertex = binaryHeap.remove();
            int currentIndex = currentVertex.getIndex();
            visited.add(currentIndex);
            if(visited.size() % 50000 == 0)System.out.printf("%d/%d\n", visited.size(), vLength.size());
            int visitedX = currentIndex % xMax;
            int visitedY = currentIndex / xMax;
            long currentWeight = currentVertex.getVal();
            if((visitedX-1) >= 0) visitNode(weights, binaryHeap, visitedX-1, visitedY, currentWeight,visited, vLength);
            if((visitedX+1) < xMax) visitNode(weights, binaryHeap, visitedX+1, visitedY, currentWeight,visited,vLength);
            if((visitedY-1) >= 0) visitNode(weights, binaryHeap, visitedX, visitedY-1, currentWeight,visited,vLength);
            if((visitedY+1) < yMax) visitNode(weights, binaryHeap, visitedX, visitedY+1, currentWeight,visited,vLength);
        }


        return String.valueOf(vLength.get(vLength.size()-1));
    }


    private static void visitNode(long[][] weights, Queue<Vertex> binaryHeap, int toVisitX, int toVisitY, long currentWeight, List<Integer> visited, List<Long> vLength) {
        int xMax = weights[0].length;
        int indexOfVertex = toVisitX+toVisitY*xMax;
        if(visited.contains(indexOfVertex)) return;
        long weight = weights[toVisitY][toVisitX];
        long currentVWeight = vLength.get(indexOfVertex);
        long updatedWeight = weight + currentWeight;
        if(updatedWeight < currentVWeight ) {
            Vertex v = new Vertex(indexOfVertex, updatedWeight);
            binaryHeap.add(v);
            vLength.set(indexOfVertex, updatedWeight);
        }


    }


    private static String part2(List<String> input){
        int yMax = input.size()*5;
        int xMax = input.get(0).length()*5;
        long[][] weights = new long[yMax][xMax];


        for (int x = 0; x < (xMax / 5); x++) {
            for (int y = 0; y < (yMax / 5); y++) {
                weights[y][x] = Long.parseLong(input.get(y).substring(x,x+1));
            }
        }

        for (int x = 0; x < (xMax/5); x++) {
            for (int y = (yMax/5); y < yMax; y++) {
                int previousY = y-(yMax/5);
                long weight = weights[previousY][x]+1;
                if(weight == 10) weight =1;
                weights[y][x] = weight;
            }
        }

        for (int x = (xMax/5); x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                int previousX = x-(xMax/5);
                long weight = weights[y][previousX]+1;
                if(weight == 10) weight =1;
                weights[y][x] = weight;
            }
        }


        return dijkstra(yMax, xMax, weights);
    }
}

class Vertex implements Comparable<Vertex>{
    int index;
    long val;

    public Vertex(int index, long val) {
        this.index = index;
        this.val = val;
    }

    public int getIndex() {
        return index;
    }

    public long getVal() {
        return val;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setVal(long val) {
        this.val = val;
    }

    @Override
    public int compareTo(Vertex o) {
        return o.getVal() < this.getVal() ? 1: -1;
    }
}
