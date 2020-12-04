// Alexander is a well-known programmer. Today he decided to finally go out and
// play football, but with the first hit he left a dent on the new Rolls-Royce
// of the wealthy businessman Big Vova. Vladimir has recently opened a store on
// the popular online marketplace "Zmey-Gorynych", and offers Alex a job: if he
// shows his programming skills by solving a task, he'll work as a cybersecurity
// specialist. Otherwise, he'll be delivering some doubtful products for the
// next two years.

// You're given 𝑛 positive integers 𝑎1,𝑎2,…,𝑎𝑛. Using each of them exactly
// at once, you're to make such sequence 𝑏1,𝑏2,…,𝑏𝑛 that sequence
// 𝑐1,𝑐2,…,𝑐𝑛 is lexicographically maximal, where 𝑐𝑖=𝐺𝐶𝐷(𝑏1,…,𝑏𝑖) -
// the greatest common divisor of the first 𝑖 elements of 𝑏.

// Alexander is really afraid of the conditions of this simple task, so he asks
// you to solve it.

// A sequence 𝑎 is lexicographically smaller than a sequence 𝑏 if and only if
// one of the following holds:

// 𝑎 is a prefix of 𝑏, but 𝑎≠𝑏; in the first position where 𝑎 and 𝑏
// differ, the sequence 𝑎 has a smaller element than the corresponding element
// in 𝑏. Input Each test contains multiple test cases. The first line contains
// the number of test cases 𝑡 (1≤𝑡≤103). Description of the test cases
// follows.

// The first line of each test case contains a single integer 𝑛 (1≤𝑛≤103)  —
// the length of the sequence 𝑎.

// The second line of each test case contains 𝑛 integers 𝑎1,…,𝑎𝑛
// (1≤𝑎𝑖≤103)  — the sequence 𝑎.

// It is guaranteed that the sum of 𝑛 over all test cases does not exceed 103.

// Output For each test case output the answer in a single line  — the desired
// sequence 𝑏. If there are multiple answers, print any.

import java.io.*;

public class BigVova {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");
    int[] arr = new int[n];
    // parseInt
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(s[i]);
    }

    // gcd
    int gcd = 0;
    // visited indices
    boolean[] visited = new boolean[n];
    // each time we find the one with maximum gcd
    for (int i=0; i<n; i++) {
      int idx = -1;
      for (int j=0; j<n; j++) {
        if (!visited[j]) {
          if (idx == -1 || GCD(gcd, arr[j]) > GCD(gcd, arr[idx])) {
            // update idx
            idx = j;
          }
        }
      }
      // now we found the optimal value for ith place
      visited[idx] = true;
      gcd = GCD(gcd, arr[idx]); // update gcd
      System.out.print(arr[idx] + " ");
    }
    System.out.println();
  }

  public static int GCD(int a, int b) {
    while (b > 0) {
      int q = b;
      b = a % b;
      a = q;
    }
    return a;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int test = Integer.parseInt(br.readLine());
      for (int trial = 1; trial <= test; trial++) {
        solve(br);
      }
    }
  }
}
