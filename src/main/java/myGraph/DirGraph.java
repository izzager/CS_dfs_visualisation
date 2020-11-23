package myGraph;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class DirGraph<T> implements iGraph<T>{
    private HashMap<T, HashMap<T, Integer>> edges;

    public DirGraph() {
        edges = new HashMap<>();
    }

    public HashMap<T, HashMap<T, Integer>> getEdges() { return edges; }

    @Override
    public void addVertex(T vertex) {
        edges.put(vertex, new HashMap<>());
    }

    @Override
    public void addEdge(T v1, T v2, int w) {
        edges.get(v1).put(v2, w);
    }

    public static HashMap<Node, Integer> getColor(Graph graph) {
        HashMap<Node, Integer> color = new HashMap<>();
        for (Node node : graph) {
            color.put(node, 0); // изначально все вершины белые - непосещенные
        }
        return color;
    }

    public static HashMap<Node, Node> getParents(Graph graph) {
        HashMap<Node, Node> parents = new HashMap<>();
        for (Node node : graph) {
            parents.put(node, null);
        }
        return parents;
    }

    public void DFS(T v, HashMap<T, Integer> color) {
        color.put(v, 1); //серый цвет
        for (T to : edges.get(v).keySet()) {
            if (color.get(to) == 0) {
                DFS(to, color);
            }
        }
        color.put(v, 2); //черный цвет
    }

    private void BFS(T s, HashMap<T, Integer> d) {
        //посещенные вершины - изначально все false
        HashMap<T, Boolean> visited = new HashMap<>();
        Set<T> keys = edges.keySet();
        for (T key : keys) {
            visited.put(key, false);
        }
        //очередь обхода вершин
        LinkedList<T> queue = new LinkedList<>();

        visited.put(s, true); //начальную вершину посетили
        queue.add(s); //добавили в очередь
        d.put(s, 0); //расстояние равно 0

        while (queue.size() != 0) { //пока очередь не пуста
            T v = queue.poll(); //вытаскиваем переднюю вершину
            Set<T> verts = edges.get(v).keySet();
            for (T to : verts) { //идем по всем смежным с ней вершинам
                if (!visited.get(to)) { //если она непосещенная
                    visited.put(to, true); //то посетили
                    queue.add(to); //добавили в очередь
                    d.put(to, d.get(v) + 1); //расстояние на 1 больше чем у родителя
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Set<T> verts = edges.keySet();
        for (T v : verts) {
            str.append("Вершина ").append(v)
                    .append(". Количество смежных вершин: ")
                    .append(edges.get(v).size()).append(". Смежные вершины: ");
            Set<T> keys = edges.get(v).keySet();
            for (T key : keys) {
                str.append(key).append(" ");
                str.append("вес ").append(edges.get(v).get(key)).append(", ");
            }
            str.append('\n');
        }
        return str.toString();
    }

}
