import java.io.*;

public class Solution {
  public static void solve(int t, BufferedReader br) throws IOException {
    long n = Long.parseLong(br.readLine());

    long res = 0;

    while (n > 0) {
      if (n%4 == 0 && n > 8) {
        // take 1
        res++;
        n-=2;
      } else if (n%2 == 0) {
        // take half
        res+=n/2;
        n=n/2;
        if (n%2 == 0) {
          n=n/2;
        } else {
          n--;
        }
      } else {
        // odd number take 1
        res++;
        n--;
        if (n%4 == 0 && n>8) {
          // opponent takes 1
          n--;
        } else {
          // opponent takes half
          n/=2;
        }
      }
    }

    System.out.println(res);
    
  }
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      solve(t, br);
    }
  }
}