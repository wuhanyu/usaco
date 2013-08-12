package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: fact4
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class fact4{
	public static PrintWriter out;
	public static int N;
	


	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());	    
	    N = Integer.parseInt(st.nextToken());
	    long remind = 1;
	    for (int i = 1; i <= N; i++){
	    	int mul = i;
	    	if (mul % 10 == 0) mul /= 10; 
	    	remind *= mul;
	    	if (remind % 10 == 0) remind /= 10;
	    	remind = remind % 100000;
	    }
	    if (remind % 10 == 0) remind /= 10;
	    out.println(remind % 10);

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}
