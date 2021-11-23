import student.TestCase;

/**
 * 
 */

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class LinkedListTest extends TestCase {

    private LinkedList list;
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    /**
     * sets up the information for the tests
     */
    public void setUp() {
        list = new LinkedList();
        point1 = new Point("a", 1, 20);
        list = new LinkedList(point1);
        point2 = new Point("b", 10, 30);
        point3 = new Point("c", 140, 10);
        point4 = new Point("d", 200, 200);
    }


    /**
     * tests the removeHead method
     */
    public void testRemoveHead() {
        list.insert(point1);
        list.removeHead();
        list.insert(point1);
        list.insert(point2);
        list.removeHead();
        System.out.println(list.getSize());
        String[] systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1], "4");
    }


    /**
     * tests the insert method
     */
    public void testInsert() {
        list.insert(point1);
        list.insert(point2);
        assertEquals(3, list.getSize());
    }


    /**
     * tests the duplicates method
     */
    public void testOutputDuplicates() {
        list.insert(point1);
        list.insert(point2);
        list.insert(point1);
        list.insert(point1);
        list.outputDuplicates();
        assertTrue(systemOut().getHistory().endsWith("(1, 20)\n"));
    }


    /**
     * tests the remove method
     */
    public void testRemove() {
        list.remove(null, true);
        list.remove(null, false);
        list.remove(point1, true);
        list.remove(point1, false);
        assertEquals(1, list.getSize());
        list.insert(point1);
        list.insert(point2);
        list.insert(point1);
        list.remove(point1, true);
        list.remove(point2, false);
        assertEquals(1, list.getSize());
    }


    /**
     * tests more of the remove method
     */
    public void testRemove2() {
        list.insert(point1);
        assertNull(list.remove(point2, true));
        Point point2diffName = new Point("a", 10, 30);
        list.insert(point2diffName);
        list.insert(point2);
        assertEquals(point2, list.remove(point2, true));
        list.insert(point1);
        list.insert(point3);
        list.insert(point4);
        list.remove(point4, false);
        assertEquals(5, list.getSize());

    }


    /**
     * tests getUnique method
     */
    public void testGetUnique() {
        list.getUnique();
        assertEquals(1, list.getSize());

    }


    /**
     * tests getHead method
     */
    public void testGetHead() {
        list.getHead();
        assertEquals(1, list.getSize());
    }


    /**
     * tests getSize method
     */
    public void testGetSize() {
        list.getSize();
        assertEquals(1, list.getSize());

    }


    /**
     * tests resize method
     */
    public void testResize() {
        list.resize();
        list.insert(point1);
        list.insert(point2);
        list.insert(point3);
        list.resize();
        assertEquals(4, list.getSize());

    }


    /**
     * tests onlyDuplicates method
     */
    public void testOnlyDuplicates() {
        list.insert(point1);
        list.insert(point1);
        list.insert(point1);
        list.onlyDuplicates();
        assertEquals(4, list.getSize());
    }

}
