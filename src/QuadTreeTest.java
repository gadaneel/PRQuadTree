import student.TestCase;

/**
 * 
 */

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class QuadTreeTest extends TestCase {

    /**
     * Creating instance of QuadTree and Point classes
     */
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private QuadTree tree;

    /**
     * sets up the instance of points
     */
    public void setUp() throws Exception {
        point1 = new Point("point1", 1, 1);
        point2 = new Point("point2", 2, 3);
        point3 = new Point("point3", 512, 512);
        point4 = new Point("point4", 5, 5);
        tree = new QuadTree();
    }


    /**
     * tests the insert for QuadTree
     */
    public void testInsert() {
        tree.insert(point1);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(point4);
        tree.insert(null);
        tree.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "QuadTree Size: 5 QuadTree Nodes Printed.");
    }


    /**
     * tests the insert for QuadTree
     */
    public void testDump() {
        tree.insert(point1);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(new Point("point4", 4, 4));
        tree.insert(new Point("point5", 5, 5));
        tree.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "QuadTree Size: 33 QuadTree Nodes Printed.");
    }


    /**
     * tests for duplicates
     */
    public void testDuplicates() {
        Point point5 = new Point("a", 1, 1);
        Point point6 = new Point("a", 2, 3);
        Point point7 = new Point("d", 1, 1);
        Point point8 = new Point("e", 14, 10);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        tree.insert(point8);
        tree.duplicates();
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1], "(1, 1)");
    }


    /**
     * tests removal
     */
    public void testRemove() {
        tree.insert(point1);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(point4);
        tree.dump();
        tree.remove(point1, true);
        tree.remove(point1, false);
        tree.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "QuadTree Size: 1 QuadTree Nodes Printed.");
        tree.remove(point1, true);
        tree.dump();

        tree.dump();
        tree.remove(point1, false);
        tree.dump();

    }

}
