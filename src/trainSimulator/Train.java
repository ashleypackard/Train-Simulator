package trainSimulator;

/**
 * 
 * @author Michael Kucharski, Ashley Packard
 *
 */

import java.util.ArrayList;

public class Train {

	// used to store where the train currently is
	private Station currentStation;
	// passengers are stored in the train in an unordered collection
	private ArrayList<Passenger> passengers;
	
	// constructor
	public Train(Station startStation){
		passengers = new ArrayList<Passenger>();
		currentStation = startStation;
	}

	// removes the passengers from the train if the stop it is currently at is their destination
	// NOTE: returns how many passengers were let off
	public int letOffPassegers(){
		int count = 0;
		// loop through every passenger on the train and test if the current stop is their stop
		// if so, remove them from the train and increment count
		for(int i = 0; i < passengers.size(); i++){
			if(currentStation.getStationNumber() == passengers.get(i).getEndingStation()) 
			{
				passengers.remove(passengers.get(i));
				count++;
			}
		}
		return count;
	}
	
	// add a new passenger to the train
	public void addPassenger(Passenger p)          	   {passengers.add(p);}
	// mutator and accessor methods for the current station
	public Station getStation() 						   {return currentStation;}
	public void setStation(Station newStation)             {currentStation = newStation;}
	// tests if the train is out of passengers
	public boolean isEmpty()							   {return passengers.isEmpty();}
	// returns the number of passengers on board
	public int passengersOnTrain()						   {return passengers.size();}
	
}
