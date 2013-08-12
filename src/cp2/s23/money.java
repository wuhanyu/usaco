package cp2.s23;

/*
ID: wuhanyu1
LANG: JAVA
TASK: money
*/
import java.io.*;
import java.util.*;

class money{
	public static PrintWriter out;
	public static int V, N;
	public static int[] coin;
	public static long[] result;
	public static int count = 0;

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("money.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    V = Integer.parseInt(st.nextToken());
	    N = Integer.parseInt(st.nextToken());
	    coin = new int[V];
	    while (count < V){
		    st = new StringTokenizer(f.readLine());
		    while (st.hasMoreElements()){
		    	coin[count] = Integer.parseInt(st.nextToken());
		    	count++;
		    }
	    }
	    
	    result = new long[N+1];
	    result[0] = 1L;
	    for (int i = 0; i < V; i++){
	    	for (int j = coin[i]; j <= N; j++){
	    		result[j] += result[j-coin[i]];
	    	}
	    }
	    out.println(result[N]);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}