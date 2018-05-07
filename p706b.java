import java.io.*;
import java.util.*;

//@author Rose
public class p706b {

	// process input and sort soda prices to preprocess for binary search
	public static void main(String[] s) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numShops = Integer.parseInt(br.readLine());
		int[] sodaPrices = new int[numShops];
		StringTokenizer prices = new StringTokenizer(br.readLine());
		for (int i = 0; i < numShops; i++)
			sodaPrices[i] = Integer.parseInt(prices.nextToken());
		Arrays.sort(sodaPrices);

		int daysToBuy = Integer.parseInt(br.readLine());
		int[] coinsCanSpend = new int[daysToBuy];
		for (int i = 0; i < daysToBuy; i++)
			coinsCanSpend[i] = Integer.parseInt(br.readLine());

		for (Integer dailyBudget : coinsCanSpend)
			System.out.println(p706b.binarySearch(sodaPrices, dailyBudget));
	}

	public static boolean canAfford(int sodaPrice, int budget) {
		return sodaPrice <= budget;
	}

	// return the total number of sodas can buy in a sorted list of soda prices
	// returns number based on index in array
	public static int binarySearch(int[] allPrices, int budget) {
		int lo = 0;
		int hi = allPrices.length - 1;
		int mid = 0;

		if (!p706b.canAfford(allPrices[lo], budget))
			return lo;
		else if (p706b.canAfford(allPrices[hi], budget))
			return allPrices.length;

		while (lo + 1 < hi) {
			mid = lo + (hi - lo) / 2;
			if (p706b.canAfford(allPrices[mid], budget)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		// although low is true and hi is false, want to return +1 more value, because
		// of 0-indexing
		return hi;
	}
}
