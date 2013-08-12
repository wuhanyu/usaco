/*
ID: wuhanyu1
LANG: JAVA
TASK: fence4
 */
import java.io.*;
import java.util.*;

class fence4 {

	public static PrintWriter out;
	public static int N;
	public static Point ob;
	public static Point[] points;
	public static Segment[] fences;
	
	
	public static void main(String[] args) throws IOException {
		String className = new Throwable().getStackTrace()[0].getClassName();
		BufferedReader f = new BufferedReader(new FileReader(className + ".in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(className + ".out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		points = new Point[N];
		fences = new Segment[N];
		st = new StringTokenizer(f.readLine());
		ob = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		
		//check fence valid
		for (int i = 0; i < N; i++){
			fences[i] = new Segment(points[i], points[i % N]);
			for (int j = 0; j < i - 1; j++){
				if (fences[i].isIntersected(fences[j])){
					out.println("NOFENCE");
					out.close(); // close the output file
					System.exit(0); // don't omit this!
				}
			}
		}
		
		//check visible fence
		for (int i = 0; i < N; i++){
			
		}

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
	

}

class Point{
	int x, y;
	Point (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	Segment minus(Point p){
		return new Segment(this, p);
	}
}

class Segment{
	Point start, end;
	Point a, b;
	int x, y;
	Segment(Point a, Point b){
		this.start = new Point(Math.min(a.x, b.x), Math.min(a.y, b.y));
		this.end = new Point(Math.max(a.x, b.x), Math.max(a.y, b.y));
		this.a = a;
		this.b = b;
		x = a.x - b.x;
		y = a.y - b.y;
	}
	
	boolean isIntersected(Segment s){
		if (!rectangleTest(s)) return false;
		if (isTwoSide(this.a, s.a, s.b) && isTwoSide(s.a, this.a, this.b)) return true;
		return false;
	}
	
	boolean isTwoSide(Point start, Point a, Point b){
		Point sa = new Point(a.x - start.x, a.y - start.y);
		Point sb = new Point(b.x - start.x, b.y - start.y);
		if (XMulti(sa, sb) > 0 ) return true;
		return false;
	}
	
	int XMulti(Point sa, Point sb){
		return sa.x * sb.y - sa.y * sb.x;
	}
	
	private boolean rectangleTest(Segment s){
		if (this.start.x > s.end.x || this.end.x < s.start.x || this.start.y > s.end.y || this.end.y < s.start.y) return true;
		return true;
	}
}