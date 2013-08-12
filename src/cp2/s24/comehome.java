package cp2.s24;
/*
ID: wuhanyu1
LANG: JAVA
TASK: comehome
*/
import java.io.*;
import java.util.*;

public class comehome{
	public static PrintWriter out;
	public static int N;
	public static int[][] dis;
	public static final int MAX = 52;
	
	public static boolean isUpper(char c){
		if (c - 'a' >= 0) return false;
		return true;
	}
	
	public static int getOrder(char c){
		if (c - 'a' >= 0) return c - 'a';
		return (c - 'A' + 26);
	}
	
	public static char getChar(int index){
		return (char) ('A' + index - 26);
	}
	
	public static void printMatrix(int[][] matrix){
		for (int i = 0; i < MAX; i++){
			for (int j = 0; j < MAX; j++){
				System.out.print(matrix[j][i] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("comehome.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    dis = new int[MAX][MAX];
	    for (int i = 0; i < MAX; i++){
	    	for (int j = 0; j < MAX; j++){
	    		dis[i][j] = 10000000;
	    	}
	    }
	    
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	char from = st.nextToken().charAt(0);
	    	char to = st.nextToken().charAt(0);
	    	int d = Integer.parseInt(st.nextToken());
	    	int forder = getOrder(from);
	    	int torder = getOrder(to);
	    	if (isUpper(from)){
	    		dis[forder][forder] = 0;
	    	}
	    	if (isUpper(to)){
	    		dis[torder][torder] = 0;

	    	}
	    	if (d < dis[forder][torder]){
	    		dis[forder][torder] = d;
	    		dis[torder][forder] = d;   		
	    	}
	    }
//	    System.out.println("--------");
//	    printMatrix(dis);
	    
	    //Floyd
	    for (int k = 0; k < MAX; k++){
	    	for (int i = 0; i < MAX; i++){
	    		for (int j = 0; j < MAX; j++){
    				if (i != j && dis[i][k] + dis[k][j] < dis[i][j]){
    					dis[i][j] = dis[i][k] + dis[k][j];
    				}
	    		}
	    	}
	    }
//	    System.out.println("--------");
//	    printMatrix(dis);	    
	    int min = Integer.MAX_VALUE;
	    int mini = -1;
	    for (int i = 0; i < MAX - 1; i++){
	    	if (dis[i][i] == 0 && dis[i][MAX-1] < min){
	    		min = dis[i][MAX-1];
	    		mini = i;
	    	}
	    }
	    out.println(getChar(mini) + " " + min);

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

