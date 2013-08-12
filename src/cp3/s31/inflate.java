package cp3.s31;
/*
ID: wuhanyu1
LANG: JAVA
TASK: inflate
*/
import java.io.*;
import java.util.*;

public class inflate{
	public static PrintWriter out;
	public static int M, N;
	public static int[] bag;
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("inflate.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    M = Integer.parseInt(st.nextToken());
	    N = Integer.parseInt(st.nextToken());
	    bag = new int[M + 1];
	    for (int i = 0; i < N ; i++){
	    	st = new StringTokenizer(f.readLine());
	    	int value = Integer.parseInt(st.nextToken());
	    	int size = Integer.parseInt(st.nextToken());
	    	for (int j = size; j <= M; j++){
	    		if (bag[j] < bag[j-size] + value) bag[j] = bag[j-size] + value;
	    	}
	    }
	    
	    out.println(bag[M]);

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

