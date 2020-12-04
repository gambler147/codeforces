import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    for (int i=4*n; i>2*n; i -= 2) {
      sb.append(" " + String.valueOf(i));
    }

    System.out.println(sb.toString());
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