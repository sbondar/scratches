package stepik;

import java.util.*;
import java.util.stream.Collectors;
/*По данной непустой строке ss длины не более 10^410
4
 , состоящей из строчных букв латинского алфавита, постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв kk, встречающихся в строке, и размер получившейся закодированной строки. В следующих kk строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.*/

public class CodeHaffman {
    public static void main(String[] args) {
        new CodeHaffman().findCodedString();
    }


    class BinarySearchTreeNode {
        private int weight;
        private Character symbol;
        private String code;
        private BinarySearchTreeNode left;
        private BinarySearchTreeNode right;

        BinarySearchTreeNode(Character symbol, int nodeData) {
            this.weight = nodeData;
            this.symbol = symbol;
        }


        public BinarySearchTreeNode(final int weight, final BinarySearchTreeNode left,
                                    final BinarySearchTreeNode right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
        }
    }

    private void findCodedString() {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        HashMap<Character, Integer> weights = new HashMap<>();
        input.chars().mapToObj(c -> (char) c).forEach(c -> {
            if (weights.containsKey(c)) {
                weights.put(c, weights.get(c) + 1);
            } else {
                weights.put(c, 1);
            }
        });

        //build a tree
        Queue<BinarySearchTreeNode> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));


        weights.forEach((c, w) ->
                q.add(new BinarySearchTreeNode(c, w)));
        while (q.size() > 1) {
            BinarySearchTreeNode left = q.poll();
            BinarySearchTreeNode right = q.poll();
            q.add(new BinarySearchTreeNode(left.weight + right.weight, left, right));
        }
        //collect codes for characters
        Map<Character, String> codes = new HashMap<>();
        if (weights.size() == 1) {
            codes.put(input.charAt(0), "0");
        } else {
            collectCodes(q, codes);
        }

        //coded string
        String codedString = input.chars().mapToObj(c -> codes.get((char) c)).collect(Collectors.joining(""));

        //количество различных букв kk + coded string length
        System.out.println(weights.size() + " " + codedString.length());
        //character codes
        codes.forEach((c, str) -> System.out.println(String.format("%c: %s", c, str)));
        System.out.println(codedString);

    }

    private void collectCodes(final Queue<BinarySearchTreeNode> q, final Map<Character, String> codes) {
        BinarySearchTreeNode root = q.poll();
        LinkedList<BinarySearchTreeNode> stack = new LinkedList<>();
        stack.push(root);
        boolean goLeft = true;
        BinarySearchTreeNode current = root;
        current.code = "";
        String parentCode = "";
        while (stack.size() > 0) {
            if (goLeft) {
                while (current.left != null) {
                    stack.push(current);
                    parentCode = current.code;
                    current = current.left;
                    current.code = parentCode + "0";
                }
            }

            if (current.right != null) {
                parentCode = current.code;
                current = current.right;
                current.code = parentCode + "1";
                goLeft = true;
            } else {
                codes.put(current.symbol, current.code);
                current = stack.pop();
                goLeft = false;
            }
        }
    }
}
/*
*     BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
    String s = stdin.readLine();
    int N = Integer.parseInt(s);
* */

//P.S. Коды символов можно формировать не отдельным обходом получившегося дерева, а прямо в процессе его формирования
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

        @Override
        public int compareTo(final BinarySearchTreeNode o) {
            return this.weight - o.weight;
        }
*/