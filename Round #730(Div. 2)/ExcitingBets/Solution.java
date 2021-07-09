import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    long a = Long.parseLong(s[0]);
    long b = Long.parseLong(s[1]);        
    // if a == b, print 0 0
    // otherwise, max gcd is max(a,b) - min(a,b) 
    long diff = Math.max(a,b) - Math.min(a,b);
    if (diff == 0) {
      System.out.println("0 0");
    } else {
      // operations needed is min(a%diff, diff - a%diff)
      System.out.format("%s %s%n", diff, Math.min(a%diff, diff-a%diff));
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
