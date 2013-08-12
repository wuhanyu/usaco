package cp2.s24;
/*
ID: wuhanyu1
LANG: JAVA
TASK: ttwo
*/
import java.io.*;
import java.util.*;
class Point2{
	public int x, y;
	public Point2(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point2 a){
		if (this.x != a.x) return false;
		if (this.y != a.y) return false;
		return true;
	}
	
	public void print(){
		System.out.println("x:" + x + "     y:" + y);
	}
}
public class ttwo{
	public static PrintWriter out;
	public static int height, width;
	public static boolean[][] obstacle = new boolean[10][10];
	public static Point2 F, C;
	public static int FD, CD;
	
	public static boolean isOut(int x, int y){
		if (x < 0 || y < 0 || x >= 10 || y >= 10) return true;
		return false;
	}
	public static int move(Point2 p, int d){
		int x = p.x;
		int y = p.y;
		if (d == 0){
			y -= 1;
		} else if (d == 1){
			x += 1;
		}  else if (d == 2){
			y += 1;
		}  else if (d == 3){
			x -= 1;
		}
		if (isOut(x, y) || obstacle[x][y]){
			return (d+1) % 4;
		} else{
			p.x = x;
			p.y = y;
			return d;
		}
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
	    for (int i = 0; i < 10; i++){
	    	StringTokenizer st = new StringTokenizer(f.readLine());
	    	String line = st.nextToken();
	    	for (int j = 0; j < 10; j++){
	    		char place = line.charAt(j);
	    		if (place == 'F'){
	    			F = new Point2(j, i);
	    		} else if (place == 'C'){
	    			C = new Point2(j, i);
	    		}
	    		if (place == '*'){
	    			obstacle[j][i] = true;
	    		}
	    	}
	    }
	    int count = 0;
	    while (!F.equals(C)){
	    	count++;
//	    	System.out.println(count + "----------------");
//	    	F.print();
//	    	C.print();
	    	if (count > 10000){
	    		count = 0;
	    		break;
	    	}
	    	FD = move(F, FD);
	    	CD = move(C, CD);
	    }
	    out.println(count);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

