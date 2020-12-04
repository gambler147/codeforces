import java.io.*;
import java.util.*;


public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    long n = Long.parseLong(br.readLine());
    List<Long[]> l = new ArrayList<>();
    // let n = p1^a1 * p2^a2 * ... pk^ak, then the result is max(a1, ... ak)
    for (long i=2; i*i<=n; i++) {
      long c = 0;
      while (n%i == 0) {
        c++;
        n/=i;
      }
      if (c > 0) l.add(new Long[] {i, c});
    }
    if (n>1) {l.add(new Long[] {n, (long) 1});};

    Collections.sort(l, (a,b) -> (int) (b[1] - a[1]));
    int m = l.get(0)[1].intValue();
    long[] res = new long[m];
    for (int i=0; i<m; i++) {
      res[i] = l.get(0)[0];
    }
    // the last element is multiplied by the rest values
    for (int i=1; i<l.size(); i++) {
      for (int j=0; j<l.get(i)[1]; j++) {
        res[res.length-1] *= l.get(i)[0];
      }
    }

    System.out.println(res.length);
    StringBuilder sb = new StringBuilder();
    for (long v:res) {
      sb.append(v);
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
