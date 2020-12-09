import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    int k = Integer.parseInt(s[1]);

    // all points can be moved to the same point if and only if there exist some point i
    // such that all other points lie within the boundary
    int[][] points = new int[n][2];
    
    for (int i=0; i<n; i++) {
      s = br.readLine().split(" ");
      points[i][0] = Integer.parseInt(s[0]);
      points[i][1] = Integer.parseInt(s[1]);
    }

    for (int i=0; i<n; i++) {
      int count = 0;
      for (int j=0; j<n; j++) {
        if (j==i) continue;
        if (Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]) > k) {
          break;
        } else {
          count++;
        }
      }
      if (count == n-1) {
        System.out.println(1);
        return;
      }
    }
    
    System.out.println(-1);
    return;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
