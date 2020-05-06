import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;


public class NoTimeForTaxi {

    static int calculateBlocks(String[] data) {
        int direction = 0, x = 0, y = 0, displace;

        for (String s : data) {
            if (s.charAt(0) == 'R')
                direction = (direction + 1) % 4;
            else
                direction = (direction + 3) % 4;
            displace = Integer.parseInt(s.substring(1));
            switch (direction) {
                case 0:
                    y += displace;
                    break;
                case 1:
                    x += displace;
                    break;
                case 2:
                    y -= displace;
                    break;
                case 3:
                    x -= displace;
                    break;
            }
        }
        return (Math.abs(x) + Math.abs(y));
    }

    static int firstPointVisitedTwice(String[] data) {
        int direction = 0, x = 0, y = 0, displace;
        Set<String> visited = new HashSet<>();
        visited.add(x + "," + y);
        boolean found = false;
        for (String s : data) {
            if (found) {
                break;
            }
            if (s.charAt(0) == 'R')
                direction = (direction + 1) % 4;
            else
                direction = (direction + 3) % 4;
            displace = Integer.valueOf(s.substring(1));
            switch (direction) {
                case 0:
                    for (int i = y + 1; i <= y + displace; i++) {
                        String coord = x + "," + i;
                        if (visited.contains(coord)) {
                            return (Math.abs(x) + Math.abs(i));
                        }
                        visited.add(coord);
                    }
                    y += displace;
                    break;
                case 1:
                    for (int i = x + 1; i <= x + displace; i++) {
                        String coord = i + "," + y;
                        if (visited.contains(coord)) {
                            return (Math.abs(i) + Math.abs(y));

                        }
                        visited.add(coord);
                    }
                    x += displace;
                    break;
                case 2:
                    for (int i = y - 1; i >= y - displace; i--) {
                        String coord = x + "," + i;
                        if (visited.contains(coord)) {
                            return (Math.abs(x) + Math.abs(i));

                        }
                        visited.add(coord);
                    }
                    y -= displace;
                    break;
                case 3:
                    for (int i = x - 1; i >= x - displace; i--) {
                        String coord = i + "," + y;
                        if (visited.contains(coord)) {
                            return (Math.abs(i) + Math.abs(y));
                        }
                        visited.add(coord);
                    }
                    x -= displace;
                    break;
            }
        }
        return (Math.abs(x) + Math.abs(y));
    }

    public static void main(String... args) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"));
            String[] data = fileReader.readLine().split(", ");
        System.out.println("Question 1: How many blocks away is Easter Bunny HQ?");

            System.out.println("Answer 1: " +calculateBlocks(data)+ " blocks.\n");
            System.out.println("Question 2: How many blocks away is the first location you visit twice?");

            System.out.println("Answer 2: "+firstPointVisitedTwice(data)+" blocks.");
        } catch (Exception x) {
            System.out.println("ae");
        }
    }
}

