package visualization;

import myGraph.DirGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static myGraph.DirGraph.getColor;
import static myGraph.DirGraph.getParents;

public class Visual {
    private boolean flagDFS = true;
    private Graph graph = new SingleGraph("VisualDFS");
    private String startNode;

    private SpriteManager sprites = new SpriteManager(graph);
    private Sprite s;
    private Sprite s1;

    private Node cycleStart = null;
    private Node cycleEnd = null;

    public Visual() {
        setProperties(graph);
    }

    public void addEdgeVisual(String from, String to) {
        graph.addEdge(from + to, from, to, true);
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public Graph getGraph() { return graph; }

    public void run() {
        graph.display();
        createGraph(graph);
        makeLabels(graph);
        DirGraph<Node> algoGraph = createAlgoGraph(graph);
        if (flagDFS) {
            HashMap<Node, Integer> color = getColor(graph);
            HashMap<Node, Node> parents = getParents(graph);
            visualDFS(algoGraph, graph.getNode(startNode), color, parents, 2000);
            findCycle(parents);
        } else {
            visualBFS(algoGraph, graph.getNode(startNode), 2000);
        }
    }

    public void runVisual() {
        setProperties(graph);
        makeLabels(graph);
        DirGraph<Node> algoGraph = createAlgoGraph(graph);
        if (flagDFS) {
            HashMap<Node, Integer> color = getColor(graph);
            HashMap<Node, Node> parents = getParents(graph);
            visualDFS(algoGraph, graph.getNode(startNode), color, parents, 10);
            findCycle(parents);
        } else {
            visualBFS(algoGraph, graph.getNode(startNode), 10);
        }
    }

    private void setProperties(Graph graph) {
        graph.setAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
    }

    public void createGraph(Graph graph) {
        try (FileReader fr = new FileReader("D:\\graph.txt")) {
            Scanner scanner = new Scanner(fr);
            flagDFS = scanner.nextBoolean();
            startNode = scanner.next();
            String from;
            String to;
            while (scanner.hasNext()) {
                from = scanner.next();
                to = scanner.next();
                graph.addEdge(from + to, from, to, true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public DirGraph<Node> createAlgoGraph(Graph graph) {
        DirGraph<Node> algoGraph = new DirGraph<>();
        System.out.println(graph.getNodeCount());
        for (Node node : graph) {
            algoGraph.addVertex(node);
        }
        Stream<Edge> edges = graph.edges();
        edges.forEach(e -> algoGraph.addEdge(e.getNode0(), e.getNode1(), 1));
        System.out.println(algoGraph);
        return algoGraph;
    }


    public void visualDFS(DirGraph<Node> algoGraph, Node v,
                          HashMap<Node, Integer> color, HashMap<Node, Node> parents, int millisec) {
        color.put(v, 1); //серый цвет
        v.setAttribute("ui.class", "markedGrey");
        s = sprites.addSprite("S1");
        sprites.getSprite("S1").setAttribute("ui.class","v");
        sprites.getSprite("S1").setAttribute("ui.label", "v");
        s.attachToNode(v.getId());
        sleep(millisec);

        for (Node to : algoGraph.getEdges().get(v).keySet()) {
            if (color.get(to) == 2) { // не ходим в уже окончательно посещенные вершины
                continue;
            }
            s1 = sprites.addSprite("S2");
            sprites.getSprite("S2").setAttribute("ui.class","tov");
            sprites.getSprite("S2").setAttribute("ui.label", "to");
            s1.attachToNode(to.getId());
            sleep(millisec);
            if (color.get(to) == 0) {
                parents.put(to, v);
                s.detach();
                s1.detach();
                s.clearAttributes();
                s1.clearAttributes();
                visualDFS(algoGraph, to, color, parents, millisec);
            } else if (color.get(to) == 1) {
                // пытались пойти в серую вершину, т.е в ту,
                // в которую уже ходили и по потомкам котрой идем (сделали круг и вернулись обратно)
                cycleStart = to;
                cycleEnd = v;
            }
            s.detach();
            s1.detach();
            s.clearAttributes();
            s1.clearAttributes();
        }

        color.put(v, 2); //черный цвет
        v.setAttribute("ui.class", "markedBlack");

        sleep(millisec);
    }

    public void visualBFS(DirGraph<Node> algoGraph, Node s, int millisec) {
        //массив расстояний до начальной вершины
        HashMap<Node, Integer> d = new HashMap<>();
        for (Node vert : algoGraph.getEdges().keySet()) {
            d.put(vert, 0);
        }
        //посещенные вершины - изначально все false
        HashMap<Node, Boolean> visited = new HashMap<>();
        for (Node vert : algoGraph.getEdges().keySet()) {
            visited.put(vert, false);
        }
        //очередь обхода вершин
        LinkedList<Node> queue = new LinkedList<>();

        visited.put(s, true); //начальную вершину посетили
        s.setAttribute("ui.class", "markedBlack");
        sleep(millisec);
        queue.add(s); //добавили в очередь
        d.put(s, 0); //расстояние равно 0
        sprites.addSprite("S" + s.getId());
        sprites.getSprite("S" + s.getId()).setAttribute("ui.class","bfs");
        sprites.getSprite("S" + s.getId()).setAttribute("ui.label", d.get(s).toString());
        sprites.getSprite("S" + s.getId()).attachToNode(s.getId());
        sleep(millisec);

        while (queue.size() != 0) { //пока очередь не пуста
            Node v = queue.poll(); //вытаскиваем переднюю вершину
            v.setAttribute("ui.class", "markedBlack");
            sleep(millisec);
            Set<Node> verts = algoGraph.getEdges().get(v).keySet();
            for (Node to : verts) { //идем по всем смежным с ней вершинам
                if (!visited.get(to)) { //если она непосещенная
                    visited.put(to, true); //то посетили
                    to.setAttribute("ui.class", "markedGrey");
                    queue.add(to); //добавили в очередь
                    d.put(to, d.get(v) + 1); //расстояние на 1 больше чем у родителя
                    sprites.addSprite("S" + to.getId());
                    sprites.getSprite("S" + to.getId()).setAttribute("ui.class","bfs");
                    sprites.getSprite("S" + to.getId()).setAttribute("ui.label", d.get(to).toString());
                    sprites.getSprite("S" + to.getId()).attachToNode(to.getId());
                }
            }
            sleep(millisec);
        }
    }

    public void findCycle(HashMap<Node, Node> parents) {
        Node currNode = cycleEnd;
        while (currNode != null && !currNode.equals(cycleStart)) {
            currNode.setAttribute("ui.class", "markedCycle");
            currNode = parents.get(currNode);
        }
        if (currNode != null) {
            currNode.setAttribute("ui.class", "markedCycle");
        }
    }

    public void makeLabels(Graph graph) {
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }
    }


    protected void sleep(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        new Visual().run();
    }

    protected String styleSheet =
            "node {" +
                    "text-size: 20px;" +
                    "text-alignment: above;" +
                    "size: 35px;" +
                    "fill-color: white;" +
                    "stroke-mode: plain;" +
                    "stroke-color: black;" +
                    "text-mode: normal;" +
            "}" +
            "node.markedGrey {" +
                    "text-size: 20px;" +
                    "text-alignment: above;" +
                    "size: 35px;" +
                    "fill-color: grey;" +
                    "stroke-mode: plain;" +
                    "stroke-color: black;" +
                    "text-mode: normal;" +
            "}" +
            "node.markedBlack {" +
                    "text-size: 20px;" +
                    "text-alignment: above;" +
                    "size: 35px;" +
                    "fill-color: black;" +
                    "stroke-mode: plain;" +
                    "stroke-color: black;" +
                    "text-mode: normal;" +
            "}" +
            "node.markedCycle {" +
                    "text-size: 20px;" +
                    "text-alignment: above;" +
                    "size: 35px;" +
                    "fill-color: red;" +
                    "stroke-mode: plain;" +
                    "stroke-color: black;" +
                    "text-mode: normal;" +
            "}" +
            "edge {" +
                    "arrow-size: 10px; " +
                    "fill-color: black;" +
            "}" +
            "edge.marked {" +
                    "arrow-size: 10px; " +
                    "fill-color: red;" +
            "}" +
            "sprite.v {" +
                    "text-size: 20px;" +
                    "text-color: black;" +
                    "size: 20px;" +
                    "fill-color: blue;" +
                    "text-mode: normal;" +
            "}" +
            "sprite.tov {" +
                    "text-size: 20px;" +
                    "text-color: black;" +
                    "size: 20px;" +
                    "fill-color: green;" +
                    "text-mode: normal;" +
            "}" +
            "sprite.bfs {" +
                    "text-size: 20px;" +
                    "text-color: white;" +
                    "size: 20px;" +
                    "text-mode: normal;" +
            "}";

}
