import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedWeightedGraphAlgorithmsTest
{
    public static DirectedWeightedGraph graphCreator(int v, int e, int seed) {
        DecimalFormat round = new DecimalFormat("##.##");
        round.setRoundingMode(RoundingMode.DOWN);
        Random rand = new Random(seed);
        DirectedWeightedGraph g = new DirectedWeightedGraph_ans();
        int node1, node2;
        double tag;

        while (g.nodeSize() < v) {
        NodeData x = new NodeDI();
            g.addNode(x);
        }

        while (g.edgeSize() < e) {
            node1 = (int) (v * rand.nextDouble());
            node2 = (int) (v * rand.nextDouble());
            tag = (rand.nextDouble() * v);

            g.connect(node1, node2, (
                    Double.valueOf(round.format(tag))));
        }

        return g;
    }


    @Test
    void init()
    {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator(3, 2,);
        check.init(root);

        assertEquals(root,check.getGraph());
    }

    @Test
    void getGraph()
    {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator(2, 1, 1);
        check.init(root);

        assertEquals(root, check.getGraph());
    }

    @Test
    void copy()
    {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator(100, 100, 1);
        check.init(root);

        DirectedWeightedGraph root2 = check.copy();

        assertEquals(root, root2);
    }

    @Test
    void isConnected()////////////////////////////////////////////////////////////////////////////////////////////////
    {
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        DirectedWeightedGraph root = graphCreator(5, 5, 1);
        root.connect(4, 1, 2);
        root.connect(2, 1, 2);
        root.connect(3, 1, 2);
        root.connect(1, 2, 2);
        root.connect(1, 3, 2);
        root.connect(1, 4, 2);
        root.connect(1, 0, 2);
        root.connect(0, 1, 2);

        check.init(root);

        boolean f = check.isConnected();
        assertTrue(f);

        root.removeEdge(4, 1);
        root.connect(0, 4, 1);
        assertFalse(check.isConnected());

        root.connect(4, 1, 1);
        root.removeEdge(1, 4);
        assertTrue(check.isConnected());

    }

    @Test
    void shortestPathDist()
    {
        DirectedWeightedGraph_ans root= (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        check.init(root);
        double d = check.shortestPathDist(3,2 );
        assertEquals(d, 1.0);
    }

    @Test
    void shortestPath()
    {
        DirectedWeightedGraph_ans root= (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        root.connect(10, 4, 2);
        root.connect(5, 10, 2);
        root.connect(2, 9, 2);
        root.connect(7, 0, 2);
        check.init(root);
        List<NodeData> sp = check.shortestPath(1, 2);
        int[] checkKey = {1,3,2};
        int i = 0;
        for (NodeData n : sp) {
            assertEquals(n.getKey(), checkKey[i]);
            i++;
        }
    }

    @Test
    void center()
    {
//        DirectedWeightedGraph_ans root= (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
//        check.init(root);
//        String str = "WDGraph.json";
//        check.save(str);
        check.load("G1.json");
        int CenterOfG1=check.center().getKey();
        assertEquals(CenterOfG1,8);
    }

    @Test
    void tsp()
    {///////////////////////////////////////////////////////////////////////////////////////////////////////////
        GeoLocation_class p0 = new GeoLocation_class(0,0,0);
        GeoLocation_class p1 = new GeoLocation_class(1,2,0);
        GeoLocation_class p2 = new GeoLocation_class(1,5,0);
        GeoLocation_class p3 = new GeoLocation_class(4,4,0);
        GeoLocation_class p4 = new GeoLocation_class(4,3,0);
        GeoLocation_class p5 = new GeoLocation_class(4,0,0);
        GeoLocation_class p6 = new GeoLocation_class(9,2,0);
        DirectedWeightedGraph g= new DirectedWeightedGraph_ans();
        NodeDI n0 = new NodeDI(0,p0);
        NodeDI n1 = new NodeDI(1,p1);
        NodeDI n2 = new NodeDI(2,p2);
        NodeDI n3 = new NodeDI(3,p3);
        NodeDI n4 = new NodeDI(4,p4);
        NodeDI n5 = new NodeDI(5,p5);
        NodeDI n6 = new NodeDI(6,p6);
        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);
        g.connect(n0.getKey(),n1.getKey(),1);
        g.connect(n1.getKey(),n2.getKey(),1);
        g.connect(n2.getKey(),n1.getKey(),2);
        g.connect(n2.getKey(),n3.getKey(),2);
        g.connect(n3.getKey(),n4.getKey(),1);
        g.connect(n4.getKey(),n3.getKey(),1);
        g.connect(n2.getKey(),n4.getKey(),4);
        g.connect(n4.getKey(),n2.getKey(),2);
        g.connect(n4.getKey(),n6.getKey(),5);
        g.connect(n0.getKey(),n6.getKey(),15);
        g.connect(n5.getKey(),n6.getKey(),12);
        DirectedWeightedGraphAlgorithms_ans testGraphAlgo = new DirectedWeightedGraphAlgorithms_ans();
        testGraphAlgo.init(g);
        //testGraphAlgo.save("TestTSP1.json");
        List<NodeData> subGraph1 = new ArrayList<NodeData>();
        subGraph1.add(n3);
        subGraph1.add(n4);
        subGraph1.add(n2);
        ArrayList<NodeData> subGraphTSP1 = (ArrayList<NodeData>) testGraphAlgo.tsp(subGraph1);
        double pathW = 0.0;
        for(int i=0; i<=subGraphTSP1.size()-2; i++){
            pathW += testGraphAlgo.shortestPathDist(subGraphTSP1.get(i).getKey(),subGraphTSP1.get(i+1).getKey());
        }
        String t1 = subGraphTSP1.toString();
        assertTrue(pathW <= 3*1.5);
        assertFalse(pathW <= 2); // because 3 is the Best case, it will never be smaller then that.

    }

    @Test
    void save() {
//        DirectedWeightedGraph g0 = graphCreator(500, 10000, 1);
//        DirectedWeightedGraphAlgorithms ga= new DirectedWeightedGraphAlgorithms_ans();
//        ga.init(g0);
//        String str = "WDGraph.json";
//        ga.save(str);
//        ga.load("WDGraph.json");
//        assertEquals(g0,ga.getGraph());

        DirectedWeightedGraph_ans root= (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        check.init(root);
        String str = "WDGraph.json";
        check.save(str);
        check.load("G1.json");

//        assertEquals(root,check.getGraph());

    }

    @Test
    void load()
    {
//        DirectedWeightedGraph g0 = graphCreator(500, 10000, 1);
//        DirectedWeightedGraphAlgorithms ag0 = new DirectedWeightedGraphAlgorithms_ans();
//        ag0.init(g0);
//        String str = "Graph_json.json";
//        ag0.save(str);
//        DirectedWeightedGraph g1 = graphCreator(500, 10000, 1);
//        assertTrue(ag0.load(str));
//        assertNotSame(g0,g1);
//        assertEquals(g0, g1);
//        g0.removeNode(0);
//        assertNotEquals(g0, g1);
        DirectedWeightedGraph_ans root= (DirectedWeightedGraph_ans) graphCreator();
        DirectedWeightedGraphAlgorithms check = new DirectedWeightedGraphAlgorithms_ans();
        check.init(root);
        String str = "WDGraph.json";
        check.save(str);
        check.load("G1.json");
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