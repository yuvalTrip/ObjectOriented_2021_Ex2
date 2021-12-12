public class GeoLocation_class implements api.GeoLocation {

    private double x, y,z;

    public GeoLocation_class(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public GeoLocation_class(){
        this.x= 0;
        this.y= 0;
        this.z= 0;
    }

    public double x(){
        return this.x;
    }
    public double y(){
        return this.y;
    }
    public double z(){return this.z; }
    public int ix() {return (int)x;}
    public int iy() {return (int)y;}
    public int iz() {return (int)z;}

    public double distance(api.GeoLocation g){
        double ans= Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2);
        return Math.sqrt(ans);
    }

    public String toString(){
        return "( " + this.x + "," + this.y+ "," + this.z+ " )";
    }
}
