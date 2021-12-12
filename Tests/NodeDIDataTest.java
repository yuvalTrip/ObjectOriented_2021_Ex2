import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeDIDataTest {
    String info="";
    double x= 1;
    double y= 2;
    double z= 3;
    GeoLocation_class GeoLoc = new GeoLocation_class(x, y, z);
    int id=4;
    double weight=10;
    int tag=9;
    NodeDI nodeDICheck = new NodeDI(id, GeoLoc,weight ,tag);
    //    public Node(int id, GeoLocation g,double weight ,int tag)
    @Test
    void getKey()
    {
        assertEquals(nodeDICheck.getKey(),4);
    }

    @Test
    void getLocation()
    {
        assertEquals(nodeDICheck.getLocation(),GeoLoc);
    }

    @Test
    void setLocation()
    {
        double x= 3;
        double y= 3;
        double z= 3;
        GeoLocation_class GeoLocCheck = new GeoLocation_class(x, y, z);
        nodeDICheck.setLocation(GeoLoc);
        assertEquals(nodeDICheck.getLocation(),GeoLocCheck);
    }

    @Test
    void getWeight()
    {
        assertEquals(nodeDICheck.getWeight(),10);
    }

    @Test
    void setWeight()
    {
        nodeDICheck.setWeight(5);
        assertEquals(nodeDICheck.getWeight(),5);
    }

    @Test
    void getInfo()
    {
        //    public String getInfo() {
        //        return "id: " +this.id +" pos: " +g.toString() +" weight: " +this.weight;
        //    }
        String ToCheck= "id: 4 pos: 3,3,3 weight: 5";//////////////////////////////////////////////////////
        assertEquals(nodeDICheck.getInfo(),ToCheck);
    }

    @Test
    void setInfo()
    {
        String ToSet= "id: 0 pos: 0,0,0 weight: 0";////////////////////////////////////////////////////////
        nodeDICheck.setInfo(ToSet);
        assertEquals(nodeDICheck.getInfo(),ToSet);
    }

    @Test
    void getTag()
    {
        assertEquals(nodeDICheck.getTag(),9);
    }

    @Test
    void setTag()
    {
        nodeDICheck.setTag(0);
        assertEquals(nodeDICheck.getTag(),0);
    }
}