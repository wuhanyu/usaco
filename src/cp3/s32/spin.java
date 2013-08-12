package cp3.s32;
/*
ID: wuhanyu1
LANG: JAVA
TASK: spin
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class spin{
	public static PrintWriter out;
	public static int N;
	public static int[][] wedge = new int[5][11];
	public static int[] speed = new int[5];
	
	public static boolean isFit(int angle, int start, int extend){
		if (angle >= start && angle <= start + extend) return true;
		angle += 360;
		if (angle >= start && angle <= start + extend) return true;
		return false;
	}
	
	public static boolean isAlignedAngle(int angle){
		for (int i = 0; i < 5; i++){
			boolean flag = false;
			for (int j = 0; j < wedge[i][0]; j++){
				if (isFit(angle, wedge[i][2 * j + 1], wedge[i][2 * j + 2])){
					flag = true;
					break;
				}
			}
			if (!flag) return false;
		}
		return true;
	}

	public static boolean isAligned(){
		for (int i = 0; i < 360; i++){
			if (isAlignedAngle(i)){
				return true;
			}
		}
		return false;
	}
	
	public static void rotate(){
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < wedge[i][0]; j++){
				wedge[i][j * 2 + 1] = (wedge[i][j * 2 + 1] + speed[i]) % 360;
			}
		}
	}

	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("spin.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
	    
	    for (int i = 0; i < 5; i++){
	    	StringTokenizer st = new StringTokenizer(f.readLine());
	    	speed[i] = Integer.parseInt(st.nextToken());
	    	wedge[i][0] = Integer.parseInt(st.nextToken());
	    	for (int j = 0; j < wedge[i][0] * 2; j++)
	    		wedge[i][j + 1] = Integer.parseInt(st.nextToken());
	    }
	    int i = 0;
	    while (i < 360){
	    	if (isAligned()){
	    		break;
	    	}
	    	rotate();
	    	i++;
	    }
	    if (i == 360){
	    	out.println("none");
	    } else {
	    	out.println(i);
	    }
	    
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}
