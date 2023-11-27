package raceOfThreads;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> evenNumbers=new ArrayList<Integer>();
		ArrayList<Integer> oddNumbers=new ArrayList<Integer>();
		ArrayList<Integer> listOfNumbers=new ArrayList<Integer>();
		
		for(int i=1; i<=10000; i++) {
			listOfNumbers.add(i);
		}
		
		List<List<Integer>> divideLists=divideArrayList(listOfNumbers,4);
		
        List<Thread> threads = new ArrayList<>();


     // ExecutorService oluştur
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        // Her bir parçadaki çift ve tek sayıları bulan ve ortak ArrayList'lere ekleyen Task'leri oluştur
        for (List<Integer> divide : divideLists) {
            executorService.submit(new EvenNumberThread(divide, evenNumbers));
            executorService.submit(new OddNumberThread(divide, oddNumbers));
        }

		
        // ExecutorService kapat
        executorService.shutdown();

        try {
            // Tüm Task'lerin bitmesini bekleyerek devam et
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
     // Ortak çift ve tek sayıları içeren ArrayList'leri ekrana yazdır
        System.out.println("Çift Sayılar: " + evenNumbers);
        System.out.println("Tek Sayılar: " + oddNumbers);
        
		for(int i=0; i<divideLists.size(); i++) {
			System.out.println("Parça - "+(i+1)+": "+divideLists.get(i));
		}
	}
	
	private static List<List<Integer>> divideArrayList(List<Integer> listOfArray, int numberOfPieces){
		int numberOfElements=listOfArray.size();
		int lengthOfPiece=numberOfElements/numberOfPieces;
		int startIndex=0;
		
		List<List<Integer>> divideLists=new ArrayList<>();
		
		for(int i=0; i<numberOfPieces; i++) {
			int finishIndex=Math.min(startIndex+lengthOfPiece, numberOfElements);
			List<Integer> piece=new ArrayList<Integer>(listOfArray.subList(startIndex, finishIndex));
			divideLists.add(piece);
			startIndex=finishIndex;
		}
		return divideLists;
	}

}
