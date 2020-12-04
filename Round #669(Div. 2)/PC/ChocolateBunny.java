// This is an interactive problem.

// We hid from you a permutation 𝑝 of length 𝑛, consisting of the elements
// from 1 to 𝑛. You want to guess it. To do that, you can give us 2 different
// indices 𝑖 and 𝑗, and we will reply with 𝑝𝑖mod𝑝𝑗 (remainder of division
// 𝑝𝑖 by 𝑝𝑗).

// We have enough patience to answer at most 2⋅𝑛 queries, so you should fit in
// this constraint. Can you do it?

// As a reminder, a permutation of length 𝑛 is an array consisting of 𝑛
// distinct integers from 1 to 𝑛 in arbitrary order. For example, [2,3,1,5,4]
// is a permutation, but [1,2,2] is not a permutation (2 appears twice in the
// array) and [1,3,4] is also not a permutation (𝑛=3 but there is 4 in the
// array).

// Input The only line of the input contains a single integer 𝑛 (1≤𝑛≤104) —
// length of the permutation.

// Interaction The interaction starts with reading 𝑛.

// Then you are allowed to make at most 2⋅𝑛 queries in the following way:

// "? x y" (1≤𝑥,𝑦≤𝑛,𝑥≠𝑦). After each one, you should read an integer 𝑘,
// that equals 𝑝𝑥mod𝑝𝑦.

// When you have guessed the permutation, print a single line "! " (without
// quotes), followed by array 𝑝 and quit.

// After printing a query do not forget to output end of line and flush the
// output. Otherwise, you will get Idleness limit exceeded. To do this, use:

// fflush(stdout) or cout.flush() in C++; System.out.flush() in Java;
// flush(output) in Pascal; stdout.flush() in Python; see documentation for
// other languages. Exit immediately after receiving "-1" and you will see Wrong
// answer verdict. Otherwise you can get an arbitrary verdict because your
// solution will continue to read from a closed stream.

// Hack format

// In the first line output 𝑛 (1≤𝑛≤104). In the second line print the
// permutation of 𝑛 integers 𝑝1,𝑝2,…,𝑝𝑛.


import java.io.*;

public class ChocolateBunny {
  public static int getNext(int[] arr, int maxIdx) {
    // search in arr to find the first index of zero element
    for (int i=1; i<arr.length; i++) {
      if (arr[i] == 0 && i != maxIdx) return i;
    }
    return -1;
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int n = Integer.parseInt(br.readLine());
      if (n == 1) {
        // if only 1 element, can only be 1 permutation
        System.out.println("! 1");
        return;
      }
      // ask at most n questions
      int[] arr = new int[n+1];

      // maxIdx and next are indexed from 1 to n
      int maxIdx=2;
      int next=getNext(arr, maxIdx);

      for (int i = 1; i < n; i++) {
        // ask question to compare (next and maxIdx)
        System.out.println("? " + next + " " + maxIdx);
        System.out.flush(); // flush output
        int a = Integer.parseInt(br.readLine());

        // ask second question (maxIdx, next)
        System.out.println("? " + maxIdx + " " + next);
        System.out.flush(); // flush output
        int b = Integer.parseInt(br.readLine());

        // check which one is smaller
        if (a < b) {
          // arr[next] > arr[maxIdx]
          arr[maxIdx] = b;
          maxIdx = next;
        } else {
          // a > b
          arr[next] = a;
        }
        next = getNext(arr, maxIdx);
        
      }
      arr[maxIdx] = n;

      System.out.print("! ");
      for (int i = 1; i<=n; i++) {
        System.out.print(arr[i] + " ");
      }
      System.out.println();
      br.close();
    }

    return;
  }
}
