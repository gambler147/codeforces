
/*
Koa the Koala has two strings 𝐴 and 𝐵 of the same length 𝑛 (|𝐴|=|𝐵|=𝑛) consisting of the first 20 lowercase English alphabet letters (ie. from a to t).

In one move Koa:

selects some subset of positions 𝑝1,𝑝2,…,𝑝𝑘 (𝑘≥1;1≤𝑝𝑖≤𝑛;𝑝𝑖≠𝑝𝑗 if 𝑖≠𝑗) of 𝐴 such that 𝐴𝑝1=𝐴𝑝2=…=𝐴𝑝𝑘=𝑥 (ie. all letters on this positions are equal to some letter 𝑥).
selects a letter 𝑦 (from the first 20 lowercase letters in English alphabet) such that 𝑦>𝑥 (ie. letter 𝑦 is strictly greater alphabetically than 𝑥).
sets each letter in positions 𝑝1,𝑝2,…,𝑝𝑘 to letter 𝑦. More formally: for each 𝑖 (1≤𝑖≤𝑘) Koa sets 𝐴𝑝𝑖=𝑦.
Note that you can only modify letters in string 𝐴.

Koa wants to know the smallest number of moves she has to do to make strings equal to each other (𝐴=𝐵) or to determine that there is no way to make them equal. Help her!

Input
Each test contains multiple test cases. The first line contains 𝑡 (1≤𝑡≤10) — the number of test cases. Description of the test cases follows.

The first line of each test case contains one integer 𝑛 (1≤𝑛≤105) — the length of strings 𝐴 and 𝐵.

The second line of each test case contains string 𝐴 (|𝐴|=𝑛).

The third line of each test case contains string 𝐵 (|𝐵|=𝑛).

Both strings consists of the first 20 lowercase English alphabet letters (ie. from a to t).

It is guaranteed that the sum of 𝑛 over all test cases does not exceed 105.

Output
For each test case:

Print on a single line the smallest number of moves she has to do to make strings equal to each other (𝐴=𝐵) or −1 if there is no way to make them equal.

Example
Input:
  5
  3
  aab
  bcc
  4
  cabc
  abcb
  3
  abc
  tsr
  4
  aabd
  cccd
  5
  abcbd
  bcdda

Output:
  2
  -1
  3
  2
  -1

Note
In the 1-st test case Koa:
selects positions 1 and 2 and sets 𝐴1=𝐴2= b (𝑎𝑎𝑏→𝑏𝑏𝑏).
selects positions 2 and 3 and sets 𝐴2=𝐴3= c (𝑏𝑏𝑏→𝑏𝑐𝑐).
In the 2-nd test case Koa has no way to make string 𝐴 equal 𝐵.
In the 3-rd test case Koa:
selects position 1 and sets 𝐴1= t (𝑎𝑏𝑐→𝑡𝑏𝑐).
selects position 2 and sets 𝐴2= s (𝑡𝑏𝑐→𝑡𝑠𝑐).
selects position 3 and sets 𝐴3= r (𝑡𝑠𝑐→𝑡𝑠𝑟).

*/

import java.util.Scanner;
import java.util.*;

public class StringTransformation {
  // main method
  public static int solve(int n, String A, String B) {
    // idea: greedy algorithm
    // starting from the alphabetically smallest letter a in A, then alphabetically smallest letter b in B, convert all a to b first,
    // we check whether any pair matches, if so we remove this pair from both lists, then we repeat this process until all pairs matched
    int ALP = 20; // alphabet size, here is 20
    ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    for (int i=0; i<ALP; i++) {
      adj.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < n; i++ ) {
      // if there is a character in A > character in B, impossible, return -1
      if (A.charAt(i) > B.charAt(i)) {
        return -1;
      } else {
        adj.get(A.charAt(i) - 'a').add(B.charAt(i) - 'a');
        adj.get(B.charAt(i) - 'a').add(A.charAt(i) - 'a');
      }
    }

    // dfs
    Set<Integer> visited = new HashSet<>();
    
    int res = ALP;
    for (int i = 0; i < ALP; i++) {
      if (!visited.contains(i)) {
        dfs(i, visited, adj);
        res--;
      }
    }
    return res;


  }

  public static void dfs(int node, Set<Integer> visited, ArrayList<ArrayList<Integer>> adj) {
    visited.add(node);
    for (int u : adj.get(node)) {
      if (!visited.contains(u)) {
        dfs(u, visited, adj);
      }
    }

  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int repeat = input.nextInt();
    int n;
    String A;
    String B;
    int res;
    while (repeat-- > 0) {
      n = input.nextInt();
      input.nextLine(); // throw away the \n not consumed by nextInt()
      A = input.nextLine();
      B = input.nextLine();
      res = solve(n, A, B);
      System.out.println(res);
    }

    input.close();
  }
}

