import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine()); // number of vertices, also number of edges
    List<List<Integer>> graph = new ArrayList<>();
    for (int i=0; i<n; i++) {
      graph.add(new ArrayList<>());
    }
    // read edges
    String[] s;
    for (int i=0; i<n; i++) {
      s = br.readLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int b = Integer.parseInt(s[1]);
      a--; b--;
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    int[] degree = new int[n];
    Set<Integer> cycle = new HashSet<>();
    // first find cycle.
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i=0; i<n; i++) {
      degree[i] = graph.get(i).size();
      if (degree[i] == 1) {
        deque.add(i); // leaves
      }
    }
    while (!deque.isEmpty()) {
      int cur = deque.pollFirst();
      for (int next : graph.get(cur)) {
        if (degree[next] == 2) {
          deque.add(next);
        }
        degree[next] -= 1;
      }
    }
    // remaining vertices are cycle
    for (int i=0; i<n; i++) {
      if (degree[i] > 1) {
        cycle.add(i);
      }
    }

    // then we loop through each vertex in the cycle, this node separates remaining vertices into two parts,
    // one is a subtree, the other is a one-cycle graph, vertices on the subtree side has exactly one simple paths
    // to each other, vertices of which one is from the subtree and the other is from the remaining graph has two 
    // simple paths 
    long res = (long) n*(n-1); // assume every pair of nodes has two simple paths, we will subtract later
    for (int cur : cycle) {
      // find number of nodes in subtree side
      long subtree = 0; // the vertex itself
      Deque<Integer> subq = new ArrayDeque<>();
      Set<Integer> visited = new HashSet<>();
      subq.add(cur);
      while (!subq.isEmpty()) {
        int v = subq.pollFirst();
        visited.add(v);
        subtree++;
        for (int next : graph.get(v)) {
          if (cycle.contains(next) || visited.contains(next)) continue; // if it is in the cycle or visited, ignore
          subq.add(next);
        }
      }
      // we can calculate number of simple paths including this subtree nodes
      res -= subtree * (subtree-1) / 2; // pair of nodes in this subtree has only one simple paths
    }

    System.out.println(res);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
