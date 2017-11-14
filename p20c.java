import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class p20c {
	public static void main(String... arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertices = Integer.parseInt(st.nextToken()), edges = Integer.parseInt(st.nextToken());
		Map<Integer, HashMap<Integer, Integer>> nodes = new HashMap<>();
		for (int i = 0; i < edges; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1, n2 = Integer.parseInt(st.nextToken()) - 1,
					weight = Integer.parseInt(st.nextToken());
			HashMap<Integer, Integer> node1 = nodes.get(n1);
			if (node1 == null)
				node1 = new HashMap<Integer, Integer>();
			node1.put(n2, weight);
			nodes.put(n1, node1);
			HashMap<Integer, Integer> node2 = nodes.get(n2);
			if (node2 == null)
				node2 = new HashMap<Integer, Integer>();
			node2.put(n1, weight);
			nodes.put(n2, node2);

		}
		dijkstra(0, vertices - 1, vertices, nodes);
	}

	private static void dijkstra(int a, int b, int vertices, Map<Integer, HashMap<Integer, Integer>> nodes) {
		Set<Integer> visited = new HashSet<>();
		int[] dist = new int[vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[a] = 0;
		int[] parent = new int[vertices];
		parent[0] = -1;

		Queue<Node> pq = new PriorityQueue<>(new Node());
		pq.add(new Node(a, dist[a]));
		while (!pq.isEmpty()) {
			Node n = pq.remove();
			if (n.node == b) {
				printPath(parent, b);
				return;
			}
			if (visited.contains(n))
				continue;
			visited.add(n.node);
			if (nodes.get(n.node) == null)
				continue;
			for (Integer nbr : nodes.get(n.node).keySet()) {
				if (!visited.contains(nbr)) {
					int newDist = dist[n.node] + nodes.get(n.node).get(nbr);
					if (newDist < dist[nbr]) {
						parent[nbr] = n.node;
						dist[nbr] = newDist;
					}
					pq.add(new Node(nbr, dist[nbr]));
				}
			}
		}
		System.out.println(-1);
	}

	static void printPath(int parent[], int j) {
		if (j != 0)
			printPath(parent, parent[j]);
		System.out.print(j + 1 + " ");
	}
}

class Node implements Comparator<Node> {
	int node, cost;

	public Node() {
	}

	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compare(Node node1, Node node2) {
		if (node1.cost < node2.cost)
			return -1;
		if (node1.cost > node2.cost)
			return 1;
		return 0;
	}
}