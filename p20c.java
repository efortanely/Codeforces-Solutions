import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p20c {
	public static void main(String[] s) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertices = Integer.parseInt(st.nextToken());
		int edges = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[vertices + 1];

		for (int i = 0; i < edges; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			nodes = p20c.addNode(n1, n2, weight, nodes);
			nodes = p20c.addNode(n2, n1, weight, nodes);
		}

		p20c.dijkstra(1, vertices, nodes);
	}

	private static Node[] addNode(int n1, int n2, int weight, Node[] nodes) {
		if (nodes[n1] == null)
			nodes[n1] = new Node(n1);
		nodes[n1].addNeighbor(n2, weight);
		return nodes;
	}

	public static void dijkstra(int start, int goal, Node[] nodes) {
		if (nodes[start] == null) {
			System.out.println("-1");
			return;
		}

		// this is the only thing that made the memory overflow, allocating boolean
		// flags for each visited used way too much space, since only a meager amount of
		// nodes will actually be looked at to find the path, creating a set to
		// represent which nodes have been visited takes less space
		HashSet<Integer> visited = new HashSet<>();
		Queue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
		Node n = nodes[start];
		n.cost = 0;
		pq.add(n);
		while (!pq.isEmpty() && n.node != goal) {
			n = pq.remove();
			if (visited.contains(n.node))
				continue;
			visited.add(n.node);
			for (Entry<Integer, Integer> path : n.edges.entrySet()) {
				Node nbr = nodes[path.getKey()];
				if (!visited.contains(nbr.node)) {
					nbr.addParent(n, n.cost + path.getValue());
					pq.add(nbr);
				}
			}
		}

		System.out.println(n.node == goal ? n.getPath() : "-1");
	}

	private static class Node {
		int node, cost;
		HashMap<Integer, Integer> edges;
		Node parent;

		public Node(int node) {
			this.node = node;
			this.cost = Integer.MAX_VALUE;
			this.edges = new HashMap<>();
		}

		public void addNeighbor(int nbr, int weight) {
			Integer prevWeight = this.edges.get(nbr);
			if (prevWeight == null || prevWeight != null && weight < prevWeight)
				this.edges.put(nbr, weight);
		}

		public void addParent(Node parent, int newCost) {
			if (newCost < this.cost) {
				this.parent = parent;
				this.cost = newCost;
			}
		}

		public StringBuilder builderPath() {
			if (this.node > 1)
				return this.parent.builderPath().append(" " + this.node);
			return new StringBuilder("1");
		}

		public String getPath() {
			return this.builderPath().toString();
		}
	}
}
