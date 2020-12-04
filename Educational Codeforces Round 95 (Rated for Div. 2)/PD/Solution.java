import java.io.*;
import java.util.*;

public class Solution {
  public static void add_one_to_map(int value, TreeMap<Integer, Integer> treemap) {
    if (treemap.containsKey(value)) {
      treemap.put(value, treemap.get(value) + 1);
    } else {
      treemap.put(value, 1);
    }
  }

  public static void delete_one_from_map(int value, TreeMap<Integer, Integer> treemap) {
    if (treemap.containsKey(value)) {
      treemap.put(value, treemap.get(value) - 1);
      if (treemap.get(value) == 0) {
        treemap.remove(value);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String[] s = br.readLine().split(" ");
      int n = Integer.parseInt(s[0]);
      int q = Integer.parseInt(s[1]);

      // read array
      int[] arr = new int[n];
      s = br.readLine().split(" ");
      for (int i=0; i<n; i++) {
        arr[i] = Integer.parseInt(s[i]);
      }

      Arrays.sort(arr);
      // calculate gaps and initialization
      TreeSet<Integer> values = new TreeSet<>();
      TreeMap<Integer, Integer> gaps = new TreeMap<>();
      gaps.put(0, 1);
    
      values.add(arr[0]);
      for (int i=1; i<n; i++) {
        values.add(arr[i]);
        int gap = arr[i] - arr[i-1];
        add_one_to_map(gap, gaps);
      }
      
      System.out.println(values.last() - values.first() - gaps.lastKey());

      while (q-- > 0) {
        String[] input = br.readLine().split(" ");
        int t = Integer.parseInt(input[0]);  // type
        int p = Integer.parseInt(input[1]);  // position

        if (t == 1) {
          // add a pile to position p
          values.add(p);
          if (values.size() == 1) {
            System.out.println(0);
            continue;
          } 
          
          if (values.last() == p) {
            // at least 2 elements and p is largest position
            int gap = p - values.lower(p);
            add_one_to_map(gap, gaps);
          } else if (values.first() == p) {
            // at least 2 elements and p is the smallest
            int gap = values.higher(p) - p;
            add_one_to_map(gap, gaps);
          } else {
            // at least 3 elements and p is in the middle
            int prev = values.lower(p);
            int next = values.higher(p);
            int gap_prev = p - prev;
            int gap_next = next - p;
            int gap = next - prev;
            // remove gap and add gap_prev and gap_next
            delete_one_from_map(gap, gaps);
            add_one_to_map(gap_prev, gaps);
            add_one_to_map(gap_next, gaps);
          }
          
        } else {
          // t == 0, remove a pile from position p
          if (values.size() == 1) {
            // there is only one position, remove and print 0
            values.remove(p);
            System.out.println(0);
            continue;
          }

          if (values.last() == p) {
            // at least two elements and removed position is the greatest, remove and update distance to previous pile
            int gap = p - values.lower(p);
            values.remove(p);
            delete_one_from_map(gap, gaps);
          } else if (values.first() == p) {
            // at least two piles and position to be removed is the smallest one, remove from values set and update
            int gap = values.higher(p) - p;
            values.remove(p);
            delete_one_from_map(gap, gaps);
          } else {
            // at least 3 elements and removed position is in the middle.
            int prev = values.lower(p);
            int next = values.higher(p);

            int gap_prev = p - prev;
            int gap_next = next - p;
            int gap = next - prev;

            values.remove(p);
            add_one_to_map(gap, gaps);
            delete_one_from_map(gap_prev, gaps);
            delete_one_from_map(gap_next, gaps);
          }
        }

        System.out.println(values.last() - values.first() - gaps.lastKey());
      }
    }
  }
}