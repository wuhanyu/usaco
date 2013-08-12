package cp1.s15;
/*
ID: wuhanyu1
LANG: JAVA
TASK: sprime
 */
import java.io.*;
import java.util.*;

class sprime {
	public static int N;
	public static boolean[] flag = new boolean[1000001];
	public static int[] superribs = new int[1000];
	public static int[] tmpsuperribs = new int[1000];

	public static void createTable() {
		flag[0] = true;
		flag[1] = true;

		for (int i = 2; i <= 1000000; i++) {
			if (!flag[i]) {
				int j = i;
				while (j <= 1000000 - i) {
					j += i;
					flag[j] = true;
				}
			}
		}
	}

	public static boolean checkPrime(int number) {
		if (number < 1000000) return !flag[number];
		int k = (int) Math.sqrt(number) + 3;
//		 System.out.println(k);
		for (int j = 2; j < k; j++) {
			if (!flag[j] && (number % j == 0))
				return false;
		}
		return true;
	}

	public static boolean checkSuperRib(int num) {
		while (num > 0){
			if (!checkPrime(num)) return false;
			
			num = num / 10;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"sprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		createTable();
		int upper = (int) Math.pow(10, N);
		System.out.println(upper);
		int count = 0;
		int k = 1;
		upper = (int) Math.pow(10, k);
		for (int i = upper / 10; i < upper; i++){
			if (!flag[i] && checkSuperRib(i)){
				superribs[count] = i;
				count++;
			}
		}
		while (k < N){
			k++;
			int p = 0;
			for (int i = 0; i < count; i++){
				for (int j = 1; j <= 9; j+=2){
					int number = superribs[i] * 10 + j;
					if (checkPrime(number)){
						tmpsuperribs[p] = number;
						p++;
					}
				}
			}
			count = p;
			for (int i = 0; i < count; i++){
				superribs[i] = tmpsuperribs[i];
			}
		}
		for (int i = 0; i < count; i++){
			out.println(superribs[i]);
		}
		out.close();

		System.exit(0);
	}
}