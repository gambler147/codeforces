import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String s = br.readLine();
    /* valid parenthesis problem
      if s.length is odd return "NO";
      iterate the char array, maintain the count of '(' and '*'
      whenever reaching a ')', subtract 1 count of '(' or '*',
      if '(' or '*' goes negative return "NO". We also need to do
      a reverse check to maintain the count of ')' and '*'.
    */ 
    if (s.length()%2 != 0) {
      System.out.println("NO");
      return;
    }
    int asterisk = 0;
    int par = 0;
    
    for (char c : s.toCharArray()) {
      switch (c) {
        case '(': {
          par++;
          break;
        }
        case '?': {
          asterisk++;
          break;
        }
        case ')': {
          if (par>0) {
            par--;
          } else {
            asterisk--;
            if (asterisk < 0) {
              System.out.println("NO");
              return;
            }
          }
        }
      }
    }
    // reverse loop
    asterisk = 0;
    par = 0;
    s = new StringBuilder(s).reverse().toString();
    for (char c : s.toCharArray()) {
      switch (c) {
        case ')': {
          par++;
          break;
        }
        case '?': {
          asterisk++;
          break;
        }
        case '(': {
          if (par>0) {
            par--;
          } else {
            asterisk--;
            if (asterisk < 0) {
              System.out.println("NO");
              return;
            }
          }
        }
      }
    }
    System.out.println("YES");
    return;

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}

