package cp2.s24;

/*
ID: wuhanyu1
LANG: JAVA
TASK: cowtour
*/
import java.io.*;
import java.util.*;

class Point3{
	public int x, y;
	public Point3(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point3 a){
		if (this.x != a.x) return false;
		if (this.y != a.y) return false;
		return true;
	}
	
	public void print(){
		System.out.println("x:" + x + "     y:" + y);
	}
	
	public Point3 clone(){
		return new Point3(this.x, this.y);
	}
	
	public double getDis(Point3 a){
		double x = a.x - this.x;
		double y = a.y - this.y;
		return Math.sqrt(x * x + y * y);
	}
}

class cowtour{
	public static PrintWriter out;
	public static int N;
	public static double[][] dis;
	public static Point3[] pos;
	public static final int MAX = 10000000;
	
	public static void printMatrix(double[][] matrix){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				System.out.print((int)matrix[j][i] + "\t");
			}
			System.out.println();
		}
	}

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    dis = new double[N][N];
	    pos = new Point3[N];
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	int x = Integer.parseInt(st.nextToken());
	    	int y = Integer.parseInt(st.nextToken());
	    	pos[i] = new Point3(x, y);
	    }
	    
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++){
	    		dis[i][j] = MAX;
	    	}
	    }
	    
	    for (int i = 0; i < N; i++){
	    	String line = f.readLine();
	    	for (int j = 0; j < i; j++){
	    		if (line.charAt(j) == '1'){
	    			dis[i][j] = pos[i].getDis(pos[j]);
	    			dis[j][i] = dis[i][j];
	    		}
	    	}
	    }
//	    printMatrix(dis);
	    //Floyd
	    for (int k = 0; k < N; k++){
	    	for (int i = 0; i < N; i++){
	    		for (int j = 0; j < N; j++){
    				if (i != j && dis[i][k] + dis[k][j] < dis[i][j]){
    					dis[i][j] = dis[i][k] + dis[k][j];
    				}
	    		}
	    	}
	    }
//	    System.out.println("---------------------");
//	    printMatrix(dis);
	    double min = MAX;
	    double max = 0;
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++){
	    		if (i != j && dis[i][j] == MAX){
	    			double newDis = pos[i].getDis(pos[j]);
	    			double maxi = 0;
	    			for (int k = 0; k < N; k++){
	    				if (dis[i][k] < MAX && dis[i][k] > maxi) maxi = dis[i][k];
	    			}
	    			double maxj = 0;
	    			for (int k = 0; k < N; k++){
	    				if (dis[j][k] < MAX && dis[j][k] > maxj) maxj = dis[j][k];
	    			}
	    			if (maxi > max) max = maxi;
	    			if (maxj > max) max = maxj;
	    			if (min > newDis + maxi + maxj){
	    				min = newDis + maxi + maxj;
	    			}
	    		}
	    	}
	    }
	    out.printf("%f\n" ,Math.max(max, min));
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}