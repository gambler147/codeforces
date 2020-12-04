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
    
    // O(n): use hashmap to count number of occurences of each number, then we iterate through the set
    // find the minimum number with count equals 1. Then we go back to the array to find its index
    HashMap<Integer, Integer> counter = new HashMap<>();
    for (int i=0; i<n; i++) {
      counter.put(arr[i], counter.getOrDefault(arr[i], 0) + 1);
    }

    int min = Integer.MAX_VALUE;
    // iterate through hashmap to find minimum value
    for (int val : counter.keySet()) {
      if (counter.get(val) == 1 && val < min) {
        min = val;
      }
    }
    
    // if cannot find a number with exactly 1 occurence
    if (min == Integer.MAX_VALUE) {
      System.out.println(-1);
      return;
    }

    for (int i=0; i<n; i++) {
      if (arr[i] == min) {
        System.out.println(i+1);
        return;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
