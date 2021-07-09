import java.io.*;
import java.util.Arrays;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    int q = Integer.parseInt(s[1]);
    // read cards
    s = br.readLine().split(" ");
    int[] cards = new int[n];
    for (int i=0; i<n; i++) {
      cards[i] = Integer.parseInt(s[i]);
    }

    // read queries
    s = br.readLine().split(" ");
    int[] queries = new int[q];
    for (int i=0; i<q; i++) {
      queries[i] = Integer.parseInt(s[i]);
    }

    // we only need to care about the first card of each color
    // there are at most 50 colors
    int[] pos = new int[51];
    for (int i=0; i<n; i++) {
      if (pos[cards[i]] == 0) {
        pos[cards[i]] = i+1;
      }

    }
    StringBuilder sb = new StringBuilder();
    // then we brute force
    for (int t : queries) {
      int idx = pos[t];
      // update idx
      for (int c=1; c<51; c++) {
        if (pos[c] > 0 && pos[c] < idx) {
          pos[c]++;
        }
      }
      pos[t] = 1;
      sb.append(idx);
      sb.append(" ");
    }
    System.out.println(sb.toString());
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    solve(br);
  }
}
