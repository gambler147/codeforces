import java.util.*;
import java.io.*;

public class Solution {
  static final long MOD = 998244353L;
  public static long sum(long[] prefix, int i, int j) {
    return prefix[j] - prefix[i];
  }

  public static long mod_power(long x, long y, long mod) {
    long res = 1;
    x %= mod;
    while (y > 0) {
      if ((y&1)==1)
        res = (res * x) % mod;
      y /= 2;
      x = (x * x) % mod;
    }
    return res;
  }

  public static int binarySearch(long[] arr, long val) {
    // binary search left
    int n = arr.length;
    int l = 0;
    int r = n;
    while (l < r) {
      int m = (l+r)/2;
      if (arr[m] >= val) {
        r = m;
      } else {
        l = m+1;
      }
    }
    return l;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");

    int n = Integer.parseInt(input[0]);
    int m = Integer.parseInt(input[1]);

    long[] monster = new long[n];
    // read monster
    input = br.readLine().split(" ");
    for (int i=0; i<n; i++) {
      monster[i] = Long.parseLong(input[i]);
    }

    Arrays.sort(monster);

    // prefix sum
    long[] prefix = new long[n+1];
    for (int i=0; i<n; i++) {
      prefix[i+1] = (prefix[i] + monster[i]) % MOD; 
    }

    // find expected damage
    for (int i=0; i<m; i++){
      input = br.readLine().split(" ");
      int a = Integer.parseInt(input[0]);
      long d = Long.parseLong(input[1]);
      int index = binarySearch(monster, d);
      
      int big = n - index;
      if (big < a) {
        System.out.println(0);
        continue;
      }
      // if there is at least sheilds[i] monster, monsters can possibly cause a damage
      long res = 0L;
      if (big > a) {
        long num = big - a;
        long deno = mod_power(big, MOD-2, MOD);
        long w = sum(prefix, index, n) + MOD;
        res += (num * deno % MOD) * w % MOD;
      }
      long num = big-a+1;
      long deno = mod_power(big+1, MOD-2, MOD);
      long w = sum(prefix, 0, index);
      res += (num * deno % MOD) * w % MOD;
      System.out.println(res % MOD);
    }
  }
}

