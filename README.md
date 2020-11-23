# visualGraphs
Простая визуацизация обхода в глубину в графе с последующем выделением цикла ( если он есть) и обхода в ширину


Вершины без цвета - непосещенные. Серые вершины - были посещены и в настоящий момент посещаются их потомки. Черные вершины - посещенные, включая потомков. Красные вершины - находятся в одном из циклов графа.

Текущая вершина обозначается синей точкой с пометкой v. Вершина, в которую следует путь, обозначается зеленой точкой с пометкой to.


ПРИМЕЧАНИЯ ЗАПУСКА:

1. При запуске проекта через класс Main возможно добавлять ребра и указывать начальную вершину обхода в глубину через интерфейс, но пошаговая визуализация недоступна, только результат.
В интерфейсе следует сначала очистить поля, затем писать в них названия вершин.

Первое текстовое поле - для стартовой вершины обхода. 

Следующие два поля - для вершины, которая является началом дуги, и вершины, которая является концом дуги, соответственно.
Для добавления дуги в граф следует заполнить эти два поля и нажать кнопку "Добавить дугу". Добавленные дуги появляются парами через запятую в правом поле.

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
