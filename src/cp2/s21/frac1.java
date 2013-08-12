package cp2.s21;
/*
ID: wuhanyu1
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;

class frac1{
	public static int N;
	public static boolean[][] flag;
	public static int count = 0;
	public static ArrayList<Double> result = new ArrayList<Double>();
	public static ArrayList<String> resultstr = new ArrayList<String>();
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("frac1.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    flag = new boolean[N+1][N+1];
	    for (int i = 1; i <= N; i++){
	    	for (int j = 0; j < i; j++){
	    		if (!flag[j][i]){
	    			int k = 2;
	    			while (i * k <= N){
	    				flag[j*k][i*k] = true;
	    				k++;
	    			}
	    		}
	    	}
	    }
	    result.add((double) 0);
	    resultstr.add("0/1");
	    for (int i = N; i >= 1; i--){
	    	for (int j = 1; j < i; j++){
	    		if (!flag[j][i]){
	    			String str = (j + "/" + i);
	    			double value = (double)j / i;
	    			int k = result.size() - 1;
	    			while (k > 0 && result.get(k) > value) k--;
	    			if (k + 1 == result.size()){
	    				result.add(value);
	    				resultstr.add(str);
	    			} else {
		    			result.add(k+1, value);
		    			resultstr.add(k+1, str);	    				
	    			}
	    		}
	    	}	    	
	    }
	    resultstr.add("1/1");
	    for (int i = 0; i < resultstr.size(); i++){
	    	out.println(resultstr.get(i));
	    }
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}