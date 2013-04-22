package trainSimulator;

/**
 * 
 * @author Michael Kucharski, Ashley Packard
 *
 */

import java.util.Random;

public class TrainRoute {

	// max passengers that can be generated or "served" per day
	private final int MAX_PASSENGERS = 100;
	// used to keep track of the number of the number of passengers served so far
	private int servedSoFar;
	// a list of all the possible stations for the train to stop at
	private final Station[] route = {
			new Station(0, true), new Station(1), new Station(2), new Station(3), new Station(4),
	};
	private Train ChooChooTrain;
	private Random generator;
	
	// default constructor
	public TrainRoute()
	{
		servedSoFar = 0;
		generator = new Random();
		ChooChooTrain = new Train(route[0]);
	}
	
	// runs the train simulation
	public void Run()
	{
		// train simulator loop runs until max passengers have been generated, all passengers have been
		// picked up and brought to their destinations, and the train returns to the terminal station
		while( !(servedSoFar == MAX_PASSENGERS && isAllStationsEmpty() 
				&& ChooChooTrain.getStation().isTerminal() && ChooChooTrain.isEmpty() ) )
		{
			// move the train to its new destination
			moveTrain();
			
			// print out stats about the new station we arrived at
			System.out.println("Arrived at station: " + ChooChooTrain.getStation().getStationNumber());
			System.out.println("Passengers Served Today: " + servedSoFar);
			
			// print out the number of people at each train station
			System.out.println("+--------Passengers in Line at Each Station---------+");
			for(int i = 0; i < route.length; i++)
			{
					System.out.print("Stat" + i + ": " + route[i].getLineSize() + "  ");
			}
			System.out.println("\n+---------------------------------------------------+");
			
			// let the passengers off the train, print how many got off
			int passLetOff = ChooChooTrain.letOffPassegers();
			System.out.println("Let off " + passLetOff + " passenger(s).");
			
			// pick up all passengers waiting in line from the station
			pickUpPassengers();
			
			// print out the number of passengers currently on the train
			System.out.println("Passengers on the train: " + ChooChooTrain.passengersOnTrain() + "\n");
			
			// if we haven't reached out maximum passenger count for the day, generate more passengers at stations
			if(servedSoFar != MAX_PASSENGERS)	{generatePassengers();}
		}
		
		System.out.println("***Train Simulation is over!\nAll 100 passengers have been transported, \n" +
				"there are no more people on the train,\nand there are no more people waiting in line.***");
	}
	
	// add every passenger from this stations line to the train
	private void pickUpPassengers()
	{
		Station currentStation = route[ChooChooTrain.getStation().getStationNumber()];
		
		int lineSize = currentStation.getLineSize();
		
		for(int i = 0; i < lineSize; i++)
		{
			Passenger inQuestion = currentStation.getNextPassenger();
			System.out.println("Picked up person with destination station: " + inQuestion.getEndingStation());
			ChooChooTrain.addPassenger(inQuestion);
		}
	}
	
	// test if all stations are empty
	private boolean isAllStationsEmpty() 
	{
		for(Station s : route){
			if(!s.isEmpty() )  {return false;}
		}
		return true;
	}

	// randomly generates passengers 
	private void generatePassengers()
	{
		// calculate the number of passengers left to generate for the day
		int passengersLeft;
		if((MAX_PASSENGERS - servedSoFar)/2 < 1){
			passengersLeft = MAX_PASSENGERS - servedSoFar;
		}else{
			passengersLeft = (MAX_PASSENGERS - servedSoFar)/2;
		}
	
		// pick a random number out of how many passengers are left, and generate that many
		// Also, generate each passenger with a random destination and starting station
		int passengersToGenerate = generator.nextInt(passengersLeft);
		for(int i = 0; i <= passengersToGenerate; i++)
		{
			// generate random passenger info
			String passName = "Passenger_" + i;
			int startLoc = generator.nextInt(route.length);
			int finalDest = generator.nextInt(route.length);
			// create the new passenger and add them to their station
			Passenger p = new Passenger(passName, startLoc, finalDest);
			route[p.getStartingStation()].addPassenger(p);
			
			//increment the number of passengers served today
			servedSoFar++;
		}
	}
	
	// when the train is at the "last" station of the route, circle him around to the first station,
	// otherwise increment the station by one in the route array. This replicates a circular track.
	private void moveTrain()
	{
		if(ChooChooTrain.getStation() == route[route.length-1]){
			ChooChooTrain.setStation(route[0]);
		}else{
			int pos = -1;
			for(int i = 0; i < route.length; i++){
				if(ChooChooTrain.getStation() == route[i]){
					pos = i;
				}
			}
			pos++;
			ChooChooTrain.setStation(route[pos]);
		}
	}

}
