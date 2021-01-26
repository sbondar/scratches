package stepik;

import java.util.Scanner;
//По данным двум числам 1 \le a, b \le 2 \cdot 10^91≤a,b≤2⋅10
//9
//  найдите их наибольший общий делитель.
public class MaxCommonDev {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long a = s.nextLong();
        long b = s.nextLong();

        System.out.print(nod(a, b));
    }

    private static long nod(final long a, final long b) {
        if (a == 0 ) {
            return b;
        }
        if (b == 0 ) {
            return a;
        }
        if (a >= b) {
            return nod(a%b, b);
        }
        return nod(b%a, a);
    }

    private static long nod_noRec(long a, long b) {
        while(true) {
            if (a == 0 ) {
                return b;
            }
            if (b == 0 ) {
                return a;
            }
            if (a >= b) {
                // a, b <- a%b, b
                a%=b;
            }
            b%=a;
        }
    }
}
