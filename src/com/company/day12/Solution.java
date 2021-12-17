package com.company.day12;

import com.company.Utils;

import java.util.*;
import java.util.function.Predicate;

public class Solution {
    public static void main(String[] args) {
        int day = 12;
        List<String> test = Utils.readLines("input/"+day+"/test");
        List<String> input = Utils.readLines("input/"+day+"/input");
        System.out.println(part1(test));
//        System.out.println(part1(input));
//        System.out.println(part2(test));
//        System.out.println(part2(input));
    }

    private static String part1(List<String> input){
    //small caves at most once
        List<PathNode> unlinkedNodes = new ArrayList<>();




        for (String s : input) {
            String[] connections = s.split("-");
            PathNode originNode = checkIfCreated(connections[0],unlinkedNodes);
            PathNode destinationNode = checkIfCreated(connections[1], unlinkedNodes);
            if(originNode == null && destinationNode == null) {
                PathNode tmp = new PathNode();
                tmp.setName(connections[0]);
                PathNode tmp2 = new PathNode();
                tmp2.setName(connections[1]);
                unlinkedNodes.add(tmp);
                unlinkedNodes.add(tmp2);
                tmp.getNodes().add(tmp2);
                tmp2.getNodes().add(tmp);


            } else if(originNode == null){
                PathNode tmp = new PathNode();
                tmp.setName(connections[0]);
                unlinkedNodes.add(tmp);
                tmp.getNodes().add(destinationNode);
                destinationNode.getNodes().add(tmp);
            } else if (destinationNode == null){

                PathNode tmp2 = new PathNode();
                tmp2.setName(connections[1]);
                unlinkedNodes.add(tmp2);
                originNode.getNodes().add(tmp2);
                tmp2.getNodes().add(originNode);
            } else {
                if(!originNode.getNodes().contains(destinationNode)) originNode.getNodes().add(destinationNode);
                if(!destinationNode.getNodes().contains(originNode)) destinationNode.getNodes().add(originNode);
            }

         }
        PathNode startingNode = checkIfCreated("start", unlinkedNodes);
        List<String> paths = new ArrayList<>();
        assert startingNode != null;
        startingNode.walkAndPrint(paths);
        return String.valueOf(paths.size());
    }

    private static PathNode checkIfCreated(String nodeName, List<PathNode> nodes){
        for (PathNode node : nodes) {
            if(node.getName().equals(nodeName)) return node;
        }
        return null;
    }

    private static String part2(List<String> input){

        return "";
    }

}

class PathNode {
    public String getName() {
        return name;
    }

    public List<PathNode> getNodes() {
        return nodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodes(List<PathNode> nodes) {
        this.nodes = nodes;
    }

    String name;
    List<PathNode> nodes;
    public PathNode(){
        name = "";
        nodes = new ArrayList<>();
    }




    public void walkAndPrint(List<String> paths){
        walkAndPrint(this, "", paths, "", false);
    }

    public void walkAndPrint(PathNode currentNode, String path, List<String> paths, String vistitedCaves, boolean visitedTwiceBool){
        if(visitedTwiceBool && vistitedCaves.contains(currentNode.getName())) return;
        if(appearesTwice(vistitedCaves,"start")) return;
        if(appearesTwice(vistitedCaves+(currentNode.getName() + ","),currentNode.getName())){
            visitedTwiceBool=true;
        }


        if (currentNode.getName().charAt(0) > 96) vistitedCaves += (currentNode.getName() + ",");


        System.out.println(vistitedCaves+":"+visitedTwiceBool);
        path+=(currentNode.getName()+"-");

        if(currentNode.getName().equals("end")){
            System.out.println(path+":"+visitedTwiceBool);
            paths.add(path);
            return;
        }

        for (PathNode node : currentNode.nodes) {
            walkAndPrint(node, path, paths, vistitedCaves, visitedTwiceBool);
        }
    }
    public boolean appearesTwice(String str1, String str2){
        int count = 0;
        String[] array = str1.split(",");
        for (String s : array) {
            if (s.equals(str2)) count++;
            if(count>1) return true;
        }
        return false;
    }
}
