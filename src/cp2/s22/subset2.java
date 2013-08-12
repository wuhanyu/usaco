package cp2.s22;
/*
ID: wuhanyu1
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;

class subset2{
	public static PrintWriter out;
	public static int N;
	public static int sum;
	public static int count = 0;
	public static boolean[] flag;
	public static void DFS(int n){
		if (sum == 0){
			count++;
			return;
		}
		for (int i = n + 1; i < N; i++){
			if (!flag[i]){
				flag[i] = true;
				sum -= (i+1);
				DFS(i);
				sum += (i+1);
				flag[i] = false;
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("subset.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    for (int i = 1; i <= N; i++){
	    	sum += i;
	    }
	    if (sum % 2 == 0){
	    	sum = sum / 2;
	    	flag = new boolean[N];
	    	DFS(-1);
	    }
	    out.println(count / 2);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}