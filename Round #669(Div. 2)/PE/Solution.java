// Egor is a famous Russian singer, rapper, actor and blogger, and finally he
// decided to give a concert in the sunny Republic of Dagestan.

// There are 𝑛 cities in the republic, some of them are connected by 𝑚
// directed roads without any additional conditions. In other words, road system
// of Dagestan represents an arbitrary directed graph. Egor will arrive to the
// city 1, travel to the city 𝑛 by roads along some path, give a concert and
// fly away.

// As any famous artist, Egor has lots of haters and too annoying fans, so he
// can travel only by safe roads. There are two types of the roads in Dagestan,
// black and white: black roads are safe at night only, and white roads — in the
// morning. Before the trip Egor's manager's going to make a schedule: for each
// city he'll specify it's color, black or white, and then if during the trip
// they visit some city, the only time they can leave it is determined by the
// city's color: night, if it's black, and morning, if it's white. After
// creating the schedule Egor chooses an available path from 1 to 𝑛, and for
// security reasons it has to be the shortest possible.

// Egor's manager likes Dagestan very much and wants to stay here as long as
// possible, so he asks you to make such schedule that there would be no path
// from 1 to 𝑛 or the shortest path's length would be greatest possible.

// A path is one city or a sequence of roads such that for every road (excluding
// the first one) the city this road goes from is equal to the city previous
// road goes into. Egor can move only along paths consisting of safe roads only.

// The path length is equal to the number of roads in it. The shortest path in a
// graph is a path with smallest length.

// Input The first line contains two integers 𝑛, 𝑚 (1≤𝑛≤500000, 0≤𝑚≤500000)
// — the number of cities and the number of roads.

// The 𝑖-th of next 𝑚 lines contains three integers — 𝑢𝑖, 𝑣𝑖 and 𝑡𝑖
// (1≤𝑢𝑖,𝑣𝑖≤𝑛, 𝑡𝑖∈{0,1}) — numbers of cities connected by road and its
// type, respectively (0 — night road, 1 — morning road).

// Output In the first line output the length of the desired path (or −1, if
// it's possible to choose such schedule that there's no path from 1 to 𝑛).

// In the second line output the desired schedule — a string of 𝑛 digits, where
// 𝑖-th digit is 0, if the 𝑖-th city is a night one, and 1 if it's a morning
// one.

// If there are multiple answers, print any.

import java.io.*;
import java.util.*;

public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    String[] s = br.readLine().split(" ");
    int N = Integer.parseInt(s[0]);  // number of vertices
    int M = Integer.parseInt(s[1]);  // number of edges

    List<Pair<Integer, Integer>>[] edges = new ArrayList[N+1];
    for (int i=1; i<=N; i++) {
      edges[i] = new ArrayList<>();
    }
    // read edges, we reverse edges
    for (int i=1; i<=M; i++) {
      s = br.readLine().split(" ");
      int u = Integer.parseInt(s[0]);
      int v = Integer.parseInt(s[1]);
      int t = Integer.parseInt(s[2]);

      Pair<Integer, Integer> pair = new Pair<>(u, t);
      edges[v].add(pair);
    }

    // initialization
    int[] color = new int[N+1]; // color of vertices, can be either 1 or 0
    int[] dist = new int[N+1]; // shortest distance from N to i
    Arrays.fill(color, -1);
    Arrays.fill(dist, -1);
    dist[N] = 0;
    
    boolean[] visited = new boolean[N+1]; // whether vertices have been visited

    // bfs
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(N);
    visited[N] = true;

    while (queue.size() > 0) {
      int v = queue.poll();
      // iterate all inwards edges
      for (Pair<Integer, Integer> pair : edges[v]) {
        int u = pair.first;
        int t = pair.second;
        if (visited[u]) continue; // if visited we skip
        if (color[u] == t) {
          queue.add(u);
          visited[u] = true; 
          dist[u] = dist[v] + 1;
        } else {
          color[u] = 1 - t; // assign an opposite color to the road's color
        }
      }
    }

    StringBuilder sb = new StringBuilder(dist[1] + "\n");
    for (int i=1; i<=N; i++) {
      sb.append(Math.max(0, color[i]));
    }
    System.out.println(sb);
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      solve(br);
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