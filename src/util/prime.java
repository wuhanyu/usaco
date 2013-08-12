package util;
/*
ID: wuhanyu1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class prime{
	public static boolean[] flag;
	public static int upper;
	
	public static void createTable(){
		upper = (int)Math.sqrt(Integer.MAX_VALUE/2) + 1;
		flag = new boolean[upper+1];
		flag[0] = true;
		flag[1] = true;
		
		for (int i = 2; i <= upper; i++){
			if (!flag[i]){
				int j = i;
				while (j <= upper - i){
					j += i;
					flag[j] = true;
				}
			}			
		}
	}
	public static boolean checkPrime(int number){
		if (number <= upper) return !flag[number];
		else{
			int k = (int)Math.sqrt(number/2) + 1;
			for (int j = 2; j < k; j++){
				if (!flag[j] && (number % j == 0)) return false;
			}
			return true;
		}
	}

	public static void main (String [] args) throws IOException {
		createTable();
	    for (int i = 0; i < Integer.MAX_VALUE; i++){
	    	if (checkPrime(i)) System.out.println(i);
	    }
	}
}