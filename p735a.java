import java.util.Scanner;

public class p735a {
	public static void main(String[] st) {
		Scanner s = new Scanner(System.in);
		s.nextInt();
		int jump = s.nextInt();
		String[] moves = s.next().split("");
		int ghi = indexOf("G", moves);
		int ti = indexOf("T", moves);
		if (ti < ghi) {
			for (int i = ti; i <= ghi; i += jump) {
				if (moves[i].equals("#")) {
					System.out.println("NO");
					System.exit(0);
				}
				if (i == ghi) {
					System.out.println("YES");
					System.exit(0);
				}
			}
			System.out.println("NO");
		} else if (ghi < ti) {
			for (int i = ghi; i <= ti; i += jump) {
				if (moves[i].equals("#")) {
					System.out.println("NO");
					System.exit(0);
				}
				if (i == ti) {
					System.out.println("YES");
					System.exit(0);
				}
			}
			System.out.println("NO");
		} else if (ti == ghi) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static int indexOf(String a, String arr[]) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(a)) {
				return i;
			}
		}
		return -1;
	}
}