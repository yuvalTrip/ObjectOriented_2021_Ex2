public class Edge implements api.EdgeData {
    private int src;
    private double w;
    private int dest;
    private int tag;
    private String info;


    public Edge (int src, double weight, int dest){
        this.src= src;
        this.w = weight;
        this.dest= dest;
        this.info= "";
        this.tag=0;

    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }
    //"src-" + this.src.getInfo() +"\n"+ "weight-" + this.weight + "\n" + "dest-" +this.dest.getInfo();


    @Override
    public void setInfo(String s) {
//        String[] temp1=s.split("\n");
//        String[] arr_src= temp1[0].split("-");
//        this.src.setInfo(arr_src[1]);
//        String[] arr_weight= temp1[1].split("-");
//        this.weight = Double.parseDouble(arr_weight[1]);
//        String[] arr_dest= temp1[2].split("-");
//        this.dest.setInfo(arr_dest[1]);
        this.info= info;
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
