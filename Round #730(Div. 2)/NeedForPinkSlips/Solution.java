import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    double[] prob = new double[4];
    for (int i=0; i<4; i++) {
      prob[i] = Double.parseDouble(s[i]);
    }
    System.out.println(dfs(prob[0], prob[1], prob[2], prob[3]));

  }

  public static double dfs(double c, double m, double p, double v) {

    double res = 1;
    double new_m, new_c, new_p;
    if (c > 10e-6) {
      new_c = (c < v) ? 0 : c - v;
      new_m = (m < 10e-6) ? 0 : m+(c-new_c)/2;
      new_p = 1-new_c-new_m;
      res += c * dfs(new_c, new_m, new_p, v);
    }

    if (m > 10e-6 ) {
      new_m = (m < v) ? 0 : m - v;
      new_c = (c < 10e-6) ? 0 : c+(m-new_m)/2;
      new_p = 1-new_c-new_m;
      res += m * dfs(new_c, new_m, new_p, v);
    }
    
    return res;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
