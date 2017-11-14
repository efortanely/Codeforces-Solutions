import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p520b {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int init = s.nextInt(), last = s.nextInt();
		bfs(init, last);
	}

	public static boolean bfs(int n1, int n2) {
		Queue<Node> queue = new LinkedList<Node>();
		Node first = new Node(n1, null);
		queue.add(first);
		while (!queue.isEmpty()) {
			Node n = queue.remove();
			if (n.getValue() == n2) {
				System.out.println(n.getNodeLayer());
				return true;
			}
			if (n.isVisited()) { // has been visited
				continue;
			}
			n.setVisited(true);

			int red = red(n.getValue()), blue = blue(n.getValue());
			if (red > 0 && red <= 10000 && !Node.exists(red))
				queue.add(new Node(red, n));
			if (blue > 0 && blue <= 10000 && !Node.exists(blue))
				queue.add(new Node(blue, n));
		}

		return false;
	}

	private static int red(int num) {
		return num *= 2;
	}

	private static int blue(int num) {
		return --num;
	}

	private static class Node {
		int val;
		boolean visited;
		int nodeLayer;
		static ArrayList<Integer> existingNodes = new ArrayList<Integer>();

		public Node(int val, Node parent) {
			existingNodes.add(val);
			this.val = val;
			this.visited = false;
			if (parent == null)
				this.nodeLayer = 0;
			else
				this.nodeLayer = parent.nodeLayer + 1;
			;
		}

		public boolean getVisited() {
			return visited;
		}

		public void setVisited(boolean v) {
			visited = v;
		}

		public int getValue() {
			return val;
		}

		public int getNodeLayer() {
			return nodeLayer;
		}

		public boolean isVisited() {
			return visited;
		}

		public static boolean exists(int n) {
			for (Integer i : existingNodes) {
				if (i.intValue() == n)
					return true;
			}
			return false;
		}

	}
}