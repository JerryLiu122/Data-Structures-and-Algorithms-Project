package functionalityA;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class busJourney 
{
	 public ArrayList<busJourneyInfo> validData;

	    public busJourney(File stop_times) throws IOException 
	    {
	        validData = new ArrayList<>();
	        readStopTimes(stop_times);
	    }
	    public void readStopTimes(File stop_times) throws IOException {
	        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(stop_times))) 
	        {
				String string;
				int count = 0;
				    while ((string = bufferedReader.readLine()) != null) 
				    {
				        String[] line = string.split(",");
				        if (count != 0) {
				            int trip_id = -1;
				            int stop_id = -1;
				            int stop_sequence = -1;
				            int stop_headsign = -1;
				            int pickup_type = -1;
				            int drop_off_type = -1;
				            float shape_dist_traveled = -1;

				            String arrival_time = line[1];
				            String departure_time = line[2];

				            if (!line[0].equals("")) 
				            {
				                trip_id = Integer.parseInt(line[0]);
				            }
				            if (!line[3].equals("")) 
				            {
				                stop_id = Integer.parseInt(line[3]);
				            }
				            if (!line[4].equals("")) 
				            {
				                stop_sequence = Integer.parseInt(line[4]);
				            }
				            if (!line[5].equals("")) 
				            {
				                stop_headsign = Integer.parseInt(line[5]);
				            }
				            if (!line[6].equals("")) 
				            {
				               pickup_type = Integer.parseInt(line[6]);
				            }
				            if (!line[7].equals("")) 
				            {
				                drop_off_type = Integer.parseInt(line[7]);
				            }
				            if ((line.length == 9) && !line[8].equals("")) 
				            {
				                shape_dist_traveled = Float.parseFloat(line[8]);
				            }
				            if (isTimeValid(arrival_time) && isTimeValid(departure_time)) 
				            {
				                validData.add(new busJourneyInfo(trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_traveled));
				            }
				        }
				        count++;
				    }
				
				System.out.println("Completed reading Stop time " + count + " times.");
			}
	    }

	    public static boolean isTimeValid(String time) {
	        final int MAX_H = 23;
	        final int MAX_MIN = 59;
	        final int MAX_SEC = 59;
	        String time_without_space = time.replaceAll("\\s", "");
	        String[] individualParts = time_without_space.split(":");
	        int hours = Integer.parseInt(individualParts[0]);
	        int minutes = Integer.parseInt(individualParts[1]);
	        int seconds = Integer.parseInt(individualParts[2]);	       
	        if((hours<=MAX_H)&&(minutes<=MAX_MIN)&&(seconds<=MAX_SEC)) 
	        {
	        	return true;
	        }
	        else 
	        {
	        	return false;
	        }
	            
	    }

}
