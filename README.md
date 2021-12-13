# ObjectOriented_2021_Ex2
## Ex2_OOP

# ***About the Project:***   
This project represents the structure of a directed weighted graph, and run algorithms on it

![An-example-of-a-directed-graph-with-9-nodes](https://user-images.githubusercontent.com/93086649/145077579-9d8d11fa-9baa-4f7c-be9b-088ea8e97473.png)


  

# ***Directed Weighted Graphs_ans :***  
Represents the graph, and all the funcation it has- getNode, addNode, getEdge, connect (add new Edge), removeNode, removeEdge
**Nodes-** an HashMapcontains all the Nodes (with all the data anout him) in the graph.  
**Edges-** an HashMap contains all the Edges (with all the data anout him) in the graph.
**EdgesSize-** the number of edges in the graph.
**NodesSize-** the number of vertices in the graph.

<!-- **DirectedWeightedGraph_ans-** respresent the Graph itself. Each graph has two HashMaps:   
Hash of Nodes- represent all the Nodes (with all the data anout him) in the graph.    
Hash of Hash- represent node, and all the edges connect to him. Each edge have information about himself.    
We can get all the information about the Graph- include all the nodes and the edges, and set new information: add and remove Node and Edges, create a new edge by connect two node, etc.    
In order to do this, we have to implement 3 class:  NodeData, EdgeData, and GeaLocation.    -->


# ***NodaData:***  
 represent the nodes in the graph.  
 Each node have: 
- **key-** id associated with this node.
- **Geolocation-** location of this node 
- **weight-** he weight associated with this node
- **tag and info**


# ***EdgeData:***   
 represent all the edges in the graph. 
  Each edge have :  
 - **Src-** The id of the source node of this edge.
- **Dest-** The id of the destination node of this edge.
- **weight-** the weight associated with this edge
- **tag and info**  

# ***GeaLocation:***   
represent the location of (x,y,z), and distance between each teo of them. 




# ***Directed Weighted Graphs Algoritem :***    
This class resprents algoritems we can use on a Graph.  

- **isConnected-** check if the graph is connected. We with DFS algoritem on the original grahp, and the transpose graph. --> if equals, the graph is connected.
- **shortestPathDist-** returns the shortest path between to nodes. We use dijkstra algoritem to find the shortestpath.
- **shortestPath-** return a list of nodes, that represent the shortest path between to given node
- **center-**
- **tsp-** This method get list of vertices and computes a relatively short path which visit each node in the targets List.just a simple path going over all nodes in the list.
- **save-** saves a graph to json file.
- **load-** loads a graph from json to DirectedWeightedGraph_ans object.
