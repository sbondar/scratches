package stepik;


import java.util.*;
        import java.util.stream.Collectors;

class Points {
    public static void main(String[] args) {
        new Points().findPoints();
    }

    public void findPoints() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        List<Pair> pairs = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            pairs.add(new Pair(s.nextInt(), s.nextInt()));
        }

        pairs.sort(Comparator.comparing(Pair::getLeft));

        int k = 0;
        int[] ps = new int[n];

        for (int i = 0; i < n; i++) {
            ps[i] = pairs.get(i).getLeft();
            for (int j = k; j < i; j++) {
                if (pairs.get(i).getLeft() >= pairs.get(j).getLeft() && pairs.get(i).getLeft() <= pairs.get(j).getRight()) {
                    ps[j] = pairs.get(i).getLeft();
                } else {
                    k = i;
                    break;
                }
            }
        }
        Set<Integer> points = Arrays.stream(ps).boxed().collect(Collectors.toSet());
        System.out.println(points.size());
        for (Integer point : points) {
            System.out.print(point + " ");
        }
    }

    class Pair {
        int left;
        int right;
        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }
    }
}
