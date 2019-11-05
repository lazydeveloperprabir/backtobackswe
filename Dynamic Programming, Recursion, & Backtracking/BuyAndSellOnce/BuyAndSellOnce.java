/*
  Best Time to Buy and Sell Stock - LeetCode: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
  This code passes all Leetcode test cases as of Oct. 27 2019
*/

class LinearSpace {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }

    /*
      opt[i] denotes the best profit that we can make
      if we sell on day i. The answer can be 1 of 2 options:

      1.) Lengthen opt[i - 1]: The best achieved yesterday
      with the profit we can reap waiting 1 more day (to
      sell today - day i)

      2.) Don't lengthen opt[i - 1]: If we decide to sell today
      (day i) we will lose money (go below $0 profit).

      To answer opt[i] we just decide to buy today (buy on day i)
      and we are back to a total profit of 0 ending at day i as
      we could not extend yesterday's profit without losing money.

      The Bigger Picture: We know that an optimal answer will end
      on one of these days. If we answer opt[i] for all days, we
      know we will capture the optimal solution when the algorithm
      is run.
    */
    int[] opt = new int[prices.length];
    opt[0] = 0;

    int globalMax = 0;
    for (int i = 1; i < prices.length; i++) {
      int profitDelta = prices[i] - prices[i - 1];

      opt[i] = Math.max(0, opt[i - 1] + profitDelta);
      globalMax = Math.max(globalMax, opt[i]);
    }

    return globalMax;
  }
}

class ConstantSpace {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }

    // Only 1 thing matters: lowest price seen so far, 'globalMin'
    int globalMax = 0;
    int globalMin = prices[0];

    for (int i = 1; i < prices.length; i++) {
      globalMin = Math.min(globalMin, prices[i]);
      globalMax = Math.max(globalMax, prices[i] - globalMin);
    }

    return globalMax;
  }
}
