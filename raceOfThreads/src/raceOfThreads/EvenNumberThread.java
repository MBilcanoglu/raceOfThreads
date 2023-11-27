package raceOfThreads;

import java.util.List;

public class EvenNumberThread implements Runnable{
	
	List<Integer> divide;
	List<Integer> evenNumbers;
	
	
	public EvenNumberThread(List<Integer> divide, List<Integer> evenNumbers) {
		super();
		this.divide = divide;
		this.evenNumbers = evenNumbers;
	}


	@Override
	public void run() {
		for(Integer number: divide) {
			if(number%2==0){
				evenNumbers.add(number);
			}
		}
	}

}
