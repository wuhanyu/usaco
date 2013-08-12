package cp2.s21;
/*
ID: wuhanyu1
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

class hamming{
	public static PrintWriter out;
	public static boolean[] flag;
	public static int N, B, D;
	public static int[] result;
	public static int count;
	
	public static int hammingDis(int a, int b){
		int count = 0;
		for (int i = 0; i < B; i++){
			int ad = a % 2;
			int bd = b % 2;
			if (ad != bd) count++;
			a = a / 2;
			b = b / 2;
		}
		return count;
	}
	
	public static boolean isSatisfied(int n){
		if (flag[n]) return false;
		for (int i = 0; i < count; i++){
			if (hammingDis(result[i], n) < D){
				flag[n] = true;
				return false;
			}
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("hamming.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    B = Integer.parseInt(st.nextToken());
	    D = Integer.parseInt(st.nextToken());
	    
	    int upper = (int) Math.pow(2, B);
	    flag = new boolean[upper];
	    result = new int[N];
	    count = 0;
	    for (int i = 0; i < upper; i++){
	    	if (count==0 || isSatisfied(i)){
	    		result[count] = i;
	    		count++;
	    		out.print(i);
	    		if (count == N){
	    			out.println();
	    			break;
	    		}
	    		if (count % 10 != 0){
	    			out.print(" ");
	    		} else {
	    			out.println();
	    		}
	    	}
	    }
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}