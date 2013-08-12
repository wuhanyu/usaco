package cp2.s23;
/*
ID: wuhanyu1
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

public class concom2{
	public static PrintWriter out;
	public static int N;
	public static int[] control;
	public static ArrayList<Cons> result = new ArrayList<Cons>();
	
	public static void add(Cons c){
		int i = result.size() - 1;
		while (i >= 0 && 
				(result.get(i).mother > c.mother || 
						(result.get(i).mother == c.mother && result.get(i).child > c.child))){
			i--;
		}
		result.add(i + 1, c);
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("concom.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    N = Integer.parseInt(st.nextToken());
	    control = new int[10000];
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	int mother = Integer.parseInt(st.nextToken());
	    	int child = Integer.parseInt(st.nextToken());
	    	int rate = Integer.parseInt(st.nextToken());
	    	if (rate > 50){
	    		ArrayList<Integer> ancs = new ArrayList<Integer>();
	    		int anc = control[mother];
	    		ancs.add(mother);
	    		while (anc > 0){
	    			ancs.add(anc);
	    			anc = control[anc];
	    		}
	    		control[child] = mother;
	    		for (int j = ancs.size() - 1; j >= 0; j--){
	    			add(new Cons(ancs.get(j), child));
	    		}
	    	}
	    }
	    
	    for (int i = 0; i < result.size(); i++){
	    	out.println(result.get(i).mother + " " +result.get(i).child);
	    }
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

class Cons{
	public int mother, child;
	Cons(int mother, int child){
		this.mother = mother;
		this.child = child;
	}
}

