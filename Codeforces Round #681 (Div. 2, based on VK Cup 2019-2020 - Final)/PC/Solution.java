import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    // read restaurants delivery time
    String[] s = br.readLine().split(" ");
    int[] a = new int[n];
    for (int i=0; i<n; i++) {
      a[i] = Integer.parseInt(s[i]);
    }
    // read pick up time
    s = br.readLine().split(" ");
    int[] b = new int[n];
    for (int i=0; i<n; i++) {
      b[i] = Integer.parseInt(s[i]);
    }

    // notice the fact the total time for delivery depends on the maximum delivery time for restaurants
    // we can sort array a descending
    int[] idx = IntStream.range(0, n).boxed().sorted((x, y) -> a[y] - a[x]).mapToInt(ele -> ele).toArray();

    int res = a[idx[0]];
    int ptime = 0;
    int i = 0;
    while (i < n && ptime < a[idx[i]]) {
      ptime += b[idx[i]];
      int cur = i+1 < n ? Math.max(ptime, a[idx[i+1]]) : ptime;
      res = Math.min(res, cur);
      i++;
    }

    System.out.println(res);

  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int t = Integer.parseInt(br.readLine());
      while (t-- > 0) {
        solve(br);
      }
    }
  }
}