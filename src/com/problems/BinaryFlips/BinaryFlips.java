package com.problems.BinaryFlips;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.StdIn;

public class BinaryFlips {
	static int N, max;
	static int b[];
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("D:\\eclipse workspace\\Algorithm\\src\\com\\problems\\BinaryFlips\\input"));
		N = StdIn.readInt();
		b = new int[N];
		for (int i = 0; i < N; i++)	
			b[i] = StdIn.readInt();
		
		System.out.print("\nInput --> "); for (int i = 0; i < N; i++) System.out.print(b[i] + " ");
		System.out.println("\nbruteforce --> " + brute() );
	}
	
	/*
	 *  Brute force approach
	 *  Time  : O(n^3)
	 *  Space : O(n)      
	 */
	private static int brute() {
		for (int L = 0; L < N-1; L++) { 
			for(int R = L + 1; R < N; R++){
				//flip(L,R);
				int c = 0;
				for (int i = 0; i < L; i++)	if(b[i] == 1) c++;
				for (int i = L; i <= R; i++) if(b[i] == 0) c++;
				for (int i = R+1; i < N; i++) if(b[i] == 1) c++;
				max = (c > max) ? c : max;
				
			}
		}
		return max;
	}

	private static int fast() {
			
		return max;
	}

}
