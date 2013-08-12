package cp2.s21;
/*
ID: wuhanyu1
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;

class sort3{
	public static int N;
	public static int[] data;
	public static int count1 = 0;
	public static int count2 = 0;
	
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("sort3.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    data = new int[N];
	    int count = 0;
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	data[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    for (int i = 0; i < N; i++){
	    	if (data[i] == 1) count1++;
	    	if (data[i] == 2) count2++;
	    }
	    int num[] = {0,0};
	    for (int i = 0; i < count1; i++){
	    	if (data[i] == 2) num[0]++;
	    	if (data[i] == 3) num[1]++;
	    }
	    int cnum[] = {0,0};
	    for (int i = count1; i < count1 + count2; i++){
	    	if (data[i] == 1) cnum[0]++;
	    }
	    for (int i = count1 + count2; i < N; i++){
	    	if (data[i] == 1) cnum[1]++;
	    }
	    count += Math.min(num[0], cnum[0]) + Math.min(num[1], cnum[1]);
	    count += Math.abs(num[0] - cnum[0]) * 2;
	    num[0] = 0;
	    cnum[0] = 0;
	    for (int i = count1; i < count1 + count2; i++){
	    	if (data[i] == 3) num[0]++;
	    }
	    for (int i = count1 + count2; i < N; i++){
	    	if (data[i] == 2) cnum[0]++;
	    }
	    count += Math.min(num[0], cnum[0]);
	    out.println(count);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}