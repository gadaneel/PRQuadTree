import student.TestCase;

/**
 * 
 */

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 *
 */
public class QuadInternalTest extends TestCase {

    private QuadInternal node1;

    /**
     * sets up the class
     */
    public void setUp() {
        node1 = new QuadInternal();
    }


    /**
     * tests the remove method
     */
    public void testRemove() {
        Point point = new Point("a", 1, 1);
        node1.insert(0, 0, 10, point);
        point = node1.remove(0, 0, 1024, point, false);
        System.out.println(point.getX());
        String[] systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1], "1");
    }


    /**
     * tests the adjustTree method
     */
    public void testAdjustTree() {
        QuadTree tree = new QuadTree();
        Point newPoint = new Point("a", 1023, 1);
        tree.insert(newPoint);
        tree.insert(newPoint);
        tree.insert(new Point("b", 1022, 1));
        tree.insert(new Point("c", 1, 1));
        tree.remove(new Point("c", 1, 1), true);
        tree.dump();
        String[] systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1],
            "QuadTree Size: 1 QuadTree Nodes Printed.");

    }


    /**
     * Tests the constructor of QuadInternal class
     */
    public void testQuadLeaf() {
        assertNotNull(this.node1);
    }


    /**
     * Tests the dump method of QuadInternal class
     */
    public void testDump() {
        assertNotNull(this.node1);
    }


    /**
     * Tests the insert of QuadInternal class
     */
    public void testInsert() {
        assertNotNull(this.node1);
    }


    /**
     * Tests the getData method of QuadInternal class
     */
    public void testGetData() {
        assertNull(node1.getData());
    }


    /**
     * Tests the duplicates method of QuadInternal class
     */
    public void testDuplicates() {
        assertNotNull(this.node1);
    }


    /**
     * Tests the regionSearch method of QuadInternal class
     */
    public void testRegionSearch() {
        int[] numOfVisits = {0};
        node1.regionSearch(0, 0, 0, 0, null, 0, 0, 0, numOfVisits);
        assertNotNull(this.node1);
    }


    /**
     * Tests the getUnique method of QuadInternal class
     */
    public void testGetUnique() {
        assertEquals(4, node1.getUnique());
    }

}
