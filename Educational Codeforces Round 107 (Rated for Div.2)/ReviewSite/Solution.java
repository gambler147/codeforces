import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    // reviewer
    int res = 0;
    for (int i=0; i<n; i++) {
      int v = Integer.parseInt(s[i]);
      if (v == 1 || v == 3) {
        res++;
      }
    }
    System.out.println(res);


  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
