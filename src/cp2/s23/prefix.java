package cp2.s23;
/*
ID: wuhanyu1
LANG: JAVA
TASK: prefix
*/
import java.io.*;
import java.util.*;

class prefix{
	public static PrintWriter out;
	public static ArrayList<String> primitives = new ArrayList<String>();
	public static boolean[] flag = new boolean[200001];
	public static int count = 0;
	
	public static String getPastStr(String line, int i, int len){
		if (i + 1 - len < 0) return null;
		return line.substring(i + 1 - len, i + 1);
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("prefix.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
	    
	    String token;
	    StringTokenizer st;
	    while (true){
	    	st = new StringTokenizer(f.readLine());
	    	token = st.nextToken();
	    	if (!token.equals(".")){
	    		primitives.add(token);
	    		while (st.hasMoreTokens()){
	    			token = st.nextToken();
	    			primitives.add(token);
	    		}
	    	} else {
	    		break;
	    	}
	    }
	    String line = "";
	    line = f.readLine();
	    String oldline = "";
	    flag[0] = true;
	    while (line != null){
	    	String processline = oldline + line;
	    	int start = oldline.length();
	    	int end = start + line.length();
	    	for (int i = start; i < end; i++){
	    		count++;
	    		for (int j = 0; j < primitives.size(); j++){
	    			int len = primitives.get(j).length();
	    			String paststr = getPastStr(processline, i, len);
	    			if (paststr != null && (count - len >= 0) && flag[count-len] && paststr.equals(primitives.get(j))){
	    				flag[count] = true;
	    				break;
	    			}
	    		}
	    	}
	    	oldline = line;
	    	line = f.readLine();
	    }
	    for (int i = 200000; i >= 0; i--){
	    	if (flag[i]){
	    		out.println(i);
	    		break;
	    	}
	    }
	    
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}