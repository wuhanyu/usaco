package cp3.s31;
/*
ID: wuhanyu1
LANG: JAVA
TASK: agrinet
*/
import java.io.*;
import java.util.*;

class Line implements Comparable<Line>{
	public int start, end, length;
	
	public Line(int start, int end, int length){
		this.start = start;
		this.end = end;
		this.length = length;
	}
	
	public int compareTo(Line line) {
		if (line.length > this.length) return -1;
		if (line.length < this.length) return 1;
	    return 0;
	}
	
	public String toString(){
		return this.length + "(" + this.start + "," + this.end + ")";
	}
}

public class agrinet{
	public static PrintWriter out;
	public static int N;
	public static int[][] net;
	public static Line[] line;
	public static int[] flag;
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    net = new int[N][N];
	    for (int i = 0; i < N; i++){
	    	int count = 0;
	    	while (count < N){
	    		st = new StringTokenizer(f.readLine());
	    		while (st.hasMoreTokens()){
	    			net[i][count] = Integer.parseInt(st.nextToken());
	    			count++;
	    		}
	    	}
	    }
	    
	    line = new Line[N * (N - 1) / 2];
	    int count = 0;
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < i; j++){
	    		line[count] = new Line(i, j, net[i][j]); 
	    		count++;
	    	}
	    }
	    Arrays.sort(line);
//	    System.out.println(Arrays.toString(line));
	    flag = new int[N];
	    for (int i = 0; i < N; i++) flag[i] = i;
	    int result = 0;
	    count = 0;
	    for (int i = 0; i < line.length; i++){
	    	if (flag[line[i].start] == flag[line[i].end]) continue;
	    	count ++;
	    	int start, end;
	    	if (flag[line[i].start] < flag[line[i].end]){
	    		start = line[i].start;
	    		end = line[i].end;
	    	} else {
	    		start = line[i].end;
	    		end = line[i].start;	    		
	    	}
	    	int change = flag[end];
	    	for (int j = 0; j < N; j++){
	    		if (flag[j] == change) flag[j] = flag[start];
	    	}
	    	result += line[i].length;
	    }
//	    System.out.println(count);
	    out.println(result);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

