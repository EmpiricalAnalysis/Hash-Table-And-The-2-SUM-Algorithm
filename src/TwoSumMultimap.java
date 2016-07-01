import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Enumeration;

import java.util.Scanner;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class TwoSumMultimap {

	public static void main(String[] args) throws FileNotFoundException {
						
		Multimap<Long, Long> set = HashMultimap.create();
		
		long startTime = System.nanoTime(); 
	     
	    //read in numbers from file, put each number into hash table
		Scanner scanner = new Scanner(new File("algo1_programming_prob_2sum.txt"));			// algo1_programming_prob_2sum.txt			//100000.txt			//2sum_test1M.txt
		int count = 0;
		boolean[] found_list = new boolean[20_001]; 
		Long next_Long;												//Integer next_Itgr;
		long[] list = new long[1_000_000];
		int i_count = 0;
		
		while(scanner.hasNextLong()) {								//hasNextInt()
			next_Long = (Long)scanner.nextLong();					//next_Itgr = (Integer)scanner.nextInt()
			//de-duplicate
			if (!set.get(next_Long).contains(next_Long) ) {
				list[i_count] = next_Long.longValue();
				set.put(next_Long/10_000, next_Long);
				i_count++;
			}
		}
		scanner.close();
		
		System.out.println("time for reading integers from file is: "+(System.nanoTime() - startTime)/Math.pow(10.,9.));

		System.out.println("Input "+(i_count+1)+" distinct integers");
		
		/*
		System.out.println(775215/10_000);
		System.out.println(-782501/10_000 );
		
		System.out.println(set.containsValue((Long)(long)775215));
		System.out.println(set.containsValue((Long)(long)-782501));
		*/
		
		
        startTime = System.nanoTime();
        
        long y;
        Enumeration<Long> longs;
        
		//for each integer x in file
		for (int i = 0; i < i_count; i++) {
			//find corresponding down-sized bucket
			long key = list[i]/10_000;
			for (long key_comp = -key -1; key_comp <= -key + 1; key_comp++) {
				//check if complement bucket contains value 
				if (set.get((Long)key_comp).size() != 0) {
					longs = Collections.enumeration(set.get((Long)key_comp));
					while(longs.hasMoreElements()) {
						y = longs.nextElement();
				        if (Math.abs((list[i] + y)) <= 10_000 ) {
				        	//make sure that x and t-x are distinct numbers
				        	if ( list[i] != y) {
				        		found_list[(int) (list[i]+y+10_000)] = true;
							}
								
						}

				     }
						
				}	
			}

		}
		
		System.out.println("time for lookup all 2-sum pairs is: "+(System.nanoTime() - startTime)/Math.pow(10.,9.));

		//output count

		for (int i = 0; i < 20_001; i++) {
			count += found_list[i]? 1:0;
		}


	    System.out.println(count + " pairs of distinct 2-sums exists");

	}

}
