package cp3.s31;
/*
ID: wuhanyu1
LANG: JAVA
TASK: stamps
*/
import java.io.*;
import java.util.*;

public class stamps{
	public static PrintWriter out;
	public static int N, K;
	public static final int MAX = 2100000;
	public static int[] coins;

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("stamps.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    K = Integer.parseInt(st.nextToken());
	    N = Integer.parseInt(st.nextToken());
	    coins = new int[N];
	    int count = 0;
	    while (count < N){
	    	st = new StringTokenizer(f.readLine());
	    	while (st.hasMoreElements()){
	    		coins[count] = Integer.parseInt(st.nextToken());
	    		count++;
	    	}
	    }
	    boolean[] flag = new boolean[MAX];
	    int[] left = new int[MAX];
	    flag[0] = true;
	    left[0] = K;
	    int i = 0;
	    while (flag[i] && left[i] >= 0){
	    	for (int j = 0; j < N; j++){
	    		if (i + coins[j] > MAX) break;
	    		if (!flag[i + coins[j]] || left[i] - 1 > left[i + coins[j]]){
	    			flag[i + coins[j]] = true;
	    			left[i + coins[j]] = left[i] - 1;
	    		}
	    	}
	    	i++;
	    }
	    out.println(i-1);

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

