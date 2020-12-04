import java.io.*;
import java.util.Arrays;

public class Solution {
  public static final long MOD = (long) Math.pow(10, 9) + 7;
  public static final int MAXN = 1000;
  public static int[][] prefix = new int[MAXN+1][MAXN+1];
  public static long[] f = new long[2*MAXN+1]; // n! % MOD
  public static long[] g = new long[2*MAXN+1]; // modulo inverse

  public static void precalc() {
    f[0] = 1;
    g[0] = 1;
    for (int i=1; i<=2*MAXN; i++) {
      f[i] = (f[i-1] * i) % MOD;
      g[i] = g[i-1] * power_mod(i, MOD-2, MOD)%MOD;
    }
  }
  public static long power_mod(long a, long power, long mod) {
    if (power == 0) return 1;
    if (power % 2 == 0) {
      long res = power_mod(a, power/2, mod);
      return res*res % mod;
    }
    return a * power_mod(a, power-1, mod) % mod;
  }

  public static long comb(int n, int k) {
    if (k > n || k < 0) return 0;
    return f[n]*g[k]%MOD *g[n-k]%MOD;
  }

  public static int numberOfSnakesWithin(int x1, int y1, int x2, int y2) {
    // returns number of snakes within square from top left (x1, y1) to bottom right (x2, y2)
    x1 = Math.max(x1, 1);
    x2 = Math.min(x2, 1000);
    y1 = Math.max(y1, 1);
    y2 = Math.min(y2, 1000);
    if (x1 > x2 || y1 > y2) return 0; // (x1, y1) must be smaller than (x2, y2)
    return prefix[x2][y2] - prefix[x1-1][y2] - prefix[x2][y1-1] + prefix[x1-1][y1-1];
  }

  public static void solve(BufferedReader br) throws IOException {
    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);
    int R = Integer.parseInt(input[2]);

    precalc();
    // read mad snakes locations and dange levels
    int[][] snakes = new int[N][2];
    int[] dangers = new int[N];

    for (int i=0; i<N; i++) {
      input = br.readLine().split(" ");
      dangers[i] = Integer.parseInt(input[2]);
      int x = Integer.parseInt(input[0]);
      int y = Integer.parseInt(input[1]);
      prefix[x][y]++;
      snakes[i] = new int[] {x, y};
    }

    // prefix sum, prefix[i][j] now means number of snakes from (1,1) to (i,j)
    for (int i=1; i<=MAXN; i++) {
      for (int j=1; j<=MAXN; j++) {
        prefix[i][j] = prefix[i][j] + prefix[i][j-1] + prefix[i-1][j] - prefix[i-1][j-1];
      }
    }

    long sum = 0;
    for (int i=0; i<N; i++) {
      for (int j=i; j<N; j++) {
        long cnt = 0;
        int x1 = snakes[i][0];
        int y1 = snakes[i][1];
        int x2 = snakes[j][0];
        int y2 = snakes[j][1];

        // 1. number of snakes that hits both snake i and snake j
        // we just need to find number of snakes from (min(x1,x2), min(y1, y2)) to (max(x1, x2), max(y1,y2))
        int min_x = Math.max(x1, x2)-R;
        int max_x = Math.min(x1, x2)+R;
        int min_y = Math.max(y1, y2)-R;
        int max_y = Math.min(y1, y2)+R;
        
        int W = numberOfSnakesWithin(min_x, min_y, max_x, max_y);
        cnt += comb(N, M) - comb(N-W, M);
        if (cnt < 0) cnt += MOD;

        // 2. no snakes hit both i and j (W), but one snake that kills only i (U), another snake that kills only j (V)
        int U = numberOfSnakesWithin(x1-R, y1-R, x1+R, y1+R) - W;
        int V = numberOfSnakesWithin(x2-R, y2-R, x2+R, y2+R) - W;
        
        cnt += comb(N-W,M) - comb(N-U-W, M) - comb(N-V-W, M) + comb(N-V-U-W, M);
        cnt%=MOD;
        if (cnt<0) cnt+=MOD;
        
        if (i == j) sum+=cnt*dangers[i]%MOD * dangers[j]%MOD;
        else {
          sum += 2*cnt * dangers[i]%MOD * dangers[j]%MOD;
        }
        sum%=MOD;
        
      }
    }
    
    System.out.println(sum);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    solve(br);
  }
}
