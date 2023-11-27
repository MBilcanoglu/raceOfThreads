package raceOfThreads;

import java.util.List;

public class OddNumberThread implements Runnable{
	
	List<Integer> divide;
	List<Integer> oddNumbers;
	
	public OddNumberThread(List<Integer> divide, List<Integer> oddNumbers) {
		super();
		this.divide = divide;
		this.oddNumbers = oddNumbers;
	}
	@Override
	public void run() {
		for(Integer number: divide) {
			if(number%2!=0) {
				oddNumbers.add(number);
			}
		}
	}
	
	

}
