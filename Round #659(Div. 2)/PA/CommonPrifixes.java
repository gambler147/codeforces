import java.io.*;
import java.util.*;

public class CommonPrifixes {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine());
    while (test-- > 0) {
      int n = Integer.parseInt(br.readLine()); // number of strings
      int[] arr = new int[n];

      String[] s = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        arr[j] = Integer.parseInt(s[j]);
      }

      // main work
      String ps = new String(new char[arr[0]+1]).replace("\0", "a"); // initialize first string as 'aaaa...'
      System.out.println(ps);
      for (int i=0; i<n; i++) {
        StringBuilder sb = new StringBuilder(); // current string
        // for each i, need to compare arr[i], arr[i+1]
        int size = arr[i] + 1;
        if (i+1 < n) {
          size = Math.max(size, arr[i+1] + 1);
        }
        // copy first 0 to arr[i] characters from ps to sb
        char end = 'a'; // default end characters
        if (ps.length() > arr[i]) {
          if (ps.charAt(arr[i]) != 'z') {
            end = (char)(ps.charAt(arr[i]) + 1);
          }
        }
        for (int j=0; j<arr[i]; j++) {
          sb.append(ps.charAt(j));
        }
        for (int j=0; j<size-arr[i]; j++) {
          sb.append(end);
        }
        ps = sb.toString();
        System.out.println(ps);
      }
      
    }
    br.close();
  }
}