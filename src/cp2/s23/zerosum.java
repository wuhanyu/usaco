package cp2.s23;
/*
ID: wuhanyu1
LANG: JAVA
TASK: zerosum
*/
import java.io.*;
import java.util.*;

class zerosum{
	public static PrintWriter out;
	public static int N;
	public static int sum, lastNum, lastFactor;
	public static int[] result;
	public static char[] opr = {'+', '-', ' '};
	
	
	public static void printResult(){
		for (int i = 1; i < N; i++){
			out.print(i + "" + opr[result[i+1]]);
		}
		out.println(N);
	}
	public static void DFS(int n){
		if (n > N){
			if (sum == 0){
				printResult();
			}
		} else {
			int old = lastNum;
			int oldF = lastFactor;
			int oldsum = sum;
			
			sum -= old * oldF;
			lastNum *= 10;
			lastNum += n;
			sum += lastNum * oldF;
			result[n] = 2;
			DFS(n+1);
			lastNum = old;
			lastFactor = oldF;
			sum = oldsum;			
		
			sum = oldsum + n;
			result[n] = 0;
			lastNum = n;
			lastFactor = 1;
			DFS(n+1);
			lastNum = old;
			lastFactor = oldF;
			sum = oldsum;
			
			sum = oldsum - n;
			lastNum = n;
			lastFactor = -1;
			result[n] = 1;
			DFS(n+1);
			lastNum = old;
			lastFactor = oldF;
			sum = oldsum;
			
			

		}
	}

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    result = new int[N+1];
	    sum = 1;
	    lastNum = 1;
	    lastFactor = 1;
	    DFS(2);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}