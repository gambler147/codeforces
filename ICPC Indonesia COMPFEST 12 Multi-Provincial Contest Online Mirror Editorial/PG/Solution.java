import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int A = Integer.parseInt(s[0]);
    int B = Integer.parseInt(s[1]);
    int C = Integer.parseInt(s[2]);
    int D = Integer.parseInt(s[3]);

    // check sign
    String[] result = new String[4];
    for (int i=0; i<4; i++) result[i] = "Tidak";
    if ((A+B)%2 == 0) {
      // can only produce positive values
      if (A+D > 0) {
        result[3] = "Ya";
      } 
      if (B+C > 0) {
        result[2] = "Ya";
      }
    } else {
      // can only produce negative values
      if (A+D > 0) {
        result[0] = "Ya";
      }
      if (B+C > 0) {
        result[1] = "Ya";
      }
    }

    String ans = "";
    for (String tmp : result) {
      ans += tmp + " ";
    }
    System.out.println(ans);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) solve(br);
  } 
}