class Solution {
    public int maxProfit(int[] prices) {
        
        int n = prices.length;
		int minP = Integer.MAX_VALUE;
		int maxP = 0;

		for (int i : prices) {

			if (i < minP) {
				minP = i;
			}

			else {
				int p = i - minP;
				maxP = Math.max(maxP, p);
			}
		}

		return maxP;
    }
}