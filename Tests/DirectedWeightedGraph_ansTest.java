import api.DirectedWeightedGraph;
import api.GeoLocation;
import api.NodeData;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedWeightedGraph_ansTest {
    DirectedWeightedGraph small_graph;
    @Test
    void getNode()
    {
        DirectedWeightedGraph g= graphCreator();
        NodeData n= g.getNode(0);
        assertEquals(0,n.getKey());
    }

    @Test
    void getEdge()
    {
        DirectedWeightedGraph ga= graphCreator();
        assertEquals(9.0,ga.getEdge(9,4).getW());
    }

    @Test
    void addNode()
    {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
        NodeData n= new NodeDI(12);
        g.addNode(n);
        assertEquals(12,g.getNode(12).getKey());

        assertNull(g.getNode(13));
    }

    @Test
    void connect() {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
        g.connect(2,3,4.5);
        assertEquals(4.5,g.getEdge(2,3).getW());


        g.connect(2,3,5);
        assertEquals(5,g.getEdge(2,3).getW());

    }

    @Test
    void nodeIter() {
//        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
//        GeoLocation loc1 = new GeoLocation_class(1,2,0);
//        GeoLocation loc2 = new GeoLocation_class(2,1,0);
//        GeoLocation loc3 = new GeoLocation_class(5,6,10);
//        DirectedWeightedGraph g= new DirectedWeightedGraph_ans();
//        NodeDI n1 = new NodeDI(0,loc1);
//        NodeDI n2 = new NodeDI(1,loc2);
//        NodeDI n3 = new NodeDI(2,loc3);
//       // NodeDI e1 = new NodeDI(0,2,1);
//        g.addNode(n1);
//        g.addNode(n2);
//        g.connect(n1.getKey(),n2.getKey(),2);
//        Iterator<NodeData> it = g.nodeIter();
//        it.next();
//        //it.remove();
//        it.next();
//        g.addNode(n3);
//        Iterator<NodeData> it2 = g.nodeIter();
//        while (it2.hasNext()) {
//            System.out.println(it2.next());
//        }

    }

    @Test
    void edgeIter() {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();

    }

    @Test
    void testEdgeIter() {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();

    }

    @Test
    void removeNode() {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
        g.removeNode(9);
        assertEquals(10,g.nodeSize());
        assertEquals(14,g.edgeSize());
    }

    @Test
    void removeEdge() {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();

        g.removeEdge(3,2);
        assertEquals(16,g.edgeSize());
        g.removeEdge(3,2);
        assertEquals(16,g.edgeSize());
        assertNull(g.removeEdge(3,2));
    }

    @Test
    void nodeSize() {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
        g.removeNode(9);
        assertEquals(10,g.nodeSize());
        g.removeNode(9);
        assertEquals(10,g.nodeSize());
        g.addNode(new NodeDI(12));
        assertEquals(11,g.nodeSize());
    }

    @Test
    void edgeSize() {

        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
        assertEquals(17,g.edgeSize());
        g.removeEdge(3,2);
        assertEquals(16,g.edgeSize());
        g.removeEdge(3,2);
        assertEquals(16,g.edgeSize());
        g.connect(2,3,0.5);
        assertEquals(17,g.edgeSize());
        g.connect(2,3,0.5);
        assertEquals(17,g.edgeSize());
    }

    @Test
    void getMC()
    {
        DirectedWeightedGraph_ans g= (DirectedWeightedGraph_ans) graphCreator();
        g.removeNode(9);
        g.removeEdge(3,2);
        assertEquals(g.getMC(),30);
    }
    public DirectedWeightedGraph graphCreator(){
        GeoLocation_class GeoLoc = new GeoLocation_class(1,2,3);

        NodeData n0= new NodeDI(0);
        NodeData n1= new NodeDI(1);
        NodeData n2= new NodeDI(2);
        NodeData n3= new NodeDI(3);
        NodeData n4= new NodeDI(4);
        NodeData n5= new NodeDI(5);
        NodeData n6= new NodeDI(6);
        NodeData n7= new NodeDI(7);
        NodeData n8= new NodeDI(8);
        NodeData n9= new NodeDI(9);
        NodeData n10= new NodeDI(10);

        DirectedWeightedGraph g1= new DirectedWeightedGraph_ans();
        g1.addNode(n0);
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addNode(n3);
        g1.addNode(n4);
        g1.addNode(n5);
        g1.addNode(n6);
        g1.addNode(n7);
        g1.addNode(n8);
        g1.addNode(n9);
        g1.addNode(n10);

        g1.connect(n1.getKey(),n2.getKey(),3.0);
        g1.connect(n1.getKey(),n3.getKey(),1);
        g1.connect(n3.getKey(),n2.getKey(),1);
        g1.connect(n0.getKey(),n2.getKey(),4.5);
        g1.connect(n0.getKey(),n4.getKey(),1);
        g1.connect(n4.getKey(),n5.getKey(),0.5);
        g1.connect(n5.getKey(),n6.getKey(),8);
        g1.connect(n7.getKey(),n1.getKey(),0.5);
        g1.connect(n8.getKey(),n0.getKey(),3.5);
        g1.connect(n9.getKey(),n5.getKey(),11);
        g1.connect(n6.getKey(),n9.getKey(),5);
        g1.connect(n9.getKey(),n4.getKey(),9);
        g1.connect(n4.getKey(),n8.getKey(),10);
        g1.connect(n3.getKey(),n7.getKey(),6);
        g1.connect(n2.getKey(),n1.getKey(),4);
        g1.connect(n3.getKey(),n6.getKey(),30);
        g1.connect(n10.getKey(),n6.getKey(),2);

        return g1;
}
}