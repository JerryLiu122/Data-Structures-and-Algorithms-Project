package functionalityA         
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
        String ColumnLabel0 = "busStopId - ";
        String ColumnLabel1 = ", busStopCode - ";
        String ColumnLabel2 = ", busStopName - ";
        String ColumnLabel3 = ", busStopDesc - ";
        String ColumnLabel4 = ", busStopLat - ";
        String ColumnLabel5 = ", busStopLon - ";
        String ColumnLabel6 = ", zoneId - ";
        String ColumnLabel7 = ", busStopUrl - ";
        String ColumnLabel8 = ", locationType - ";
        String ColumnLabel9 = ", parentStation - ";

        System.out.println(ColumnLabel0 + busStopId + ColumnLabel1 + busStopCode + ColumnLabel2 + busStopName + ColumnLabel3
                + busStopDesc + ColumnLabel4 + busStopLat + ColumnLabel5 + busStopLon + ColumnLabel6 + zoneId + ColumnLabel7
                + busStopUrl + ColumnLabel8 + locationType + ColumnLabel9 + parentStation);
    }
}
