import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EdgeDataTest
{
    double w=10.5;
    int src=4;
    int dst=8;
    Edge edgeCheck= new Edge(src,w,dst);

    @Test
    void getSrc()
    {
        assertEquals(edgeCheck.getSrc(),4);
    }

    @Test
    void getDest()
    {
        assertEquals(edgeCheck.getDest(),8);
    }

    @Test
    void getWeight()
    {
        assertEquals(edgeCheck.getDest(),10.5);
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