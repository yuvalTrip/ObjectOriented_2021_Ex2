import api.GeoLocation;
import api.NodeData;

public class NodeDI implements NodeData {

    private GeoLocation_class pos;
    private int id;
    private double weight;
    private int tag;
    private String info;

    public NodeDI(int id, GeoLocation pos){
        this.id= id;
        setLocation(pos);
    }
    public NodeDI(int id, GeoLocation pos, double weight, int tag){
        this.id= id;
        setLocation(pos);
        this.weight=weight;
        this.tag= tag;
    }
    public NodeDI(){
        this.id= id;
        pos = new GeoLocation_class(0,0,0);
        //setLocation(g);
    }
    public NodeDI(int id){
        this.id= id;
        pos = new GeoLocation_class();


    }

    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(GeoLocation p) {
        double x= p.x();
        double y= p.y();
        double z= p.z();
        this.pos = new GeoLocation_class(x,y,z);
        }


    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight=w;

    }

    @Override
    public String getInfo() {

        return this.info;
                //"id: " +this.id +" pos: " +g.toString() +" weight: " +this.weight;
    }


    @Override
    public void setInfo(String s) {
//        String[] temp= s.split(" ");
//        this.id= Integer.parseInt(temp[1]);
//        String[] temp_g= temp[4].split(",");
//        this.g= new GeoLocation_class(Double.parseDouble(temp_g[0]),Double.parseDouble(temp_g[1]),Double.parseDouble(temp_g[2]));
//        this.weight= Double.parseDouble(temp[7]);
    this.info= s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;

    }

}
