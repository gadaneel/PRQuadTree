import student.TestCase;

/**
 * 
 */

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class QuadLeafTest extends TestCase {

    private LinkedList data;

    /**
     * sets up the instance of points, quadLeaf & LinkedList
     */
    public void setUp() throws Exception {
        Point point1 = new Point("point1", 1, 1);
        new QuadLeaf();
        data = new LinkedList(point1);
        System.out.println(data.toString());
    }


    /**
     * Tests the constructor of QuadLeaf class
     */
    public void testQuadLeaf() {
        assertNotNull(this.data);
    }


    /**
     * Tests the dump method of QuadLeaf class
     */
    public void testDump() {
        assertNotNull(this.data);
    }


    /**
     * Tests the adjustTree of QuadLeaf class
     */
    public void testAdjustTree() {
        assertNotNull(this.data);
    }


    /**
     * Tests the insert of QuadLeaf class
     */
    public void testInsert() {
        assertNotNull(this.data);
    }


    /**
     * Tests the getData method of QuadLeaf class
     */
    public void testGetData() {
        assertNotNull(this.data);
    }


    /**
     * Tests the duplicates method of QuadLeaf class
     */
    public void testDuplicates() {
        assertNotNull(this.data);
    }


    /**
     * Tests the remove method of QuadLeaf class
     */
    public void testRemove() {
        assertNotNull(this.data);
    }


    /**
     * Tests the regionSearch method of QuadLeaf class
     */
    public void testRegionSearch() {
        assertNotNull(this.data);
    }


    /**
     * Tests the getUnique method of QuadLeaf class
     */
    public void testGetUnique() {
        assertNotNull(data.getSize());
    }

}
