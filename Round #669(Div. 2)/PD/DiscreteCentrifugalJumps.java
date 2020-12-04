// There are 𝑛 beautiful skyscrapers in New York, the height of the 𝑖-th one
// is ℎ𝑖. Today some villains have set on fire first 𝑛−1 of them, and now the
// only safety building is 𝑛-th skyscraper.

// Let's call a jump from 𝑖-th skyscraper to 𝑗-th (𝑖<𝑗) discrete, if all
// skyscrapers between are strictly lower or higher than both of them. Formally,
// jump is discrete, if 𝑖<𝑗 and one of the following conditions satisfied:

// 𝑖+1=𝑗 max(ℎ𝑖+1,…,ℎ𝑗−1)<min(ℎ𝑖,ℎ𝑗) max(ℎ𝑖,ℎ𝑗)<min(ℎ𝑖+1,…,ℎ𝑗−1). At
// the moment, Vasya is staying on the first skyscraper and wants to live a
// little longer, so his goal is to reach 𝑛-th skyscraper with minimal count of
// discrete jumps. Help him with calcualting this number.

// Input The first line contains a single integer 𝑛 (2≤𝑛≤3⋅105) — total amount
// of skyscrapers.

// The second line contains 𝑛 integers ℎ1,ℎ2,…,ℎ𝑛 (1≤ℎ𝑖≤109) — heights of
// skyscrapers.

// Output Print single number 𝑘 — minimal amount of discrete jumps. We can show
// that an answer always exists.

import java.io.*;
import java.util.*;

public class DiscreteCentrifugalJumps {
  public static void solve(int n, int[] arr) {
    int[] dp = new int[n]; // dp array
    for (int i=1; i<n; i++) dp[i] = Integer.MAX_VALUE;
    int[] lg = new int[n]; // first greater element from the left
    int[] ll = new int[n]; // first smaller element from the left
    int[] rg = new int[n]; // first greater element from the right
    int[] rl = new int[n]; // first smaller element from the right

    List<List<Integer>> jumps = new ArrayList<>(); // for each index i, use a list to record which places it can jump to
    for (int i=0; i<n; i++) {
      jumps.add(new ArrayList<Integer>());
    }
    // preprocessing
    Stack<Pair<Integer, Integer>> stack = new Stack<>(); // each pair, first is height of skycraper, second is index in original array

    for (int i=0; i<n; i++) {
      while (!stack.isEmpty() && stack.peek().first < arr[i]) {
        stack.pop();
      }
      if (stack.isEmpty()) lg[i] = -1;
      else lg[i] = stack.peek().second; // ith building is first that is greater than s[-1].second building
      stack.add(new Pair<Integer, Integer>(arr[i], i));
    }
    stack.clear();

    for (int i=0; i<n; i++) {
      while (!stack.isEmpty() && stack.peek().first > arr[i]) {
        stack.pop();
      }
      if (stack.isEmpty()) ll[i] = -1;
      else ll[i] = stack.peek().second; 
      stack.add(new Pair<Integer, Integer>(arr[i], i));
    }
    stack.clear();

    for (int i=n-1; i>=0; i--) {
      while (!stack.isEmpty() && stack.peek().first > arr[i]) {
        stack.pop();
      }
      if (stack.isEmpty()) rg[i] = -1;
      else rg[i] = stack.peek().second; 
      stack.add(new Pair<Integer, Integer>(arr[i], i));
    }
    stack.clear();

    for (int i=n-1; i>=0; i--) {
      while (!stack.isEmpty() && stack.peek().first < arr[i]) {
        stack.pop();
      }
      if (stack.isEmpty()) rl[i] = -1;
      else rl[i] = stack.peek().second;
      stack.add(new Pair<Integer, Integer>(arr[i], i));
    }
    stack.clear();
    
    // we construct the jumps list
    for (int i = 0; i<n; i++) {
      if (lg[i] != -1) jumps.get(lg[i]).add(i);
      if (ll[i] != -1) jumps.get(ll[i]).add(i);
      if (rg[i] != -1) jumps.get(i).add(rg[i]);
      if (rl[i] != -1) jumps.get(i).add(rl[i]);
    }

    // dp
    for (int i=0; i<n; i++) {
      for (int j: jumps.get(i)) {
        dp[j] = Math.min(dp[j], dp[i] + 1);
      }
    }
    System.out.println(dp[n-1]);
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int n = Integer.parseInt(br.readLine());
      String[] s = br.readLine().split(" ");
      int[] arr = new int[n];
      for (int i=0; i<n; i++) {
        arr[i] = Integer.parseInt(s[i]);
      }
      solve(n, arr);
    }
  }
}

class Pair<T1, T2> {
  public T1 first;
  public T2 second;
  public Pair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }
}