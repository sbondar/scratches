package stepik;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

class BootcampTest {

    private static final int MOD = (int) (1e9 + 7);
    List<BigInteger> cache = new ArrayList<>();

    {
        cache.add(BigInteger.ZERO);
        cache.add(BigInteger.ONE);
    }

    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();
//        Scanner s = new Scanner(System.in);
//        long a = s.nextLong();
//        long b = s.nextLong();

        ArrayList<String> chars = new ArrayList<>();
        chars.addAll(List.of("h", "e", "l", "l", "0"));
        final List<String> count = reverseString(chars);
        count.forEach(c -> System.out.print(c + " "));

//        System.out.println(count(List.of(1), 2));
//        System.out.println(nod(a, b));
//        System.out.print(System.currentTimeMillis() - startTime + " ms");


    }


    /*Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.*/
    public static List<String> reverseString(List<String> s) {
        int k = s.size() / 2;
        String b;
        for (int i = 0; i < k; i++) {
            b = s.get(i);
            s.set(i, s.get(s.size() - 1 - i));
            s.set(s.size() - 1 - i, b);
        }
        return s;
    }

    /*
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.*/
    public static int count(List<Integer> coins, int amount) {
        int i = 0;
        int result = 0;
        List<Integer> sorted = coins.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        while (i < sorted.size()) {
            if (sorted.get(i) <= amount) {
                amount -= sorted.get(i);
                result++;
            } else {
                i++;
            }
        }
        return amount == 0 ? result : -1;
    }

    /*Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].*/
    public static List<Integer> count1(List<Integer> numbers_arr) {
        List<Integer> sorted = numbers_arr.stream().sorted().collect(Collectors.toList());

        Map<Integer, Integer> ap = new HashMap<>();
        ap.put(sorted.get(0), 0);

        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i) > sorted.get(i - 1)) {
                ap.put(sorted.get(i), i);
            }
        }

        return numbers_arr.stream().map(ap::get).collect(Collectors.toList());
    }


    private static long nod(final long a, final long b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a >= b) {
            return nod(a % b, b);
        }
        return nod(b % a, a);
    }
}
