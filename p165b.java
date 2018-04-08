import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p165b {
	public static void main(String[] s) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		System.out.println(p165b.binarySearch(n, k));
	}

	public static boolean finishesProgram(int v, int k, int n) {
		int sum = 0;
		for (int curTerm = v; sum < n && curTerm != 0; curTerm /= k)
			sum += curTerm;
		return sum >= n;
	}

	public static int binarySearch(int n, int k) {
		int lo = 0;
		int hi = n;

		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;
			if (p165b.finishesProgram(mid, k, n))
				hi = mid;
			else
				lo = mid;
		}

		return hi;
	}
}
