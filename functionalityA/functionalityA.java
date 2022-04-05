package functionalityA;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class functionalityA {
	public static busStopConnect routes;
    public static busJourney trips;

    public static void setupGraph(File stops, File stop_times, File transfers) throws IOException {
        System.out.println("Setting up Graph");

        routes = new busStopConnect(stops, transfers);
        trips = new busJourney(stop_times);

        for (int t = 1; t < trips.validData.size(); t++) {
        	busJourneyInfo trip1 = trips.validData.get(t - 1);
        	busJourneyInfo trip2 = trips.validData.get(t);
            int cost = 1;
            if (trip1.trip_id == trip2.trip_id) {
                busStopConnect.makeConnection(trip1.stop_id, trip2.stop_id, cost);
            }
        }
        System.out.println("Finishing setting graph");

    }

    public static void printShortestPathInfo(int fromStopID, int toStopID) {
        ArrayList<Integer> shortestPath = busStopConnect.getShortestPath(fromStopID, toStopID);
        double shortestCost = busStopConnect.getShortestPathCost();

        if (shortestCost == Double.POSITIVE_INFINITY) {
            System.out.println("No route from from " + fromStopID + " to " + toStopID);
        } else if (shortestCost == Double.NEGATIVE_INFINITY) {
            System.out.println("both are same");
        } else if (shortestCost == -1.0) {
            System.out.println("Invalid input");
        } else {
            System.out.println("Cost from " + fromStopID + " to " + toStopID + " is: " + shortestCost);
            for(int i = 0; i < shortestPath.size();i++){
                System.out.print(shortestPath.get(i));
                if(i != shortestPath.size() - 1){
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String stops_times_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stop_times.txt";
        File stop_times = new File(stops_times_path);

        String stops_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stops.txt";
        File stops = new File(stops_path);

        String transfers_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/transfers.txt";
        File transfers = new File(transfers_path);

        setupGraph(stops, stop_times, transfers);

        int fromStopID = 71;
        int toStopID = 646;
        printShortestPathInfo(fromStopID, toStopID);


	}

}
