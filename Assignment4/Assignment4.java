package Assignment4;

import java.util.Arrays;

public class Assignment4 {

    public static void main(String[] args) {
        int[] arr = {3, 9, 2, 1, 4, 5, 7};
        Heap heap = new Heap(arr);

        System.out.println("\nHeap tree representation before construction:");
        heap.printHeapTree();

        heap.heapify();
        System.out.println("\nHeap tree representation after construction:");
        heap.printHeapTree();

        heap.insert(10);
        System.out.println("\nHeap tree representation after inserting number 10:");
        heap.printHeapTree();

        heap.removeTopNode();
        System.out.println("\nHeap tree representation after removing top node:");
        heap.printHeapTree();
        System.out.println();
    }

    public static class Heap {
        
        private int[] nodes;

        public Heap(int[] nodes) {
            this.nodes = nodes;
        }

        public void heapify() {
            int n = nodes.length;
            for (int i = n / 2 - 1; i >= 0; i--) {
                constructHeap(i, n);
            }
        }

        public void insert(int node) {
            nodes = Arrays.copyOf(nodes, nodes.length + 1);
            nodes[nodes.length - 1] = node;
            int i = nodes.length - 1;

            while (i > 0) {
                int parent = (i - 1) / 2;
                if (nodes[parent] >= nodes[i]) continue;
                swap(parent, i);
                i = parent;
            }
        }

        public int removeTopNode() {
            int top = nodes[0];
            nodes[0] = nodes[nodes.length - 1];
            nodes = Arrays.copyOf(nodes, nodes.length - 1);
            constructHeap(0, nodes.length);
            return top;
        }

        public void printHeapTree() {
            System.out.println(Arrays.toString(nodes));
        }

        private void constructHeap(int i, int n) {
            int largestNode = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && nodes[left] > nodes[largestNode]) {
                largestNode = left;
            }

            if (right < n && nodes[right] > nodes[largestNode]) {
                largestNode = right;
            }

            if (largestNode != i) {
                swap(i, largestNode);
                constructHeap(largestNode, n);
            }
        }

        private void swap(int i, int j) {
            int temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }
    }
}