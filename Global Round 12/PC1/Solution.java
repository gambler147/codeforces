import java.io.*;
import java.util.*;


public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    int[][] arr = new int[n][n];

    for (int i=0; i<n; i++) {
      String s = br.readLine();
      for (int j=0; j<n; j++) {
        arr[i][j] = s.charAt(j) == 'X' ? 1 : 0;
      }
    }

    // split whole grid to 3 parts, i+j % 3 == 0, 1, 2 respectively, 
    // find the minimum group sum
    int[] gs = new int[3];
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        gs[(i+j)%3] += arr[i][j];
      }
    }

    // get min index
    int index = 0;
    for (int i=0; i<3; i++) {
      if (gs[i] < gs[index]) {
        index = i;
      }
    }

    // print output
    for (int i=0; i<n; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j=0; j<n; j++) {
        if ((i+j)%3 == index && arr[i][j] == 1) {
          sb.append("O");
        } else if (arr[i][j] == 1) {
          sb.append("X");
        } else {
          sb.append(".");
        }
      }
      System.out.println(sb.toString());
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
