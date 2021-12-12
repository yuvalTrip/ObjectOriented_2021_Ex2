import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

public class DirectedWeightedGraph_ans implements DirectedWeightedGraph, Serializable {
    public HashMap<Integer, NodeData> Nodes = new HashMap<>();
    public HashMap<Integer, HashMap<Integer, EdgeData>> Edges = new HashMap<>();
    private int nodeSize;
    private int edgeSize;
    private int Mc; // Mode counter- how many changes the object already have

    public DirectedWeightedGraph_ans() {
        this.Edges = new HashMap<>();
        this.Nodes = new HashMap<>();

    }

    public DirectedWeightedGraph_ans(HashMap<Integer, HashMap<Integer, EdgeData>> local_Edge_hash, HashMap<Integer, NodeData> local_Node_hash, int local_nodeSize, int local_edgeSize, int local_Mc) {
        this.Edges = local_Edge_hash;
        //this.Node_hash=local_Node_hash;
        this.nodeSize = local_nodeSize;
        this.edgeSize = local_edgeSize;
        this.Mc = local_Mc;
        Object[] Node_hash_KeysArr = local_Node_hash.keySet().toArray(); // returns an array of keys of Node_hash
        for (Object KeyOf_Node_hash : Node_hash_KeysArr)//copy all keys and values
        {
            //we will use the constructor: Node(int id, GeoLocation g,double weight ,int tag)
            NodeDI newCopy = new NodeDI(local_Node_hash.get(KeyOf_Node_hash).getTag(), local_Node_hash.get(KeyOf_Node_hash).getLocation(), local_Node_hash.get(KeyOf_Node_hash).getWeight(), local_Node_hash.get(KeyOf_Node_hash).getKey());
            Nodes.put((Integer) KeyOf_Node_hash, newCopy);
        }
        Object[] Edge_hash_KeysArr = local_Edge_hash.keySet().toArray(); // returns an array of keys of Edge_hash
        for (Object KeyOf_Edge_hash : Edge_hash_KeysArr)//copy all keys and values
        {
            //we will use the constructor: Node(int id, GeoLocation g,double weight ,int tag)
            HashMap<Integer, EdgeData> tempHashFirst = local_Edge_hash.get(KeyOf_Edge_hash);//getTag(),local_Node_hash.get(KeyOf_Node_hash).getLocation() ,local_Node_hash.get(KeyOf_Node_hash).getWeight(),local_Node_hash.get(KeyOf_Node_hash).getKey());
            Object[] tempHashKeysFirst = tempHashFirst.keySet().toArray(); // returns an array of keys of tempHashFirst
            for (Object KeyOf_tempHashFirst : tempHashKeysFirst)//copy all keys and values
            {
                //we will use the constructor: Edge (NodeData src, double weight, NodeData dest)
                Edge newCopy = new Edge(tempHashFirst.get(KeyOf_tempHashFirst).getSrc(), tempHashFirst.get(KeyOf_tempHashFirst).getWeight(), tempHashFirst.get(KeyOf_tempHashFirst).getDest());
                tempHashFirst.put((Integer) KeyOf_tempHashFirst, newCopy);//we will put keys&values in tempHashFirst (the insider hashmap)
            }
            Edges.put((Integer) KeyOf_Edge_hash, tempHashFirst);//we will put keys&values in Edge_hash (the bigger hashmap)
        }

    }

    public DirectedWeightedGraph_ans(DirectedWeightedGraph_ans g) {
        if (g == null) {
            g = new DirectedWeightedGraph_ans();
        } else {
            nodeSize = g.nodeSize;
            edgeSize = g.edgeSize;
            Nodes = new HashMap<>(g.getVertices());
            Edges = new HashMap<>(g.getEdges());
            Mc = 0;

        }

    }

    @Override
    public NodeData getNode(int key) {
        return Nodes.getOrDefault(key, null);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (Edges.containsKey(src) && Edges.get(src).containsKey(dest)) {
            return Edges.get(src).get(dest);
        } else return null;
    }

    //put- בודק אם קיים ערך ואם כן מחליף בערך החדש
    //putIfAbsent- בודק אם המפתח לא משוייך לערך , הוא משייך אותו לערך הניתן, אחרת מחזיר ריק
    @Override
    public void addNode(NodeData n) {
        int id_temp = n.getKey();
        api.GeoLocation g_temp = n.getLocation();
        Nodes.putIfAbsent(n.getKey(), new NodeDI(id_temp, g_temp));
        Edges.put(n.getKey(), new HashMap<>());
        Mc++;
        nodeSize++;

    }

