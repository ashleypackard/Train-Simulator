package queues;

/**
 * 
 * @author Ashley Packard, Mike Kucharski
 *
 */

public class MyQueue<T> implements QueueInterface<T>
{
	// store queue elements in queueArray
	private T[] queueArray;
	// keep track of the back and front elements in the array
	// NOT the capacity
	private int frontIndex;
	private int backIndex;

	// default constructor, creates an empty queue array of zero capacity
	@SuppressWarnings("unchecked")
	public MyQueue()
	{
		queueArray = (T[]) new Object[0];
		
		// make front and back indexes negative 
		// since there are no elements in the array
		frontIndex = backIndex = -1;
	}
	
	// constructor, creates an empty queue of size specified
	@SuppressWarnings("unchecked")
	public MyQueue(int newSize)
	{
		queueArray = (T[]) new Object[newSize];
		
		// make front and back indexes negative regardless of capacity 
		// since there are no elements in the array
		frontIndex = backIndex = -1;
	}
	
	// accessor methods
	public int getBackIndex()   {return backIndex;}
	public int getFrontIndex()  {return frontIndex;}
	public int capacity()       {return queueArray.length;}
	public T getFront()         {return queueArray[frontIndex];}
	
	// tests if queue array is empty, returns true if the size is , regardless of capacity
	public boolean isEmpty() {return (size() == 0) ? true : false;}

	// sets queue array equal to an empty array of the same capacity
	// resets front and back indexes to negative
	@SuppressWarnings("unchecked")
	public void clear() 
	{
		int cap = capacity();
		queueArray = (T[]) new Object[cap];
		frontIndex = backIndex = -1;
	}
	
	// counts every element of the array that isn't null and return thats number
	public int size()
	{
		int size = 0;
		
		// walk through entire array
		for (int i = 0; i < capacity(); i++)
		{
			// if element has a value, increment our counter
			if (queueArray[i] != null)  {size++;}
		}
		
		return size;
	}
	
	// adds one element to the array, makes room for it if necessary
	public void enqueue(T newEntry)
	{		
		// double the array capacity if necessary, 
		// assume there is enough capacity past this point
		if(capacity() == 0 || (size() == capacity()) ) {doubleArray();}
		
		// if array is empty, add to the first element 
		// and update back and front indexes to 0
		if( isEmpty() )
		{
			frontIndex = backIndex = 0;
			queueArray[frontIndex] = newEntry;
			return;
		}
		// runs if there is room behind front index (circular)
		else if(frontIndex != 0 && (size() != capacity()) )
		{
			// if the back index is at the end of the array,
			// we know there is room in the front, so assign backIndex = 0;
			// and add the new element there
			if(backIndex+1 == capacity())
			{
				backIndex = 0;
				queueArray[backIndex] = newEntry;
				return;
			}
		}
		// defualt case, just increment back index and add the new element
		else{
			backIndex++;
			queueArray[backIndex] = newEntry;
			return;
		}
	}
	
	// if the capacity is full with elements, create a new array of double the capacity, 
	// copy every element from the old array into the new array, 
	// and set the new array equal to the old array
	@SuppressWarnings("unchecked")
	private void doubleArray() 
	{
		T[] tempArray;
		
		// if the array size is 0, make it 1 and adjust back and front indexes
		if(capacity() == 0)
		{
			tempArray = (T[]) new Object[1];
			frontIndex = backIndex = 0;
		}
		// if the array is larger than 
		else
		{
			// create a new array of double the capacity
			tempArray = (T[]) new Object[(capacity() * 2)];
	
			if(backIndex == frontIndex)
			{
				backIndex -= 1;
			}
			
			// if the array is already ordered by queue value, copy the values over in order
			if(frontIndex < backIndex)
			{
				for (int i = 0; i < size(); i++)
				{
					tempArray[i] = queueArray[i];
				}
			}
			// in the circular array case, copy the elements in queue order
			else if(frontIndex > backIndex)
			{
				// copy from front element to the end of the array
				int indexPos = 0;
				for(int x = frontIndex; x < capacity(); x++)
				{
					tempArray[indexPos] = queueArray[x];
					indexPos++;
				}
				
				// copy the elements from the first index of the array until the " backIndex " of our queue
				for(int y = 0; y <= backIndex; y++)
				{
					tempArray[indexPos] = queueArray[y];
					indexPos++;
				}
			}
			frontIndex = 0;
			backIndex = size()-1;
		}
		
		//set the new array equal to the old array
		queueArray = tempArray;
	}

	// removes front index and returns it's value
	public T dequeue()
	{
		// return a null if the queue is empty
		if(isEmpty() ) {return null;}
		
		// store the value at the front index to return later
		T temp = queueArray[frontIndex];
		// delete front index
		queueArray[frontIndex] = null;
		
		// reajust frontIndex position
		frontIndex = (frontIndex == capacity()) ? 0 : frontIndex+1;
		
		return temp;
	}
	
	// used for testing purposes, cycle through the entire 
	// array and print out every element. Prints out nulls as well
	public String toString()
	{
		String printThisOut = "";
		
		// if the array is empty, return a message saying so
		if(isEmpty()) {return "Contents Empty!\n";}
		
		// don't directly print out from here, 
		// just add to the end of the string and return it
		for (int i = 0; i < capacity(); i++)
		{
			printThisOut += i + ": " + queueArray[i] + "\n";
		}
		
		return printThisOut;
	}

	
	
}
