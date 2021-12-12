//package api;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class graphJsonDecoder implements JsonDeserializer<DirectedWeightedGraph> {
    @Override
    public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        DirectedWeightedGraph temp = new DirectedWeightedGraph_ans();
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        JsonArray Verticals1 = jsonObj.get("Nodes").getAsJsonArray();
       // JsonObject Verticals = jsonObj.get("Edges").getAsJsonObject();
        //JsonArray jsonBodies = jsonObj.get("Nodes").getAsJsonArray();
        for(JsonElement nodeValue :Verticals1.getAsJsonArray()){

//        for (Map.Entry<String, JsonElement> set : Verticals.entrySet()) {
//            String hashKey = set.getKey();
            //JsonElement nodeValue = set.getValue();//we get: set.getValue():{"id":0,"pos":{"x":0.0,"y":0.0,"z":0.0},"weight":0.0,"tag":0}
      //      JsonArray array =nodeValue.getAsJsonArray();
           // JsonObject nodeVal=nodeValue.getAsJsonObject();
//            JsonArray array = toJsonArray(nodeValue);
           // JsonArray array = nodeValue.getAsJsonArray(nodeValue);//JsonArray(nodeValue);

            //((JsonArray) nodeValue).remove(3);//remove tag
           // ((JsonArray) nodeValue).remove(2);//remove weight
      //      array.remove(3);//remove tag
      //      array.remove(3);//remove tag

//            System.out.println("array.size "+array.size());

             //nodeValue.getAsJsonObject().remove("weight");
            //nodeValue.getAsJsonObject().remove("tag");

            System.out.println("set.getValue():"+nodeValue);
            JsonObject temp1=(nodeValue.getAsJsonObject());
//            String position=nodeValue.getAsJsonObject().get("pos").getAsString();
//            String position=temp1.get("pos").getAsString();
            String position=temp1.get("pos").toString();


            int key = nodeValue.getAsJsonObject().get("id").getAsInt();
            String[] cordinate=position.split("[,]");
//            double x= Double.parseDouble(cordinate[0]);
//            double y= Double.parseDouble(cordinate[1]);
//            double z= Double.parseDouble(cordinate[2]);
            ArrayList<Double> LocValues=new ArrayList<>();
            for (int i = 0; i < cordinate.length; i++)
            {
                String[] splittedXYZ=cordinate[i].split("[:]");

                if(i==2)
                {
//                    System.out.println("splittedXYZ[1]"+splittedXYZ[0]);
                    String[] SplitZ=splittedXYZ[0].split("\"");
                    LocValues.add(Double.parseDouble(SplitZ[0]));
                    System.out.println(Double.parseDouble(SplitZ[0]));

                }
                else if(i==0)
                {
                    String[] almostFinalSplit=splittedXYZ[0].split("\"");
                    System.out.println(almostFinalSplit[1]);
                    LocValues.add(Double.parseDouble(almostFinalSplit[1]));//להחליף פה לאיבר הרלוונטי של הדאבל
                }
                else if(i==1)
                {
                    String[] almostFinalSplit=splittedXYZ[0].split("\"");
                    System.out.println(almostFinalSplit[0]);
                    LocValues.add(Double.parseDouble(almostFinalSplit[0]));
                }
            }
            double x= LocValues.get(0);
            double y= LocValues.get(1);
            double z= LocValues.get(2);
            String GeoLocationS= x+","+y+","+z;
            GeoLocation_class GeoLoc = new GeoLocation_class(x,y,z);
            NodeData n = new NodeDI(key, GeoLoc);//Node(key, geo loc- constractor )
            temp.addNode(n);

        }
        JsonArray Edges = jsonObj.get("Edges").getAsJsonArray();
       // JsonObject Edges = jsonObj.get("Edges").getAsJsonObject();
        for(JsonElement edgeValue1 :Edges.getAsJsonArray()){
//        for (Map.Entry<String, JsonElement> set : Edges.entrySet()) {
//            String edgeKey = set.getKey();
//            JsonObject current = Edges.get(set.getKey()).getAsJsonObject();
            //JsonArray current = jsonObj.get("Edges").getAsJsonArray();


     //       JsonObject current = Edges.getAsJsonObject();
// for(JsonElement nodeValue :Verticals1.getAsJsonArray()){
//
////        for (Map.Entry<String, JsonElement> set : Verticals.entrySet()) {
//            for (Map.Entry<String, JsonElement> set2 : current.entrySet()) {
            int src = edgeValue1.getAsJsonObject().get("src").getAsInt();
            double w = edgeValue1.getAsJsonObject().get("w").getAsDouble();
            int dest = edgeValue1.getAsJsonObject().get("dest").getAsInt();
            EdgeData e = new Edge (src, w, dest);
            temp.connect(src, dest, w);
//            for(JsonElement set2 :edgeValue1.getAsJsonArray()){
//            //for (JsonElement set2 : Edges.getAsJsonArray()) {
//                JsonElement edgeValue = set2;//.getValue();
////                edgeValue.getAsJsonObject().remove("info");
////                edgeValue.getAsJsonObject().remove("tag");
//                int src = edgeValue.getAsJsonObject().get("src").getAsInt();
//                double w = edgeValue.getAsJsonObject().get("w").getAsDouble();
//                int dest = edgeValue.getAsJsonObject().get("dest").getAsInt();
//                EdgeData e = new Edge (src, w, dest);
//                temp.connect(src, dest, w);
//            }
        }
        return temp;
    }}

