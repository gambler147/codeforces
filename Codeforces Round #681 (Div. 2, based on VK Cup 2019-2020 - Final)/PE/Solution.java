import java.io.*;
import java.util.stream.IntStream;

public class Solution {
  public static long[] f = new long[16]; // factorial
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]); // number of elements
    int q = Integer.parseInt(s[1]); // number of queries
    
    // since 14! > 10^10, We can split array into (1, n-13) + (n-13, n)
    int[] head;
    int[] tail;
    int[] tail_init;
    long y = 0; // total number of permutations we shifted
    if (n > 15) {
      head = IntStream.range(1, n-14).toArray();
      tail = IntStream.range(n-14, n+1).toArray();
      tail_init = IntStream.range(n-14, n+1).toArray();
    } else {
      head = new int[0]; 
      tail = IntStream.range(1, n+1).toArray();
      tail_init = IntStream.range(1, n+1).toArray();
    }

    // prefix head
    long[] prefix = new long[head.length+1];
    for (int i=1; i<head.length+1; i++) {
      prefix[i] = prefix[i-1] + head[i-1];
    }

    // read
    while (q-- > 0) {
      s = br.readLine().split(" ");
      int op = Integer.parseInt(s[0]);
      if (op == 1) {
        int l = Integer.parseInt(s[1]);
        int r = Integer.parseInt(s[2]);
        long sum = 0;
        // calculate sum of [l,r]
        if (l <= head.length) {
          // sum + head[l, min(r, head.length)]
          int k = Math.min(r, head.length);
          sum += prefix[k] - prefix[l-1];
        }
  
        if (r > head.length) {
          // sum += tail[max(l, head.length), r]
          for (int i=Math.max(l, head.length+1); i<=r; i++) {
            sum += tail[i-head.length-1];
          }
        }
        System.out.println(sum);
      } else {
        // op == 2
        int x = Integer.parseInt(s[1]);
        y += x;
        tail = permuteNext(tail_init, y);
      }
    }
  }
  public static int[] permuteNext(int[] arr, long x) {
    // get next permutation for sorted array
    int n = arr.length;
    int[] res = new int[n];
    boolean[] mark = new boolean[n];

    long q;
    long r=x;
    for (int i=0; i<n; i++) {
      q = r / f[n-1-i];
      r = r - q * f[n-1-i];

      // qth element will be selected
      int k=0;
      while (true) {
        if (mark[k] == false) {
          q--;
          if (q<0) {
            break;
          }
        }
        k++;
      }
      res[i] = arr[k];
      mark[k] = true;
    }

    return res;
  }
  public static void main(String[] args) throws IOException {
    // precompute factorial
    f[0] = 1;
    for (int i=1; i<=15; i++) {
      f[i] = i*f[i-1];
    }
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      solve(br);
    }
  }
}