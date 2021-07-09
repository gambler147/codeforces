import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int b = Integer.parseInt(s[1]);
    int c = Integer.parseInt(s[2]);

    // simply set the gcd to be 10^c
    // and try to find two numbers with decimal digits a-c and b-c 
    // respectively that are coprime. If either is 0, then just return
    
    b -= c;
    a -= c;

    int gcd = (int) Math.pow(10, c-1);
    String sa;
    String sb;
    if (a == 0) {
      sa = String.valueOf(gcd);
      sb = String.valueOf(gcd * (int) Math.pow(10, b));
    } else if (b == 0) {
      sa = String.valueOf(gcd * (int) Math.pow(10, a));
      sb = String.valueOf(gcd);
    } else {
      // set sa = "1" + "0".repeat(a-1)
      // set sb = prime number with b digit
      sa = String.valueOf(gcd * (int) Math.pow(10, a));
      sb = String.valueOf(gcd * getPower3(b+1));
    }

    System.out.println(sa + " " + sb);
  }

  public static int getPower3(int k) {
    // return a number of power of 3 with k digit
    int res = 1;
    while (res < (int) Math.pow(10, k-1)) {
      res *= 3;
    }
    return res;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
