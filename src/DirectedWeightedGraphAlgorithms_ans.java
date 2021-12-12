import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class DirectedWeightedGraphAlgorithms_ans implements DirectedWeightedGraphAlgorithms, Serializable {
    private DirectedWeightedGraph_ans Graph = new DirectedWeightedGraph_ans();
    private HashMap<Integer, Integer> path = new HashMap<>();
    NodeData newNode = null;
    EdgeData newEdge = null;
    GeoLocation_class GeoLoc = null;
    HashMap<Integer, EdgeData> Edge_Hash_insider = null;

    @Override
//Init the graph on which this set of algorithms operates on.
    public void init(DirectedWeightedGraph g) {
        Graph = (DirectedWeightedGraph_ans) g;
    }

    @Override
//Return the underlying graph of which this class works.
    public DirectedWeightedGraph getGraph() {

        return this.Graph;
    }

    @Override
    // Return deep copy of this weighted graph.
    public DirectedWeightedGraph copy() {
//        we will use constructor:public DirectedWeightedGraph_ans (HashMap<Integer, HashMap<Integer, EdgeData>> local_Edge_hash,HashMap<Integer, NodeData> local_Node_hash,int local_nodeSize,int local_edgeSize,int local_Mc)
        DirectedWeightedGraph_ans graph_copy = new DirectedWeightedGraph_ans(Graph.Edges, Graph.Nodes, Graph.nodeSize(), Graph.edgeSize(), Graph.getMC());//we will define new graph_copy variable
        return graph_copy;

    }


    @Override
    //	 return true if the graph is connected (if there is a valid path from every node to each other node )and false if not
    public boolean isConnected() {
        if (Graph == null || Graph.nodeSize() == 0 || Graph.nodeSize() == 1)//if the graph empty or contain only one node
        {
            return true;
        }
        if (DFS(this.Graph, Graph.nodeIter().next()) == -1) {
            return false;
        }
        if (DFS(transpose(), transpose().nodeIter().next()) == -1) {
            return false;
        }
        return true;
    }

    // returns the length of the shortest path between src to dest, if no such path --> returns -1.
//	src - start node,dest - end (target) node,return Distance of the src to dest in double
    @Override
    public double shortestPathDist(int src, int dest) {
        if (Graph.getNode(src) == null || Graph.getNode(dest) == null) {//|| shortestPath(src, dest) == null) {
            return -1;//if no such path we will returns -1.
        }
        if (src == dest) {
            return 0;
        }//if the source is the dist , we will return 0
        int count = 0;
        Iterator<NodeData> iter1 = Graph.nodeIter();

        for (int i = 0; i < Graph.Nodes.size(); i++) {
            Graph.Nodes.get(i).setTag(0);
            Graph.Nodes.get(i).setWeight(0);
            Graph.Nodes.get(i).setInfo(null);
        }
        DIJKSTRA(src, dest);
        if (Graph.getNode(dest).getWeight() == 0) {
            return -1;
        }
        return Graph.getNode(dest).getWeight();
    }

    //returns the the shortest path between src to dest - as an ordered List of nodes:
    @Override
    public List<NodeData> shortestPath(int src, int dest) {/////////////////////////////////////////////////
        List<NodeData> ans = new ArrayList<>();

        if (Graph == null || Graph.nodeSize() == 0 || Graph.nodeSize() == 1) {//if the graph empty or contain only one node
            return null;
        }
        if (src == dest) { //if source is equal to dest, so there will be only one node
            ans.add(Graph.getNode(src));
            return ans;
        }
        if (shortestPathDist(src, dest) == -1) {
            return null;

        }
        for (Integer i = dest; i != -1; i = path.get(i)) {
            ans.add(Graph.getNode(i));
        }
        Collections.reverse(ans);
        return ans;
    }


    @Override // finds the NodeData which minimizes the max distance to all the other nodes.
    public NodeData center()///////////////////////////////////////////////////////////////////////////
    {
        NodeData initNode;
        if (isConnected() == true) {

            int id;
            int i = 0;
            int j = 0;
            ;
            double MaxDist = 0;
            HashMap<Integer, Double> HashToCompare = new HashMap<>();//define new hashmap that will contain all id of nodes as keys, and all Maxdistance as values
            Iterator<NodeData> iter1 = Graph.nodeIter();//define iterator that will moove over all nodes in graph
            while (iter1.hasNext())// now we will move on each node and check if he could be the center
            {
                MaxDist = 0;
                NodeData node = iter1.next();
                id = node.getKey();//
                Iterator<NodeData> iter2 = Graph.nodeIter();
                while (iter2.hasNext() == true)//check the short path with all the other nodes
                {
                    NodeData nodeDest = iter2.next();
                    if (shortestPathDist(node.getKey(), nodeDest.getKey()) > MaxDist)//if the shortestPathDist is bigger than the Max num, so we need to define the shortestPathDist as the new max
                        MaxDist = shortestPathDist(node.getKey(), nodeDest.getKey());
                }
                HashToCompare.put(id, MaxDist);//we will put the id of the node as the key and the MaxDist we computed as the max short path as the value
            }
            double MinDist = HashToCompare.get(0);//we will init MinDist as the first MaxDist
            for (int eachDist = 0; eachDist < HashToCompare.size(); eachDist++)//we will moove over all the MaxDist HashToCompare(means all the values of HashToCompare)
            {
                if (HashToCompare.get(i) <= MinDist)//if the MaxDist in HashToCompare is smaller than the MinDist
                {
                    MinDist = HashToCompare.get(i);//we will define the MaxDist as the new MinDist
                    j = i;//we will save the key of the MinDist, so we can return it later
                }
                i++;//if not, we will continur to the next MaxDist
            }
            return Graph.getNode(j);//we will return the node with key j that we saved as the key of the MinDist
        }
        return null;//if the graph is not connected, we will return null
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        ArrayList<NodeData> temp = new ArrayList<>();
        ArrayList<NodeData> ans = new ArrayList<>();
        Iterator<NodeData> iter = cities.iterator();//define new iterator
        while (iter.hasNext()) {
            NodeData key = iter.next();
            if (Graph.getNode(key.getKey()) == null) {
                return null;
            }
            temp.add(Graph.getNode(key.getKey()));
        }
        for (int i = 0; i < temp.size() - 1; i++) {
            ArrayList<NodeData> temp2 = (ArrayList<NodeData>) shortestPath(temp.get(i).getKey(), temp.get(i + 1).getKey());
            if (temp2.isEmpty()) {
                return null;
            }
            for (int j = 0; j < temp2.size(); j++) {
                if (!ans.contains(temp2.get(j))) {
                    ans.add(temp2.get(j));
                }
            }
        }
        ArrayList<NodeData> temp2 = (ArrayList<NodeData>) shortestPath(temp.get(temp.size() - 1).getKey(), temp.get(0).getKey());
        if (temp2.isEmpty()) {
            return null;
        }
        for (int j = 0; j < temp2.size(); j++) {
            if (!ans.contains(temp2.get(j))) {
                ans.add(temp2.get(j));
            }
        }
        return ans;

    }

    //     * Saves this weighted (directed) graph to the given
    @Override
    public boolean save(String file) {

        //Making json object
        Gson graph_json = new GsonBuilder().setPrettyPrinting().create();
        String json = graph_json.toJson(Graph);
        //write json to file
        try {
            FileWriter x = new FileWriter(file);
            x.write(json);
            x.close();
            /**
             // write object to file
             PrintWriter graph_Out =new PrintWriter(new File(file));
             graph_Out.write(json);
             graph_Out.close();*/
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(DirectedWeightedGraph_ans.class, new graphJsonDecoder());
            Gson gson = builder.create();

            FileReader reader = new FileReader(file);
            DirectedWeightedGraph_ans temp = gson.fromJson(reader, DirectedWeightedGraph_ans.class);
            init(temp);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int DFS(DirectedWeightedGraph g, NodeData start) {
        // hashmap saves all nodes which visited by bfs algorithm
        Map<Integer, NodeData> visited = new HashMap<Integer, NodeData>();
        // queue of BFS
        Stack<NodeData> queue = new Stack<NodeData>();
        visited.put(start.getKey(), start);
        queue.push(start);
        int ans = -1;
        while (queue.size() != 0) {
            // take the first-added node, poll it from the queue, name it start and repeat the process
            start = queue.pop();
            int StartId = start.getKey();
            Collection<EdgeData> EdgesOutFromNode = EdgesGettingOfFromNode(StartId, (DirectedWeightedGraph_ans) g);
            int sizeOF = EdgesOutFromNode.size();
            //check all start node neighbours and add the unchecked nodes to the queue and the hashmap
            for (EdgeData n : EdgesOutFromNode) {
                if (!visited.containsKey(n.getDest())) {
                    visited.put(n.getDest(), g.getNode(n.getDest()));
                    queue.push(g.getNode(n.getDest()));
                    if (visited.size() == g.nodeSize()) {
                        ans = n.getDest();
                    }
                }
            }

        }
        //returns the number of connected nodes
        return ans;
    }

    //This function transpose on the graph and returns it after the change.
    public DirectedWeightedGraph transpose() {
        DirectedWeightedGraph trans = new DirectedWeightedGraph_ans();
        Collection<NodeData> AllNodes = EdgesGettingOfFromNode(Graph);

        for (NodeData n : AllNodes) {
            trans.addNode(n);
        }

        for (NodeData n : AllNodes) {
            Collection<EdgeData> AllEdges = EdgesGettingOfFromNode(n.getKey(), Graph);

            for (EdgeData e : AllEdges) {
                trans.connect(e.getDest(), e.getSrc(), e.getWeight());

            }
        }
        return trans;
    }


    private void DIJKSTRA(int start, int end) {
        PriorityQueue<NodeDI> q = new PriorityQueue<NodeDI>(new comperator());
        q.add((NodeDI) Graph.getNode(start));
        path.put(start, -1);                 //adding the src to the path and queue
        int count = 0;


        while (!q.isEmpty()) {
            NodeData curr = q.poll();
            if (curr.getInfo() == null) {       //true if we didn't visit this node
                curr.setInfo("v");
                if (curr.getKey() == end)     //when we get to dest node
                    return;
                for (EdgeData i : EdgesGettingOfFromNode(curr.getKey(), Graph)) {     //moving on each neighbour of curr
                    NodeData temp = Graph.getNode(i.getDest());
                    if (temp.getInfo() == null) {                //true if we didn't visit this node
                        double w = i.getWeight();
                        w += curr.getWeight();
                        if (temp.getWeight() != 0) {
                            if (w < temp.getWeight()) {             //if the new weight is less then the exist
                                temp.setWeight(w);
                                path.put(i.getDest(), curr.getKey());
                            }
                        } else {                                    //if it's first time we reach to this node
                            temp.setWeight(w);
                            path.put(i.getDest(), curr.getKey());
                        }
                        q.add((NodeDI) temp);
                    }
                }
            }
        }
    }

    //function returns a pointer for the collection represent all the edges getting out of the given node
    public Collection<EdgeData> EdgesGettingOfFromNode(int node_id, DirectedWeightedGraph_ans g) {
        if (g.Edges.containsKey(node_id))
            return g.Edges.get(node_id).values();
        return null;
    }

    private Collection<NodeData> EdgesGettingOfFromNode(DirectedWeightedGraph_ans graph) {
        return graph.Nodes.values();

    }

    private static class comperator implements Comparator<NodeDI> {
        public int compare(NodeDI n1, NodeDI n2) {

            return Double.compare(n1.getWeight(), n2.getWeight());
        }
    }


}




