import java.io.*;

public class Solution {
  public static int ceiLog2(int x) {
    // calculate ceil of log2(x)
    int y = 0;
    while ((1 << y) < x) {
      y++;
    }
    return y;
  }
  public static void solve(BufferedReader br) throws IOException {
    String[] input = br.readLine().split(" ");
    int n = Integer.parseInt(input[0]);
    int k = Integer.parseInt(input[1]);
    String s = br.readLine();
    // maximum digits needed in the final answer (last p digits p <= k)
    int lg = Math.min(k, ceiLog2(n-k+2));
    int zerocnt = 0; // this keeps track of number of zeros in the leading (k-lg) digits in the size-k rolling window
    boolean[] forbidden = new boolean[1<<lg];
    int mask = 0;
    // initialize zero count; if s[i] == '1', zero count++;
    for (int i=0; i<k-lg; i++) {
      zerocnt += (s.charAt(i) == '1') ? 1 : 0;
    }
    // initialize binary string value -> mask
    for (int i=k-lg; i<k-1; i++) {
      mask = (mask << 1) | ((s.charAt(i) == '1') ? 1 : 0);
    }
    for (int i=k-1; i<n; i++) {
      // update mask; rolling window;
      mask = ((mask << 1) | ((s.charAt(i) == '1') ? 1 : 0)) & ((1 << lg) - 1);
      // since we only care about answers that have k-lg prefix '0's;
      // we just need to ban these kinds of potential answers
      if (zerocnt == k-lg) {
        forbidden[((1<<lg)-1) ^ mask] = true;
      }
      // the left end of the window is moving out of the window, we decrease our zero count
      // if s[i-k+1] is '1'
      if (s.charAt(i-k+1) == '1') {
        zerocnt--;
      }
      // the right end of the first (k-lg) window is moving into the window, we add our zero
      // count if s[i-lg+1] is '1'
      if (s.charAt(i-lg+1) == '1') {
        zerocnt++;
      }
    }
    // get result
    int res = 0;
    while (res < (1<<lg) && forbidden[res]) {
      res++;
    }
    // if we all possible size-k substrings are forbidden, we print NO
    if (res == (1 << lg)) {
      System.out.println("NO");
      return;
    }
    StringBuilder sb = new StringBuilder();
    // set initial k-lg chars '0'
    for (int i=0; i<k-lg; i++) {
      sb.append("0");
    }
    // for the remaining, append res to sb
    for (int i=lg - 1; i>=0; i--) {
      sb.append((res >> i)&1);
    }
    System.out.println("YES");
    System.out.println(sb.toString());
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
