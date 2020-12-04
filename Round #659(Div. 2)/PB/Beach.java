import java.io.*;
import java.util.*;

public class Beach {
  static class State {
    public final int tide;
    public final int pos;
    public final boolean down;
    public State(int pos, int tide, boolean down) {
      this.tide = tide;
      this.pos = pos;
      this.down = down;
    }

    @Override
    public int hashCode() {
      return Arrays.deepHashCode(new Object[] {pos, tide, down});
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof State)) {
        return false;
      }

      State o = (State)other;
      if (this.tide == o.tide && this.pos == o.pos && this.down == o.down) {
        return true;
      } else {
        return false;
      }

    }
  }

  public static boolean dp(int pos, int tide, boolean down, Set<State> memo, int n, int k, int l, int[] depth) {
    // System.out.println(pos +" " + tide + " " + down);
    State state = new State(pos, tide, down);
    if (memo.contains(state)) {
      return false;
    }
    
    if (pos > n) {
      return true;
    }
    
    memo.add(state);

    tide += down ? -1 : +1;
    if (tide == 0) {
      down = false;
    }
    if (tide == k) {
      down = true;
    }

    // stay at current position
    if (depth[pos] + tide <= l && dp(pos, tide, down, memo, n, k, l, depth)) {
      return true;
    }
    if (depth[pos+1] + tide <= l && dp(pos+1, tide, down, memo, n,k,l,depth)) {
      return true;
    }
    return false;
  }
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine()); // 3 elements
    while (test-- > 0) {
      String[] s = br.readLine().split(" ");
      int n = Integer.parseInt(s[0]); // length of the shore
      int k = Integer.parseInt(s[1]); // seconds of tide for each wave
      int l = Integer.parseInt(s[2]); // maximum depth Koa can swim
      
      // depths array
      String[] sd = br.readLine().split(" ");
      int[] depth = new int[n+2];
      for (int i=0; i<n; i++) {
        depth[i+1] = Integer.parseInt(sd[i]);
      }
      depth[0] = -k;
      depth[n+1] = -k; 


      /* main work: use dynamic programming, we use tuple (int pos, int tide, boolean down) represents
      the status of Koa, each time he can transfer to two status, either stay at same position (pos, tide + (int) down, down)
      or swim to next position (pos+1, tide?, down?)
      */
      Set<State> memo = new HashSet<>(); // record unsuccessful results
      
      if (dp(0, 0, false, memo, n, k, l, depth)) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }


    }
    br.close();
  }
}