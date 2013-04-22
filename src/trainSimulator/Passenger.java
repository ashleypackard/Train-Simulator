package trainSimulator;

/**
 * 
 * @author Michael Kucharski, Ashley Packard
 *
 */

public class Passenger 
{

	private String name;
	private int startingStation;
	private int endingStation;
	
	public Passenger(String n, int first, int end){
		name = n;
		startingStation = first;
		endingStation = end;
	}
	
	// mutator member functions
	public void setName(String newName) {name = newName;}
	public void setStartingStation(int start){startingStation = start;}
	public void setEndingStation(int end){endingStation = end;}
	// accessor member functions
	public int getStartingStation(){return startingStation;}
	public String getName(){return name;}
	public int getEndingStation(){return endingStation;}
	
}
