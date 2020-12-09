# visualGraphs

Simple visualization of depth-first search in the graph, then highlighting the loop (if there is one) and breadth-first search.

Vertexes without color are unvisited. Grey vertexes were visited and their adjacent nodes are currently visited. Black vertexes are visited including their adjacent nodes. Red vertices are located in one of the graph cycles.

The current node has blue colored mark with symbol "v".The current adjacent node has greem colored mark with symbol "to".

LAUNCH NOTES:

1. When you starting a project using the Main class, it is possible to add edges and specify the initial vertex of the depth-first search through the interface, but step-by-step visualization is not available, only the result. In the interface, you should first clear the fields, then write the names of vertexes in them.

The first text field is for the starting vertex of the search.

The next two fields are for the vertex that is the beginning of the arc and the vertex that is the end of the arc.
To add an arc to the graph, fill in these two fields and click the "Add arc" button. The added arcs appear in the window behind the current window.

To start visualization, click the "Start visualization" button.

2. When you run the project through the Visual class, visualization of each step of the algorithm is available, but you need to manage the graph content through a file "graph.txt", located at the root of disk D (you can change the path inside the code). The file should be built according to the following rule:

true/false (DFS/BFS)

name_first_vertex

vertex1 vertex2

Where vertex1 is the name of the vertex that is the beginning of the arc, and vertex2 is the name of the vertex that is the end of the arc. There can be an unlimited number of such pairs of vertexes.

Example input file:

true

A

A B

B C

C A

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Простая визуацизация обхода в глубину в графе с последующем выделением цикла ( если он есть) и обхода в ширину


Вершины без цвета - непосещенные. Серые вершины - были посещены и в настоящий момент посещаются их потомки. Черные вершины - посещенные, включая потомков. Красные вершины - находятся в одном из циклов графа.

Текущая вершина обозначается синей точкой с пометкой v. Вершина, в которую следует путь, обозначается зеленой точкой с пометкой to.


ПРИМЕЧАНИЯ ЗАПУСКА:

1. При запуске проекта через класс Main возможно добавлять ребра и указывать начальную вершину обхода в глубину через интерфейс, но пошаговая визуализация недоступна, только результат.
В интерфейсе следует сначала очистить поля, затем писать в них названия вершин.

Первое текстовое поле - для стартовой вершины обхода. 

Следующие два поля - для вершины, которая является началом дуги, и вершины, которая является концом дуги, соответственно.
Для добавления дуги в граф следует заполнить эти два поля и нажать кнопку "Добавить дугу". Добавленные дуги появляются в окне позади текущего окна.

Для начала визуализации необходимо нажать кнопку "Начать визуализацию".

2. При запуске проекта через класс Visual доступна визуализация каждого шага алгоритма, но управлять содержанием графа необходимо через файл "graph.txt", лежащий в корне диске D
(внутри кода можно поменять путь). Сам файл должен быть построен по следующему принципу:

  true/false (DFS/BFS)
  
  название_начальной_вершины_обхода
  
  вершина1 вершина2
  
где вершина1 - название вершины, которая является началом дуги, вершина2 - название вершины, которая является концом дуги. Таких пар вершин может быть неограниченное количество.

Пример входного файла: 

true

A

A B

B C

C A
