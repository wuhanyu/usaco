package cp3.s33;
/*
ID: wuhanyu1
LANG: JAVA
TASK: camelot
 */
import java.io.*;
import java.util.*;
class Point {
	public int x, y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(String x, String y){
		this.x = x.charAt(0) - 'A';
		this.y = Integer.parseInt(y) - 1;
	}
}

class camelot {

	public static PrintWriter out;
	public static int R, C;
	public static Point king;
	public static List<Point> knights = new ArrayList<Point>();
	public static int[][][][] d;
	public static int[][] v;
	public static int MAX = 10000;
	
	public static void move(int ox, int oy, int x, int y, int dx, int dy, Queue<Point> q){
		int tx, ty;
		tx = x + dx; ty = y + dy;
//		System.out.printf("%d %d %d %d %d %d\n", ox, oy, x, y, tx, ty);
		if ((tx >= 0) && (ty >= 0) && (tx < C) && (ty < R) && (d[oy][ox][y][x] + 1 < d[oy][ox][ty][tx])){
			d[oy][ox][ty][tx] = d[oy][ox][y][x] + 1;
			q.add(new Point(tx, ty));
		}		
	}
	
	public static int pick(int ox, int oy, int kx, int ky, int tx, int ty, int kingmove){
		if ((kx >= 0) && (ky >= 0) && (kx < C) && (ky < R)){
			return d[oy][ox][ky][kx] + d[ky][kx][ty][tx] + kingmove - d[oy][ox][ty][tx];
		} else {
			return MAX;
		}
	}
	
	public static void BFS(int x, int y){
		d[y][x][y][x] = 0;
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		
		while (!q.isEmpty()){
			Point p = q.remove();
			move(x, y, p.x, p.y, -2, -1, q);
			move(x, y, p.x, p.y, -2, +1, q);
			move(x, y, p.x, p.y, -1, -2, q);
			move(x, y, p.x, p.y, -1, +2, q);
			move(x, y, p.x, p.y, +1, +2, q);
			move(x, y, p.x, p.y, +1, -2, q);
			move(x, y, p.x, p.y, +2, +1, q);
			move(x, y, p.x, p.y, +2, -1, q);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter(
				"camelot.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		king = new Point(st.nextToken(), st.nextToken());
		String line = f.readLine();
		while (line != null){
			st = new StringTokenizer(line);
			while (st.hasMoreElements()){
				knights.add(new Point(st.nextToken(), st.nextToken()));
			}
			line = f.readLine();
		}
		d = new int[R][C][R][C];
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				for (int k = 0; k < R; k++){
					for (int p = 0; p < C; p++){
						d[i][j][k][p] = MAX;
					}
				}
			}
		}
		
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				BFS(j, i);
			}
		}
		
//		for (int i = 0; i < R; i++){
//			System.out.println(Arrays.toString(d[0][0][i]));
//		}
		v = new int[R][C];
		for (int k = 0; k < knights.size(); k++){
			int x = knights.get(k).x;
			int y = knights.get(k).y;
			for (int i = 0; i < R; i++){
				for (int j = 0; j < C; j++){
					v[i][j] += d[i][j][y][x];
				}
			}
		}
//		for (int i = 0; i < R; i++){
//			System.out.println(Arrays.toString(v[i]));
//		}
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				int min = MAX;
				for (int m = -2; m <= 2; m++){
					for (int n = -2; n <= 2; n++){
						int tx = king.x+m;
						int ty = king.y+n;
						int offset = 0;
						if (Math.abs(m) > 0 || Math.abs(n) > 0) offset = 1;
						if (Math.abs(m) > 1 || Math.abs(n) > 1) offset = 2;
						if ((tx >= 0) && (ty >= 0) && (tx < C) && (ty < R)){
							for (int k = 0; k < knights.size(); k++){
								int value = v[i][j] + pick(knights.get(k).x, knights.get(k).y, tx, ty, j, i, offset);
								if (value < min) min = value;
							}
						}
					}
				}
				v[i][j] = min;
			}
		}
		int min = MAX;
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				if (v[i][j] < min) min = v[i][j];
			}
		}
		if (knights.size() == 0){
			min = 0;
		}

//		for (int i = 0; i < R; i++){
//			System.out.println(Arrays.toString(v[i]));
//		}
		System.out.println(min);
		out.println(min);


		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}