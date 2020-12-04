
// Alexandra has an even-length array 𝑎, consisting of 0s and 1s. The elements
// of the array are enumerated from 1 to 𝑛. She wants to remove at most 𝑛2
// elements (where 𝑛 — length of array) in the way that alternating sum of the
// array will be equal 0 (i.e. 𝑎1−𝑎2+𝑎3−𝑎4+…=0). In other words, Alexandra
// wants sum of all elements at the odd positions and sum of all elements at the
// even positions to become equal. The elements that you remove don't have to be
// consecutive.

// For example, if she has 𝑎=[1,0,1,0,0,0] and she removes 2nd and 4th
// elements, 𝑎 will become equal [1,1,0,0] and its alternating sum is
// 1−1+0−0=0.

// Help her!

// Input Each test contains multiple test cases. The first line contains the
// number of test cases 𝑡 (1≤𝑡≤103). Description of the test cases follows.

// The first line of each test case contains a single integer 𝑛 (2≤𝑛≤103, 𝑛
// is even)  — length of the array.

// The second line contains 𝑛 integers 𝑎1,𝑎2,…,𝑎𝑛 (0≤𝑎𝑖≤1)  — elements of
// the array.

// It is guaranteed that the sum of 𝑛 over all test cases does not exceed 103.

// Output For each test case, firstly, print 𝑘 (𝑛2≤𝑘≤𝑛) — number of elements
// that will remain after removing in the order they appear in 𝑎. Then, print
// this 𝑘 numbers. Note that you should print the numbers themselves, not their
// indices.

// We can show that an answer always exists. If there are several answers, you
// can output any of them. 

import java.io.*;

public class Ahahahahahahahaha {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    String[] s = br.readLine().split(" ");

    int ones = 0;

    for (int i=0;i<n;i++) {
      if (s[i].equals("1")) {
        ones++;
      }
    }
    
    int t = (n/2)%2 == 0 ? n/2 : n/2 + 1; // result length, must be even
    // if ones > n/2, simply return all 1s
    if (ones > n/2) {
      System.out.println(t);
      System.out.print("1");
      for (int i=1;i<t;i++) {
        System.out.print(" 1");
      }
    } else if (ones < n/2) {
      System.out.println(t);
      System.out.print("0");
      for (int i=1;i<t;i++) {
        System.out.print(" 0");
      }
    } else {
      // ones == n/2 == zeros, we keep two 1s and others 0s
      if (ones == 1) {
        System.out.println(1);
        System.out.print(0);
      } else {
        // use string builder to find first two 1s, if first two 1s have same parity, remove one 0 in the middle
        StringBuilder sb = new StringBuilder();
        int prev_one = -1;
        for (int i=0; i<n; i++) {
          if (s[i].equals("0")) sb.append('0');
          if (s[i].equals("1")) {
            if (prev_one == -1) {
              sb.append('1');
              prev_one = i;
            } else {
              // encounter second 1
              if ((prev_one + i) % 2 == 0) {
                // same parity
                sb.deleteCharAt(sb.length()-1);
                t = n/2+1;
              } else {
                t = n/2+2;
              }
              sb.append('1');
              System.out.println(t);
              break;
            }
          }
        }
        for (int i=sb.length(); i<t; i++) {
          sb.append('0');
        }
        System.out.print(sb.charAt(0));
        for (int i=1; i<sb.length(); i++) {
          System.out.print(" " + sb.charAt(i));
        }
      }
    }

    System.out.print('\n');


    return;
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int test = Integer.parseInt(br.readLine());
      for (int trial = 1; trial <= test; trial++) {
        solve(br);
      }
    }
  }
}