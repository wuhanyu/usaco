package cp2.s22;
/*
ID: wuhanyu1
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;

class runround{
	public static PrintWriter out;
	public static int N;
	
	
	public static boolean isRounroundNum(int n){
		int len = getLength(n);
		boolean[] flag = new boolean[len];
		int[] digits = new int[len];
		for (int i = 0; i < len; i++){
			digits[len - i - 1] = n % 10;
			n = n / 10;
		}
		int p = 0;
		flag[p] = true;
		for (int i = 0; i < len - 1; i++){
			p = (p + digits[p]) % len;
			if (flag[p] == true) return false;
			flag[p] = true;
		}
		p = (p + digits[p]) % len;
		if (p == 0)
			return true;
		else
			return false;
	}
	
	public static int getLength(int n){
		return (int) Math.log10(n) + 1;
	}
	
	public static boolean isNum(int n){
		boolean[] digits = new boolean[10];
		while (n > 0){
			int digit = n % 10;
			if (digit == 0) return false;
			if (digits[digit]) return false;
			digits[digit] = true;
			n = n / 10;
		}
		return true;
	}
	

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("runround.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    while (true){
	    	N++;
//	    	System.out.println(N);
	    	if (isNum(N) && isRounroundNum(N)){
	    		out.println(N);
	    		break;
	    	}	
	    }
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}