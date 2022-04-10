import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import functionalityA.functionalityA;
import functionalityB.functionalityB;

public class UI 
{

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("This is a bus management system. Welcome!" + "\n");
		System.out.println("Please select a functionality from the options in follow:");
		System.out.println("Finding shortest paths between two stop(1)");
		System.out.println("Searching for a bus stop(2)");
		System.out.println("Exit(0)");
		
		int choice = 0;
		boolean exit = false;
		while((choice != 0) || (exit == false)) 
		{
			try (Scanner input = new Scanner(System.in)) 
			{
				if(input.hasNextInt())
				{
					choice = input.nextInt();
					input.nextLine();
					if(choice == 1)
					{	
						System.out.println("Finding shortest paths");
						while(choice != 0)
						{
							System.out.println("Please enter the departure stop ID or enter 0 to exit");
							
							System.out.println("Please enter the destination stop ID or enter 0 to exit");
							
							if(input.hasNextInt())
						    {
								int fromStopID = input.nextInt();
								int toStopID = input.nextInt();
						    	if(fromStopID != 0 && toStopID != 0)
						    	{
						    		String stops_times_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stop_times.txt";
						            File stop_times = new File(stops_times_path);
						            String stops_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stops.txt";
						            File stops = new File(stops_path);
						            String transfers_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/transfers.txt";
						            File transfers = new File(transfers_path);
						            functionalityA.setupGraph(stops, stop_times, transfers);
						    		functionalityA.printShortestPathInfo(fromStopID, toStopID);
						        }
						    	else 
						    	{
						    		choice = 0;
						    		exit = true;
						    	}
						    } 
						    else if(input.hasNext())
						    {
						    	System.out.print("Input error" + "\n\n");
						    	input.nextLine();
						    }
						}
						
					}
					else if(choice == 2)
					{
						while(choice != 0)
						{
							System.out.print("Please enter the bus stop:");
							String stopName = input.next();
							if(input.hasNextInt()) 
					        {
					        	choice = 0;
					        	exit = true;
					        }
							String stops_path = "/Users/lzy/eclipse-workspace/Algorithm finial project/input files/stops.txt";
					        File stops = new File(stops_path);
					        ArrayList<String> stopNames = functionalityB.getStop(stops);
					        functionalityB.insertStopName(stopNames);
					        String[] HastingsSearch = functionalityB.STOP_NAME.search(stopName);
					        for(String x: HastingsSearch) 
					        {
					            System.out.println(x);
					            System.out.println("Length "+HastingsSearch.length);
					        }
					        
						}
					}
					else if(choice == 0)
					{
						exit = true;
					}
				    else
				    {
					System.out.print("Input error!" + "\n" + "Please enter a number :" + "\n");
				    }
			    }
			}
			
		}
		System.out.print("Goodbye!");
		
	 }

}
