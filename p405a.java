import java.util.Scanner;

public class p405a {
	public static void main(String[] s) {
		Scanner sc = new Scanner(System.in);
		int numCol = sc.nextInt();
		int[] input = new int[numCol];

		for (int i = 0; i < numCol; i++) {
			input[i] = sc.nextInt();
		}

		for (int i = 0; i < input.length; i++) {
			for (int j = 1; j < input.length - i; j++) {
				if (input[j - 1] > input[j]) {
					int temp = input[j - 1];
					input[j - 1] = input[j];
					input[j] = temp;
				}
			}
		}

		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
	}
}