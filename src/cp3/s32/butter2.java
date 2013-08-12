package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: butter
 */
import java.io.*;
import java.util.*;

class butter2 {

	public static PrintWriter out;
	public static int N, P, C;
	public static int[] cowL;
	public static int[][] connect;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		// Get line, break into tokens
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cowL = new int[N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			cowL[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		connect = new int[P][P];
		for (int i = 0; i < P; i++)
			for (int j = 0; j < P; j++)
				if (i != j)
					connect[i][j] = 100000;
				else
					connect[i][j] = 0;
		for (int i = 0; i < C; i++){
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int dis = Integer.parseInt(st.nextToken());
			connect[start][end] = dis;
			connect[end][start] = dis;
		}
//		for (int i = 0; i < P; i++)
//			System.out.println(Arrays.toString(connect[i]));
		//floyd
		for (int k = 0; k < P; k++){
			for (int i = 0; i < P; i++){
				for (int j = 0; j < P; j++){
					if (connect[i][j] > connect[i][k] + connect[k][j]){
						connect[i][j] = connect[i][k] + connect[k][j];
					}
				}
			}
		}
//		for (int i = 0; i < P; i++)
//			System.out.println(Arrays.toString(connect[i]));
		int min_sum = Integer.MAX_VALUE;
		for (int i = 0; i < P; i++){
			int sum = 0;
			for (int j = 0; j < N; j++){
				if (i != cowL[j])
					sum += connect[i][cowL[j]];
			}
			if (sum < min_sum) min_sum = sum;
		}
		out.println(min_sum);

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}