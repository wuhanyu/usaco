package cp2.s23;
/*
ID: wuhanyu1
LANG: JAVA
TASK: nocows
*/
import java.io.*;
import java.util.*;

class nocows{
	public static PrintWriter out;
	public static int N, K;
	public static int[][] dp, ss;
	public static int count;
	public static final int MOD = 9901;

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("nocows.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    dp = new int[K + 1][N + 1];
	    ss = new int[K + 1][N + 1];
	    dp[1][1] = 1;
	    for (int i = 2; i <= K; i++){
	    	for (int j = 1; j <= N; j++){
	    		for (int s = 1; s < j; s++){
	    			dp[i][j] += ss[i-2][s] * dp[i-1][j-s-1];
	    			dp[i][j] += dp[i-1][s] * ss[i-2][j-s-1];
	    			dp[i][j] += dp[i-1][s] * dp[i-1][j-s-1];
	    			dp[i][j] %= MOD;
	    		}
	    		ss[i-1][j] = (ss[i-2][j] + dp[i-1][j]) % MOD;
	    	}	
	    }
	    out.println(dp[K][N]);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}