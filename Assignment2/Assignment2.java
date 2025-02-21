package Assignment2;

import java.util.*;

public class Assignment2 {

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 0}, {2, 0}, {2, 3}, {2, 4}};
        int n = edges.length;
        List<List<Integer>> adjList = buildAdjacencyList(edges, n);

        System.out.println();
        for (int source = 0; source < edges.length; source++) {
            System.out.println("BFS from source: " + source + ": " + bfs(adjList, n, source));
        }

        System.out.println();
        for (int source = 0; source < edges.length; source++) {
            System.out.println("DFS from source: " + source + ": " + dfs(adjList, n, source));
        }
        System.out.println();
    }

    private static List<List<Integer>> buildAdjacencyList(int[][] edges, int n) {
        List<List<Integer>> adjList = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }

    private static List<Integer> bfs(List<List<Integer>> adjList, int n, int source) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        List<Integer> result = new ArrayList<>();

        queue.offer(source);
        visited[source] = true;
        result.add(source);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    result.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    private static List<Integer> dfs(List<List<Integer>> adjList, int n, int source) {
        boolean[] visited = new boolean[n];
        List<Integer> result = new ArrayList<>();
        dfsHelper(adjList, visited, result, source);
        return result;
    }

    private static void dfsHelper(List<List<Integer>> adjList, boolean[] visited, List<Integer> result, int current) {
        visited[current] = true;
        result.add(current);

        for (int neighbor : adjList.get(current)) {
            if (!visited[neighbor]) {
                dfsHelper(adjList, visited, result, neighbor);
            }
        }
    }
}