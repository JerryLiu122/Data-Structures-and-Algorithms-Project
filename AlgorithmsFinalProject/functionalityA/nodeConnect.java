package functionalityA;
public class nodeConnect {
	public int stop_id;
    public double cost;

    public nodeConnect(int stop_id, double cost) {
        this.stop_id = stop_id;
        this.cost = cost;
    }

    public void printNode(){
        System.out.println("Stop id - "+stop_id+" Cost - "+cost);
    }
}
