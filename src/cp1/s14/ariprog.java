package cp1.s14;
/*
ID: wuhanyu1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog{
	public static boolean[] flag = new boolean[200000];
	
	public static void createTable(int M){
		for (int i = 0; i <= M; i++){
			for (int j = 0; j <= M; j++){
				flag[i * i + j * j] = true;
			}
		}
	}
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    int N = Integer.parseInt(st.nextToken());   
	    st = new StringTokenizer(f.readLine());
	    int M = Integer.parseInt(st.nextToken());
	    createTable(M);
	    int upperA = M * M * 2;
	    int upperB = upperA / (N - 1) + 1;
	    boolean hasResult = false;
	    for (int b = 1; b < upperB; b++){
	    	for (int a = 0; a < upperA; a++){
	    		boolean isResult = true;
	    		for (int i = 0; i < N; i++){
	    			int s = a + i * b;
//	    			System.out.println(s);
	    			if (flag[s] == false){
	    				isResult = false;
	    				break;
	    			}
	    		}
	    		if (isResult){
	    			hasResult = true;
	    			out.println(a + " " + b);
	    		}
	    	}
	    }
	    if (hasResult == false) out.println("NONE");
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}