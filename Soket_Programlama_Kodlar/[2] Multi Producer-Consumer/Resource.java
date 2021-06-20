
import java.util.*;



class Resource
{
	Stack<Integer> stack;
	private int numResources;
	private final int MAX = 5;

		
	public Resource(int startLevel)
	{
		numResources = startLevel;
		stack 					= new Stack<Integer>();	
	}
	
	public int getLevel()
	{
		return numResources;
	}
	
	public synchronized void addOne()
	{
		
		try
		{
			while (numResources >= MAX)	wait();
			
			numResources++;	
			
			
			int random=(int)(Math.random()*100);
			stack.push(random);
			
			System.out.println("PUSHED ITEM = " + random);			
			if(numResources == MAX) System.out.println("STACK IS FULL");
			//'Wake up' any waiting consumer...
			notifyAll();
		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
	}
	
	public synchronized int takeOne()
	{
		
		try
		{			
			while (numResources == 0) wait();
			
			
			
			numResources--;
			System.out.println("POPED ITEM = " + stack.peek());
			
			if(numResources == 0) System.out.println("STACK IS EMPTY");
			//'Wake up' waiting producer...
			notify();
		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
		return stack.pop();
	}
}