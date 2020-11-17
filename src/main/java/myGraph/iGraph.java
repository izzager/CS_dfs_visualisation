package myGraph;

public interface iGraph<T> {
    void addVertex(T vertex);
    void addEdge(T v1, T v2, int w);
    String toString();
}
