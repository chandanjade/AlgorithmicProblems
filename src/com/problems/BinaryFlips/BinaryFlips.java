package com.problems.BinaryFlips;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.StdIn;

public class BinaryFlips {
	static int N;
	static int[] b;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/com/problems/BinaryFlips/input"));
		
		/*
		N = StdIn.readInt();
		b = new int[N];
		for (int i = 0; i < N; i++)	
			b[i] = StdIn.readInt();
		*/
		
		N = (int)(Math.random() * 1000);
		System.out.println("N = " + N);
		b = new int[N];
		System.out.print("Random bit string --> ");
		for (int i = 0; i < N; i++){
			b[i] = (int)(Math.random() * 10) > 5 ? 1 : 0;
			System.out.print(b[i] + " ");
		}
		
		System.out.println("\nbrute \t--> " + brute() );
		System.out.println("fast \t--> " + fast() );
	}
	
	/*
	 *  Brute force approach
	 *  Time  : O(n^3)
	 *  Space : O(n)      
	 */
	private static int brute() {
		int max = 0, count = 0;
		for (int L = 0; L < N-1; L++) { 
			for(int R = L; R < N; R++){
				//flip(L,R);
				int c = 0;
				for (int i = 0; i < L; i++)	if(b[i] == 1) c++;
				for (int i = L; i <= R; i++) if(b[i] == 0) c++;
				for (int i = R+1; i < N; i++) if(b[i] == 1) c++;
				max = (c > max) ? c : max;
			}
			
		}

		// fix for case - Input contains all 1's : no flip is required 
		for (int i = 0; i < N; i++) if(b[i]==1) count++;
		if(count>max) max = count; 
		
		return max;
	}

	/*
	 * Dynamic Programming
	 * time : O(n)
	 * Space: O(n)
	 */
	private static int fast() {
		int[] c = new int[N];
		int max = 0, maxI = 0, L, R;
		c[0] = b[0] == 0 ? 1 : 0;
		for(int i = 1; i < N; i++) {
			if(b[i] == 0)
				c[i] = Math.max(c[i-1] + 1, 1 );
			else
				c[i] = c[i-1] - 1;

			if(c[i] > max) { max = c[i]; maxI = i; }
		}
		if(c[0] > max){ max = c[0]; maxI = 0; } 

		//System.out.print("\n i \t --> "); for (int i = 0; i < N; i++) System.out.format("%3d", i);
		//System.out.print("\n b \t --> "); for (int i = 0; i < N; i++) System.out.format("%3d", b[i]);
		//System.out.print("\n c \t --> "); for (int i = 0; i < N; i++) System.out.format("%3d", c[i]);
		//System.out.println("\nmax \t --> " + max + " \nmaxI \t --> " + maxI);
		R = maxI;
		
		int count = 0;
		
		//count 1's to the right side of R 
		for(int i = R + 1; i < N; i++ ) if(b[i] == 1) count++;
		
		//determine L & count 0's between L & R inclusive.
		int j = R;
		while(j >=0 && c[j] > 0) {
			if(b[j--] == 0) count++; 
		}
		L = j + 1;
		
		//count 1's to the left side of L.
		for(int i = L - 1; i >= 0; i-- ) if(b[i] == 1) count++;

		//System.out.println("\n choice of (L,R) --> (" + L + "," + R+")");
		return count;
	}

}
