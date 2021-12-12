import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DirectedWeightedGraphAlgorithmsTest {
//    public static DirectedWeightedGraph graphCreator(int v, int e, int seed) {
//        DecimalFormat round = new DecimalFormat("##.##");
//        round.setRoundingMode(RoundingMode.DOWN);
//        Random rand = new Random((long)seed);
//        DirectedWeightedGraph_ans g = new DirectedWeightedGraph_ans();
//
//        while(g.nodeSize() < v) {
//            NodeDI x = new NodeDI();
//            g.addNode(x);
//        }
//
//        while(g.edgeSize() < e) {
//            int node1 = (int)((double)v * rand.nextDouble());
//            int node2 = (int)((double)v * rand.nextDouble());
//            double tag = rand.nextDouble() * (double)v;
//            g.connect(node1, node2, Double.valueOf(round.format(tag)));
//        }
//
//        return g;
//    }


    @Test
    void init() {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator();
        check.init(root);

        Assertions.assertEquals(root, check.getGraph());
    }

    @Test
    void getGraph() {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator();
        check.init(root);
        Assertions.assertEquals(root, check.getGraph());
//        DirectedWeightedGraph g= graphCreator(3, 2, 1);
//        DirectedWeightedGraph g1= graphCreator(10, 90, 1);
//
//        check.init(g);
//        Assertions.assertEquals(g, check.getGraph());
//
//        //Assertions.assertTrue(check.getGraph().equals(g1));
    }

    @Test
    void copy() {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator();
        check.init(root);
        DirectedWeightedGraph root2 = check.copy();
        Assertions.assertEquals(root.nodeSize(), root2.nodeSize());
    }

    @Test
    void isConnected() {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator();
        root.connect(10, 4, 2);
        root.connect(5, 6, 2);
        root.connect(5, 10, 2);
        root.connect(2, 9, 2);
        root.connect(7, 0, 2);
        check.init(root);
        boolean f = check.isConnected();
        Assertions.assertTrue(f);

//        DirectedWeightedGraph g= graphCreator(10, 90, 1);
//        check.init(g);
//        boolean f = check.isConnected();
//        Assertions.assertTrue(f);

    }


    @Test
    void shortestPathDist() {
        DirectedWeightedGraph_ans root = (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        check.init(root);
        double d = check.shortestPathDist(3, 2);
        Assertions.assertEquals(d, 1.0);
    }

    @Test
    void shortestPath() {
        DirectedWeightedGraph_ans root = (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        root.connect(10, 4, 2);
        root.connect(5, 10, 2);
        root.connect(2, 9, 2);
        root.connect(7, 0, 2);
        check.init(root);
        List<NodeData> sp = check.shortestPath(1, 2);
        int[] checkKey = {1, 3, 2};
        int i = 0;
        for (NodeData n : sp) {
            Assertions.assertEquals(n.getKey(), checkKey[i]);
            i++;
        }
    }

    @Test
    void center() {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        //check.load("G3.json");
        check.load("1000Nodes.json");
        int CenterOfG1 = check.center().getKey();
        System.out.println(CenterOfG1);
        Assertions.assertEquals(0, CenterOfG1);
    }

    @Test
    void tsp() {
        GeoLocation_class p0 = new GeoLocation_class(0, 0, 0);
        GeoLocation_class p1 = new GeoLocation_class(1, 2, 0);
        GeoLocation_class p2 = new GeoLocation_class(1, 5, 0);
        GeoLocation_class p3 = new GeoLocation_class(4, 4, 0);
        GeoLocation_class p4 = new GeoLocation_class(4, 3, 0);
        GeoLocation_class p5 = new GeoLocation_class(4, 0, 0);
        GeoLocation_class p6 = new GeoLocation_class(9, 2, 0);
        DirectedWeightedGraph g = new DirectedWeightedGraph_ans();
        NodeDI n0 = new NodeDI(0, p0);
        NodeDI n1 = new NodeDI(1, p1);
        NodeDI n2 = new NodeDI(2, p2);
        NodeDI n3 = new NodeDI(3, p3);
        NodeDI n4 = new NodeDI(4, p4);
        NodeDI n5 = new NodeDI(5, p5);
        NodeDI n6 = new NodeDI(6, p6);
        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);
        g.connect(n0.getKey(), n1.getKey(), 1);
        g.connect(n1.getKey(), n2.getKey(), 1);
        g.connect(n2.getKey(), n1.getKey(), 2);
        g.connect(n2.getKey(), n3.getKey(), 2);
        g.connect(n3.getKey(), n4.getKey(), 1);
        g.connect(n4.getKey(), n3.getKey(), 1);
        g.connect(n2.getKey(), n4.getKey(), 4);
        g.connect(n4.getKey(), n2.getKey(), 2);
        g.connect(n4.getKey(), n6.getKey(), 5);
        g.connect(n0.getKey(), n6.getKey(), 15);
        g.connect(n5.getKey(), n6.getKey(), 12);
        DirectedWeightedGraphAlgorithms_ans testGraphAlgo = new DirectedWeightedGraphAlgorithms_ans();
        testGraphAlgo.init(g);
        //testGraphAlgo.save("TestTSP1.json");
        List<NodeData> subGraph1 = new ArrayList<NodeData>();
        subGraph1.add(n3);
        subGraph1.add(n4);
        subGraph1.add(n2);
        ArrayList<NodeData> subGraphTSP1 = (ArrayList<NodeData>) testGraphAlgo.tsp(subGraph1);
        double pathW = 0.0;
        for (int i = 0; i <= subGraphTSP1.size() - 2; i++) {
            pathW += testGraphAlgo.shortestPathDist(subGraphTSP1.get(i).getKey(), subGraphTSP1.get(i + 1).getKey());
        }
        String t1 = subGraphTSP1.toString();
        Assertions.assertTrue(pathW <= 3 * 1.5);
        Assertions.assertFalse(pathW <= 2); // because 3 is the Best case, it will never be smaller then that.

    }

    @Test
    void save() {

        DirectedWeightedGraph_ans root = (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        check.init(root);
        String str = "DirectedGraph.json";
        check.save(str);
        check.load("G1.json");


    }

    @Test
    void load() {
        DirectedWeightedGraph_ans root = (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        check.init(root);
        String str = "DirectedGraph.json";
        check.save(str);
        check.load("G1.json");
        //check.load("1000Nodes.json");

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