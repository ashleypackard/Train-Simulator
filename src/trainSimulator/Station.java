package trainSimulator;

/**
 * 
 * @author Michael Kucharski, Ashley Packard
 *
 */

import queues.MyQueue;

public class Station {
	
	// represent the line as a queue, FIFO format
	private MyQueue<Passenger> line;
	private int stationNumber;
	private boolean terminal;
	
	// constructor
	public Station(int num){
		stationNumber = num;
		line = new MyQueue<Passenger>();
		terminal = false;
	}
	
	// constructor which allows you to specify if it's a terminal station
	public Station(int num, boolean term){
		stationNumber = num;
		line = new MyQueue<Passenger>();
		terminal = term;
	}
	
	// accessor methods
	public int getLineSize()			  {return line.size();}
	public int getStationNumber()		  {return stationNumber;}
	// add and remove passengers from line
	public Passenger getNextPassenger()   {return line.dequeue();}
	public void addPassenger(Passenger p) {line.enqueue(p);}
	// test if the station is a terminal station
	public boolean isTerminal()  		  {return terminal;}
	// tests if the Station is out of passengers
	public boolean isEmpty() 			  {return line.isEmpty() ? true : false;}

	
}
