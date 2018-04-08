import java.util.*;

public class GravityFlip {
	public static void main(String[] s){
		Scanner sc = new Scanner(System.in);
		int numCol = sc.nextInt();
		int[] input = new int[numCol];
		
		for(int i = 0; i < numCol; i++)
			input[i] = sc.nextInt();
		
		Arrays.sort(input);
		
		for(int i = 0; i < input.length; i++)
			System.out.print(input[i] + " ");
	}
}
