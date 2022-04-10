package functionalityA;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class busStopConnect 
{
	public static HashMap<Integer, ArrayList<nodeConnect>> connection;
    public static HashMap<Integer, busStop> mapDetails;

    private static void initMaps() 
    {
        connection = new HashMap<>();
        mapDetails = new HashMap<>();
    }

    public busStopConnect() 
    {
        initMaps();
    }

    public busStopConnect(File stops, File transfers) throws Exception 
    {
        initMaps();
        readBusStops(stops);
        readTransfers(transfers);
    }

    public static void readTransfers(File transfer) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(transfer));
        String string;
        int count = 0;
            while ((string = bufferedReader.readLine()) != null) 
            {
                String[] line = string.split(",");
                if (count != 0) {
                    int emptyCode = -1;
                    int from_stop_id = (line[0].equals("") || line[0].equals(" ")) ? emptyCode : Integer.parseInt(line[0]);
                    int to_stop_id = (line[1].equals("") || line[1].equals(" ")) ? emptyCode : Integer.parseInt(line[1]);
                    int transfer_type = (line[2].equals("") || line[2].equals(" ")) ? emptyCode : Integer.parseInt(line[2]);
                    double cost;
                    switch (transfer_type) 
                    {
                    case 0:
                        cost = 2;
                        makeConnection(from_stop_id, to_stop_id, cost);
                        break;
                    case 2:
                        double min_transfer_time = Double.parseDouble(line[3]);
                        cost = min_transfer_time / 100;
                        makeConnection(from_stop_id, to_stop_id, cost);
                        break;
                    default:
                        throw new Exception("invalid transfer type at line" + count + "\n" + line);
                    }
                }
                count++;
            }
        System.out.println("Completed reading Transfers " + count + " times.");
        bufferedReader.close();
    }

    public static void makeConnection(int from_stop_id, int to_stop_id, double cost) 
    {
        connection.computeIfAbsent(from_stop_id, k -> new ArrayList<>());
        connection.computeIfAbsent(to_stop_id, k -> new ArrayList<>());
        connection.get(from_stop_id).add(new nodeConnect(to_stop_id, cost));
    }

    public static void readBusStops(File stops) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(stops));
        String string;
        int count = 0;
        int emptyCode = -1;
        double emptyCodeDouble = -1.0;
        String emptyString = "";
            while ((string = bufferedReader.readLine()) != null) 
            {
                String[] line = string.split(",");
                if (count != 0) {
                    int stop_id = (line[0].equals("") || line[0].equals(" ")) ? emptyCode : Integer.parseInt(line[0]);
                    int stop_code = (line[1].equals("") || line[1].equals(" ")) ? emptyCode : Integer.parseInt(line[1]);
                    String name = null;
					String stop_name = name;		
                    String stop_desc = (line[3].equals("") || line[3].equals(" ")) ? emptyString : line[3];
                    double stop_lat = (line[4].equals("") || line[4].equals(" ")) ? emptyCodeDouble: Double.parseDouble(line[4]);
                    double stop_lon = (line[5].equals("") || line[5].equals(" ")) ? emptyCodeDouble: Double.parseDouble(line[5]);
                    String zone_id = (line[6].equals("") || line[6].equals(" ")) ? emptyString : line[6];
                    String stop_url = (line[7].equals("") || line[7].equals(" ")) ? emptyString : line[7];
                    int location_type = (line[8].equals("") || line[8].equals(" ")) ? emptyCode : Integer.parseInt(line[8]);
                    String parent_station = (line.length == 9) ? emptyString : line[9];
                    addBusStop(new busStop(stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station));
                }
                count++;
            }
        System.out.println("Completed reading Stops " + count + " times ");
        bufferedReader.close();
    }

    public static void addBusStop(busStop stop) 
    {
        connection.put(stop.busStopId, new ArrayList<>());
        mapDetails.put(stop.busStopId, stop);
    }

    public static double shortestPathCost;

    public static double getShortestPathCost() 
    {
        return shortestPathCost;
    }

    public static boolean stopIdValidOrNot(int stop_id) 
    {
        return connection.keySet().contains(stop_id);
    }


    public static ArrayList<Integer> getShortestPath(int from_stop_id, int to_stop_id) 
    {
        if (stopIdValidOrNot(from_stop_id) && stopIdValidOrNot(to_stop_id)) 
        {
            if (from_stop_id == to_stop_id) {
                shortestPathCost = Double.NEGATIVE_INFINITY;
                System.out.println("Both stops are the same");
                return null;
            }
            HashMap<Integer, Double> distanceTo = new HashMap<>(connection.size());
            HashMap<Integer, Integer> prev = new HashMap<>(connection.size());
            HashSet<Integer> seen = new HashSet<>(connection.size());

            for (int key : connection.keySet()) 
            {
                distanceTo.put(key, Double.POSITIVE_INFINITY);
                prev.put(key, Integer.MAX_VALUE);
                seen.add(key);
            }
            distanceTo.put(from_stop_id, 0.0);
            while (!seen.isEmpty()) 
            {
                int currentStop = Integer.MAX_VALUE;
                double minVal = Double.POSITIVE_INFINITY;
                for (int val : seen) 
                {
                    double newVal = distanceTo.get(val);
                    if (newVal < minVal) 
                    {
                        minVal = newVal;
                        currentStop = val;
                    }
                }
                if (currentStop == Integer.MAX_VALUE) 
                {
                    break;
                }
                seen.remove(currentStop);

                if (currentStop == to_stop_id) 
                {
                    break;
                }

                ArrayList<nodeConnect> adjacent = connection.get(currentStop);

                if (adjacent != null) 
                {
                    for (nodeConnect adjNode : adjacent) 
                    {
                        if (adjNode.cost != Double.POSITIVE_INFINITY && distanceTo.get(currentStop) != null) 
                        {
                            double adjDist = distanceTo.get(currentStop) + adjNode.cost;
                            if (distanceTo.get(adjNode.stop_id) > adjDist) 
                            {
                                distanceTo.put(adjNode.stop_id, adjDist);
                                prev.put(adjNode.stop_id, currentStop);
                            }
                        }
                    }
                }
            }

            ArrayList<Integer> shortestPath = new ArrayList<>();
            int stop = to_stop_id;
            if (prev.get(stop) != null) 
            {
                if (prev.get(stop) != Integer.MAX_VALUE || stop == from_stop_id) 
                {
                    while (stop != Integer.MAX_VALUE) 
                    {
                        shortestPath.add(0, stop);
                        stop = prev.get(stop);
                    }
                }
            }
            if (distanceTo.get(to_stop_id) != null) 
            {
                shortestPathCost = distanceTo.get(to_stop_id);
            }

            return shortestPath;
        }
        if (!stopIdValidOrNot(from_stop_id)) 
        {
            System.out.println("invalid from stop id " + from_stop_id);
        }
        if (!stopIdValidOrNot(to_stop_id)) 
        {
            System.out.println("invalid to stop id " + to_stop_id);
        }
        shortestPathCost = -1.0;
        return null;
    }

   
}
