package cp2.s21;
/*
ID: wuhanyu1
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

class holstein{
	public static int V, G;
	public static int[] requirements;
	public static int[][] vitamins;
	public static Queue<int[]> queue;
	public static Queue<Integer> queue_id;
	public static int[] parent;
	public static int[] current;
	public static int MAX_LENGTH = 100000;
	public static int k = 0;
	public static PrintWriter out;
	
	public static int[] minus(int[] a, int[] b){
		int[] result = clone(a);
		for (int i = 0; i < a.length; i++){
			result[i] = a[i] - b[i];
		}
		return result;
	}
	
	public static boolean isSatisfied(int[] a){
		for (int i = 0; i < a.length; i++){
			if (a[i] > 0 ) return false;
		}
		return true;
	}
	
	public static int[] clone(int[] a){
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++){
			result[i] = a[i];
		}
		return result;
	}
	
	public static void printResult(){
		int[] result = new int[100];
		int p = k;
		int count = 0;
		while (parent[p] >= 0){
			result[count] = current[p]+1;
			count++;
			p = parent[p];
		}
		out.print(count);
		for (int i = count - 1; i >= 0; i--){
			out.print(" " + result[i]);
		}
		out.println();
	}
	
	public static void BFS(){
		while (!queue.isEmpty()){
			int[] seed = queue.remove();
			int id = queue_id.remove();
			for (int i = current[id] + 1; i < G; i++){
				int[] child = minus(seed, vitamins[i]);
				current[k] = i;
				parent[k] = id;
				if (isSatisfied(child)){
					printResult();
					return;
				} else {
					queue.add(child);
					queue_id.add(k);
					k++;
				}
			}
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
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("holstein.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

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
	    
	    queue = new LinkedList<int[]>();
	    queue_id = new LinkedList<Integer>();
	    current = new int[MAX_LENGTH];
	    parent = new int[MAX_LENGTH];
	    queue.add(requirements);
	    queue_id.add(k);
	    current[k] = -1;
	    parent[k] = -1;
	    k++;
	    BFS();
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}