package cp3.s33;
/*
ID: wuhanyu1
LANG: JAVA
TASK: game1
 */
import java.io.*;
import java.util.*;

class game1 {

	public static PrintWriter out;
	public static int N;
	public static int[] data;
	public static int[][] sum, dp;
	
	public static int dp(int i, int j){
		if (i == j) return data[i];
		if (dp[i][j] == 0){
			dp[i][j] = sum[i][j] - Math.min(dp(i+1, j), dp(i, j-1));
			return dp[i][j];
		} else {
			return dp[i][j];
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		data = new int[N];
		int count = 0;
		while (count < N) {
			st = new StringTokenizer(f.readLine());
			while (st.hasMoreElements()) {
				data[count] = Integer.parseInt(st.nextToken());
				count++;
			}
		}
		sum = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++){
			sum[i][i] = data[i];
			dp[i][i] = data[i];
		}
		for (int i = 0; i < N; i++){
			for (int j = i + 1; j < N; j++){
				sum[i][j] = sum[i][j-1] + data[j];
				sum[j][i] = sum[i][j];
			}
		}
		dp(0, N-1);
		out.printf("%d %d\n", dp[0][N-1], sum[0][N-1] - dp[0][N-1]);
//		for (int i = 0; i < N; i++){
//			System.out.println(Arrays.toString(sum[i]));
//		}
//		System.out.println();
//		for (int i = 0; i < N; i++){
//			System.out.println(Arrays.toString(dp[i]));
//		}

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}