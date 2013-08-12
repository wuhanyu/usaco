package cp2.s22;
/*
ID: wuhanyu1
LANG: JAVA
TASK: lamps
 */
import java.io.*;
import java.util.*;

class lamps {

	public static PrintWriter out;
	public static int N;
	public static int C;
	public static boolean[] on, off;
	public static Queue<boolean[]> queue = new LinkedList<boolean[]>();
	public static Queue<boolean[]> oldqueue = new LinkedList<boolean[]>();
	public static Set<String> keys = new HashSet<String>();
	
	public static boolean isOn(boolean[] states){
		for (int i = 0; i < N; i++){
			if (on[i] && !states[i]) return false;
		}
		return true;
	}

	public static boolean isOff(boolean[] states){
		for (int i = 0; i < N; i++){
			if (off[i] && states[i]) return false;
		}
		return true;
	}
	
	public static boolean[] clone(boolean[] input){
		boolean[] result = new boolean[input.length];
		for (int i = 0; i < input.length; i++) result[i] = input[i];
		return result;
	}
	
	public static boolean[] action1(boolean[] states){
		boolean[] result = clone(states);
		for (int i = 0; i < N; i++){
			result[i] = !states[i];
		}
		return result;
	}

	public static boolean[] action2(boolean[] states){
		boolean[] result = clone(states);
		for (int i = 0; i < N; i+=2){
			result[i] = !states[i];
		}
		return result;
	}
	
	public static boolean[] action3(boolean[] states){
		boolean[] result = clone(states);
		for (int i = 1; i < N; i+=2){
			result[i] = !states[i];
		}
		return result;
	}

	public static boolean[] action4(boolean[] states){
		boolean[] result = clone(states);
		for (int i = 0; i < N; i+=3){
			result[i] = !states[i];
		}
		return result;
	}
	
	public static void print(boolean[] states){
		for (int i = 0; i < N; i++){
			if (states[i]) out.print(1);
			else out.print(0);
		}
		out.println();
	}
	
	public static String getKey(boolean[] states){
		String result = "";
		for (int i = 0; i < N; i++){
			if (states[i]) result += 1;
			else result += 0;
		}
		return result;
	}
	
	public static void add(boolean[] states){
		String key = getKey(states);
		if (keys.contains(key)) return;
		else{
			keys.add(key);
		}
		queue.add(states);
	}
	
	public static boolean isLess(boolean[] a, boolean[] b){
		for (int i = 0; i < N; i++){
			if (a[i] && !b[i]) return false;
			if (!a[i] && b[i]) return true;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		// input file name goes above
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"lamps.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		StringTokenizer st = new StringTokenizer(f.readLine());
		// Get line, break into tokens
		N = Integer.parseInt(st.nextToken()); 
		st = new StringTokenizer(f.readLine());
		int C = Integer.parseInt(st.nextToken());
		on = new boolean[N];
		off = new boolean[N];
		st = new StringTokenizer(f.readLine());
		int number = Integer.parseInt(st.nextToken());
		while (number != -1){
			on[number-1] = true;
			number = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		number = Integer.parseInt(st.nextToken());
		while (number != -1){
			off[number-1] = true;
			number = Integer.parseInt(st.nextToken());
		}
		boolean[] start = new boolean[N];
		for (int i = 0; i < N; i++) start[i] = true;
		oldqueue.add(start);
		if (C > 8) C = 8;
		for (int i = 0; i < C; i++){
			keys.clear();
			while (!oldqueue.isEmpty()){
				boolean[] seed = oldqueue.remove();
				add(action1(seed));
				add(action2(seed));
				add(action3(seed));
				add(action4(seed));
			}
			oldqueue = queue;
			queue = new LinkedList<boolean[]>();
		}
		boolean flag = true;
		boolean[][] results = new boolean[16][N];
		int count = 0;
		while (!oldqueue.isEmpty()){
			boolean[] result = oldqueue.remove();
			if (isOn(result) && isOff(result)){
				flag = false;
				results[count] = result;
				count++;
			}
		}
		if (flag) out.println("IMPOSSIBLE");
		else {
			for (int i = 0; i < count - 1; i++){
				boolean[] min = results[i];
				int minj = i;
				for (int j = i + 1; j < count; j++){
					if (isLess(results[j], min)){
						min = results[j];
						minj = j;
					}
				}
				boolean[] tmp = clone(results[i]);
				results[i] = min;
				results[minj] = tmp;
			}
			
			for (int i = 0; i < count; i++){
				print(results[i]);
			}
		}
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}