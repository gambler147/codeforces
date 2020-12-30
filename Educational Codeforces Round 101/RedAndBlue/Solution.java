import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    // simply find max prefix sum of r and s
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    // find max prefix sum of r
    int prefix = 0;
    int max_prefix_r = 0;
    for (int i=0; i<n; i++) {
      prefix += Integer.parseInt(s[i]);
      max_prefix_r = Math.max(max_prefix_r, prefix);
    }

    int m = Integer.parseInt(br.readLine());
    s = br.readLine().split(" ");
    // find max prefix sum of s
    prefix = 0;
    int max_prefix_s = 0;
    for (int i=0; i<m; i++) {
      prefix += Integer.parseInt(s[i]);
      max_prefix_s = Math.max(max_prefix_s, prefix);
    } 

    System.out.println(Math.max(max_prefix_r+max_prefix_s, 0));
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
