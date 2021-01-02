import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    // for any number except 1,2 and 8, divide it by n, (n-4 ops)
    // then divide n by 8 until 1 (max log8(n) <= 6 ops, since (n <= 2*10**5))
    // then divide 8 by 2 until 1 (3 ops)
    // so total <= n+5 ops
    int n = Integer.parseInt(br.readLine());
    List<String> res = new ArrayList<>();
    // case 1: n < 8, divide n from 3-n-1
    // then divide n by 2 until 1
    if (n < 8) {
      for (int i=3; i<n; i++) {
        res.add(String.format("%d %d", i, n));
      }
      // divide n by 2 if n > 3 until 0
      int r = n;
      if (r > 2) {
        while (r > 1) {
          r = r/2 + (r%2==0 ? 0 : 1);
          res.add(String.format("%d %d", n, 2));
        }
      }
    } else {
      // n >= 8
      // divide 3 to n-1 (except 8) by n
      for (int i=3; i<n; i++) {
        if (i!=8) {
          res.add(String.format("%d %d", i, n));
        }
      }
      // divide n by 8 until n -> 1
      int r = n;
      if (r > 8) {
        while (r > 1) {
          r = r/8 + (r%8==0 ? 0 : 1);
          res.add(String.format("%d %d", n, 8));
        }
      }
      // divide 8 by 2 until 1
      for (int k=0; k<3; k++) {
        res.add(String.format("%d %d", 8, 2));
      }  
    }
    System.out.println(res.size());
    for (String s : res) {
      System.out.println(s);
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
