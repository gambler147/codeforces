import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    int[] arr = new int[n];
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(s[i]);
    }

    // create increasing array inc and decreasing array dec such that inc[i] <= inc[i+1] and dec[i] >= dec[i+1] and inc[i] + dec[i] == arr[i]
    // since dec[i] <= dec[i-1] and dec[i] = arr[i] - inc[i] <= arr[i] - inc[i-1], greedily pick dec[i] = min(dec[i-1], arr[i]-inc[i-1])
    // we iterate i if for some i we have dec[i] > arr[i], we return False
    int inc=0;
    int dec=Integer.MAX_VALUE;
    for (int v : arr) {
      dec = Math.min(dec, v - inc);
      inc = v - dec;
      if (dec < 0 || inc < 0) {
        System.out.println("NO");
        return;
      }
    }
    System.out.println("YES");
    
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int t = Integer.parseInt(br.readLine());
      while (t-- > 0) {
        solve(br);
      }
    }
  }
}