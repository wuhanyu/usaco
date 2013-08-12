package cp1.s15;
/*
ID: wuhanyu1
LANG: JAVA
TASK: checker
*/
import java.io.*;
import java.util.*;

class checker{
	public static boolean[] x, c1, c2;
	public static int[] result;
	public static int N;
	public static int count;
	public static PrintWriter out;
	
	public static void DFS(int j){
		if (j == N){
			count++;
			if (count > 3) return;
			for (int i = 0; i < N - 1; i++){
				out.print((result[i]+1) + " ");
			}
			out.println(result[N-1]+1);
		} else {
			for (int i = 0; i < N; i++){
				if (x[i] || c1[N - 1 - i + j] || c2[i + j]) continue;
		    	x[i] = true;
		    	c1[N - 1 - i + j] = true;
		    	c2[i + j] = true;
		    	result[j] = i;
		    	DFS(j+1);
		    	x[i] = false;
		    	c1[N - 1 - i + j] = false;
		    	c2[i + j] = false;
			}
		}
	}
		

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("checker.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken()); 
	    x = new boolean[N];
	    c1 = new boolean[N * 2 - 1];
	    c2 = new boolean[N * 2 - 1];
	    result = new int[N];
	    count = 0;
	    for (int i = 0; i < N; i++){
	    	x[i] = true;
	    	c1[N - 1 - i] = true;
	    	c2[i] = true;
	    	result[0] = i;
	    	DFS(1);
	    	x[i] = false;
	    	c1[N - 1 - i] = false;
	    	c2[i] = false;
	    }
	    out.println(count);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}