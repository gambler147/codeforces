import java.io.*;


public class Solution {
  public static void solve(BufferedReader br) throws IOException {
    int n = Integer.parseInt(br.readLine());
    char[][] board = new char[n][n];

    for (int i=0; i<n; i++) {
      String s = br.readLine();
      for (int j=0; j<n; j++) {
        board[i][j] = s.charAt(j);
      }
    }

    // the idea is same, we group the board into 3 categories based on (i+j)%3
    // for each group, we have number of Xs x_g and number of Os o_g, for g = 0, 1, 2
    // our goal is to find some i and j, i!=j such that x_i + o_j is minimum and
    // x_i + o_j must be smaller than [k/3] since sum_i,j=0,1,2 (x_i + o_j) = 2k
    int arr[][] = new int[3][2];

    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        switch(board[i][j]) {
          case 'X':
            arr[(i+j)%3][0]++;
            break;
          case 'O':
            arr[(i+j)%3][1]++;
            break;
          default:
            break;
        }
      }
    }

    int min = Integer.MAX_VALUE;
    int gx=-1, go=-1;
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        if (i == j) continue;
        if (arr[i][0] + arr[j][1] < min) {
          gx = i;
          go = j;
          min = arr[i][0] + arr[j][1];
        }
      }
    }

    // print output
    for (int i=0; i<n; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j=0; j<n; j++) {
        if ((i+j)%3 == gx && board[i][j] == 'X') {
          sb.append('O');
        } else if ((i+j)%3 == go && board[i][j] == 'O') {
          sb.append('X');
        } else {
          sb.append(board[i][j]);
        }
      }
      System.out.println(sb.toString());
    }

    return;


  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      solve(br);
    }
  }
}
