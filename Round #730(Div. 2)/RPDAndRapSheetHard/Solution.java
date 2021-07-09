import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution {
  public static void main(String[] args) throws IOException {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    InputReader in = new InputReader(inputStream);
    OutputWriter out = new OutputWriter(outputStream);
    int t = in.nextInt();
    while (t-- > 0) {
      int n=in.nextInt();
      int k=in.nextInt();

      for (int i=0; i<n; i++) {
        if (i == 0) {
          out.println(0);
        } else if (i%2 == 0) {
          out.println(knxor(i, i-1, k));
        } else {
          out.println(knxor(i-1, i, k));
        }
        out.flush();
        int resp = in.nextInt();
        if (resp==1) break;
        
      }
    }
    out.close();
  }
  
  public static int knxor(int x, int y, int k) {
    int z = 0;
    int p=1;
    while (x > 0 || y>0) {
      int a=x%k;
      int b=y%k;
      x = x/k;
      y = y/k;
      int c=(a-b+k)%k;
      z=z+p*c;
      p*=k;
    }
    return z;
  }


  static class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private InputReader.SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c)) c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null) {
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);

    }

  }

  static class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void flush() {
        writer.flush();
    }

    public void println(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0) {
                writer.print(' ');
            }
            writer.print(objects[i]);
        }
        writer.print('\n');
    }

    public void close() {
        writer.close();
    }

  }

}