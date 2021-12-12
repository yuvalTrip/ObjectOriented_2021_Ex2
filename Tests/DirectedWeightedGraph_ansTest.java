import api.DirectedWeightedGraph;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectedWeightedGraph_ansTest {
    DirectedWeightedGraph small_graph;

    @Test
    void getNode() {
        DirectedWeightedGraph g = graphCreator();
        NodeData n = g.getNode(0);
        Assertions.assertEquals(0, n.getKey());

    }

    @Test
    void getEdge() {
        DirectedWeightedGraph ga = graphCreator();
        Assertions.assertEquals(9.0, ga.getEdge(9, 4).getWeight());
    }

    @Test
    void addNode() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();
        NodeData n = new NodeDI(12);
        g.addNode(n);
        Assertions.assertEquals(12, g.getNode(12).getKey());

        Assertions.assertNull(g.getNode(13));
    }

    @Test
    void connect() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();
        g.connect(2, 3, 4.5);
        Assertions.assertEquals(4.5, g.getEdge(2, 3).getWeight());


        g.connect(2, 3, 5);
        Assertions.assertEquals(5, g.getEdge(2, 3).getWeight());

    }

    @Test
    void nodeIter() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();

    }

    @Test
    void edgeIter() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();

    }

    @Test
    void testEdgeIter() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();

    }

    @Test
    void removeNode() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();
        g.removeNode(9);
        Assertions.assertEquals(10, g.nodeSize());
        Assertions.assertEquals(14, g.edgeSize());
    }

    @Test
    void removeEdge() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();

        g.removeEdge(3, 2);
        Assertions.assertEquals(16, g.edgeSize());
        g.removeEdge(3, 2);
        Assertions.assertEquals(16, g.edgeSize());
        Assertions.assertNull(g.removeEdge(3, 2));
    }

    @Test
    void nodeSize() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();
        g.removeNode(9);
        Assertions.assertEquals(10, g.nodeSize());
        g.addNode(new NodeDI(12));
        Assertions.assertEquals(11, g.nodeSize());
    }

    @Test
    void edgeSize() {

        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();
        Assertions.assertEquals(17, g.edgeSize());
        g.removeEdge(3, 2);
        Assertions.assertEquals(16, g.edgeSize());
        g.removeEdge(3, 2);
        Assertions.assertEquals(16, g.edgeSize());
        g.connect(2, 3, 0.5);
        Assertions.assertEquals(17, g.edgeSize());
        g.connect(2, 3, 0.5);
        Assertions.assertEquals(17, g.edgeSize());
    }

    @Test
    void getMC() {
        DirectedWeightedGraph_ans g = (DirectedWeightedGraph_ans) graphCreator();
        g.removeNode(9);
        g.removeEdge(3, 2);
        Assertions.assertEquals(g.getMC(), 30);
    }


    public DirectedWeightedGraph graphCreator() {
        GeoLocation_class GeoLoc = new GeoLocation_class(1, 2, 3);

        NodeData n0 = new NodeDI(0);
        NodeData n1 = new NodeDI(1);
        NodeData n2 = new NodeDI(2);
        NodeData n3 = new NodeDI(3);
        NodeData n4 = new NodeDI(4);
        NodeData n5 = new NodeDI(5);
        NodeData n6 = new NodeDI(6);
        NodeData n7 = new NodeDI(7);
        NodeData n8 = new NodeDI(8);
        NodeData n9 = new NodeDI(9);
        NodeData n10 = new NodeDI(10);

        DirectedWeightedGraph g1 = new DirectedWeightedGraph_ans();
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

        g1.connect(n1.getKey(), n2.getKey(), 3.0);
        g1.connect(n1.getKey(), n3.getKey(), 1);
        g1.connect(n3.getKey(), n2.getKey(), 1);
        g1.connect(n0.getKey(), n2.getKey(), 4.5);
        g1.connect(n0.getKey(), n4.getKey(), 1);
        g1.connect(n4.getKey(), n5.getKey(), 0.5);
        g1.connect(n5.getKey(), n6.getKey(), 8);
        g1.connect(n7.getKey(), n1.getKey(), 0.5);
        g1.connect(n8.getKey(), n0.getKey(), 3.5);
        g1.connect(n9.getKey(), n5.getKey(), 11);
        g1.connect(n6.getKey(), n9.getKey(), 5);
        g1.connect(n9.getKey(), n4.getKey(), 9);
        g1.connect(n4.getKey(), n8.getKey(), 10);
        g1.connect(n3.getKey(), n7.getKey(), 6);
        g1.connect(n2.getKey(), n1.getKey(), 4);
        g1.connect(n3.getKey(), n6.getKey(), 30);
        g1.connect(n10.getKey(), n6.getKey(), 2);

        return g1;
    }
}