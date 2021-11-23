import student.TestCase;

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 *
 */
public class PointTest extends TestCase {

    private Point point;

    /**
     * setUp the instance of Point
     */
    public void setUp() {
        // Initializing the instance of MyRectangle class
        this.point = new Point("point1", 1, 1);
    }


    /**
     * Testing constructor of Point
     */
    public void testPoint() {
        assertNotNull(point);
    }


    /**
     * Test toString method of Point class
     */
    public void testToString() {
        assertEquals(point.toString(), "(point1, 1, 1)");
    }


    /**
     * Test getX method of Point class
     */
    public void testGetX() {
        assertEquals(point.getX(), 1);
    }


    /**
     * Test getY method of Point class
     */
    public void testGetY() {
        assertEquals(point.getY(), 1);
    }


    /**
     * Test getName method of Point class
     */
    public void testGetName() {
        assertEquals(point.getName(), "point1");
    }


    /**
     * Test printCoordinates method of Point class
     */
    public void testPrintCoordinates() {
        assertEquals(point.printCoordinates(), "(1, 1)");
    }


    /**
     * Test compareTo method of Point class
     */
    public void testCompareTo() {
        Point point2 = new Point("point2", 1, 1);
        assertFalse(point2.compareTo(point) > 0);
    }

}
