import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    long mod = 998244353;
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    int k = Integer.parseInt(s[1]);

    int[] a = new int[n];
    int[] b = new int[k];

    int[] anext = new int[n];
    int[] aprev = new int[n];

    anext[n-1] = -1; // next element index
    aprev[0] = -1; // prev element index
    for (int i=0; i<n-1; i++) {
      anext[i] = i+1;
      aprev[i+1] = i;
    }

    Map<Integer, Integer> amap = new HashMap<>(); // map: value -> index for in array a
    Set<Integer> bset = new HashSet<>(); // remaining values in b
    
    s = br.readLine().split(" ");
    for (int i=0; i<n; i++) {
      a[i] = Integer.parseInt(s[i]);;
      amap.put(a[i], i);
    }

    s = br.readLine().split(" ");
    for (int i=0; i<k; i++) {
      b[i] = Integer.parseInt(s[i]);
      bset.add(b[i]);
    }

    long res=1;
    for (int i=0; i<k; i++) {
      int v = b[i];
      // check if v is in a
      if (!amap.containsKey(v)) {
        System.out.println(0);
        return;
      }

      // get idx of a
      int aidx = amap.get(v);
      // check if aidx's both neighbors' values in bset
      int l = aprev[aidx];
      int r = anext[aidx];
      if ((l == -1 || bset.contains(a[l])) && (r == -1 || bset.contains(a[r]))) {
        System.out.println(0);
        return;
      }

      // if either a[l], a[r] is in bset, we remove the other
      if ( l == -1 || bset.contains(a[l])) {
        // in this case we remove a[r]
        anext[aidx] = anext[r];
        if (anext[r] != -1) aprev[anext[r]] = aidx;
      } else if (r == -1 || bset.contains(a[r])) {
        // in this case we remove a[l]
        aprev[aidx] = aprev[l];
        if (aprev[l] != -1) anext[aprev[l]] = aidx;
      } else {
        // both l and r can be removed we can simply remove l
        aprev[aidx] = aprev[l];
        if (aprev[l] != -1) anext[aprev[l]] = aidx;
        res = 2*res % mod;
      }

      // remove v from bset
      bset.remove(v);
    }

    System.out.println(res);
  }
  public static void main(String[] args) throws IOException {
    // precompute factorial
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int t = Integer.parseInt(br.readLine());
      while (t-- > 0) {
        solve(br);
      }
    }
  }
}