import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    // just shift element by 1
    StringBuilder sb = new StringBuilder();
    sb.append(String.valueOf(n));
    sb.append(" ");
    for (int i=1; i<n; i++) {
      sb.append(i);
      sb.append(" ");
    }
    System.out.println(sb.toString());
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
