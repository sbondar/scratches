package stepik;

///=======По данному числу n найдите максимальное число kk, для которого nn можно представить как сумму kk различных натуральных слагаемых. Выведите в первой строке число kk, во второй — kk слагаемых.
import java.util.*;

class NatAdds {
    public static void main(String[] args) {
        new NatAdds ().findPoints();
    }

    public void findPoints() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        List<Integer> slags = new ArrayList<>();

        int k = 1;
        int sum = 1;
        slags.add(k);

        while (sum < n) {
            k++;
            sum += k;
            slags.add(k);
        }
        if (sum > n) {
            Integer value = sum - n;
            slags.remove(value);
        }

        System.out.println(slags.size());
        slags.forEach(sl -> System.out.print(sl + " "));
    }
}