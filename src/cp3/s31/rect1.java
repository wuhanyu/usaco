package cp3.s31;
/*
ID: wuhanyu1
LANG: JAVA
TASK: rect1
*/
import java.io.*;
import java.util.*;

class Rect{
	public int x1, y1, x2, y2, color;
	public Rect(int x1, int y1, int x2, int y2, int color){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	public boolean contains(Rect r){
		if (this.x1 <= r.x1 && this.y1 <= r.y1 && this.x2 >= r.x2 && this.y2 >= r.y2) return true;
		else return false;
	}
	
	public int getArea(){
		return (x2-x1) * (y2-y1);
	}
}

public class rect1{
	public static PrintWriter out;
	public static int N, width, height;
	public static int[] primes;
	public static ArrayList<Rect> rects = new ArrayList<Rect>();
	
	public static boolean isXin(Rect r, int x){
		if (r.x1 < x && r.x2 > x) return true;
		return false;
	}

	public static boolean isYin(Rect r, int y){
		if (r.y1 < y && r.y2 > y) return true;
		return false;
	}
	
	public static void cutX(int x){
		int i = 0;
		while (i < rects.size()){
			if (isXin(rects.get(i), x)){
				Rect r = rects.get(i);
				rects.remove(i);
				rects.add(new Rect(r.x1, r.y1, x, r.y2, r.color));
				rects.add(new Rect(x, r.y1, r.x2, r.y2, r.color));
				continue;
			}
			i++;
		}
	}
	
	public static void cutY(int y){
		int i = 0;
		while (i < rects.size()){
			if (isYin(rects.get(i), y)){
				Rect r = rects.get(i);
				rects.remove(i);
				rects.add(new Rect(r.x1, r.y1, r.x2, y, r.color));
				rects.add(new Rect(r.x1, y, r.x2, r.y2, r.color));
				continue;
			}
			i++;
		}		
	}
	
	public static void addRect(Rect r){
		int x1 = r.x1;
		int x2 = r.x2;
		int y1 = r.y1;
		int y2 = r.y2;
		cutX(x1);
//		printArea();
		cutX(x2);
//		printArea();
		cutY(y1);
//		printArea();
		cutY(y2);
//		printArea();
		paint(new Rect(r.x1, r.y1, r.x2, r.y2, r.color));
//		printArea();
	}
	
	public static void paint(Rect r){
		int i = 0;
		while (i < rects.size()){
			if (r.contains(rects.get(i))){
				rects.remove(i);
				continue;
			}
			i++;
		}
		rects.add(r);
	}
	
	public static void printArea(){
		int area = 0;
		for (int i = 0; i < rects.size(); i++){
			area += rects.get(i).getArea();
		}
		System.out.println(area);
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("rect1.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    width = Integer.parseInt(st.nextToken());
	    height = Integer.parseInt(st.nextToken());
	    N = Integer.parseInt(st.nextToken());
	    
	    rects.add(new Rect(0, 0, width, height, 1));
	    for (int i = 0; i < N; i++){
	    	System.out.println(i);
	    	st = new StringTokenizer(f.readLine());
	    	int x1 = Integer.parseInt(st.nextToken());
	    	int y1 = Integer.parseInt(st.nextToken());
	    	int x2 = Integer.parseInt(st.nextToken());
	    	int y2 = Integer.parseInt(st.nextToken());
	    	int color = Integer.parseInt(st.nextToken());
	    	addRect(new Rect(x1, y1, x2, y2, color));
	    }
	    Map<Integer, Integer> colors = new HashMap<Integer, Integer>();
	    for (int i = 0; i < rects.size(); i++){
	    	Rect r = rects.get(i);
	    	if (colors.containsKey(r.color)){
	    		colors.put(r.color, colors.get(r.color) + r.getArea());
	    	} else {
	    		colors.put(r.color, r.getArea());
	    	}
	    }
	    
	    for (int i = 1; i < 1001; i++){
	    	if (colors.containsKey(i)){
	    		out.println(i + " " + colors.get(i));
	    	}
	    }

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

