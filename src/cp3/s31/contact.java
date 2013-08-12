package cp3.s31;
/*
ID: wuhanyu1
LANG: JAVA
TASK: contact
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class contact{
	public static PrintWriter out;
	public static int A, B, N;
	
	public static void addMap(Map<String, Integer> m, String key, int inc){
		if (m.containsKey(key)){
			m.put(key, m.get(key) + inc);
		} else {
			m.put(key, inc);
		}
	}

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("contact.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
	    
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    B = Integer.parseInt(st.nextToken());
	    A = Integer.parseInt(st.nextToken());
	    
	    N = Integer.parseInt(st.nextToken());
	    String[] slices = new String[A - B + 1];
	    for (int i = 0; i < slices.length; i++) slices[i] = "";
	    Map<String, Integer> countS = new HashMap<String, Integer>();
	    int count = 0;
	    for (String line; (line=f.readLine()) != null; ){
	    	for (int i = 0; i < line.length(); i++){
	    		char c = line.charAt(i);
	    		count++;
	    		for (int j = 0; j < slices.length; j++){
	    			slices[j] += c;
	    			if (count >= B + j){
	    				slices[j] = slices[j].substring(slices[j].length() - B - j);
	    				addMap(countS, slices[j], 1);
	    			}
	    		}
	    	}
	    }
	    Str[] result = new Str[countS.entrySet().size()];
	    count = 0;
	    Iterator<Entry<String, Integer>> iter = countS.entrySet().iterator(); 
	    while (iter.hasNext()) { 
	        Map.Entry entry = (Map.Entry) iter.next(); 
	        String key = (String) entry.getKey(); 
	        int val = (Integer) entry.getValue();
	        result[count] = new Str(key, val);
	        count++;
	    } 
	    Arrays.sort(result);
	    int old = Integer.MAX_VALUE;
	    count = 0;
	    int breakcount = 0;
	    for (int i = 0; i < result.length; i++){
	    	if (result[i].count < old){
	    		count++;
	    		if (count == N + 1) break;
	    		if (i != 0) out.println();
	    		out.println(result[i].count);
	    		out.print(result[i].key);
	    		old = result[i].count;
	    		breakcount = 1;
	    	} else {
	    		if (breakcount < 6){
	    			out.print(" " + result[i].key);
	    			breakcount++;
	    		} else{
	    			out.println();
	    			out.print(result[i].key);
	    			breakcount = 1;
	    		}
	    	}
	    	
	    }
	    out.println();

	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}

class Str implements Comparable<Str>{
	String key;
	int count;
	public Str(String key, int count){
		this.key = key;
		this.count = count;
	}
	@Override
	public int compareTo(Str o) {
		if (count < o.count) return 1;
		else if (count > o.count) return -1;
		else{
			if (o.key.length() == this.key.length())
				return this.key.compareTo(o.key);
			else
				return this.key.length() - o.key.length();
		}
	}
}
