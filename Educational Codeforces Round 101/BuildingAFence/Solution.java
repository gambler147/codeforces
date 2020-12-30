import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    // for each i, find the range of top of the fence
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    int k = Integer.parseInt(s[1]);

    s = br.readLine().split(" ");
    int[] heights = new int[n];
    for (int i=0; i<n; i++) {
      heights[i] = Integer.parseInt(s[i]);
    }

    int low = heights[0]+k;
    int high = heights[0]+k;
    for (int i=1; i<n; i++) {
      low = Math.max(heights[i]+k, low-(k-1));
      high = Math.min(heights[i]+2*k-1, high+(k-1));
      if (high < low) {
        System.out.println("NO");
        return;
      }
    }
    if (low != heights[n-1]+k) {
      System.out.println("NO");
    } else {
      System.out.println("YES");
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
