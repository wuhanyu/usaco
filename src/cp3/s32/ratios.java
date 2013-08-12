package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: ratios
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class ratios{
	public static PrintWriter out;
	public static int[][] factor = new int[3][3];
	public static int[] goal = new int[3];
	public static int[] result = new int[4];
	public static int[] mul = new int[3];
	public static int min_count = Integer.MAX_VALUE;
	
	public static int isResult(){
		int[] tmp = new int[3];
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				tmp[j] += factor[i][j] * mul[i];
		}
		for (int i = 0; i < 3; i++)
			if (goal[i] != 0 && tmp[i] % goal[i] != 0) return 0;
		int m = tmp[0] / goal[0];
		for (int i = 1; i < 3; i++)
			if (goal[i] != 0){
				if (tmp[i] / goal[i] != m) return 0;
			} else {
				if (tmp[i] != 0) return 0;
			}
		return m;
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("ratios.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
	    for (int i = 0; i < 4; i++){
	    	StringTokenizer st = new StringTokenizer(f.readLine());
	    	if (i == 0){
	    		for (int j = 0; j < 3; j++)
	    			goal[j] = Integer.parseInt(st.nextToken());
	    	} else {
	    		for (int j = 0; j < 3; j++)
	    			factor[i-1][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    for (int i = 0; i < 100; i++){
	    	if (i >= min_count) break;
	    	for (int j = 0; j < 100; j++){
	    		if (i + j >= min_count) break;
	    		for (int k = 0; k < 100; k++){
	    			int n = i + j + k;
	    			if (n >= min_count) break;
	    			mul[0] = i;
	    			mul[1] = j;
	    			mul[2] = k;
	    			int m = isResult();
	    			if (m > 0){
	    				min_count = n;
	    				for (int s = 0; s < 3; s++) result[s] = mul[s];
	    				result[3] = m;	    				
	    			}
	    		}
	    	}
	    }
	    System.out.println(Arrays.toString(result));
	    if (result[3] > 0){
		    for (int i = 0; i < 3; i++)
		    	out.print(result[i] + " ");
		    out.println(result[3]);
	    } else {
	    	out.println("NONE");
	    }
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}
