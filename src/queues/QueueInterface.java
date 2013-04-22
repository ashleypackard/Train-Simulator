package queues;

/**
 * 
 * @author Ashley Packard, Mike Kucharski
 *
 */

public interface QueueInterface <T>
{
	// adds one element to the back of the queue
	public void enqueue(T newEntry);
	// removes the front element and returns it
	public T dequeue();
	// returns the front element of the queue
	// but does not return it
	public T getFront();
	// returns true if there are no elements in the queue, returns false otherwise
	public boolean isEmpty();
	// removes every element of the queue
	public void clear();
	
}
