import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] input = br.readLine().split(" ");
    int a = Integer.parseInt(input[0]); // cost of activating a mine
    int b = Integer.parseInt(input[1]); // cost of placing a mine

    String s = br.readLine().strip(); // map
    int gap = 0;
    int cost = 0; // optimal cost
    boolean initial = false;
    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      if (c == '0') {
        gap++;
      } else {
        if (initial == false) {
          cost += a;
          gap = 0;
          initial = true;
          continue;
        }
        if (gap != 0) {
          // check whether we can fill the gap with mines
          if (gap * b < a) {
            cost += b*gap;
          } else {
            cost += a;
          }
          gap = 0;
        }
      }
    }

    System.out.println(cost);
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