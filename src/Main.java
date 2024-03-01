import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] mapData = getMaze("data/mapData");
        System.out.println(findPath(mapData));
    }

    public static String findPath(String[][] mapData) {
        ArrayList<String> coordinates = new ArrayList<>();
        int r = 0;
        int c = 0;
        while (r != mapData.length - 1 && c != mapData[0].length - 1) {
            if (r + 1 != mapData.length && r + 1 != Integer.parseInt(coordinates.get(coordinates.size() - 2).substring(1, 2)) && mapData[r + 1][c].equals(".")) {
                r++;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else if (r - 1 >= 0 && r - 1 != Integer.parseInt(coordinates.get(coordinates.size() - 2).substring(1, 2)) && mapData[r - 1][c].equals(".")) {
                r--;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else if (c + 1 != mapData[0].length && c + 1 != Integer.parseInt(coordinates.get(coordinates.size() - 2).substring(4, 5)) &&  mapData[r][c + 1].equals(".")) {
                c++;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else if (c - 1 >= 0 && c - 1 != Integer.parseInt(coordinates.get(coordinates.size() - 2).substring(4, 5)) && mapData[r][c - 1].equals(".")) {
                c++;
                coordinates.add("(" + r + ", " + c + ")");
            }
            System.out.println(coordinates);
        }
        return "hello";
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }
}