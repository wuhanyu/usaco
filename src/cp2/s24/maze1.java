package cp2.s24;

/*
ID: wuhanyu1
LANG: JAVA
TASK: maze1
*/
import java.io.*;
import java.util.*;

class Point{
	public int x, y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point a){
		if (this.x != a.x) return false;
		if (this.y != a.y) return false;
		return true;
	}
	
	public void print(){
		System.out.println("x:" + x + "     y:" + y);
	}
	
	public Point clone(){
		return new Point(this.x, this.y);
	}
}

public class maze1{
	public static PrintWriter out;
	public static int height, width;
	public static boolean[][] fence;
	public static int[][] dis;
	public static int count;
	public static Point[] exit = new Point[2];
	
	public static void DFS(Point p){
		Point t = p.clone();
		for (int i = 0; i < 4; i++){
			if (move(p, i)){
				if (dis[t.x][t.y] + 1 < dis[p.x][p.y]){
					dis[p.x][p.y] = dis[t.x][t.y] + 1;
					DFS(p);
				}
			}
			p = t.clone();
		}
	}
	
	public static boolean isOut(int x, int y){
		if (x < 0 || y < 0 || x >= width || y >= height) return true;
		return false;
	}
	
	public static boolean move(Point p, int d){
		int x = p.x;
		int y = p.y;
		int fencex = x * 2 + 1;
		int fencey = y * 2 + 1;
		if (d == 0){
			y -= 1;
			fencey -= 1;
		} else if (d == 1){
			x += 1;
			fencex += 1;
		}  else if (d == 2){
			y += 1;
			fencey += 1;
		}  else if (d == 3){
			x -= 1;
			fencex -= 1;
		}
		if (isOut(x, y) || fence[fencex][fencey]){
			return false;
		} else{
			p.x = x;
			p.y = y;
			return true;
		}
	}
	
	public static void printMatrix(int[][] matrix){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				System.out.print(matrix[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("maze1.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    width = Integer.parseInt(st.nextToken());
	    height = Integer.parseInt(st.nextToken());
	    dis = new int[width][height];
	    for (int i = 0; i < width; i++){
	    	for (int j = 0; j < height; j++) dis[i][j] = 10000;
	    }
	    fence = new boolean[2 * width + 1][2 * height + 1];
	    for (int i = 0; i < 2 * height + 1; i++){
	    	String line = f.readLine();
	    	for (int j = 0; j < 2 * width + 1; j++){
	    		char place = line.charAt(j);
	    		if (place == '+' || place == '-' || place == '|'){
	    			fence[j][i] = true;
	    		}
	    		if ((i == 2 * height || i == 0 || j == 2 * width || j == 0) && (place == ' ')){
	    			int x = j;
	    			int y = i;
	    			if (x == 2 * width) x = x / 2 - 1;
	    			else x = x / 2;
	    			if (y == 2 * height) y = y / 2 - 1;
	    			else y = y / 2;
	    			dis[x][y] = 1;
	    			exit[count] = new Point(x, y);
	    			count++;
	    		}
	    	}
	    }
	    DFS(exit[0]);
	    DFS(exit[1]);
	    int max = 0;
	    for (int i = 0; i < height; i++){
	    	for (int j = 0; j < width; j++){
	    		if (dis[j][i] != 10000 && dis[j][i] > max) max = dis[j][i];
	    	}
	    }
	    out.println(max);
	    printMatrix(dis);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

