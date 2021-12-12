import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class graphJsonDecoder implements JsonDeserializer<DirectedWeightedGraph> {
    @Override
    public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        DirectedWeightedGraph temp = new DirectedWeightedGraph_ans();
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        JsonArray Verticals1 = jsonObj.get("Nodes").getAsJsonArray();

        for (JsonElement nodeValue : Verticals1.getAsJsonArray()) {
            JsonObject temp1 = (nodeValue.getAsJsonObject());
            String position = temp1.get("pos").toString();
            int key = nodeValue.getAsJsonObject().get("id").getAsInt();
            String[] cordinate = position.split("[,]");
            ArrayList<Double> LocValues = new ArrayList<>();
            for (int i = 0; i < cordinate.length; i++) {
                String[] splittedXYZ = cordinate[i].split("[:]");
                if (i == 2) {
                    String[] SplitZ = splittedXYZ[0].split("\"");
                    LocValues.add(Double.parseDouble(SplitZ[0]));
                } else if (i == 0) {
                    String[] almostFinalSplit = splittedXYZ[0].split("\"");
                    LocValues.add(Double.parseDouble(almostFinalSplit[1]));
                } else if (i == 1) {
                    String[] almostFinalSplit = splittedXYZ[0].split("\"");
                    LocValues.add(Double.parseDouble(almostFinalSplit[0]));
                }
            }
            double x = LocValues.get(0);
            double y = LocValues.get(1);
            double z = LocValues.get(2);
            String GeoLocationS = x + "," + y + "," + z;
            GeoLocation_class GeoLoc = new GeoLocation_class(x, y, z);
            NodeData n = new NodeDI(key, GeoLoc);//Node(key, geo loc- constractor )
            temp.addNode(n);

        }
        JsonArray Edges = jsonObj.get("Edges").getAsJsonArray();
        for (JsonElement edgeValue1 : Edges.getAsJsonArray()) {
            int src = edgeValue1.getAsJsonObject().get("src").getAsInt();
            double w = edgeValue1.getAsJsonObject().get("w").getAsDouble();
            int dest = edgeValue1.getAsJsonObject().get("dest").getAsInt();
            EdgeData e = new Edge(src, w, dest);
            temp.connect(src, dest, w);
        }
        return temp;
    }
}