    @Override
    public void connect(int src, int dest, double w) {
//        if (Edge_hash.get(src).containsKey(dest)&& Edge_hash.get(src).get(dest).getWeight()!=w) {
//            Edge_hash.get(src).put(dest, new_edge);
//            Mc++;
//        }
//        return; //if edge already exsits- return
//    }

        EdgeData new_edge = new Edge(src, w, dest);//create new edge with all given data
        if (Edges.get(src) != null) {
            if (Edges.get(src).containsKey(dest)) {
                if (Edges.get(src).get(dest).getWeight() != w) {
                    Edges.get(src).put(dest, new_edge);
                    Mc++;
                }
                return; //if edge already exsits- return
            }
            HashMap<Integer, EdgeData> temp = Edges.get(src);
            temp.put(dest, new_edge);
            Edges.replace(src, temp);
            edgeSize++;
            Mc++;
        }
//        HashMap <Integer, EdgeData> temp= Edge_hash.get(src);
//        temp.put(dest, new_edge);
//        Edge_hash.replace(src,temp);
//        edgeSize++;
//        Mc++ ;

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.Nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
//HashMap<Integer, HashMap<Integer, EdgeData>> Edge_hash = new HashMap<>();
//    HashMap<Integer, NodeData> Node_hash = new HashMap<>();
        HashMap<Integer, EdgeData> inner_Edge_temp = new HashMap<>();//define new inner hashmap from the same kind as the inner hashmap in Edge_hash values
        Object[] Edge_hashKeysArrOuter = Edges.keySet().toArray(); // returns an array of keys (outer hashmap)
        Object[] Edge_hashKeysArrInner = Edges.values().toArray(); // returns an array of keys (inner hashmap)
        Object[] Edge_hash_KeysArr = Edges.keySet().toArray(); // returns an array of keys of Edge_hash
        for (Object KeyOf_Edge_hash : Edge_hash_KeysArr)//copy all keys and values
        {
            //we will use the constructor: Node(int id, GeoLocation g,double weight ,int tag)
            HashMap<Integer, EdgeData> tempHashFirst = Edges.get(KeyOf_Edge_hash);//getTag(),local_Node_hash.get(KeyOf_Node_hash).getLocation() ,local_Node_hash.get(KeyOf_Node_hash).getWeight(),local_Node_hash.get(KeyOf_Node_hash).getKey());
            Object[] tempHashKeysFirst = tempHashFirst.keySet().toArray(); // returns an array of keys of tempHashFirst
            for (Object KeyOf_tempHashFirst : tempHashKeysFirst)//copy all keys and values
            {
                //we will use the constructor: Edge (NodeData src, double weight, NodeData dest)
                Edge newCopy = new Edge(tempHashFirst.get(KeyOf_tempHashFirst).getSrc(), tempHashFirst.get(KeyOf_tempHashFirst).getWeight(), tempHashFirst.get(KeyOf_tempHashFirst).getDest());
                tempHashFirst.put((Integer) KeyOf_tempHashFirst, newCopy);//we will put keys&values in tempHashFirst (the insider hashmap)
            }
            //Edge_hash.put((Integer) KeyOf_Edge_hash,tempHashFirst);//we will put keys&values in Edge_hash (the bigger hashmap)
            inner_Edge_temp = tempHashFirst;
        }
        return inner_Edge_temp.values().iterator();
//        for (int i = 0; i < Edge_hashKeysArrOuter.length; i++)
//        {
//                inner_Edge_temp.put(i, (EdgeData) Edge_hash.get(i).values());//(Edge_hash.get(i))Edge_hash.get(i).get(j)
//        }
//        HashMap<Integer, EdgeData> Edge_temp = new HashMap<>();
//        HashMap<Integer, EdgeData> ans = new HashMap<>();
//        Iterator temp= this.Edge_hash.values().iterator();
//        int i =0;
//        while (temp.hasNext()){
//            Edge_temp= (HashMap<Integer, EdgeData>) temp.next();
//            Iterator temp1= Edge_temp.values().iterator();
//            while (temp1.hasNext()){
//                ans.put(i, (EdgeData) temp1.next());
//                i++;
//            }
//        }
//        return ans.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.Edges.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if (Nodes.containsKey(key)) {
            NodeData temp = Nodes.get(key);
            Nodes.remove(key);

            for (HashMap<Integer, EdgeData> current : Edges.values()) {
                if (current.containsKey(key)) {
                    current.remove(key);
                    edgeSize--;
                }
            }
            int neighbors = Edges.get(key).size();
            Edges.remove(key); //delete all the edges with src==key
            nodeSize--;
            edgeSize = edgeSize - neighbors;
            Mc++;
            return temp;
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData temp = null;
        if (Edges.containsKey(src) && Edges.get(src).containsKey(dest)) {
            temp = Edges.get(src).get(dest);
            Edges.get(src).remove(dest);
            edgeSize--;
            Mc++;
        }
        return temp;
    }

    @Override
    public int nodeSize() {
        return this.nodeSize;
    }

    @Override
    public int edgeSize() {
        return this.edgeSize;
    }

    @Override
//Returns the Mode Count - for testing changes in the graph.
    public int getMC() {
        return this.Mc;
    }

    public HashMap<Integer, NodeData> getVertices() {
        return Nodes;
    }

    //function returns a pointer for the collection represent all the nodes in the graph
    public Collection<NodeData> AllNodesInTheGraph() {
        return Nodes.values();
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getEdges() {
        return Edges;
    }
}
