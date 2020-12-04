import java.io.*;
import java.util.*;

public class Beach {
  public static String solve(int n, int k, int l, int[] arr) {
    // arr is an array [-k, 1, 2, 3 ..., -k] with (i+1) th element representing depth of the sea i meters from the shore
    // idea is to first find all safe points SP = {0<=i<n} where depth[i] + k <= l
    // then for each j from SP[p] to SP[p+1], check we can swim from SP[p] to SP[p+1] within one wave cycle
    // length of depth is n
    
    // slightly modify original arr
    int[] depth = new int[n+2];
    depth[0] = -k;
    depth[n+1] = -k;
    for (int i = 1; i <= n; i++ ) {
      if (arr[i-1] > l) {
        return "NO";
      }
      depth[i] = arr[i-1];
    }

    List<Integer> SP = new ArrayList<>();
    for (int i=0; i < depth.length; i++) {
      if (depth[i] + k <= l) {
        SP.add(i);
      }
    }

    // now from each SP[i] to SP[i+1] check whether Koa can swim across
    for (int i = 0; i < SP.size() - 1; i++ ){
      // loop depth[start] to depth[end]
      boolean down = true;
      int prev = k; // tide level for reaching previous position
      for (int j=SP.get(i)+1; j < SP.get(i+1); j++) {
        int cur = l - depth[j];
        if (down == false && cur <= prev) {
          return "NO";
        }

        if (down == false) {
          cur = prev + 1;
        } else {
          // down is true
          if (cur >= prev) {
            cur = prev - 1;
          } 
          if (cur == 0) down = false;
        }
        prev = cur;
      }
    } 
    return "YES";
  }
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine()); // 3 elements
    while (test-- > 0) {
      String[] s = br.readLine().split(" ");
      int n = Integer.parseInt(s[0]); // length of the shore
      int k = Integer.parseInt(s[1]); // seconds of tide for each wave
      int l = Integer.parseInt(s[2]); // maximum depth Koa can swim
      
      // depths array
      String[] sd = br.readLine().split(" ");
      int[] arr = new int[n];
      for (int i=0; i<n; i++) {
        arr[i] = Integer.parseInt(sd[i]);
      }
      
      System.out.println(solve(n, k, l, arr));
    }
    br.close();
  }
}