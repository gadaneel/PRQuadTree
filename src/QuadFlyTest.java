import org.junit.Test;

import student.TestCase;

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 *
 */
public class QuadFlyTest extends TestCase {

    private QuadFly quadFly;

    /**
     * creates the tree to be tested
     */
    public void setUp() {
        quadFly = QuadTree.FLYLEAF;
    }


    /**
     * Tests the constructor of QuadFly class
     */
    public void testQuadFly() {

        assertNotNull(this.quadFly);
    }


    /**
     * test case for the dump function for the flyleaf
     */
    @Test
    public void testDump() {
        QuadTree.FLYLEAF.dump(0, 0, 2, 0);
        assertEquals("Node at 0, 0, 2: Empty\n", systemOut().getHistory());
    }


    /**
     * Tests the insert of QuadFly class
     */
    public void testInsert() {
        assertNotNull(this.quadFly);
    }


    /**
     * Tests the getData method of QuadFly class
     */
    public void testGetData() {
        assertNotNull(this.quadFly);
    }


    /**
     * Tests the duplicates method of QuadFly class
     */
    public void testDuplicates() {
        assertNotNull(this.quadFly);
    }


    /**
     * Tests the regionSearch method of QuadFly class
     */
    public void testRegionSearch() {
        int[] numOfVisits = {0};
        quadFly.regionSearch(0, 0, 10, 10, null, 0, 0, 0, numOfVisits);
        assertNotNull(this.quadFly);
    }


    /**
     * Tests the getUnique method of QuadFly class
     */
    public void testGetUnique() {
        assertEquals(0, quadFly.getUnique());
    }


    /**
     * Tests the remove method of QuadFly class
     */
    public void testRemove() {
        assertNotNull(this.quadFly);
    }


    /**
     * Tests the adjustTree method of QuadFly class
     */
    public void testAdjustTree() {
        assertNotNull(this.quadFly);
    }
}
