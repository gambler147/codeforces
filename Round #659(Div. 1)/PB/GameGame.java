import java.util.Scanner;

/*

Koa the Koala and her best friend want to play a game.

The game starts with an array 𝑎 of length 𝑛 consisting of non-negative integers. Koa and her best friend move in turns and each have initially a score equal to 0. Koa starts.

Let's describe a move in the game:

During his move, a player chooses any element of the array and removes it from this array, xor-ing it with the current score of the player.
More formally: if the current score of the player is 𝑥 and the chosen element is 𝑦, his new score will be 𝑥⊕𝑦. Here ⊕ denotes bitwise XOR operation.

Note that after a move element 𝑦 is removed from 𝑎.

The game ends when the array is empty.
At the end of the game the winner is the player with the maximum score. If both players have the same score then it's a draw.

If both players play optimally find out whether Koa will win, lose or draw the game.

Input
Each test contains multiple test cases. The first line contains 𝑡 (1≤𝑡≤104) — the number of test cases. Description of the test cases follows.

The first line of each test case contains the integer 𝑛 (1≤𝑛≤105) — the length of 𝑎.

The second line of each test case contains 𝑛 integers 𝑎1,𝑎2,…,𝑎𝑛 (0≤𝑎𝑖≤109) — elements of 𝑎.

It is guaranteed that the sum of 𝑛 over all test cases does not exceed 105.

Output
For each test case print:

WIN if Koa will win the game.
LOSE if Koa will lose the game.
DRAW if the game ends in a draw.

Examples
input
3
3
1 2 2
3
2 2 3
5
0 0 0 2 2
output
WIN
LOSE
DRAW
input
4
5
4 1 5 1 3
4
1 0 1 6
1
0
2
5 4
output
WIN
WIN
DRAW
WIN

*/

public class GameGame {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int test = Integer.parseInt(scanner.nextLine());
    while (test-- > 0) {
      int n = Integer.parseInt(scanner.nextLine());
      int[] array = new int[n];
      String[] s = scanner.nextLine().split(" ");
      for (int i = 0; i < n; i++){
        array[i] = Integer.parseInt(s[i]);
      }
      // main work here
      int x = 0;
      for (int a : array) {
        x ^= a;
      }
      if (x == 0) {
        // every bit has even number of occurence in the array
        System.out.println("DRAW");
        continue;
      }

      for (int k = 30; k >= 0; --k) {
        if ((x >> k & 1) != 0) {
          int[] f = new int[2];
          for (int a : array) {
            ++f[a >> k & 1];
          }
          if (f[1] % 4 == 3 && f[0] % 2 == 0) {
            System.out.println("LOSE");
          } else {
            System.out.println("WIN");
          }
          break;
        }
      }
    }
    scanner.close();
  }
}