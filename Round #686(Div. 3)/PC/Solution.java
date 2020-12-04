import java.io.*;
import java.util.HashMap;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    // read array
    String[] s = br.readLine().split(" ");
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(s[i]);
    }
    
    // O(n); use hashmap to record last occurence of a given number, if the number is consecutively occuring
    // we ignore, other wise, we add 1 operation if we choose this number as final remaining number
    HashMap<Integer, Integer> ops = new HashMap<>();

    for (int i=0; i<n; i++) {
      if (i==0) {
        if (n == 1 || arr[0] == arr[1]) {
          ops.put(arr[i], 0);
        } else {
          ops.put(arr[0], 1);
        }
      } else if (i == n-1) {
        if (!ops.containsKey(arr[i])) {
          ops.put(arr[i], 1);
        }
      } else {
        if (!ops.containsKey(arr[i])) {
          ops.put(arr[i], 1);
        }
        if (arr[i] == arr[i+1]){
          continue;
        } else {
          ops.put(arr[i], ops.get(arr[i]) + 1);
        }

      }
    }

    int min = Integer.MAX_VALUE;
    for (int v : ops.keySet()) {
      if (ops.get(v) < min) {
        min = ops.get(v);
      }
    }
    System.out.println(min);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
