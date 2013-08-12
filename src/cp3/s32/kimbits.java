package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: kimbits
*/
import java.io.*;
import java.util.*;

public class kimbits{
	public static PrintWriter out;
	public static long I;
	public static int N, L;
	public static int count;
	public static int[] list;
	
	public static void DFS(int n){
		if (L < 0) return;
		if (n == N){
			count++;
			if (count == I){
				for (int i = 0; i < N; i++){
					out.print(list[i]);
				}
				out.println();
			    out.close(); 
                
			    System.exit(0); 
			}
		} else {
			list[n] = 0;
			DFS(n+1);
			L--;
			list[n] = 1;
			DFS(n+1);
			L++;
		}
	}
	
	public static long getInteger(){
		long result = 1L;
		long mul = 1L;
		for (int i = N - 1; i >= 0; i--){
			result += mul * list[i];
			mul *= 2;
		}
		return result;
	}
	
	public static void DFS2(int n){
		if (N - n <= L) return;
		if (n == N){
			long num = getInteger();
			if (num >= I){
				System.out.println(I);
				System.out.println(count);
				int[] result = new int[N];
				int i = N - 1;
				I = I - 1 + count;
		    	while (I > 0){
		    		result[i] = (int) (I % 2);
		    		I /= 2;
		    		i--;
		    	}
		    	for (i = 0; i < N; i++) out.print(result[i]);
		    	out.println();
			    out.close(); 
                
			    System.exit(0); 				
			}
			count++;
		} else {
			list[n] = 0;
			DFS2(n+1);
			L--;
			list[n] = 1;
			DFS2(n+1);
			L++;			
		}
	}

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());	    
	    N = Integer.parseInt(st.nextToken());
	    L = Integer.parseInt(st.nextToken());
	    I = Long.parseLong(st.nextToken());
	    list = new int[N];
	    if (L <= N / 2){
	    	DFS(0);
	    } else {
	    	DFS2(0);
	    	System.out.println("!");
			System.out.println(I);
			System.out.println(count);
			int[] result = new int[N];
			int i = N - 1;
			I = I - 1 + count;
	    	while (I > 0){
	    		result[i] = (int) (I % 2);
	    		I /= 2;
	    		i--;
	    	}
	    	for (i = 0; i < N; i++) out.print(result[i]);
	    	out.println();
		    out.close(); 
            
		    System.exit(0); 
	    }

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}
