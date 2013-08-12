package cp2.s22;
/*
ID: wuhanyu1
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;

class subset{
	public static PrintWriter out;
	public static int N;
	public static int sum;
	public static int count = 0;
	public static int[][] dp;
	
	public static int getSum(int n){
		return n * (n + 1) / 2;
	}
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("subset.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    sum = getSum(N);
	    dp = new int[N + 1][sum + 1];
	    dp[0][0] = 0;
	    if (sum % 2 == 0){
	    	for (int i = 1; i <= N; i++){
	    		int tsum = getSum(i);
	    		dp[i][0] = 1;
	    		System.out.println(i);
	    		for (int j = 1; j < tsum; j++){
	    			if (j >= i){
	    				dp[i][j] = dp[i-1][j] + dp[i-1][j-i];
	    			} else {
	    				dp[i][j] = dp[i-1][j];
	    			}
	    		}
	    	}
	    	out.println(dp[N][sum / 2]);
	    } else {
	    	out.println(0);
	    }
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}