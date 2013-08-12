package cp2.s23;
/*
ID: wuhanyu1
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

public class concom{
	public static PrintWriter out;
	public static int N;
	public static int[][] control;
	public static boolean[][] flag;
	
	public static void add(int i, int j){
		for (int s = 0; s < 1000; s++){
			if (control[j][s] > 0){
				control[i][s] += control[j][s];
				controls(i, s);
			}
		}		
	}
	
	public static void controls(int i, int j){
		if (control[i][j] > 50 && !flag[i][j]){
			flag[i][j] = true;
			add(i, j);
		}	
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("concom.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    control = new int[1000][1000];
	    flag = new boolean[1000][1000];
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	int mother = Integer.parseInt(st.nextToken());
	    	int child = Integer.parseInt(st.nextToken());
	    	int rate = Integer.parseInt(st.nextToken());
	    	control[mother][child] = rate;
	    }
	    for (int i = 0; i < 1000; i++){
	    	for (int j = 0; j < 1000; j++){
	    		controls(i, j);
	    	}
	    }
	    
	    for (int i = 0; i < 1000; i++){
	    	for (int j = 0; j < 1000; j++){
	    		if (flag[i][j] && i != j) out.println(i + " " + j);
	    	}
	    }

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

