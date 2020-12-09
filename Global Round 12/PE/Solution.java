import java.io.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);
    int m = Integer.parseInt(s[1]);

    int[][] graph = new int[n][n];  // graph[i][j] is the distance from i to j
    // initialize with INTEGER_MAX
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        graph[i][j] = (i==j) ? 0 : Integer.MAX_VALUE;
      }
    }

    // read inputs
    int[] u = new int[m];
    int[] v = new int[m];
    for (int k=0; k<m; k++) {
      s = br.readLine().split(" ");
      u[k] = Integer.parseInt(s[0]) - 1;
      v[k] = Integer.parseInt(s[1]) - 1;
      int b = Integer.parseInt(s[2]);

      graph[u[k]][v[k]] = 1;
      graph[v[k]][u[k]] = (b == 0) ? 1 : -1;
    }

    // calculate shortest path for each pair u,v. Floyd-Warshall algorithm
    for (int k=0; k<n; k++) {
      for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
          if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE && graph[i][k] + graph[k][j] < graph[i][j]) {
            graph[i][j] = graph[i][k] + graph[k][j];
          }
        }
        // whenever there is a negative cycle, where graph[i][i] < 0, then it is impossible
        if (graph[i][i] < 0) {
          System.out.println("NO");
          return;
        }
      }
    }

    // find maximum distance of pair (u,v)
    int max_dist = -1;
    int max_idx = -1; // vertex u which has maximum distance (u,v)
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        if (graph[i][j] > max_dist) {
          max_dist = graph[i][j];
          max_idx = i;
        }
      }
    }

    // check whether the graph is bipartite
    for (int k=0; k<m; k++) {
      if (graph[max_idx][u[k]] == graph[max_idx][v[k]]) {
        System.out.println("NO");
        return;
      }
    }

    // to this point, the condition is possible
    System.out.println("YES");
    System.out.println(max_dist);

    StringBuilder sb = new StringBuilder();
    for (int i=0; i<n; i++) {
      sb.append(String.valueOf(graph[max_idx][i]));
      sb.append(" ");
    }

    System.out.println(sb.toString());

  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    solve(br);
  }
}
