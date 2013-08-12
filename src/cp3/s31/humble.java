package cp3.s31;
/*
ID: wuhanyu1
LANG: JAVA
TASK: humble
*/
import java.io.*;
import java.util.*;

public class humble{
	public static PrintWriter out;
	public static int K, N;
	public static int[] primes;
	public static int[] p_index;
	public static long[] list = new long[100001];
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("humble.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    K = Integer.parseInt(st.nextToken());
	    N = Integer.parseInt(st.nextToken());
	    st = new StringTokenizer(f.readLine());
	    primes = new int[K];
	    for (int i = 0; i < K; i++){
	    	primes[i] = Integer.parseInt(st.nextToken());
	    }
	    list[0] = 1;
	    int count = 0;
	    int min_p = 0;
	    long min;
	    p_index = new int[K];
	    while (count < N){
	    	long old = list[count];
	    	count++;
	    	min = Long.MAX_VALUE;
	    	for (int i = 0; i < K; i++){
	    		for (int p = p_index[i]; p < count; p++){
	    			long newN = list[p] * primes[i];
	    			if (newN > old){
	    				if (min > newN){
	    					min = newN;
	    				}
	    				p_index[i] = p;
	    				break;
	    			}
	    		}
	    	}
	    	list[count] = min;
	    }
	    out.println(list[count]);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

