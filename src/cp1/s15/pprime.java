package cp1.s15;
/*
ID: wuhanyu1
LANG: JAVA
TASK: pprime
 */
import java.io.*;
import java.util.*;

class pprime {
	public static int S, M;
	public static boolean[] flag = new boolean[1000001];

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
		int k = (int) Math.sqrt(number) + 3;
		 k = number / 10;
//		 System.out.println(k);
		for (int j = 2; j < k; j++) {
			if (!flag[j] && (number % j == 0))
				return false;
		}
		return true;
	}

	public static boolean checkPalindrome(int num) {
		String s = String.valueOf(num);
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) != s.charAt(j))
				return false;
			i++;
			j--;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"pprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		createTable();
		int upper = 1000000;
		if (M < 1000000)
			upper = M;
		for (int i = S; i <= upper; i++) {
			if (!flag[i] && checkPalindrome(i)) {
				out.println(i);
			}
		}
		if (M > 1000000) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				for (int d2 = 0; d2 <= 9; d2++) {
					for (int d3 = 0; d3 <= 9; d3++) {
						for (int d4 = 0; d4 <= 9; d4++) {
							int palindrome = 1000000 * d1 + 100000 * d2 + 10000
									* d3 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
							if (palindrome >= S && palindrome <= M && checkPrime(palindrome)) out.println(palindrome);
						}

					}
				}
			}
		}
		out.close();

		System.exit(0);
	}
}