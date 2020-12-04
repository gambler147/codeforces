import java.io.*;
import java.util.*;


public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    int[] arr = new int[n];
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(s[i]);
    }

    // construct prefix and suffix max
    int[] prefix_max = new int[n];
    int[] suffix_max = new int[n];

    prefix_max[0] = arr[0];
    for (int i=1; i<n; i++) {
      prefix_max[i] = Math.max(prefix_max[i-1], arr[i]);
    }
    suffix_max[n-1] = arr[n-1];
    for (int i=n-2; i>=0; i--) {
      suffix_max[i] = Math.max(suffix_max[i+1], arr[i]);
    }

    // find maximum value and their indices in the array
    List<Integer> list = new ArrayList<>();
    for (int i=0; i<n; i++) {
      if (arr[i] == prefix_max[n-1]) {
        list.add(i);
      }
    }

    // let l = r be left and right bound of the middle partition, and set l = r be l.get(l.size() / 2)
    // which means set l be the middle position of maximum values, we notice that if there are at least 3
    // maximum values, it is always posible for the paritition. Elsewise, we expand our bound to the left or 
    // to the right, determined by which side's expanding making it more likely to satisfy condition.
    int l = list.get(list.size()/2);
    int r = l;
    int middle_min = prefix_max[n-1]; // starting with single element, which is also the maximum element in the array
    while (true) {
      // if we reach to the bound of the array, print NO
      if (l == 0 || r == n-1) {
        System.out.println("NO");
        return;
      }
    
      // otherwise we firstt check if we meet the condition
      if (prefix_max[l-1] == middle_min && suffix_max[r+1] == middle_min) {
        System.out.println("YES");
        System.out.format("%d %d %d%n", l, r-l+1, n-r-1);
        return;
      }
      
      // if not meet, we move l to the left or r to the right
      // we greedily pick a side that causes min(left_max, middle_min, right_max) decrease minimum
      // due to the fact that we always decrease left_max, middle_min and right_max by expanding our middle partition
      int left_max = (l - 1 == 0) ? Integer.MIN_VALUE : Math.min(prefix_max[l-2], arr[l-1]); // new min(u,v,w) is determined by prefix[l-2] and arr[l-1]
      int right_max = (r + 1 == n-1) ? Integer.MIN_VALUE : Math.min(suffix_max[r+2], arr[r+1]); // new min(u,v,w) is determined by suffixe[r+2] and arr[r+1]
      if (left_max >= right_max) {
        // it is better to expand middle partition to the left
        middle_min = Math.min(middle_min, arr[l-1]);
        l--;
      } else {
        // it is better to expand middle partition to the right
        middle_min = Math.min(middle_min, arr[r+1]);
        r++;
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
