package cp1.s14;
/*
ID: wuhanyu1
LANG: JAVA
TASK: packrec
*/
import java.io.*;
import java.util.*;

class packrec{
    public static int minArea = 100000;
    public static int[] resultWidth = new int[100];
    public static int count = 0;
    public static boolean[] getOrder(int t){
    	boolean[] result = new boolean[4];
    	for (int i = 0; i < 4; i++){
    		int p = t % 2;
    		t = t / 2;
    		if (p == 0) result[i] = true;
    		else result[i] = false;
    	}
    	return result;
    }
    
    public static void validResult(int width, int height){
    	int area = width * height;
    	if (area < minArea){
    		minArea = area;
    		if (width > height) width = height;
    		resultWidth[0] = width;
    		count = 1;
    	} else if (area == minArea){
    		if (width > height) width = height;
    		int p = 0;
    		while (width > resultWidth[p] && p < count){
    			p++;
    		}
    		if (p < count && resultWidth[p] == width) return;
    		if (p < count){
    			for (int i = count; i > p; i--) resultWidth[i] = resultWidth[i-1];
    			resultWidth[p] = width;
    		} else {
    			resultWidth[count] = width;
    		}
    		count++;
    	}
    }
    
	public static void calCase1(int[] width, int[] height){
		int rwidth = 0;
		int rheight = 0;
		for (int s = 0; s < 4; s++){
			rwidth += width[s];
			if (height[s] > rheight) rheight = height[s];
		}
		validResult(rwidth, rheight);
	}
	
	public static void calCase2(int[] width, int[] height){
		int rwidth = 0;
		int rheight = 0;
		for (int s = 0; s < 3; s++){
			rwidth += width[s];
			if (height[s] > rheight) rheight = height[s];
		}
		rheight += height[3];
		if (width[3] > rwidth) rwidth = width[3];
		validResult(rwidth, rheight);
	}

	public static void calCase3(int[] width, int[] height){
		int rwidth = 0;
		int rheight = 0;
		for (int s = 0; s < 2; s++){
			rwidth += width[s];
			if (height[s] > rheight) rheight = height[s];
		}
		rheight += height[2];
		if (width[2] > rwidth) rwidth = width[2];
		rwidth += width[3];
		if (height[3] > rheight) rheight = height[3];
		validResult(rwidth, rheight);
	}
	
	public static void calCase4(int[] width, int[] height){
		int rwidth = width[0];
		int rheight = height[0];
		if (width[1] > rwidth) rwidth = width[1];
		rheight += height[1];
		rwidth += width[2];
		if (height[2] > rheight) rheight = height[2];
		rwidth += width[3];
		if (height[3] > rheight) rheight = height[3];
		validResult(rwidth, rheight);
	}
	
	public static void calCase5(int[] width, int[] height){
		int rwidth = width[0];
		int rheight = height[0];
		if (width[1] < rwidth) return;
		rwidth = width[1];
		rheight += height[1];
		rwidth += width[2];
		if (height[1] > height[2]) return;
		if (height[2] > rheight) return;
		if (width[0] + width[3] > rwidth) return;
		if (height[2] + height[3] > rheight) rheight = height[2] + height[3];
		validResult(rwidth, rheight);
	}
	
	public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("packrec.in"));                    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
	    // Use StringTokenizer vs. readLine/split -- lots faster
	    int[] width = new int[4];
	    int[] height = new int[4];
	    for (int i = 0; i < 4; i++){
	    	StringTokenizer st = new StringTokenizer(f.readLine());
	        width[i] = Integer.parseInt(st.nextToken());   
	        height[i] = Integer.parseInt(st.nextToken());
	    }
	    

	    
	    for (int i = 0; i < 4; i++){
	    	for (int j = 0; j < 4; j++){
	    		for (int k = 0; k < 4; k++){
	    			for (int p = 0; p < 4; p++){
	    				if (i == j || j == k || k == p || i == k || i == p || j == p) continue;
	    				for (int t = 0; t < 16; t++){
	    					boolean[] flag = getOrder(t);
	    					int[] order = new int[4];
	    					order[0] = i;
	    					order[1] = j;
	    					order[2] = k;
	    					order[3] = p;
	    					int[] newWidth = new int[4];
	    					int[] newHeight = new int[4];
	    					for (int s = 0; s < 4; s++){
	    						if (flag[s]){
	    							newWidth[s] = width[order[s]];
	    							newHeight[s] = height[order[s]];
	    						} else {
	    							newWidth[s] = height[order[s]];
	    							newHeight[s] = width[order[s]];				
	    						}
	    					}
	    					calCase1(newWidth, newHeight);
	    					calCase2(newWidth, newHeight);
	    					calCase3(newWidth, newHeight);
	    					calCase4(newWidth, newHeight);
	    					calCase5(newWidth, newHeight);
	    				}
	    			}
	    		}
	    	}
	    }
//	    System.out.println(minArea);
//	    for (int i = 0; i < count; i++){
//	    	System.out.println(resultWidth[i] + " " + minArea / resultWidth[i]);
//	    }
	    out.println(minArea);
	    for (int i = 0; i < count; i++){
	    	out.println(resultWidth[i] + " " + minArea / resultWidth[i]);
	    }
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}