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
        ArrayList<String> deadEnds = new ArrayList<>();
        int r = (mapData[0][1].equals(".")) ? 0 : 1;
        int c = (mapData[0][1].equals(".")) ? 1 : 0;
        coordinates.add("(0, 0)");
        coordinates.add("(" + r + ", " + c + ")");
        while (r != mapData.length - 1 || c != mapData[0].length - 1) {
            if (r + 1 < mapData.length && !("(" + (r + 1) + ", " + c + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + (r + 1) + ", " + c + ")") && mapData[r + 1][c].equals(".")) {
                r++;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else if (r - 1 >= 0 && !("(" + (r - 1) + ", " + c + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + (r - 1) + ", " + c + ")") && mapData[r - 1][c].equals(".")) {
                r--;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else if (c + 1 < mapData[0].length && !("(" + r + ", " + (c + 1) + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + r + ", " + (c + 1) + ")") && mapData[r][c + 1].equals(".")) {
                c++;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else if (c - 1 >= 0 && !("(" + r + ", " + (c - 1) + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + r + ", " + (c - 1) + ")") && mapData[r][c - 1].equals(".")) {
                c--;
                coordinates.add("(" + r + ", " + c + ")");
            }
            else {
                deadEnds.add("(" + r + ", " + c + ")");
                coordinates = new ArrayList<>();
                r = (mapData[0][1].equals(".")) ? 0 : 1;
                c = (mapData[0][1].equals(".")) ? 1 : 0;
                coordinates.add("(0, 0)");
                coordinates.add("(" + r + ", " + c + ")");
            }
        }
        String result = "";
        for (String coordinate : coordinates) {
            result += coordinate + " -> ";
        }
        return result.substring(0, result.length() - 4);
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