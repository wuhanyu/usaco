package cp1.s14;
/*
ID: wuhanyu1
LANG: JAVA
TASK: clocks
*/
import java.io.*;
import java.util.*;

class clocks2{
	public static long N = 12L;
	public static long[] clocks = new long[9];
	public static ArrayList<Long> queue = new ArrayList<Long>();
	public static ArrayList<Integer> id = new ArrayList<Integer>();
	public static ArrayList<Integer> parent = new ArrayList<Integer>();
	public static ArrayList<Integer> movequeue = new ArrayList<Integer>();
	public static ArrayList<Long> tmpqueue;
	public static HashMap<Long, Integer> map = new HashMap<Long , Integer>();   
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
	
	public static long move(long clocks, int moveIndex){
		long result = clocks;
		result += moves[moveIndex];
		return result;
	}
	
	public static boolean isResult(long key){
		for (int i = 0; i < 9; i++){
			if (key % N % 4 != 3) return false;
			key = key / N;
		}
		return true;
	}
	
	public static void printResult(int p, PrintWriter out){
		int[] result = new int[100];
		int count = 0;
		while (p > 0){
			p -= 1;
			result[count] = movequeue.get(p);
			p = parent.get(p);
			count++;
		}
		for (int i = count - 1; i > 0; i--){
			out.print((result[i] + 1) + " ");
		}
		out.println(result[0] + 1);
	}
	
	public static boolean isDupulicated(long key, int p){	
		if (map.containsKey(key)) return true;
		else{
			map.put(key, p);
//			System.out.println(key);
			return false;
		}
	}
	
	public static long getHashKey(long[] clocks){
		long result = 0L;
		for (int i = 0; i < 9; i++){
			result *= N;
			result += clocks[i];
		}
		return result;
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
	    
	 
	    int p = 1;
	    long key = getHashKey(clocks);
//	    printClock(clocks);
//	    System.out.println(key);
//	    printClock(key);
//	    for (int i = 0; i < 9; i++) System.out.println(moves[i]);
//	    for (int i = 0; i < 9; i++){
//		    printClock(move(key, i));
//		    System.out.println(isResult(move(key, i)));
//	    }
	    
	    queue.add(key);
	    map.put(key, 0);
	    boolean flag = true;
	    while (flag){
	    	tmpqueue = new ArrayList<Long>();
	    	for (int i = 0; i < queue.size(); i++){
	    		key = queue.get(i);
	    		int index = map.get(key);
	    		for (int j = 0; j < moves.length; j++){
	    			long tmpkey = move(key, j);
	    			if (isDupulicated(tmpkey, p)) continue;
	    			tmpqueue.add(tmpkey);
	    			parent.add(index);
	    			movequeue.add(j);
	    			if (isResult(tmpkey)){
	    				printResult(p, out);
	    				flag = false;
	    			    out.close();             
	    			    System.exit(0);  	    				
	    			} else { 
	    				p++;
	    			}
	    		}
	    	}
	    	queue = tmpqueue;
	    }
	    
	    out.close(); 
        
	    System.exit(0);                              
	}
}