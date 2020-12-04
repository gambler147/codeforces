import java.io.*;


public class Solution {
  public static void solve(int trial, BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" "); 
    long x = Long.parseLong(s[0]);
    long y = Long.parseLong(s[1]);
    long k = Long.parseLong(s[2]);

    // for operation 1, we increase our sticks by x-1
    // for operation 2, we decrease our sticks by y and increase our coals by 1
    // we initially have 1 stick and 0 coal, our goal is to get at least k sticks and k coals
    // so we need to perform operation 2 at least k times, so, we need to perform operation 1 n times
    // such that 1 + (x-1)*n - y*k >= k, so n >= (k - 1 + y*k) / (x-1)
    // so total number of operations should be n + k
    
    long n;
    if ((k-1+y*k) % (x-1) == 0) {
      n = (k-1+y*k)/(x-1);
    } else {
      n = (k-1+y*k)/(x-1) + 1;
    }
    System.out.println(n+k);
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int trials = Integer.parseInt(br.readLine());
      for (int trial=1; trial <= trials; trial++) {
        solve(trial, br);
      }
    }
  }
}