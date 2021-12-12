import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class EdgeDataTest
{
    double w=10.5;
    int src=4;
    int dst=8;
    Edge edgeCheck= new Edge(src,w,dst);

    @Test
    void getSrc()
    {
        Assertions.assertEquals(edgeCheck.getSrc(),4);
    }

    @Test
    void getDest()
    {
        Assertions.assertEquals(edgeCheck.getDest(),8);
    }

    @Test
    void getWeight()
    {
        Assertions.assertEquals(edgeCheck.getDest(),10.5);
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo()
    {

    }

    @Test
    void getTag()
    {

    }

    @Test
    void setTag()
    {

    }

}