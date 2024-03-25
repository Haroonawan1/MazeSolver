import java.util.ArrayList;
import java.util.Arrays;

public class MazeSolver {
    private String[][] mapData;
    private ArrayList<String> coordinates, deadEnds;
    private int r, c;

    public MazeSolver(String[][] mapData) {
        this.mapData = mapData;
        coordinates = new ArrayList<>(Arrays.asList("(0, 0)", "(0, 0)"));
        deadEnds = new ArrayList<>();
        r = c = 0;
    }

    public String findPath() {
        while (r != mapData.length - 1 || c != mapData[0].length - 1) {
            if(!(checkSouth() || checkNorth() || checkEast() || checkWest())) {
                deadEnd();
            }
            else {
                coordinates.add("(" + r + ", " + c + ")");
            }
        }
        return formatString();
    }

    public boolean checkSouth() {
        if (r + 1 < mapData.length && !("(" + (r + 1) + ", " + c + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + (r + 1) + ", " + c + ")") && mapData[r + 1][c].equals(".")) {
            r++;
            return true;
        }
        return false;
    }

    public boolean checkNorth() {
        if (r - 1 >= 0 && !("(" + (r - 1) + ", " + c + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + (r - 1) + ", " + c + ")") && mapData[r - 1][c].equals(".")) {
            r--;
            return true;
        }
        return false;
    }

    public boolean checkEast() {
        if (c + 1 < mapData[0].length && !("(" + r + ", " + (c + 1) + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + r + ", " + (c + 1) + ")") && mapData[r][c + 1].equals(".")) {
            c++;
            return true;
        }
        return false;
    }

    public boolean checkWest() {
        if (c - 1 >= 0 && !("(" + r + ", " + (c - 1) + ")").equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains("(" + r + ", " + (c - 1) + ")") && mapData[r][c - 1].equals(".")) {
            c--;
            return true;
        }
        return false;
    }

    public void deadEnd() {
        deadEnds.add("(" + r + ", " + c + ")");
        coordinates = new ArrayList<>(Arrays.asList("(0, 0)", "(0, 0)"));
        r = c = 0;
    }

    public String formatString() {
        String result = "";
        for (String coordinate : coordinates) {
            result += coordinate + " -> ";
        }
        for (int i = result.length() - 1; i > 5; i--) {
            if (result.startsWith("(0, 0)", i - 6)) {
                result = result.substring(i - 6);
            }
        }
        return result.substring(0, result.length() - 4);
    }
}