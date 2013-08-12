package cp2.s21;
/*
ID: wuhanyu1
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;
	
class castle{
	public static int N, M;
	public static int[][] castle;
	public static int[][] flag;
	public static int[] size;
	public static boolean[][] conj;
	public static int count = 0;
	public static int p;
	public static PrintWriter out;
	
	public static void explore(int x, int y, int order){
		if (flag[x][y] >= 0) return;
		int module = castle[x][y];
		size[order]++;
		flag[x][y] = order;
		boolean[] walls = new boolean[4];
		//walls: west, north, east, south
		for (int i = 0; i < 4; i++){
			if (module % 2 != 0) walls[i] = true;
			module = module / 2;
		}
		if (!walls[0] && y > 0) explore(x, y-1, order);
		if (!walls[1] && x > 0) explore(x-1, y, order);
		if (!walls[2] && y < M - 1) explore(x, y+1, order);
		if (!walls[3] && x < N - 1) explore(x+1, y, order);
	}
	
	public static void checkMax(){
	    char direction = 'N';
	    int max = 0;
	    int x = 0, y = 0;
	    for (int j = 0; j < M; j++){
	    	for (int i = N - 1; i >= 0; i--){
	    		if (i > 0 && (flag[i][j] != flag[i-1][j])){
	    			if (size[flag[i][j]] + size[flag[i-1][j]] > max){
	    				max = size[flag[i][j]] + size[flag[i-1][j]];
	    				x = i;
	    				y = j;
	    				direction = 'N';
	    			}
	    		}
	    		if (j < M - 1 && (flag[i][j] != flag[i][j + 1])){
	    			if (size[flag[i][j]] + size[flag[i][j+1]] > max){
	    				max = size[flag[i][j]] + size[flag[i][j+1]];
	    				x = i;
	    				y = j;
	    				direction = 'E';
	    			}
	    		}
	    	}
	    }
	    out.println(max);
	    out.print((x+1) + " ");
	    out.print((y+1) + " ");
	    out.println(direction);
	}
	
	public static void process(){
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < M; j++){
	    		if (flag[i][j] < 0){
	    			size[count] = 0;
	    			explore(i, j, count);
	    			count++;		
	    		}
	    	}
	    }
	}
	
	public static void printMatrix(int[][] matrix){
		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("castle.in"));                    
	    out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    M = Integer.parseInt(st.nextToken());   
	    N = Integer.parseInt(st.nextToken());
	    castle = new int[N][M];
	    flag = new int[N][M];
	    size = new int[M * N];
	    conj = new boolean[M * N][M * N];
	    for (int i = 0; i < N; i++){
	    	st = new StringTokenizer(f.readLine());
	    	for (int j = 0; j < M; j++){
	    		castle[i][j] = Integer.parseInt(st.nextToken());
	    		flag[i][j] = -1;
	    	}
	    }
	    
	    process();
	    printMatrix(castle);
	    printMatrix(flag);
	    out.println(count);
	    int max = 0;
	    for (int i = 0; i < count; i++){
	    	if (size[i] > max) max = size[i];
	    }
	    out.println(max);
	    checkMax();
	    out.close(); 
	                                     
	    System.exit(0);                              
	}
}