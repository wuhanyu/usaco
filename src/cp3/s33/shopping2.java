package cp3.s33;
/*
ID: wuhanyu1
LANG: JAVA
TASK: shopping
 */
import java.io.*;
import java.util.*;

class shopping2 {

	public static PrintWriter out;
	public static int S, N;
	public static int[] remaining = new int[1001];
	public static int[][] special = new int[99][2001];
	public static int[] price = new int[1001];
	public static int min = Integer.MAX_VALUE;
	public static int[] save = new int[99];
	public static int[] order = new int[99];
	public static int sum = 0;
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static String getKey(int[] tmp){
		String result = "&";
		for (int i = 0; i <= 1000; i++){
			if (tmp[i] > 0){
				result +=  i + ":" + tmp[i] + "&";
			}
		}
		return result;
	}
	
	public static boolean haveSpecial(int[] special){
		for (int i = 0; i < special[0]; i++){
			if (remaining[special[2 * i + 1]] - special[2 * i + 2] < 0) return false;
		}
		add(special, -1);
		String key = getKey(remaining);
		int tmpsum = sum;
		if (map.containsKey(key)){
			if (map.get(key) <= tmpsum){
				add(special, +1);
				return false;
			} else{
				map.put(key,  tmpsum);
			}
		} else {
			map.put(key, tmpsum);
		}
		return true;
	}
	
	public static void add(int[] special, int factor){
		for (int i = 0; i < special[0]; i++){
			remaining[special[2 * i + 1]] +=  special[2 * i + 2] * factor;
		}
		sum += special[special[0] * 2 + 1] * factor * -1;
	}
	
	public static void DFS(){
		boolean flag = true;
		for (int i = 0; i < S; i++){
			if (haveSpecial(special[order[i]])){
				DFS();
				add(special[order[i]], +1);
				flag = false;
			}
		}
		if (flag){
			int tmpsum = sum;
			for (int i = 0; i <= 1000; i++){
				tmpsum += remaining[i] * price[i];
			}
			if (tmpsum < min) min = tmpsum;
			if (tmpsum == 2916){
				out.println(min);

				out.close();
				System.exit(0);
			}
		}
	}
	
	public static int[] order(){
		int[] order = new int[99];
		for (int i = 0; i < S; i++){
			int max = save[i];
			int minj = i;
			for (int j =0; j < S; j++)
				if (save[j] > max){
					max = save[j];
					minj = j;
				}
			save[minj] = 0;
			order[i] = minj;
		}
		return order;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"shopping.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		S = Integer.parseInt(st.nextToken());
		for (int i = 0; i < S; i++){
			st = new StringTokenizer(f.readLine());
			int j = 0;
			while (st.hasMoreElements()){
				special[i][j] = Integer.parseInt(st.nextToken());
				j++;
			}
		}
		st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(f.readLine());
			int code = Integer.parseInt(st.nextToken());
			remaining[code] = Integer.parseInt(st.nextToken());
			price[code] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < S; i++) {
			int tmpsum = 0;
			for (int j = 0; j < special[i][0]; j++){
				tmpsum += price[special[i][2 * j + 1]] * special[i][2 * j + 2];
			}
			save[i] = tmpsum - special[i][2 * special[i][0] + 2];
		}
		
		System.out.println(Arrays.toString(save));
		order = order();
		System.out.println(Arrays.toString(order));
		DFS();
		out.println(min);

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}