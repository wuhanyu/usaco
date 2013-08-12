package cp1.s14;
/*
ID: wuhanyu1
LANG: JAVA
TASK: clocks
*/
import java.io.*;
import java.util.*;

class clocks{
	public static long N = 30L;
	public static long[] clocks = new long[9];
	public static long clocksKey;
	public static int p = 0;
	public static int[] answer = new int[30];
	public static int[][] answers = new int[10000][31];
	public static long[] moves = new long[9];
	public static void printClock(long[] clocks){
	    for (int i = 0; i < 3; i++){
	    	for (int j = 0; j < 3; j++){
	    		System.out.print(clocks[i * 3 + j] + " ");
	    	}
	    	System.out.println();
	    }	 		
	}
	
	public static void printClock(long key){
		long[] result = new long[9];
	    for (int i = 0; i < 9; i++){
		    	result[8-i] = key % N % 4;
		    	key = key / N;
	    }
		printClock(result);
	}
	
	public static long move(long clocks, int moveIndex, int times){
		long result = clocks;
		result += moves[moveIndex] * times;
		return result;
	}
	
	public static long moveBack(long clocks, int moveIndex, int times){
		long result = clocks;
		result -= moves[moveIndex] * times;
		return result;
	}
	
	public static boolean isResult(long key){
		for (int i = 0; i < 9; i++){
			if (key % N % 4 != 3) return false;
			key = key / N;
		}
		return true;
	}
	
	public static void printResult(PrintWriter out){
		int minLength = 100;
		int minIndex = 0;
//		for (int i = 0; i < p; i++){
//			if (answers[i][0] < minLength){
//				minLength = answers[i][0];
//				minIndex = i;
//			}
//		}
		int n = answers[minIndex][0];
//		System.out.println(p);
		for (int i = 1; i < n; i++){
			out.print((answers[minIndex][i] + 1) + " ");
		}
		out.println((answers[minIndex][n] + 1));
//		for (int i = 0; i < p; i++){
//			int n = answers[i][0];
//			for (int j = 1; j < n; j++){
//				out.print((answers[i][j] + 1) + " ");
//			}
//		}
	}
	

	
	public static long getHashKey(long[] clocks){
		long result = 0L;
		for (int i = 0; i < 9; i++){
			result *= N;
			result += clocks[i];
		}
		return result;
	}
	
	public static int[] clone(int length, int[] arr){
		int[] result = new int[arr.length + 1];
		result[0] = length;
		for (int i = 0; i < length; i++){
			result[i + 1] = arr[i];
		}
		return result;
	}
	
	public static void DFS(int n, int k){
		if (n == 9){
			if (isResult(clocksKey)){
				answers[p] = clone(k, answer);
				p++;
			}
		} else {
			for (int i = 0; i < 4; i++){
				clocksKey = move(clocksKey, n, i);
				for (int j = 0; j < i; j++){
					answer[k] = n;
					k++;
				}
				DFS(n+1, k);
				clocksKey = moveBack(clocksKey, n, i);
				for (int j = 0; j < i; j++){
					k--;
				}
			}
			
		}
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("clocks.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
	    for (int i = 0; i < 3; i++){
	    	StringTokenizer st = new StringTokenizer(f.readLine());
	    	for (int j = 0; j < 3; j++){
	    		clocks[i * 3 + j] = Integer.parseInt(st.nextToken()) / 3 - 1;
	    	}
	    }
		moves[0] = (long) (Math.pow(N, 8) + Math.pow(N, 7) + Math.pow(N, 5) + Math.pow(N, 4));
		moves[1] = (long) (Math.pow(N, 8) + Math.pow(N, 7) + Math.pow(N, 6));
		moves[2] = (long) (Math.pow(N, 7) + Math.pow(N, 6) + Math.pow(N, 4) + Math.pow(N, 3));
		moves[3] = (long) (Math.pow(N, 8) + Math.pow(N, 5) + Math.pow(N, 2));
		moves[4] = (long) (Math.pow(N, 7) + Math.pow(N, 5) + Math.pow(N, 4) + Math.pow(N, 3) + N);
		moves[5] = (long) (Math.pow(N, 6) + Math.pow(N, 3) + 1);
		moves[6] = (long) (Math.pow(N, 5) + Math.pow(N, 4) + Math.pow(N, 2) + N);
		moves[7] = (long) (Math.pow(N, 2) + N + 1);
		moves[8] = (long) (Math.pow(N, 4) + Math.pow(N, 3) + N + 1); 
	    
	 
	    clocksKey = getHashKey(clocks);
	    p = 0;
	    DFS(0, 0);

	    printResult(out);
	    
	    out.close(); 
        
	    System.exit(0);                              
	}
}