import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    int[] arr = new int[n];

    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(s[i]);
    }

    // dp[i][j] means minimum number of skip points if ith boss is killed in jth case:
    // 0: killed by myself and previous one is killed by my friend
    // 1: killed by myself and previous one was killed by myself
    // 2: killed by my friend and previous one was killed by me
    // 3: killed by my friend and previous one was also killed by him
    // so: 
    // dp[i][0] = min(dp[i-1][2], dp[i-1][3])
    // dp[i][1] = dp[i-1][0]
    // dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + (arr[i] == 1)
    // dp[i][3] = dp[i-1][2] + (arr[i] == 1)
    int[][] dp = new int[n][4];

    for (int i=0; i<n; i++) {
      if (i == 0) {
        dp[i][0] = Integer.MAX_VALUE;
        dp[i][1] = Integer.MAX_VALUE;
        dp[i][2] = arr[i];
        dp[i][3] = arr[i];
      } else {
        dp[i][0] = Math.min(dp[i-1][2], dp[i-1][3]);
        dp[i][1] = dp[i-1][0];
        dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(dp[i-1][0], dp[i-1][1]) + arr[i];
        dp[i][3] = dp[i-1][2] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i-1][2] + arr[i];
      }
    }

    int min = Integer.MAX_VALUE;
    for (int i=0; i<4; i++) {
      min = Math.min(dp[n-1][i], min);
    }

    System.out.println(min);


  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int trials = Integer.parseInt(br.readLine());
      for (int trial=1; trial <= trials; trial++) {
        solve(br);
      }
    }
  }
}