package stepik;

import java.util.*;
/*Восстановите строку по её коду и беспрефиксному коду символов. */

public class DecodeString {
    public static void main(String[] args) {
        new DecodeString().decodeString();
    }

    class BinarySearchTreeNode {
        private Character symbol;
        private BinarySearchTreeNode left;
        private BinarySearchTreeNode right;

        BinarySearchTreeNode() {
        }
    }
    private void decodeString() {
        Scanner s = new Scanner(System.in);
        int count = s.nextInt();
        s.nextLine();

        HashMap<Character, String> codes = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String[] inputs = s.nextLine().split(": ");
            codes.put(inputs[0].charAt(0), inputs[1]);
        }
        String input = s.nextLine();
        String result = "";

        BinarySearchTreeNode root = new BinarySearchTreeNode();
        codes.forEach((k, v) -> {
            BinarySearchTreeNode current = root;
            for (int i = 0; i < v.length(); i++) {
                if (v.charAt(i) == '0') {
                    if (current.left == null) {
                        current.left = new BinarySearchTreeNode();
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new BinarySearchTreeNode();
                    }
                    current = current.right;
                }
            }
            current.symbol = k;
        });

        BinarySearchTreeNode current = root;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i) == '0' ? current.left : current.right;
            if (current.left == null && current.right == null) {
                result += current.symbol;
                current = root;
            }
        }
        System.out.print(result);
    }
}
/*
*     BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
    String s = stdin.readLine();
    int N = Integer.parseInt(s);
* */

//P.S. Коды символов можно формировать не отдельным обходом получившегося дерева, а прямо в процессе его формирования!)
/*        @Override
        public int hashCode() {
            return Objects.hash(weight);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinarySearchTreeNode employee = (BinarySearchTreeNode) o;
            return employee.weight == weight;
        }

1 1
a: 0
0
        @Override
        public int compareTo(final BinarySearchTreeNode o) {
            return this.weight - o.weight;
        }
*/
//        LinkedHashMap<String, String> sortedCodes = codes.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(String::length)))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
