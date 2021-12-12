import api.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import java.util.Comparator;

// Java program to read JSON from a file
import java.io.FileReader;
import java.io.*;

public class DirectedWeightedGraphAlgorithms_ans implements DirectedWeightedGraphAlgorithms, Serializable {
    private DirectedWeightedGraph_ans Graph = new DirectedWeightedGraph_ans();
    private HashMap<Integer, Integer> path=new HashMap<>();
    NodeData newNode = null;
    EdgeData newEdge = null;
    GeoLocation_class GeoLoc = null;
    HashMap<Integer, EdgeData> Edge_Hash_insider = null;

//    public static void main(String[] args) {
//        boolean ans = DirectedWeightedGraphAlgorithms_ans. //("G1.json");
//    }

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

//        DirectedWeightedGraph_ans graph_copy= new DirectedWeightedGraph_ans(Graph);
//        return graph_copy;

    }


    @Override
    //Returns true if and only if there is a valid path from EVREY node to each
    //	 other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
    //	 return true if the graph is connected and false if not
    public boolean isConnected() {
        if (Graph == null || Graph.nodeSize() == 0 || Graph.nodeSize() == 1)//if the graph empty or contain only one node
        {
            return true;
        }
//        if (DFS(this.Graph, Graph.nodeIter().next()) == -1) {
//            return false;
//        }
        //AllNodesInTheGraph

        if (DFS(this.Graph, Graph.AllNodesInTheGraph().iterator().next()) == -1) {
            return false;
        }
//        if (DFS(transpose(), transpose().nodeIter().next()) == -1) {
        if (DFS(transpose(), transpose().nodeIter().next()) == -1) {
            return false;
        }
        return true;
    }

    // returns the length of the shortest path between src to dest, if no such path --> returns -1.
