package cp2.s21;
/*
ID: wuhanyu1
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

class holstein2{
	public static int V, G;
	public static int[] requirements;
	public static int[][] vitamins;
	public static int[] remaining;
	public static int min_count = 100;
	public static int count = 0;
	public static int[] min_result;
	public static int[] result;
	public static boolean[] flag;
	public static int[][] constrait;
	
	public static void add(int[] a, int[] b, int factor){
		for (int i = 0; i < a.length; i++){
			a[i] = a[i] + b[i] * factor;
		}
	}
	
	public static boolean isSatisfied(int[] a){
		for (int i = 0; i < a.length; i++){
			if (a[i] > 0 ) return false;
		}
		return true;
	}
	
	public static void clone(int[] a, int[] b){
		for (int i = 0; i < a.length; i++){
			b[i] = a[i];
		}
	}
	
	public static void DFS(){
		if (count >= min_count) return;
		if (!checkEnough()) return;
		if (isSatisfied(remaining)){
			min_count = count;
			clone(result, min_result);
			return;
		}
		for (int i = 0; i < G; i++){
			if (flag[i]) continue;
			flag[i] = true;
			add(remaining, vitamins[i], -1);
			result[count] = i;
			count++;
			DFS();
			count--;
			add(remaining, vitamins[i], +1);
			flag[i] = false;
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	public static void sort(int [] a){
		for (int i = 0; i < a.length; i++){
			int min = 10000;
			int minj = -1;
			for (int j = i; j < a.length; j++){
				if (a[j] < min){
					min = a[j];
					minj = j;
				}
			}
			swap(a, i, minj);
		}
	}
	
	public static void print(int[][] m){
		for (int i = 0; i < m.length; i++){
			for (int j = 0; j < m[0].length; j++){
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean checkEnough(){
		if (count >= G) return true;
		if (min_count >= 100) return true;
		for (int i = 0; i < V; i++){
			if (remaining[i] > constrait[min_count-count-1][i]){
//				System.out.println(count + ":" +remaining[i] + "##" + constrait[min_count-count-1][i]);
				return false;
			}
		}
		return true;
	}
	
	public static void buildCons(){
	    constrait = new int[G][V];
	    for (int i = 0; i < V; i++){
	    	int[]tmp = new int[G];
	    	for (int j = 0; j < G; j++){
	    		tmp[j] = vitamins[j][i];
	    	}
	    	sort(tmp);
	    	int value = 0;
	    	for (int j = 0; j < G; j++){
	    		value += tmp[G-j-1];
	    		constrait[j][i] = value;
	    		
	    	}
	    }
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("holstein.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    V = Integer.parseInt(st.nextToken());
	    requirements = new int[V];
	    st = new StringTokenizer(f.readLine());
	    for (int i = 0; i < V; i++){
	    	requirements[i] = Integer.parseInt(st.nextToken());
	    }
	    st = new StringTokenizer(f.readLine());
	    G = Integer.parseInt(st.nextToken());
	    vitamins = new int[G][V];
	    for (int i = 0; i < G; i++){
	    	st = new StringTokenizer(f.readLine());
	    	for (int j = 0; j < V; j++){
	    		vitamins[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    remaining = new int[V];
	    result = new int[G];
	    min_result = new int[G];
	    flag = new boolean[G];
	    
	    buildCons();
	    print(constrait);
	    for (int i = 0; i < V; i++) remaining[i] = requirements[i];
	    DFS();
	    out.print(min_count);
	    for (int i = 0; i < min_count; i++){
	    	out.print(" " + (min_result[i] + 1));
	    }
	    out.println();
	    
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}