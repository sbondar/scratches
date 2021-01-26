package stepik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*очередь с приоритетамичерез кучу */

public class Main {
    public static void main(String[] args) {
        new Main().heapAtWork();
    }

    private List<Integer> heap = new ArrayList<>();
    private int count;

    private void heapAtWork() {
        Scanner s = new Scanner(System.in);
        count = s.nextInt();
        s.nextLine();

        for (int i = 0; i < count; i++) {
            String input = s.nextLine();
            if (input.startsWith("Insert")) {
                insert(Integer.parseInt(input.substring(7)));
            } else {
                extractMax();
            }
        }
    }

    private void extractMax() {
        Integer result = heap.get(0);
        Integer lastOne = heap.remove(heap.size() - 1);
        heap.add(0, lastOne);


    }

    private void insert(final int newEl) {
        int i = heap.size();
        heap.add(newEl);
        int p = i / 2;
        int k;
        while (heap.get(i) > heap.get(p)) {
                k = heap.get(i);
                heap.add(i, heap.get(p));
                heap.add(p, k);
                i = p;
                p = i / 2;
        }
    }

}
