package stepik;

///===========непрерывный рюкзак
/*Первая строка содержит количество предметов n и вместимость рюкзака w. Каждая из следующих nn строк задаёт стоимость  и объём  предмета.
 Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
 помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.*/
 import java.util.*;

class BackPack {
    public static void main(String[] args) {
        new BackPack().findPoints();
    }

    public void findPoints() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        double v = s.nextInt();

        List<Pair> pairs = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            pairs.add(new Pair(s.nextInt(), s.nextInt()));
        }

        pairs.sort(Comparator.comparing(p -> p.getRight() / p.getLeft()));
        double res = 0;

        int i = 0;
        while (i < n && v > 0) {
            if (pairs.get(i).getRight() <= v) {
                res += pairs.get(i).getLeft();
                v -= pairs.get(i).getRight();
            } else {
                res += v * pairs.get(i).getLeft()/pairs.get(i).getRight();
                v = 0;
            }
            i++;
        }

        System.out.println(String.format("%.3f", res));
    }

    class Pair {
        double left;
        double right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public double getLeft() {
            return left;
        }

        public double getRight() {
            return right;
        }
    }
}
