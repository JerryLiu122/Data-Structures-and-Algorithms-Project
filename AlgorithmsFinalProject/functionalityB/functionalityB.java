package functionalityB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class functionalityB {
	public static searchTree STOP_NAME = new searchTree();

    public static String[] getColumn(File filename) throws IOException 
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String string;
        while ((string = bufferedReader.readLine()) != null) 
        {
            String[] line = string.split(",");
            bufferedReader.close();
            return line;
        }
        bufferedReader.close();
        return null;
    }

    public static String makeSence(String stopName) 
    {
        int length = 2;
        int stopLength = 8;
        String temp = stopName.substring(0, length).strip().toUpperCase();
        String tempFlagStop = stopName.substring(0, stopLength).strip().toUpperCase();
        if (temp.equals("WB") || temp.equals("NB") || temp.equals("SB") || temp.equals("EB")) 
        {
            String end = stopName.substring(length + 1);
            String begin = stopName.substring(0, length);
            String str = end.concat(" ").concat(begin);
            return makeSence(str);
        }
        if (tempFlagStop.equals("FLAGSTOP")) 
        {
            String end = stopName.substring(stopLength + 1);
            String begin = stopName.substring(0, stopLength);
            String str = end.concat(" ").concat(begin);
            return makeSence(str);
        } 
        else 
        {
        	return stopName;
        }
    }

    public static ArrayList<String> getStop(File filename) throws IOException 
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String string;
        ArrayList<String> stopNames = new ArrayList<String>();
        while ((string = bufferedReader.readLine()) != null) 
        {
            String[] line = string.split(",");
            if (!line[2].equals("stop_name")) 
            {
                String meaningfulName = makeSence(line[2]);
                stopNames.add(meaningfulName);
            }
        }
        bufferedReader.close();
        return stopNames;
    }

    public static void printStation(ArrayList<String> stopNames) 
    {
        ArrayList<String> stopNamesUniques = new ArrayList<String>();
        int duplicateCount = 0;
        System.out.println("Duplicate Stop Names");
        for (String x : stopNames) 
        {
            if (stopNamesUniques.contains(x)) 
            {
                duplicateCount++;
                System.out.println(duplicateCount + " " + x);
            } 
            else
            {
                stopNamesUniques.add(x);
            }
        }

        System.out.println("Stops count - " + stopNames.size());
        System.out.println("Unique Stops count - " + stopNamesUniques.size());
    }

    public static Map<String, ArrayList<String>> createDetails(File filename) throws IOException 
    {

        int indexOfStopName = 2;
        Map<String, ArrayList<String>> Time_Line = new TreeMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String string;
        while ((string = bufferedReader.readLine()) != null) 
        {
            String[] line = string.split(",");
            if (!line[indexOfStopName].equals("stop_name")) 
            {
                String meaningfulName = makeSence(line[indexOfStopName]);
                Time_Line.computeIfAbsent(meaningfulName, k -> new ArrayList<>()).add(string);
            }
        }
        bufferedReader.close();
        return Time_Line;
    }

    public static void insertStopName(ArrayList<String> stopNames) 
    {
        for (String stopName : stopNames) 
        {
            STOP_NAME.insert(stopName);
        }
    }

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		String stops_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stops.txt";
        File stops = new File(stops_path);
        ArrayList<String> stopNames = getStop(stops);
        insertStopName(stopNames);
        String[] HastingsSearch = STOP_NAME.search("HASTINGS");
        for(String x: HastingsSearch) 
        {
            System.out.println(x);
            System.out.println("Length"+HastingsSearch.length);

        }
    }

	

}
