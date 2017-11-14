import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class p378c {
	static final int SPACE = 0, WALL = 1, NEW_WALL = 2, FINAL_SPACE = 3;
	static int[][] maze;
	static int remove, r, c, spaces = 0, newNbrs = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		r = s.nextInt();
		c = s.nextInt();
		remove = s.nextInt();
		maze = new int[r][c];
		int i = 0;
		Stack<Integer[]> nbrs = new Stack<Integer[]>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals(""))
				continue;
			for (int j = 0; j < c; j++) {
				if (line.charAt(j) == '#') {
					maze[i][j] = WALL;
				} else {
					maze[i][j] = SPACE;
					spaces++;
					if (spaces == 1) {
						nbrs.push(new Integer[] { i, j });
					}
				}
			}
			i++;
		}

		if (remove != 0) {
			while (newNbrs < spaces - remove && !nbrs.isEmpty()) {
				Integer[] curr = nbrs.pop();
				for (Integer[] nbr : getNeighbors(curr[0], curr[1]))
					nbrs.push(nbr);
			}

			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					String val = "";
					if (maze[j][k] == WALL)
						val = "#";
					else if (maze[j][k] == SPACE)
						val = "X";
					else if (maze[j][k] == FINAL_SPACE)
						val = ".";
					System.out.print(val);
				}
				System.out.println("");
			}
		} else {
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					String val = "";
					if (maze[j][k] == WALL)
						val = "#";
					else if (maze[j][k] == SPACE)
						val = ".";
					System.out.print(val);
				}
				System.out.println("");
			}
		}

	}

	private static ArrayList<Integer[]> getNeighbors(int r, int c) {
		if (maze[r][c] == SPACE && newNbrs < spaces - remove) {
			maze[r][c] = FINAL_SPACE;
			newNbrs++;
		}
		int top = (r == 0) ? -1 : r - 1;
		int bottom = (r == maze.length - 1) ? -1 : r + 1;
		int left = (c == 0) ? -1 : c - 1;
		int right = (c == maze[r].length - 1) ? -1 : c + 1;

		int neighbors[][] = { { top, c }, { r, left }, { r, right }, { bottom, c } };
		ArrayList<Integer[]> nbrs = new ArrayList<Integer[]>();
		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i][0] == -1 || neighbors[i][1] == -1)
				continue;
			if (maze[neighbors[i][0]][neighbors[i][1]] == SPACE && newNbrs < spaces - remove) {
				maze[neighbors[i][0]][neighbors[i][1]] = FINAL_SPACE;
				nbrs.add(new Integer[] { neighbors[i][0], neighbors[i][1] });
				newNbrs++;
			}
		}
		return nbrs;
	}
}