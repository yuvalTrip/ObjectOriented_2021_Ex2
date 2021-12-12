import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationTest
{
    double x= 1;
    double y= 2;
    double z= 3;
    GeoLocation_class GeoLoc = new GeoLocation_class(x, y, z);

    @Test
    void x()
    {
        double xCheck=GeoLoc.x();
        assertEquals(xCheck,1);
    }

    @Test
    void y()
    {
        double yCheck=GeoLoc.y();
        assertEquals(yCheck,2);
    }

    @Test
    void z()
    {
        double zCheck=GeoLoc.z();
        assertEquals(zCheck,3);
    }

    @Test
    void distance()
    {
        double x1= 2;
        double y1= 1;
        double z1= 2;
        GeoLocation_class GeoLoc1 = new GeoLocation_class(x1, y1, z1);
        double distToCheck=GeoLoc1.distance(GeoLoc);
        assertEquals(distToCheck,1.73);
        // Answer by the website:https://www.engineeringtoolbox.com/distance-relationship-between-two-points-d_1854.html



    }
}