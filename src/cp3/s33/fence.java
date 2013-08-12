package cp3.s33;
/*
ID: wuhanyu1
LANG: JAVA
TASK: fence
 */
import java.io.*;
import java.util.*;

class fence {

	public static PrintWriter out;
	public static int N;
	public static int MAXN;
	public static int[][] flag = new int[510][510];
	public static int[] result = new int[1100];
	public static int count = 0;
	public static void DFS(int node){
		for (int i = 0; i < 500; i++){
			if (flag[node][i] > 0){
				flag[node][i]--;
				flag[i][node]--;
				DFS(i);	
			}	
		}
		result[count] = node;
        count++;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"fence.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		// Get line, break into tokens
		N = Integer.parseInt(st.nextToken());
		int[] degree = new int[1024];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			flag[start][end]++;
			flag[end][start]++;
			degree[start]++;
			degree[end]++;
		}
		int p = 0;
		while (degree[p] % 2 == 0 && p < N) p++;
		if (p == N){
			p = 0;
			while (degree[p] == 0) p++;
		}
		DFS(p);
		for (int i = count - 1; i >= 0; i--){
			out.println(result[i] + 1);
		}

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}