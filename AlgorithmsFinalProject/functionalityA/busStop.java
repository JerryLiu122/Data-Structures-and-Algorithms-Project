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

}
