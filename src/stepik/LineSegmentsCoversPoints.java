package stepik;

///=========== optimal solution
//По данным nn отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков содержит хотя бы одну из точек.
import java.util.*;

class LineSegmentsCoversPoints {
    public static void main(String[] args) {
        new LineSegmentsCoversPoints().findPoints();
    }

    public void findPoints() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        List<Pair> pairs = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            pairs.add(new Pair(s.nextInt(), s.nextInt()));
        }

        pairs.sort(Comparator.comparing(Pair::getRight));
        Set<Integer> points = new HashSet<>();
        int x = pairs.get(0).getRight();
        points.add(x);

        for (int i = 1; i < n; i++) {
            if (x < pairs.get(i).getLeft()) {
                x = pairs.get(i).getRight();
                points.add(x);
            }
        }
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