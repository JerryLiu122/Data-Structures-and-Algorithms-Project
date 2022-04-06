package functionalityA;

public class busStop {
	
	public int busStopId;
    public int busStopCode;
    public String busStopName;
    public String busStopDesc;
    public double busStopLat;
    public double busStopLon;
    public String zoneId;
    public String busStopUrl;
    public int locationType;
    public String parentStation;

    public busStop(int busStopId, int busStopCode, String busStopName, String busStopDesc, double busStopLat, double busStopLon, String zoneId, String busStopUrl, int locationType, String parentStation) 
    {
        this.busStopId = busStopId;
        this.busStopCode = busStopCode;
        this.busStopName = busStopName;
        this.busStopDesc = busStopDesc;
        this.busStopLat = busStopLat;
        this.busStopLon = busStopLon;
        this.zoneId = zoneId;
        this.busStopUrl = busStopUrl;
        this.locationType = locationType;
        this.parentStation = parentStation;
    }

    public void printStopDetails() {
        String label0 = "busStopId - ";
        String label1 = ", busStopCode - ";
        String label2 = ", busStopName - ";
        String label3 = ", busStopDesc - ";
        String label4 = ", busStopLat - ";
        String label5 = ", busStopLon - ";
        String label6 = ", zoneId - ";
        String label7 = ", busStopUrl - ";
        String label8 = ", locationType - ";
        String label9 = ", parentStation - ";

        System.out.println(label0 + busStopId + label1 + busStopCode + label2 + busStopName + label3
                + busStopDesc + label4 + busStopLat + label5 + busStopLon + label6 + zoneId + label7
                + busStopUrl + label8 + locationType + label9 + parentStation);
    }
}