//	src - start node,dest - end (target) node,return Distance of the src to dest in double
    @Override
    public double shortestPathDist(int src, int dest) {
        if (Graph.getNode(src) == null || Graph.getNode(dest) == null ){//|| shortestPath(src, dest) == null) {
            return -1;//if no such path we will returns -1.
        }
        if (src == dest) {
            return 0;
        }//if the source is the dist , we will return 0
        int count=0;
        Iterator<NodeData> iter1 = Graph.nodeIter();

        for (int i = 0; i < Graph.Nodes.size(); i++)
        {
            Graph.Nodes.get(i).setTag(0);
            Graph.Nodes.get(i).setWeight(0);
            Graph.Nodes.get(i).setInfo(null);
        }
//        while (iter1.hasNext()) {
//
//            NodeData temp= Graph.nodeIter().next() ;
//            temp.setInfo(null);
//            temp.setWeight(0);
//            temp.setTag(0);
//            count++;
//            System.out.println("this is the :"+ count+"iteration");
//        }
        DIJKSTRA(src, dest);
        if (Graph.getNode(dest).getWeight() == 0) {
            return -1;
        }
        return Graph.getNode(dest).getWeight();
    }

    @Override // מחזיר את המסלול הכי קצר בתור רשימה //דיאקסטרה
    public List<NodeData> shortestPath(int src, int dest) {/////////////////////////////////////////////////
        List<NodeData> ans = new ArrayList<>();

        if (Graph == null || Graph.nodeSize() == 0 || Graph.nodeSize() == 1) {//if the graph empty or contain only one nod
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


    // הקודקוד שהכי רחוק ממני אבל בדרך של שורטפאט'
    @Override // finds the NodeData which minimizes the max distance to all the other nodes.
    public NodeData center()///////////////////////////////////////////////////////////////////////////
    {

//            double Min = Double.MAX_VALUE;//define Min to be the vertical with the min radius value to the most far vertical.
//            int index = -1;
//            Graph.getMC();  // there is some changes in the graph at the last time we chack the short path.
//            Iterator<NodeData> node = Graph.nodeIter();//define iterator that will moove over all nodes in graph
//            while (node.hasNext()) // now we will move on each node and check if he could be the center
//            {
//                double Max = 0;
//                NodeData src = node.next();
//                Iterator<NodeData> temp = Graph.nodeIter();
//                while (temp.hasNext())  //check the short path with all the other nodes
//                {
//                    NodeData dst = temp.next();
//                    if (shortestPathDist(src.getKey(), dst.getKey()) > Max)//if the shortestPathDist is bigger than the Max num, so we need to define the shortestPathDist as the new max
//                    {
//                        Max= shortestPathDist(src.getKey(),dst.getKey());
//                    }
//                }
//                if (Min > Max)
//                {
//                    Min = Max;
//                    index = src.getKey();
//                }
//            }
//            if (index != -1)
//            {
//                return Graph.getNode(index);
//            }

//            //*****sec algo *****************************************************************************************///
//            NodeData initNode;
//            if (isConnected() == true) {
//            int id;
//            int i = 0;
//            int j = 0;
//            ;
//            double MaxDist = 0;
//            HashMap<Integer, Double> HashToCompare = new HashMap<>();//define new hashmap that will contain all id of nodes as keys, and all Maxdistance as values
//            Iterator<NodeData> iter1 = Graph.nodeIter();//define iterator that will moove over all nodes in graph
//            while (iter1.hasNext())// now we will move on each node and check if he could be the center
//            {
//                MaxDist = 0;
//                NodeData node = iter1.next();
//                id = node.getKey();//
//                Iterator<NodeData> iter2 = Graph.nodeIter();
//                while (iter2.hasNext() == true)//check the short path with all the other nodes
//                {
//                    NodeData nodeDest = iter2.next();
//                    if (shortestPathDist(node.getKey(), nodeDest.getKey()) > MaxDist)//if the shortestPathDist is bigger than the Max num, so we need to define the shortestPathDist as the new max
//                        MaxDist = shortestPathDist(node.getKey(), nodeDest.getKey());
//                }
//                HashToCompare.put(id, MaxDist);//we will put the id of the node as the key and the MaxDist we computed as the max short path as the value
//            }
//            double MinDist = HashToCompare.get(0);//we will init MinDist as the first MaxDist
//            for (int eachDist = 0; eachDist < HashToCompare.size(); eachDist++)//we will moove over all the MaxDist HashToCompare(means all the values of HashToCompare)
//            {
//                if (HashToCompare.get(i) <= MinDist)//if the MaxDist in HashToCompare is smaller than the MinDist
//                {
//                    MinDist = HashToCompare.get(i);//we will define the MaxDist as the new MinDist
//                    j = i;//we will save the key of the MinDist, so we can return it later
//                }
//                i++;//if not, we will continue to the next MaxDist
//            }
//            return Graph.getNode(j);//we will return the node with key j that we saved as the key of the MinDist
//        }
//        return null;//if the graph is not connected, we will return null


        NodeData initNode;
        if (isConnected() == true)
        {
            int id;
            int i = 0;
            int j = 0;
            double MaxDist = 0;
            HashMap<Integer, Double> HashToCompare = new HashMap<>();//define new hashmap that will contain all id of nodes as keys, and all Maxdistance as values
            Collection<NodeData> listOfAllNodes=Graph.AllNodesInTheGraph();
            for (NodeData n: listOfAllNodes)
            {
                MaxDist = 0;
                id = n.getKey();//
                for (NodeData n1 : listOfAllNodes)//check the short path with all the other nodes
                {
                    if (n.getKey() == n1.getKey()) {
                        continue;
                    }
                    if (shortestPathDist(n.getKey(), n1.getKey()) > MaxDist)//if the shortestPathDist is bigger than the Max num, so we need to define the shortestPathDist as the new max
                    {
                        MaxDist = shortestPathDist(n.getKey(), n1.getKey());
                    }
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
                i++;//if not, we will continue to the next MaxDist
            }
            return Graph.getNode(j);//we will return the node with key j that we saved as the key of the MinDist

        }
        return null;//if the graph is not connected, we will return null
    }


    @Override //מסלול הקצר ביותר שמכיל את כל הקודקודים שמקבלים בליסט
    public List<NodeData> tsp(List<NodeData> cities) {
            ArrayList<NodeData> temp=new ArrayList<>();
            ArrayList<NodeData> ans= new ArrayList<>();
            Iterator<NodeData> iter=cities.iterator();//define new iterator
            while(iter.hasNext()){
                NodeData key=iter.next();
                if(Graph.getNode(key.getKey())==null)
                {
                    return null;
                }
                temp.add(Graph.getNode(key.getKey()));
            }
            for(int i=0;i<temp.size()-1;i++){
                ArrayList<NodeData> temp2= (ArrayList<NodeData>) shortestPath(temp.get(i).getKey(),temp.get(i+1).getKey());
                if(temp2.isEmpty())
                {
                    return null;
                }
                for(int j=0;j<temp2.size();j++){
                    if(!ans.contains(temp2.get(j))){
                        ans.add(temp2.get(j));
                    }
                }
            }
            ArrayList<NodeData> temp2= (ArrayList<NodeData>) shortestPath(temp.get(temp.size()-1).getKey(),temp.get(0).getKey());
            if(temp2.isEmpty())
            {
                return null;
            }
            for(int j=0;j<temp2.size();j++){
                if(!ans.contains(temp2.get(j))){
                    ans.add(temp2.get(j));
                }
            }
            return ans;

    }
//        if (cities.size() == 0) { //check if there is no cities in the list
//            return null;
//        }
//        if (cities.size() == 1) {
//            return cities;
//        }
//        DirectedWeightedGraph_ans graph_temp = (DirectedWeightedGraph_ans) this.copy(); // copy the graph and save into temp variable
//        //now we want to delete from the graph all the nodes that not in the cities
//        int citySize=cities.size();
//        for (int i = 0; i < citySize; i++)
//        {
//            if (Graph.Node_hash.containsKey(cities.get(i)))
//                {
//                    Graph.removeNode(cities.get(i).getKey());
//                }
//        }
////        while (graph_temp.nodeIter().hasNext()) { // delete from the graph all the nodes that not in the cities
////            NodeData temp = graph_temp.nodeIter().next();
////            if (!cities.contains(temp))
////            {
////                if (Graph.Node_hash.containsKey(temp.getKey()))
////                {
////                    Graph.removeNode(temp.getKey());
////                }
////            }
////        }
//        if (!this.isConnected()) { //check if the garph is connected
//            this.Graph = graph_temp;
//            return null;
//        }
//        List<NodeData> ans = shortestPath(cities.get(0).getKey(), cities.get(1).getKey()); //create ans list
//        int cities_size = cities.size();
//        while (ans.stream().distinct().collect(Collectors.toList()).size() != cities_size) { // check if we visit all the nodes
//            cities.remove(0);
//            for (NodeData n : ans) {
//                if (cities.contains(n)) {
//                    cities.remove(n);
//                }
//            }
//            ans.addAll(shortestPath(cities.get(0).getKey(), cities.get(1).getKey())); //union the list with the shorestpath
//        }
//        this.Graph = graph_temp;
//        return ans;
//    }

    @Override
//     * Saves this weighted (directed) graph to the given
//     * file name - in JSON format
//     * @param file - the file name (may include a relative path).
//     * @return true - iff the file was successfully saved
    public boolean save(String file) {
        //Making json object
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(Graph);
//
//        //write json to file
//        try {
//            PrintWriter pw = new PrintWriter(new File(file));
//            pw.write(json);
//            pw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;


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
            DirectedWeightedGraph temp = gson.fromJson(reader, DirectedWeightedGraph_ans.class);
            init(temp);
            return true;
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


//    public int DFS(DirectedWeightedGraph g, NodeData src) {
//        HashMap<Integer, NodeData> visit = new HashMap<Integer, NodeData>();
//        Stack<NodeData> queue = new Stack<NodeData>();
//        visit.put(src.getKey(), src);
//        queue.push(src);
//        int ans = -1;
//        while (queue.size() != 0) {
//            // take the first-added node, poll it from the queue, name it start and repeat the process
//            src = queue.pop();
//            //check all start node neighbours and add the unchecked nodes to the queue and the hashmap
////            while (g.edgeIter(src.getKey()).hasNext()) {
//            while (g.edgeIter(src.getKey()).next()!=null) {
////                if(src.getInfo()==null) {break;}
//                EdgeData e = g.edgeIter(src.getKey()).next();
//                if (!visit.containsKey(e.getDest())) {
//                    visit.put(e.getDest(), g.getNode(e.getDest()));
//                    queue.push(g.getNode(e.getDest()));
//                    if (visit.size() == g.nodeSize()) {
//                        ans = e.getDest();
//                    }
//                }
//            }
//
//        }
//        //returns the number of connected nodes
//        return ans;
//    }
public int DFS(DirectedWeightedGraph g,NodeData start) {
    // hashmap saves all nodes which visited by bfs algorithm
    Map<Integer, NodeData> visited = new HashMap<Integer, NodeData>();
    // queue of BFS
    Stack<NodeData> queue = new Stack<NodeData>();
    visited.put(start.getKey(), start);
    queue.push(start);
    int ans=-1;
    while (queue.size() != 0) {
        // take the first-added node, poll it from the queue, name it start and repeat the process
        start = queue.pop();
        int StartId=start.getKey();
        Collection<EdgeData> EdgesOutFromNode=EdgesGettingOfFromNode(StartId);
        //check all start node neighbours and add the unchecked nodes to the queue and the hashmap
        for (EdgeData n : EdgesOutFromNode) {
            if (!visited.containsKey(n.getDest())) {
                visited.put(n.getDest(), g.getNode(n.getDest()));
                queue.push(g.getNode(n.getDest()));
                if(visited.size()==g.nodeSize()) {
                    ans = n.getDest();
                }
            }
        }
    }
    return ans;    //returns the number of connected nodes

}

    public DirectedWeightedGraph transpose() {
        DirectedWeightedGraph trans = new DirectedWeightedGraph_ans();

        while (Graph.nodeIter().hasNext()) {
            trans.addNode(Graph.nodeIter().next());
        }
        while (Graph.nodeIter().hasNext()) {
            while (Graph.edgeIter(Graph.nodeIter().next().getKey()).hasNext()) {
                EdgeData e = Graph.edgeIter(Graph.nodeIter().next().getKey()).next();
                trans.connect(e.getDest(), e.getSrc(), e.getW());

            }


        }
        return trans;
    }

//    private void DIJKSTRA(int start, int end) {
//        PriorityQueue<NodeData> q = new PriorityQueue<NodeData>(new comperator());
//        q.add(Graph.getNode(start)); //add the start node to the queue and the haspMap path
//        path.put(start, -1);
//        while (!q.isEmpty()) {
//            NodeData curr = q.poll();
//            if (curr.getInfo() == null) { // we didnt visit the node
//                curr.setInfo("v");
//                if (curr.getKey() == end) {//when we get to dest node
//                    return;
//                }
//                for (int i = 0; i < Graph.Node_hash.size(); i++)
//                {
//                    EdgeData e=
//
//                }
//                   /* while (Graph.edgeIter(curr.getKey()).hasNext()) { // go all over the hashEdge in the node
//                        EdgeData e = Graph.edgeIter(curr.getKey()).next();
//                        NodeData temp_node = Graph.getNode(e.getDest());
//                        if (temp_node.getInfo() == null) {  // we didnt visit the node
//                            double temp_weight = e.getWeight();
//                            temp_weight += curr.getWeight(); //the total weight of edge+node
//                            if (temp_node.getWeight() != 0) {
//                                if (temp_weight < temp_node.getWeight()) { //check min weight, if true-> set the min weight for the temp_node
//                                    temp_node.setWeight(temp_weight); //
//                                    path.put(e.getDest(), curr.getKey()); // add to the path Hash
//                                }
//                            } else {  //if we didnt visit the node yet, set the temp_weight.
//                                temp_node.setWeight(temp_weight);
//                                path.put(e.getDest(), curr.getKey());
//                            }
//                            q.add(temp_node);
//                        }
//                    }///  */
//                }
//            }
//        }

    private void DIJKSTRA(int start, int end) {
        PriorityQueue<NodeDI> q = new PriorityQueue<NodeDI>(new comperator());
        q.add((NodeDI) Graph.getNode(start));
        path.put(start, -1);                 //adding the src to the path and queue
       int count=0;
        System.out.println("the size of Q is:"+q.size());
        System.out.println("q.isEmpty():"+q.isEmpty());

        while (!q.isEmpty()) {
            System.out.println("iteration num:"+ count++);
            NodeData curr = q.poll();
            System.out.println("q.poll()"+q.poll());
            if (curr.getInfo() == null) {       //true if we didn't visit this node
                curr.setInfo("v");
                if (curr.getKey() == end)     //when we get to dest node
                    return;

//                HashMap<Integer, EdgeData> inner_Edge_temp = new HashMap<>();//define new inner hashmap from the same kind as the inner hashmap in Edge_hash values
//                Object[]Edge_hashKeysArrOuter=  Graph.Edge_hash.keySet().toArray(); // returns an array of keys (outer hashmap)
//                Object[]Edge_hashKeysArrInner=  Graph.Edge_hash.values().toArray(); // returns an array of keys (inner hashmap)
//                Object[]Edge_hash_KeysArr=  Graph.Edge_hash.keySet().toArray(); // returns an array of keys of Edge_hash
//                for( Object KeyOf_Edge_hash:Edge_hash_KeysArr )//copy all keys and values
//                {
//                    //we will use the constructor: Node(int id, GeoLocation g,double weight ,int tag)
//                    HashMap<Integer, EdgeData> tempHashFirst=Graph.Edge_hash.get(KeyOf_Edge_hash);//getTag(),local_Node_hash.get(KeyOf_Node_hash).getLocation() ,local_Node_hash.get(KeyOf_Node_hash).getWeight(),local_Node_hash.get(KeyOf_Node_hash).getKey());
//                    Object[]tempHashKeysFirst=  tempHashFirst.keySet().toArray(); // returns an array of keys of tempHashFirst
//                    for( Object KeyOf_tempHashFirst:tempHashKeysFirst )//copy all keys and values
//                    {
//                        //we will use the constructor: Edge (NodeData src, double weight, NodeData dest)
//                        Edge newCopy=new Edge(tempHashFirst.get(KeyOf_tempHashFirst).getSrc(),tempHashFirst.get(KeyOf_tempHashFirst).getWeight(),tempHashFirst.get(KeyOf_tempHashFirst).getDest());
//                        tempHashFirst.put((Integer) KeyOf_tempHashFirst,newCopy);//we will put keys&values in tempHashFirst (the insider hashmap)
//                    }
//                    //Edge_hash.put((Integer) KeyOf_Edge_hash,tempHashFirst);//we will put keys&values in Edge_hash (the bigger hashmap)
//                    inner_Edge_temp=tempHashFirst;
//                }
//                Object[]ValuesOfInnerEdgeHash=  inner_Edge_temp.values().toArray(); // returns an array of keys


                for (EdgeData i : EdgesGettingOfFromNode(curr.getKey())) {     //moving on each neighbour of curr
                    NodeData temp = Graph.getNode(i.getDest());
                    if (temp.getInfo() == null) {                //true if we didn't visit this node
                        double w = i.getW();
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
    public Collection<EdgeData> EdgesGettingOfFromNode(int node_id) {
        if (Graph.Edges.containsKey(node_id))
            return Graph.Edges.get(node_id).values();
        return null;
    }

    private static class comperator implements Comparator<NodeDI>{
        public int compare (NodeDI n1, NodeDI n2){

            return Double.compare(n1.getWeight(), n2.getWeight());
        }
    }


}




