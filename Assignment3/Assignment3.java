package Assignment3;

import java.util.ArrayList;
import java.util.List;

public class Assignment3 {
    
    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 19, 1, 6, 8, 11, 9};
        int k = 4;

        System.out.println("\nSelection Problem with Array:");
        System.out.println("k-th smallest index: " + selectProblem(arr, k));

        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        System.out.println("\nSelection Problem with Graph:");
        System.out.println("k-th smallest degree: " + graph.findKthSmallestDegree(k) + "\n");
    }

    private static int selectProblem(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }    
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static class Graph {

        private List<List<Integer>> adjList;
        
        public Graph(int n) {
            adjList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        public int findKthSmallestDegree(int k) {
            int[] degrees = getDegrees();
            return quickSelect(degrees, 0, degrees.length - 1, k);
        }

        private int[] getDegrees() {
            int[] degrees = new int[adjList.size()];
            for (int i = 0; i < adjList.size(); i++) {
                degrees[i] = adjList.get(i).size();
            }
            
            return degrees;
        }
    }
}