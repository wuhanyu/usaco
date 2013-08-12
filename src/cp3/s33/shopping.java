package cp3.s33;
/*
ID: wuhanyu1
LANG: JAVA
TASK: shopping
 */
import java.io.*;
import java.util.*;

class shopping {

	public static PrintWriter out;
	public static int S, N;
	public static int[] code = new int[5];
	public static int[] number = new int[5];
	public static int[] price = new int[5];
	public static int[][][][][] dp = new int[6][6][6][6][6];
	public static int[][] special = new int[99][2001];
	
	public static int getIndex(int c){	
		for (int i = 0; i < 5; i++){
			if (code[i] == c){		
				return i;
			}
		}	
		return -1;
	}
	
	public static int[] minus(int[] number, int[] special){
		int[] result = Arrays.copyOf(number, number.length);
		for (int i = 0; i < special[0]; i++){
			result[getIndex(special[2 * i + 1])] -=  special[2 * i + 2];
		}		

		return result;
	}
	
	public static int[] minus(int[] number, int index){
		int[] result = Arrays.copyOf(number, number.length);
		result[index] -= 1;
		return result;
	}
	
	public static int DP(int[] points){
//		System.out.println(Arrays.toString(points));
		boolean flag = true;
		for (int i = 0; i < 5; i++){
			if (points[i] < 0) return 100000;
			if (points[i] > 0) flag = false;
		}
		if (flag) return 0;
		if (dp[points[0]][points[1]][points[2]][points[3]][points[4]] == 0){
			int min = 100000;

			for (int i = 0; i < S; i++){
				int value = DP(minus(points, special[i]));
				if (value + special[i][2 * special[i][0] + 1] < min){
					min = value + special[i][2 * special[i][0] + 1];
					dp[points[0]][points[1]][points[2]][points[3]][points[4]] = min;
				}
			}
			for (int i = 0; i < N; i++){
				int value = DP(minus(points, i));		
				if (value + price[i] < min){
					min = value + price[i];
					dp[points[0]][points[1]][points[2]][points[3]][points[4]] = min;
				}		
			}
//			System.out.println(Arrays.toString(points) + min);
			return min;
		} else {
			return dp[points[0]][points[1]][points[2]][points[3]][points[4]];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"shopping.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		S = Integer.parseInt(st.nextToken());
		for (int i = 0; i < S; i++){
			st = new StringTokenizer(f.readLine());
			int j = 0;
			while (st.hasMoreElements()){
				special[i][j] = Integer.parseInt(st.nextToken());
				j++;
			}
		}
		st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			code[i] = Integer.parseInt(st.nextToken());
			number[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		out.println(DP(number));

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}