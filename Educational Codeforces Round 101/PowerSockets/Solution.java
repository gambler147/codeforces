import java.io.*;
import java.util.*;

public class Solution {
  static int MAXN = 200000;
  public static void solve(BufferedReader br) throws IOException {
    // greedy;
    // 1. it is always optimal to pick the longest chain to attach to current tree;
    // 2. it is always optimal to attach the middle node of the new chain
    // 3. it is always optimal to attach the new chain to the closest white node so far in the tree
    // notice that whenever we add a chain to the tree, we increase our white node of (new_l + tree_l - 2)
    // we sort the chains by length decreasingly and find first distance such that number of nodes with distance
    // smaller than d is greater than k.
    String[] input = br.readLine().split(" ");
    int n = Integer.parseInt(input[0]);
    int k = Integer.parseInt(input[1]);
    input = br.readLine().split(" ");
    int[] l = new int[n];
    for (int i=0; i<n; i++) {
      l[i] = Integer.parseInt(input[i]);
    }
    Arrays.sort(l);
    reverseArray(l);

    // keep track of number of white nodes for each given distance to the root
    // the following array f[i] records the dynamics of changes of iteration
    // and the prefix sum of f[0:i] is the total number of white nodes have distance
    // smaller or equal than i
    int[] f = new int[MAXN + 5];
    f[0] = 1; // initially, root has distance 0;
    f[1] = -1; // f[0:j] = 0 for j >= 1
    int res = Integer.MAX_VALUE;
    int sum = 0;
    // 
    for (int d=0, i=0; d<MAXN; d++) {
      sum += f[d]; // total number of white nodes with distance smaller of equal than d
      f[d+1] += f[d]; // total number of white nodes with distance d+1
      // if with adding nodes with distance d+1, we get total count greater than k, we update our result
      if (sum + f[d+1] >= k) {
        res = Math.min(res, d+1);
      }
      // repeatedly add chain to current tree, if we have available chains (i < n) and the current node is white (i.e. f[d] > 0)
      while (f[d] > 0 && i < n) {
        int u = ((l[i]-1) >> 1); // left sub-chain
        int v = l[i]-1-u; // right sub-chain
        // left sub-chain contributes to adding nodes with distance of (d+2, d+3, ..., d+u+2)
        f[d+2]++;
        f[d+2+u]--;
        // right sub-chain contributes to adding nodes with distance of (d+2, d+3, ..., d+v+2)
        f[d+2]++;
        f[d+2+v]--;
        // the closest white node of distance becomes black
        f[d]--;
        sum--;
        // next chain
        i++;
      }
    }
    if (res == Integer.MAX_VALUE) {
      System.out.println("-1");
    } else {
      System.out.println(res);
    }
  }
  public static void reverseArray(int[] arr) {
    int n = arr.length;
    int i=0, j=n-1;
    int tmp;
    while (i < j) {
      tmp = arr[i]; 
      arr[i] = arr[j];
      arr[j] = tmp;
      i++;
      j--;
    }
    return;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    solve(br);
  }
}
