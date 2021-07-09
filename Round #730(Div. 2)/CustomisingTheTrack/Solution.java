import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    long total = 0;
    for (int i=0; i<n; i++) {
      total += Long.parseLong(s[i]);
    }
    long k = (total%n);
    // the answer is k*(n-k), where k is sum(arr)%n
    System.out.println(k*(n-k));
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
