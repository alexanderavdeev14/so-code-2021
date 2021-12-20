//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company.day18;

import com.company.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        int day = 18;
        List<String> test = Utils.readLines("input/" + day + "/test.txt");
        List<String> input = Utils.readLines("input/" + day + "/input.txt");
        System.out.println(part1(test));
        System.out.println(part1(input));
        part2(test);
        part2(input);
    }

    private static Integer part1(List<String> input) {
        Queue<String> begin = shuntingAlgorithm((String)input.get(0), new LinkedList());
        Object listBegin = reversePolishToList(begin);
//        System.out.println(listBegin);

        for(int i = 1; i < input.size(); ++i) {
            Queue<String> reversPolish = shuntingAlgorithm((String)input.get(i), new LinkedList());
            Object list = reversePolishToList(reversPolish);
            Object listSum = new ArrayList();
            ((ArrayList)listSum).add(listBegin);
            ((ArrayList)listSum).add(list);
//            System.out.println(listSum);
            Boolean tmp;
            do {
                while(walkListExplode(listSum)) {
//                    System.out.println("Explode: "+listSum);
                }
                tmp = walkListSplit(listSum);
//                if(tmp) System.out.println("Split: "+listSum);
            } while(tmp);




            listBegin = listSum;
        }
        Integer x = calculateMagnitude(listBegin);
//        System.out.println(x);
        return x;
    }
    private static Integer calculateMagnitude(Object o){
        if(o instanceof List){
           Object e1 = ((List<?>) o).get(0);
           Object e2 = ((List<?>) o).get(1);
           Integer l1;
           if(e1 instanceof List){
               l1 = 3*calculateMagnitude(e1);
           } else if (e1 instanceof Pair){
               l1 = 3*calculateMagnitude(e1);
            } else l1 =  (Integer) e1;
            Integer l2;
            if(e2 instanceof List){
                l2 = 2*calculateMagnitude(e2);
            } else if (e2 instanceof Pair){
                l2=2*calculateMagnitude(e2);
            } else l2 =  (Integer) e2;
            return l1+l2;
        } else if( o instanceof Pair){
            Integer pair1 = ((Pair) o).pair1;
            Integer pair2 = ((Pair) o).pair2;
            return 3* pair1 +2*  pair2;
        } else return  (Integer) o;
    }

    private static boolean walkListExplode(Object list) {
        return walkListExplode(list, 0, new Stack());
    }

    private static boolean walkListExplode(Object list, int depth, Stack<Object> stack) {
        if (list instanceof List) {
            List<Object> tmpList = (List)list;
            int size = tmpList.size();

            for(int i = 0; i < size; ++i) {
                Object o = tmpList.get(i);
                Stack clone;
                if (o instanceof Pair) {
                    if (depth == 3) {
                        clone = (Stack)stack.clone();
                        clone.add(tmpList);
                        explodeRight((Pair)o, o, clone);
                        clone = (Stack)stack.clone();
                        clone.add(tmpList);
                        explodeLeft((Pair)o, o, clone);
                        if (size == 2) {
                            Object o1 = tmpList.get((i + 1) % 2);
                            if (o1 instanceof Integer) {
                                Pair p2 = new Pair();
                                if (i == 0) {
                                    p2.pair1 = 0;
                                    p2.pair2 = (Integer)o1;
                                } else {
                                    p2.pair2 = 0;
                                    p2.pair1 = (Integer)o1;
                                }

                                List lst = (List)stack.pop();
                                lst.set(lst.indexOf(tmpList), p2);
                            } else {
                                tmpList.set(tmpList.indexOf(o), 0);
                            }
                        } else {
                            tmpList.set(tmpList.indexOf(o), 0);
                        }

                        return true;
                    }

                    } else{
                    clone = (Stack)stack.clone();
                    clone.add(tmpList);
                    if (walkListExplode(o, depth + 1, clone)) {
                        return true;
                    }
                }

                }

        }



        return false;
    }

    private static boolean walkListSplit(Object list) {
        return walkListSplit(list, 0, new Stack());
    }

    private static boolean walkListSplit(Object list, int depth, Stack<Object> stack) {
        if (list instanceof List) {
            List<Object> tmpList = (List)list;
            int size = tmpList.size();

            for(int i = 0; i < size; ++i) {
                Object o = tmpList.get(i);
                if (o instanceof Integer) {
                    if (split(tmpList, i, (Integer)o)) {
                        return true;
                    }
                } else if (o instanceof Pair) {
                     Integer e1 = ((Pair) o).pair1;
                     Integer e2 = ((Pair) o).pair2;
                     if(e1 > 9) {
                         List<Object> tmp = new ArrayList<>();
                         tmp.add(0,e2);
                         tmp.add(0,null);
                         if(split(tmp,0,e1)) {
                             tmpList.set(i,tmp);
                             return true;
                         }
                     }
                    if(e2 > 9) {
                        List<Object> tmp = new ArrayList<>();
                        tmp.add(0,null);
                        tmp.add(0,e1);

                        if(split(tmp,1,e2)) {
                            tmpList.set(i,tmp);
                            return true;
                        }
                    }
                } else { Stack clone = (Stack) stack.clone();
                    clone.add(tmpList);
                    if (walkListSplit(o, depth + 1, clone)) {
                        return true;}

            }

            }

        }

    return false;
    }

    private static boolean split(List<Object> tmpList, int i, Integer o) {
        if (o > 9) {
            Pair p = new Pair();
            p.pair1 = o / 2;
            p.pair2 = (int)Math.ceil((double)o / 2.0D);
            tmpList.set(i, p);
            return true;
        } else {
            return false;
        }
    }

    private static boolean explodeRight(Pair pair, Object currentObject, Stack<Object> stack) {
        if (stack.isEmpty()) {
            return false;
        } else {
            List list = (List)stack.pop();
            int startingi;
            if (list.contains(currentObject)) {
                startingi = list.indexOf(currentObject) + 1;
            } else {
                startingi = 0;
            }

            for(int i = startingi; i < list.size(); ++i) {
                Object o = list.get(i);
                if (o instanceof Integer) {
                    Object o1 = (Integer)o + pair.pair2;
                    list.set(i, o1);
                    return true;
                }

                if (o instanceof Pair) {
                    Pair var7 = (Pair)o;
                    var7.pair1 = var7.pair1 + pair.pair2;
                    return true;
                }

                if (o instanceof List) {
                    stack.add(o);
                    if (explodeRight(pair, new Pair(), stack)) {
                        return true;
                    }
                }
            }

            return explodeRight(pair, list, stack);
        }
    }


    private static boolean explodeLeft(Pair pair, Object currentObject, Stack<Object> stack) {
        if (stack.isEmpty()) {
            return false;
        } else {
            List list = (List)stack.pop();
            int startingi;
            if (list.contains(currentObject)) {
                startingi = list.indexOf(currentObject) - 1;
            } else {
                startingi = list.size() - 1;
            }

            for(int i = startingi; i >= 0; --i) {
                Object o = list.get(i);
                if (o instanceof Integer) {
                    Object o1 = (Integer)o + pair.pair1;
                    list.set(i, o1);
                    return true;
                }

                if (o instanceof Pair) {
                    Pair var7 = (Pair)o;
                    var7.pair2 = var7.pair2 + pair.pair1;
                    return true;
                }

                if (o instanceof List) {
                    stack.add(o);
                    if (explodeLeft(pair, new Pair(), stack)) {
                        return true;
                    }
                }
            }

            return explodeLeft(pair, list, stack);
        }
    }

    private static void walkTree(Tree tree) {
        walkTree(tree, 0);
    }

    private static void walkTree(Tree tree, int depth) {
        Iterator var2 = tree.listTree.iterator();

        while(var2.hasNext()) {
            Object o = var2.next();
            if (o instanceof Tree) {
                walkTree((Tree)o, depth + 1);
            } else if (o instanceof List) {
                System.out.println(o + " Depth: " + depth);
            } else {
                System.out.println(o + " Depth: " + depth);
            }
        }

    }

    private static Queue<String> shuntingAlgorithm(String s, LinkedList<ArrayList<Long>> tree) {
        Stack<String> stack = new Stack();

        LinkedList queue;
        for(queue = new LinkedList(); s.length() != 0; s = s.substring(1)) {
            char token = s.charAt(0);
            if (token == '[') {
                stack.add("[");
            } else if (token == ']') {
                while(true) {
                    String operator = (String)stack.pop();
                    if (operator.equals("[")) {
                        break;
                    }

                    queue.add(",");
                }
            } else if (token == ',') {
                stack.add(",");
            } else {
                queue.add(String.valueOf(token));
            }
        }

        return queue;
    }

    private static Object reversePolishToList(Queue<String> queue) {
        Stack stack = new Stack();

        while(true) {
            while(true) {
                while(!queue.isEmpty()) {
                    String element = (String)queue.poll();
                    if (element.equals(",")) {
                        Object e1 = stack.pop();
                        Object e2 = stack.pop();
                        if (e1 instanceof Integer && e2 instanceof Integer) {
                            Pair pair = new Pair();
                            pair.pair1 = (Integer)e2;
                            pair.pair2 = (Integer)e1;
                            stack.add(pair);
                        } else {
                            List<Object> tmp = new ArrayList();
                            tmp.add(0, e1);
                            tmp.add(0, e2);
                            stack.add(tmp);
                        }
                    } else {
                        stack.add(Integer.valueOf(element));
                    }
                }

                return stack.pop();
            }
        }
    }

    private static void part2(List<String> input) {
        long result = Long.MIN_VALUE;
        for (int i = 0; i < input.size(); i++) {
            String number = input.get(i);
            for (int i1 = 0; i1 < input.size(); i1++) {
                if(i1 ==i) continue;
                String secondNumber = input.get(i1);
                List<String> arrayList = new ArrayList<>();
                arrayList.add(number);
                arrayList.add(secondNumber);
                long tmp = part1(arrayList);
                if(tmp > result ) result =tmp;
            }
        }
        System.out.println(result);
    }
}



class Pair {
    Integer pair1;
    Integer pair2;

    Pair() {
    }

    public String toString() {
        return "[" + this.pair1 + "," + this.pair2 + "]";
    }
}


class Tree {
    List<Object> listTree = new ArrayList();
    Tree previous;

    Tree() {
    }
}

