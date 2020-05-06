import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class BalanceBot {
    static Map<String, List<Integer>> map = new TreeMap<>();
    static int[] output = new int[1000];

    static void findBot(List<String> stringList) {
        List<String> visited = new ArrayList<>();

        while (stringList.size() != 0) {

            for (String s : stringList) {
                String arr[] = s.split(" ");
                if (arr[0].equals("value")) {
                    List<Integer> list = map.containsKey(arr[5]) ? new ArrayList<>(map.get(arr[5])) : new ArrayList<>(2);
                    list.add(Integer.parseInt(arr[1]));
                    map.put(arr[5], list);
                    visited.add(s);
                } else {
                    List<Integer> integers = map.containsKey(arr[1]) ? map.get(arr[1]) : new ArrayList<>();
                    if (integers.size() == 2) {
                        Collections.sort(integers);
                        if (arr[5].equals("bot")) {
                            List<Integer> list = map.containsKey(arr[6]) ? new ArrayList<>(map.get(arr[6])) : new ArrayList<>();
                            list.add(integers.get(0));
                            map.put(arr[6], list);
                        } else {
                            output[Integer.parseInt(arr[6])] = integers.get(0);
                        }
                        if (arr[10].equals("bot")) {
                            List<Integer> list = map.containsKey(arr[11]) ? new ArrayList<>(map.get(arr[11])) : new ArrayList<>();
                            list.add(integers.get(1));
                            map.put(arr[11], list);
                        }
                        visited.add(s);
                    }
                }

            }
            stringList.removeAll(visited);
            visited = new ArrayList<>();
        }
        List<Integer> search =new ArrayList<>();
        search.add(61);
        search.add(17);
        System.out.println("Question 1: what is the number of the bot that is responsible for comparing value-61 microchips with value-17 microchips?");
        for(Map.Entry<String,List<Integer>> listEntry:map.entrySet()){
            if(listEntry.getValue().containsAll(search)){
                System.out.println("Answer 1: "+listEntry.getKey()+"\n");
                break;
            }
        }

    }


    public static void main(String... args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
            List<String> stringList = reader.lines().collect(Collectors.toList());
            findBot(stringList);
            System.out.println("Question 2: What do you get if you multiply together the values of one chip in each of outputs 0, 1, and 2?");
            System.out.println("Answer 2: "+output[0]*output[1]*output[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

