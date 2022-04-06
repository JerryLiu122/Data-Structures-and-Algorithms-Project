package functionalityB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class functionalityB {
	public static searchTree STOP_NAMES_TST = new searchTree();

    public static String[] getColumnNames(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            br.close();
            return line;
        }
        br.close();
        return null;
    }

    public static String makeMeaningful(String stopName) {

        int normalKeywordLength = 2;
        int flagtstopLength = 8;

        String temp = stopName.substring(0, normalKeywordLength).strip().toUpperCase();
        String tempFlagStop = stopName.substring(0, flagtstopLength).strip().toUpperCase();

        if (temp.equals("WB") || temp.equals("NB") || temp.equals("SB") || temp.equals("EB")) {
            String lastPart = stopName.substring(normalKeywordLength + 1);
            String firstPart = stopName.substring(0, normalKeywordLength);
            String meaningfulStr = lastPart.concat(" ").concat(firstPart);
            return makeMeaningful(meaningfulStr);
        }
        if (tempFlagStop.equals("FLAGSTOP")) {
            String lastPart = stopName.substring(flagtstopLength + 1);
            String firstPart = stopName.substring(0, flagtstopLength);
            String meaningfulStr = lastPart.concat(" ").concat(firstPart);
            return makeMeaningful(meaningfulStr);
        } else
            return stopName;
    }

    public static ArrayList<String> getStopNames(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        ArrayList<String> stopNames = new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            if (!line[2].equals("stop_name")) {
                String meaningfulName = makeMeaningful(line[2]);
                stopNames.add(meaningfulName);
            }
        }
        br.close();
        return stopNames;
    }

    public static void printDuplicateStations(ArrayList<String> stopNames) {
        ArrayList<String> stopNamesUniques = new ArrayList<String>();
        int duplicateCount = 0;
        System.out.println("------- Duplicate Stop Names -------");
        for (String x : stopNames) {
            if (stopNamesUniques.contains(x)) {
                duplicateCount++;
                System.out.println(duplicateCount + " " + x);
            } else
                stopNamesUniques.add(x);
        }

        System.out.println("Stops count - " + stopNames.size());
        System.out.println("Unique Stops count - " + stopNamesUniques.size());
    }

    public static Map<String, ArrayList<String>> createNameDetailsMap(File filename) throws IOException {

        int indexOfStopName = 2;
        Map<String, ArrayList<String>> Time_Line = new TreeMap<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;

        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            if (!line[indexOfStopName].equals("stop_name")) {
                String meaningfulName = makeMeaningful(line[indexOfStopName]);
                Time_Line.computeIfAbsent(meaningfulName, k -> new ArrayList<>()).add(st);
                // stopNames.add(meaningfulName);
            }
        }
        br.close();
        return Time_Line;
    }

    public static void insertStopNamesToTST(ArrayList<String> stopNames) {
        for (String stopName : stopNames) {
            STOP_NAMES_TST.insert(stopName);
        }
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String stops_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stops.txt";
        File stops = new File(stops_path);
        ArrayList<String> stopNames = getStopNames(stops);

        insertStopNamesToTST(stopNames);
        String[] HastingsSearch = STOP_NAMES_TST.search("HASTINGS");

        for(String x: HastingsSearch)
        System.out.println(x);
        System.out.println("Length"+HastingsSearch.length);



    }

	

}
