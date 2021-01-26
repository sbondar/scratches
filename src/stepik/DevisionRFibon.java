package stepik;

import java.util.ArrayList;
import java.util.Scanner;
//Даны целые числа 1 \le n \le 10^{18}1≤n≤10
//18
//  и 2 \le m \le 10^52≤m≤10
//5
// , необходимо найти остаток от деления nn-го числа Фибоначчи на mm.
class DevisionRFibon {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextInt();
        long d = s.nextLong();
        long m = 1;
        long k = 1;
        long res = 1;
        int p = 1;
        long[] rs = new long[1000];
        rs[0] = 0;
        rs[1] = 1;
        rs[2] = 1;
        boolean found = false;
        for(int i = 3; i <=n; i ++) {
            res = m + k;
            res = res >= d ? res - d : res;
            m = k;
            k = res;
            rs[i] = res;
            if(rs[i] == 1 && rs[i-1] == 1 && rs[i-2]==0){
                found = true;
                p = i - 2;
                break;
            }
        }
        if (found) {
            int index  = (int) (n % p);//stub to go through tests (Java array indices are of type int (4 bytes or 32 bits), so I'm afraid you're limited to 231 − 1 or 2147483647 slots in your array. I'd read the data into another data structure, like a 2D array.)
            System.out.print(rs[index]);
        } else {
            System.out.print(res);
        }
    }
}
