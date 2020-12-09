import java.io.*;
import java.util.*;


public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String s = br.readLine();

    char[] charArr = s.toCharArray();
    Arrays.sort(charArr);

    s = new String(charArr);

    System.out.println(s);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
