package queues;

/**
 * 
 * @author Ashley Packard, Mike Kucharski
 *
 */

public class QueueTester 
{
	public static void main(String[] args) 
	{
		// create a new instance of an empty queue
		MyQueue<Integer> myQueue = new MyQueue<Integer>();
		
		// test enqueue method
		for(int i = 1; i < 5; i++)
			myQueue.enqueue(i);
		printQueueWithStats(myQueue);
		
		// test isEmpty and getFront methods
		System.out.println("Is the queue empty? " + myQueue.isEmpty()
				+ "\nThe front element of the queue is: " + myQueue.getFront() + "\n");
		
		// test dequeue method
		myQueue.dequeue();
		printQueueWithStats(myQueue);
		
		// test circularness of array
		myQueue.enqueue(55);
		printQueueWithStats(myQueue);
		
		// tests that the capacity doubles when necessary
		// and that the array contents get reordered
		myQueue.enqueue(138);
		printQueueWithStats(myQueue);
		
		// test clear method
		myQueue.clear();
		printQueueWithStats(myQueue);
		
		// enqueue some more
		myQueue.enqueue(138);
		myQueue.enqueue(1000);
		printQueueWithStats(myQueue);
		
		// create a new instance of our queue to test initial capacity
		MyQueue<Integer> myQueue2 = new MyQueue<Integer>(3);
		
		System.out.println("**Now printing out second Queue");
		printQueueWithStats(myQueue2);
		myQueue2.enqueue(100);
		printQueueWithStats(myQueue2);
	}

	// Print out the statistics and the queue contents for testing purposes
	// NOTE: Prints out the entire queue array contents, not the queue in order
	@SuppressWarnings("rawtypes")
	public static void printQueueWithStats(MyQueue inputQueue)
	{
		System.out.println("Size: " + inputQueue.size() + 
				" | Cap: " + inputQueue.capacity() +
				" | FI: " + inputQueue.getFrontIndex() + 
				" | BI: " + inputQueue.getBackIndex());
		System.out.println("The queue contains: \n" + inputQueue);
	}
}
