import java.util.ArrayList;
import java.util.Arrays;

public class MazeSolver {
    private String[][] mapData;

    public MazeSolver(String[][] mapData) {
        this.mapData = mapData;
    }

    public String findPath() {
        ArrayList<String> coordinates = new ArrayList<>(Arrays.asList("(0, 0)", "(0, 0)"));
        ArrayList<String> deadEnds = new ArrayList<>();
        int r = 0;
        int c = 0;
        while (r != mapData.length - 1 || c != mapData[0].length - 1) {
            boolean south = checkDirection(r, c, r + 1, c, r + 1, mapData.length - 1, coordinates, deadEnds);
            boolean north = checkDirection(r, c, r - 1, c, ((r - 1) * -1), 0, coordinates, deadEnds);
            boolean east = checkDirection(r, c, r, c + 1, c + 1, mapData[0].length - 1, coordinates, deadEnds);
            boolean west = checkDirection(r, c, r, c - 1, ((c - 1) * -1), 0, coordinates, deadEnds);
            coordinates = (!south && !north && !east && !west) ? new ArrayList<>(Arrays.asList("(0, 0)", "(0, 0)")) : coordinates;
            r = (!south && !north && !east && !west) ? 0 : r;
            c = (!south && !north && !east && !west) ? 0 : c;
        }
        return formatArrayList(coordinates);
    }

    public boolean checkDirection(int r, int c, int rDelta, int cDelta, int compared, int bound, ArrayList<String> coordinates, ArrayList<String> deadEnds){
        String nextCoordinate = "(" + (r + rDelta) + ", " + (c + cDelta) + ")";
        if (compared <= bound && !(nextCoordinate).equals(coordinates.get(coordinates.size() - 2)) && !deadEnds.contains(nextCoordinate) && mapData[r + rDelta][c + cDelta].equals(".")) {
            r += rDelta;
            c += cDelta;
            coordinates.add("(" + r + ", " + c + ")");
        }
        else {
            deadEnds.add("(" + r + ", " + c + ")");
            return false;
        }
        return true;
    }

    public String formatArrayList(ArrayList<String> coordinates) {
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