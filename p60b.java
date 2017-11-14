import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p60b {
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		int h = s.nextInt(), c = s.nextInt(), r = s.nextInt();
		boolean[][][] plate = new boolean[h][c][r];
		int[] startingPosition = new int[2];
		int numEmpty = 0;
		s.nextLine();
		s.nextLine();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < c; j++) {
				String line = s.nextLine();
				boolean[] row = new boolean[r];
				for (int k = 0; k < line.length(); k++) {
					if (line.charAt(k) == '.') {
						row[k] = false;
						numEmpty++;
					} else
						row[k] = true;
				}
				plate[i][j] = row;
			}
			s.nextLine();
		}

		int[] dx = { 0, 0, 1, -1, 0, 0 };
		int[] dy = { 0, 0, 0, 0, 1, -1 };
		int[] dz = { 1, -1, 0, 0, 0, 0 };

		for (int i = startingPosition.length - 1; i >= 0; i--)
			startingPosition[i] = s.nextInt();

		int minutes = 0;
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.add(new Integer[] { 0, startingPosition[1] - 1, startingPosition[0] - 1 });
		while (!q.isEmpty()) {
			Integer[] current = q.remove();
			int hc = current[0], cc = current[1], rc = current[2];
			if (plate[hc][cc][rc])
				continue;
			minutes++;
			plate[hc][cc][rc] = true;
			for (int i = 0; i < dx.length; i++) {
				int hcn = hc + dz[i];
				int rcn = rc + dy[i];
				int ccn = cc + dx[i];
				if (valid(hcn, h) && valid(rcn, r) && valid(ccn, c)) {
					q.add(new Integer[] { hcn, ccn, rcn });
				}
			}
		}

		System.out.println(minutes);

	}

	static boolean valid(int coord, int size) {
		return (coord >= 0 && coord <= size - 1);
	}
}