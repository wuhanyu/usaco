package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: butter
 */
import java.io.*;
import java.util.*;
class Node{
	public int end, len;
	public Node(int end, int len){
		this.end = end;
		this.len = len;
	}
}
class butter {

	public static PrintWriter out;
	public static int N, P, C;
	public static int[] cowL;
	public static Node[][] adj;
	public static int[] cnf;
	
	public static int SPFA(int src){
		int[] dist = new int[P];
		for (int i = 0; i < P; i++) dist[i] = 10000;
		dist[src] = 0;
		
		//SPFA
		boolean[] flag = new boolean[P];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		flag[src] = true;
		while (!q.isEmpty()){
			int x = q.remove();
			flag[x] = false;
			for (int j = 0; j < cnf[x]; j++){
				if (dist[x] + adj[x][j].len < dist[adj[x][j].end]){
					dist[adj[x][j].end] = dist[x] + adj[x][j].len;
					if (!flag[adj[x][j].end]){
						q.add(adj[x][j].end);
						flag[adj[x][j].end] = true;
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++){
			if (dist[i] == 10000) return Integer.MAX_VALUE;
			else ans += dist[cowL[i]];
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		// Get line, break into tokens
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cowL = new int[1000];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			cowL[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		adj = new Node[P][P];
		cnf = new int [P];
		for (int i = 0; i < C; i++){
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int dis = Integer.parseInt(st.nextToken());
			adj[start][cnf[start]] = new Node(end, dis);
			cnf[start]++;
			adj[end][cnf[end]] = new Node(start, dis);
			cnf[end]++;
		}
		int min_sum = Integer.MAX_VALUE;
		for (int i = 0; i < P; i++){
			int sum = SPFA(i);
			if (sum < min_sum) min_sum = sum;
		}
		out.println(min_sum);

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}