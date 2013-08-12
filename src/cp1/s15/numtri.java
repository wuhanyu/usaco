package cp1.s15;
/*
ID: wuhanyu1
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri{
	public static int N = 0;
	public static int[][] data = new int[1000][1000];
	public static int[][] sum = new int [1000][1000];
	
	public static void printMatrix(int[][] M){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < i + 1; j++){
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("numtri.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());   
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	for (int j = 0; j < i + 1; j++){
	    		data[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    sum[0][0] = data[0][0];
	    
	    for (int i = 1; i < N; i++){
	    	sum[i][0] = sum[i-1][0] + data[i][0];
			for (int j = 1; j < i + 1; j++){
				if (sum[i-1][j-1] + data[i][j] > sum[i-1][j] + data[i][j]){
					sum[i][j] = sum[i-1][j-1] + data[i][j];
				} else {
					sum[i][j] = sum[i-1][j] + data[i][j];
				}
			}
	    }
//	    printMatrix(data);
	    int max = 0;
	    for (int i = 0; i < N; i++){
	    	if (sum[N-1][i] > max) max = sum[N-1][i];
	    }
	    out.println(max);

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}