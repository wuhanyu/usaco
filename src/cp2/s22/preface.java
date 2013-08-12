package cp2.s22;
/*
ID: wuhanyu1
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

class preface{
	public static PrintWriter out;
	public static int N;
	public static int[] digits = {1, 5, 10, 50, 100, 500, 1000};
	public static char[] romanDigits = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	public static int[] count = new int[7];
	public static int[] d = new int[4];
	
	
	public static String getRomanDigit(int n){
		String result = "";
		for (int i = 0; i < d.length; i++){
			d[i] = n % 10;
			n = n / 10;
		}
		for (int i = 0; i < d[3]; i++){
			result += 'M';
		}
		if (d[2] == 9){
			result += "CM";
		} else if (d[2] == 4){
			result += "CD";
		} else {
			for (int i = 0; i < d[2] / 5; i++){
				result += 'D';
			}
			for (int i = 0; i < d[2] % 5; i++){
				result += 'C';
			}
		}
		if (d[1] == 9){
			result += "XC";
		} else if (d[1] == 4){
			result += "XL";
		} else {
			for (int i = 0; i < d[1] / 5; i++){
				result += 'L';
			}
			for (int i = 0; i < d[1] % 5; i++){
				result += 'X';
			}
		}
		if (d[0] == 9){
			result += "IX";
		} else if (d[0] == 4){
			result += "IV";
		} else {
			for (int i = 0; i < d[0] / 5; i++){
				result += 'V';
			}
			for (int i = 0; i < d[0] % 5; i++){
				result += 'I';
			}
		}
		return result;
	}
	
	public static int[] getRomanDigitNumber(int n){
		int[] result = new int[digits.length];
		for (int i = 0; i < d.length; i++){
			d[i] = n % 10;
			n = n / 10;
		}
		for (int i = 0; i < d[3]; i++){
			result[6] += 1;
		}
		if (d[2] == 9){
			result[6] += 1;
			result[4] += 1;
		} else if (d[2] == 4){
			result[5] += 1;
			result[4] += 1;
		} else {
			for (int i = 0; i < d[2] / 5; i++){
				result[5] += 1;
			}
			for (int i = 0; i < d[2] % 5; i++){
				result[4] += 1;
			}
		}
		if (d[1] == 9){
			result[4] += 1;
			result[2] += 1;
		} else if (d[1] == 4){
			result[3] += 1;
			result[2] += 1;
		} else {
			for (int i = 0; i < d[1] / 5; i++){
				result[3] += 1;
			}
			for (int i = 0; i < d[1] % 5; i++){
				result[2] += 1;
			}
		}
		if (d[0] == 9){
			result[2] += 1;
			result[0] += 1;
		} else if (d[0] == 4){
			result[1] += 1;
			result[0] += 1;
		} else {
			for (int i = 0; i < d[0] / 5; i++){
				result[1] += 1;
			}
			for (int i = 0; i < d[0] % 5; i++){
				result[0] += 1;
			}
		}
		return result;
	}
	
	public static int[] add(int[] a, int[] b){
		int[] result = a;
		for (int i = 0; i < a.length; i++){
			result[i] = a[i] + b[i];
		}
		return result;
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("preface.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    for (int i = 1; i <= N; i++){
	    	add(count, getRomanDigitNumber(i));
	    }
	    for (int i = 0; i < count.length; i++){
	    	if (count[i] > 0){
	    		out.print(romanDigits[i] + " ");
	    		out.println(count[i]);
	    	}
	    }
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}