package functionalityA;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class functionalityA {
	public static busStopConnect routes;
    public static busJourney journey;

    public static void setupGraph(File stops, File stop_times, File transfers) throws Exception {
        System.out.println("Setting up Graph");

        routes = new busStopConnect(stops, transfers);
        journey = new busJourney(stop_times);

        int index = 1;
        while(index < journey.validData.size()) 
        {
        	busJourneyInfo journey1 = journey.validData.get(index - 1);
        	busJourneyInfo journey2 = journey.validData.get(index);
            int count = 1;
            if (journey1.trip_id == journey2.trip_id) 
            {
                busStopConnect.makeConnection(journey1.stop_id, journey2.stop_id, count);
            }
            index++;
        }
        System.out.println("Graph setting have finished");

    }

    public static void printShortestPathInfo(int fromStopID, int toStopID) {
        ArrayList<Integer> shortestPath = busStopConnect.getShortestPath(fromStopID, toStopID);
        double shortestCost = busStopConnect.getShortestPathCost();

        if (shortestCost == Double.POSITIVE_INFINITY) 
        {
            System.out.println("There is no route from from " + fromStopID + " to " + toStopID);
        } 
        else if (shortestCost == Double.NEGATIVE_INFINITY) 
        {
            System.out.println("Departure stop and destination stop shouldn't be same");
        } 
        else if (shortestCost == -1.0) 
        {
            System.out.println("Invalid input");
        } 
        else 
        {
            System.out.println("Cost from " + fromStopID + " to " + toStopID + " is: " + shortestCost);
            int i = 0;
            while(i < shortestPath.size()) 
            {
            	System.out.print(shortestPath.get(i));
                if(i != shortestPath.size() - 1)
                {
                    System.out.print(" -> ");
                }
                i++;
            }
            System.out.println();
        }
    }

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		


	}

}
