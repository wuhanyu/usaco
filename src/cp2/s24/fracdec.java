package cp2.s24;
/*
ID: wuhanyu1
LANG: JAVA
TASK: fracdec
*/
import java.io.*;
import java.util.*;

public class fracdec{
	public static PrintWriter out;
	public static int N, D;
	public static int count;
	public static int interval;
	public static int[] number;
	public static int[] reminder;
	public static int MAX = 1000000;
	public static int lower = 1;
	
	public static int count2(int n){
		int count = 0;
		while (n % 2 == 0){
			count++;
			n /= 2;
		}
		return count;
	}
	
	public static int count5(int n){
		int count = 0;
		while (n % 5 == 0){
			count++;
			n /= 5;
		}
		return count;
	}
	
	public static boolean isRepeat(int s, int e, int iterval){
		if (s < 0) return false;
		for (int i = s; i < e - iterval; i++){
			if (number[i] != number[i + iterval] || reminder[i] != reminder[i + iterval]) return false;
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    D = Integer.parseInt(st.nextToken());
	    
	    number = new int[MAX];
	    reminder = new int[MAX];

	    int head = N / D;
	    
	    count = 0;
	    
	    int N2 = count2(N);
	    int D2 = count2(D);
	    int N5 = count5(N);
	    int D5 = count5(D);
	    D2= Math.max(D2 - N2, 0);
	    D5= Math.max(D5 - N5, 0);
	    int len = Math.max(D2, D5);
	    System.out.println(len);
//	    len = Math.max(len, Math.abs(N2 - D2));
//	    len = Math.max(len, Math.abs(N5 - D5));
	    N = N % D;
	    while (N > 0){
	    	N = N * 10;
	    	int div = N / D;
	    	int rem = N % D;
	    	number[count] = div;
	    	reminder[count] = rem;
	    	count++;
	    	N = rem;
	    	if (count > len){
		    	if ((count - len) % 2 == 0 && isRepeat(len, count, (count - len) / 2)){
	    			interval = (count - len) / 2;
	    			break;
		    	}
	    	}
	    }
	    int k;
	    if (head >= 1){
	    	k = (int) (Math.log10(head) + 2);
	    } else {
	    	k = 2;
	    }
	    out.print(head + ".");
	    for (int i = 0; i < count - interval * 2; i++){
	    	out.print(number[i]);
	    	k++;
	    	if (k % 76 == 0) out.println();
	    }
	    if (count - interval * 2 == 0 && N == 0){
	    	out.print(0);
	    	k++;
	    	if (k % 76 == 0) out.println();
	    }
	    if (N == 0){
	    	out.println();
	    } else {
		    out.print("(");
	    	k++;
	    	if (k % 76 == 0) out.println();
		    for (int i = count - interval; i < count; i++){
		    	out.print(number[i]);
		    	k++;
		    	if (k % 76 == 0) out.println();
		    }
		    out.println(")");    	
	    }

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

