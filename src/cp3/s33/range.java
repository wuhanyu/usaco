package cp3.s33;
/*
ID: wuhanyu1
LANG: JAVA
TASK: range
 */
import java.io.*;
import java.util.*;

class range {

	public static PrintWriter out;
	public static int N;
	public static char[][] field;
	public static int[][] size;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"range.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		field = new char[N][N];
		size = new int[N][N];
		for (int i = 0; i < N; i++){
			String line = f.readLine();
			for (int j = 0; j < N; j++){
				field[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++){
			if (field[i][0] == '1') size[i][0] = 1;
			if (field[0][i] == '1') size[0][i] = 1;
		}
		
		for (int i = 1; i < N; i++){
			for (int j = 1; j < N; j++){
				if (field[i][j] == '1'){
					size[i][j] = size[i-1][j-1] + 1;
					size[i][j] = Math.min(size[i][j], size[i-1][j] + 1);
					size[i][j] = Math.min(size[i][j], size[i][j-1] + 1);
				} else {
					size[i][j] = 0;
				}
			}
		}
//		for (int i = 0; i < N; i++){
//			System.out.println(Arrays.toString(size[i]));
//		}
		int[] result = new int[N + 1];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (size[i][j] >= 2) result[size[i][j]] += 1;
			}
		}
		for (int i = N-1; i >= 2; i--){
			result[i] += result[i+1];
		}
		for (int i = 2; i <= N; i++){
			if (result[i] == 0) break;
			out.printf("%d %d\n", i, result[i]);
		}
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}