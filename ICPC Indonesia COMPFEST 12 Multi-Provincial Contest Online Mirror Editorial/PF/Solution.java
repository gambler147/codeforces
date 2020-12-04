import java.io.*;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    // each time read a number
    int[] arr = new int[N];
    // ask total number
    System.out.format("? %d %d%n", 1, N);
    System.out.flush();
    int total = Integer.parseInt(br.readLine());
    int prev = total;
    for (int i=1; i<N-1; i++) {
      System.out.format("? %d %d%n", i+1, N);
      System.out.flush();
      int cur = Integer.parseInt(br.readLine());
      arr[i-1] = prev - cur;
      prev = cur;
    }
    System.out.format("? %d %d%n", 1, N-1);
    System.out.flush();
    arr[N-1] = total - Integer.parseInt(br.readLine());
    arr[N-2] = prev - arr[N-1];

    // print result
    StringBuilder sb = new StringBuilder("!");
    for (int i=0; i<N; i++) {
      sb.append(" ");
      sb.append(String.valueOf(arr[i]));
    }
    System.out.println(sb.toString());
    
  } 
}