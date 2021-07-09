import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    int k = Integer.parseInt(s[1]);

    // freq
    int[] freq = new int[k];
    int[][] pair_freq = new int[k][k];

    // start with 'a'
    int cur = 0;
    freq[0]++;
    List<Integer> res = new ArrayList<>();
    res.add(cur);
    for (int i=1; i<n; i++) {
      // find a number from 0 to k-1 that has least freq and least pair_freq
      int nxt = cur;
      for (int p=0; p<k; p++) {
        int j = (p+cur)%k;
        if (pair_freq[cur][j] < pair_freq[cur][nxt]) {
          nxt = j;
        }
      }
      freq[nxt]++;
      pair_freq[cur][nxt]++;
      cur=nxt;
      res.add(cur);
    }

    StringBuilder sb = new StringBuilder();
    for (int a : res) {
      sb.append((char) ('a' + a));
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    solve(br);
  }
}
