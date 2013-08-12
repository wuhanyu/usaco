package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: msquare
*/
import java.io.*;
import java.util.*;

class Cube{
	public int n;
	public int[] cubes;
	public String seq;
	public Cube(int n, int[] cubes, String seq){
		this.n = n; 
		this.cubes = cubes;
		this.seq = seq;
	}
	
	public int getKey(){
		int result = 0;
		for (int i = 0; i < cubes.length; i++)
			result = result * 10 + cubes[i];
		return result;
	}
	
	public Cube oprA(){
		String seq = this.seq + 'A';
		int n = this.n + 1;
		int[] result = new int[8];
		for (int i = 0; i < 8; i++) result[i] = this.cubes[7-i];
		return new Cube(n, result, seq);
	}
	
	public Cube oprB(){
		String seq = this.seq + 'B';
		int n = this.n + 1;
		int[] result = new int[8];
		for (int i = 0; i < 3; i++) result[i + 1] = this.cubes[i];
		result[0] = this.cubes[3];
		for (int i = 0; i < 3; i++) result[i + 4] = this.cubes[i + 5];
		result[7] = this.cubes[4];
		return new Cube(n, result, seq);
	}
	
	public Cube oprC(){
		String seq = this.seq + 'C';
		int n = this.n + 1;
		int[] result = new int[8];
		for (int i = 0; i < 8; i++) result[i] = this.cubes[i];
		result[1] = this.cubes[6];
		result[2] = this.cubes[1];
		result[5] = this.cubes[2];
		result[6] = this.cubes[5];
		return new Cube(n, result, seq);
	}
	
	public boolean equals(Cube c){
		for (int i = 0; i < 8; i++){
			if (this.cubes[i] != c.cubes[i]) return false;
		}
		return true;
	}
}

public class msquare{
	public static PrintWriter out;

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("msquare.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    int[] tmp = new int[8];
	    int[] tmp1 = new int[8];
	    for (int i = 0; i < 8; i++){
	    	tmp[i] = Integer.parseInt(st.nextToken());
	    	tmp1[i] = i + 1;
	    }
	    Cube result = new Cube(0, tmp, "");
	    Cube start = new Cube(0, tmp1, "");
	    Queue<Cube> q = new LinkedList<Cube>();
	    Set<Integer> flag = new HashSet<Integer>();
	    q.add(start);
	    Cube c = null;
	    while (!q.isEmpty()){
	    	c = q.remove();
	    	if (c.equals(result)) break;
	    	int key = c.getKey();
	    	if (flag.contains(key)) continue;
	    	flag.add(key);
	    	q.add(c.oprA());
	    	q.add(c.oprB());
	    	q.add(c.oprC());
	    }
	    out.println(c.n);
	    out.println(c.seq);
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}
