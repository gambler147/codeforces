import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    HashMap<Integer, Integer> counter = new HashMap<>();
    HashMap<Integer, Integer> tags = new HashMap<>();
    int cut = 0;
    int prev = -1;
    String[] s = br.readLine().split(" ");
    for (int i=0; i<n; i++) {
      int v = Integer.parseInt(s[i]);
      counter.put(v, counter.getOrDefault(v, 0)+1);
      if (v == prev) {
        tags.put(v, tags.getOrDefault(v,0)+2);
        cut++;
      }

      if (i == 0 || i == n-1) {
        tags.put(v, tags.getOrDefault(v, 0)+1);
      }
      prev = v;
    }

    // the solution exists if and only if counter[x] <= ceil(n/2) for each x
    int max_count = 0;
    int occurences = 0;
    for (int key : tags.keySet()) {
      int c = tags.get(key);
      if (c > max_count) {
        max_count = c;
        occurences = counter.get(key);
      }
    }
    
    if (occurences > Math.ceil(1.0*n/2)) {
      System.out.println(-1);
      return;
    }

    // otherwise, return k + max(0, max_count - (cut+2))
    System.out.println(cut + Math.max(0, max_count - (cut+2)));

  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
