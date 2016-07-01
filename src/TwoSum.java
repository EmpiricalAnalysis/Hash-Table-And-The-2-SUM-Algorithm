import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;


public class TwoSum {

	public static void main(String[] args) throws FileNotFoundException {
				
		Hashtable<Long, Long> set = new Hashtable<Long, Long>(1_999_993,(float)0.5);
		//Hashtable<Integer, Integer> set = new Hashtable<Integer, Integer>(1_999_993);
		
		long startTime = System.nanoTime(); 
	     
	    //read in numbers from file, put each number into hash table
		Scanner scanner = new Scanner(new File("100.txt"));			// algo1_programming_prob_2sum.txt			//100000.txt
		int count = 0;
		//boolean[] found_list = new boolean[20_001]; 
		Long next_Long;												//Integer next_Itgr;
		long[] list = new long[1_000_000];
		int i_count = 0;
		long diff;
		
		while(scanner.hasNextLong()) {								//hasNextInt()
			next_Long = (Long)scanner.nextLong();					//next_Itgr = (Integer)scanner.nextInt()
			//de-duplicate
			if (set.get(next_Long) == null) {
				list[i_count] = next_Long.longValue();
				set.put(next_Long, next_Long);
				i_count++;
			}
		}
		scanner.close();
		
		System.out.println("time for reading integers from file is: "+(System.nanoTime() - startTime)/Math.pow(10.,9.));

		System.out.println("Input "+(i_count+1)+" distinct integers");
		
		/*
		//test hashtable lookup time
		startTime = System.nanoTime();
        
		for (int i = 0; i < i_count; i++) {
			set.get((Long)list[i]);
		}
		
        System.out.println("time for lookup all elements in hashtable is: "+(System.nanoTime() - startTime)/Math.pow(10.,9.));
        */

		
		//System.out.println("Hashtable contains key "+155+" is "+set.get((Integer)(155)));
		
        startTime = System.nanoTime();
        
		//loop over t = [-10000, 10000], lookup t - x, where x is a number from set in sequence
		for (int t = -10_000; t <= 10_000; t++) {
			for (int i = 0; i < i_count; i++) {
				//if t-x exists
				diff = (Long)(t-list[i]);
				if (set.get(diff) != null) {
					//make sure that x and t-x are distinct numbers
					if ( t != 2*list[i]	) {
					    //increase count by 1
						count++;
						System.out.println(list[i]+" + "+diff+" = "+t);
						//found_list[t+10_000] = true;
						break;
					}
				
				}
				
			}
		}
		
		System.out.println("time for lookup all 2-sum pairs is: "+(System.nanoTime() - startTime)/Math.pow(10.,9.));

		//output count
	    System.out.println(count + " pairs of distinct 2-sums exists");

	}

}
