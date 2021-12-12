import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
    // * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */

    public static DirectedWeightedGraph getGrapg(String json_file) {
        // ****** Add your code here ******
        DirectedWeightedGraph ans = new DirectedWeightedGraph_ans();
        DirectedWeightedGraphAlgorithms_ans graphAlgo = new DirectedWeightedGraphAlgorithms_ans();
        graphAlgo.init(ans);//Init the graph on which this set of algorithms operates on.
        graphAlgo.load(json_file);//load this json_file
        return graphAlgo.getGraph();//Return the underlying graph of which this class works.

    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        // ****** Add your code here ******
        DirectedWeightedGraph ans = new DirectedWeightedGraph_ans();
        DirectedWeightedGraphAlgorithms_ans graphAlgo = new DirectedWeightedGraphAlgorithms_ans();
        graphAlgo.init(ans);
        graphAlgo.load(json_file);
        return graphAlgo;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        GUi.runGUI((DirectedWeightedGraph_ans)alg.getGraph());
    }

    public static void main(String[] args)
    {
        runGUI(args[0]);
        //runGUI("data/G1.json");
    }
}