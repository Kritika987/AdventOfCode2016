import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class Security {
    static int result = 0,seco=0;
    static List<String> encrypt = new ArrayList<>();

    static void stringFromData(String s) {
        int secto = Integer.parseInt(s.replaceAll("[\\[|\\]a-z-]", ""));
        String checksum = s.substring(s.indexOf('[') + 1, s.indexOf(']'));
        List<String> strings = Arrays.asList(s.substring(0, s.indexOf('[')).replaceAll("[-\\d]", "").split(""));
        Set<String> strings1 = new TreeSet<>(strings);
        Map<Integer, String> stringMap = new HashMap<>();
        for (String x : strings1) {
            int frequency = Collections.frequency(strings, x);
            if (stringMap.containsKey(frequency)) {
                String prev = stringMap.get(frequency);
                prev += x;
                char a[] = prev.toCharArray();
                Arrays.sort(a);
                prev = new String(a);
                stringMap.put(frequency, prev);
            } else {
                stringMap.put(frequency, x);
            }
        }
        List<Integer> keys = new ArrayList<>(stringMap.keySet());
        Collections.sort(keys, Collections.reverseOrder());
        String finl = "";
        for (int i : keys) {
            finl += stringMap.get(i);
        }

        if (finl.contains(checksum)) {
            result += secto;
            encrypt.add(s);
        }


    }

    static void decrypt(String s) {
        int sectorId= Integer.parseInt(s.replaceAll("[\\[|\\]a-z-]", ""));
        s=s.substring(0,s.lastIndexOf('-'));
        char[] c=s.toCharArray();
        for(int i=0;i<c.length;i++){
            if(Character.isLetter(c[i])){
                int offset=c[i]-'a';
                c[i]=(char)((offset+sectorId)%26+'a');
            }
            else{
                c[i]=' ';
            }
        }
        s=new String(c);
        if(s.contains("northpole")){
            seco=sectorId;
        }

    }

    public static void main(String... args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
            reader.lines().forEach(Security::stringFromData);
            System.out.println("Question 1: What is the sum of the sector IDs of the real rooms?");
            System.out.println("Answer 1: Sum of sectorId of all the real rooms is "+result+"\n" );
            for(String s:encrypt){
                decrypt(s);
                if(seco!=0) break;
            }
            System.out.println("Question 2: What is the sector ID of the room where North Pole objects are stored?");
            System.out.println("Answer 2: " + seco);
        } catch (Exception e) {

        }
    }
}

