import java.io.*;
import java.util.PriorityQueue;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    int[] locked = new int[n];

    String[] s = br.readLine().split(" ");
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(s[i]);
    }
    
    s = br.readLine().split(" ");
    for (int i=0; i<n; i++) {
      locked[i] = Integer.parseInt(s[i]);
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i=0; i<n; i++) {
      if (locked[i] == 0) {
        pq.add(arr[i]);
      }
    }

    for (int i=n-1; i>=0; i--) {
      if (locked[i] == 0) {
        arr[i] = pq.poll();
      } 
    }
    printArray(arr);
  }

  public static void swap(int i, int j, int[] arr) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void printArray(int[] arr) {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<arr.length; i++) {
      sb.append(arr[i] + " ");
    }
    System.out.println(sb.toString());
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int trials = Integer.parseInt(br.readLine());
      for (int trial=1; trial <= trials; trial++) {
        solve(br);
      }
    }
  }
}