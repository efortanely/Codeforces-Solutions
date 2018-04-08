import java.util.*;
import java.io.*;

//@author Rose
public class p448d {
	public static void main(String[] s) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long rows = Long.parseLong(st.nextToken());
		long cols = Long.parseLong(st.nextToken());
		long kthLargestNum = Long.parseLong(st.nextToken());

		System.out.println(p448d.binarySearch(rows, cols, kthLargestNum));
	}

	public static long binarySearch(long rows, long cols, long kthLargestNum) {
		long lo = 1;
		long hi = rows * cols;

		if (kthLargestNum == 1)
			return 1;
		else if (kthLargestNum == hi)
			return hi;

		while (lo + 1 < hi) {
			long mid = lo + (hi - lo) / 2;
			if (p448d.tooFewNumbers(rows, cols, kthLargestNum, mid))
				lo = mid;
			else
				hi = mid;
		}

		return hi;
	}

	// retrieve the number of values less than the middle in each row
	public static boolean tooFewNumbers(long rows, long cols, long kthLargestNum, long mid) {
		long sum = 0;
		for (int i = 1; i <= rows; i++)
			sum += Math.min(mid / i, cols);
		return sum < kthLargestNum;
	}
}
