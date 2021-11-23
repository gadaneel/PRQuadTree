import student.TestCase;

/**
 * 
 */

/**
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class LinkedNodeTest extends TestCase {
    /**
     * Creating instance of QuadLeaf, LinkedNode, LinkedList and Point classes
     */
    private LinkedNode node;
    private LinkedList data;
    private Point point1;

    /**
     * sets up the instance of points, quadLeaf & LinkedList
     */
    public void setUp() throws Exception {
        point1 = new Point("point1", 1, 1);
        new Point("point2", 2, 3);
        new Point("point3", 512, 512);
        new QuadLeaf();
        data = new LinkedList(point1);
        node = new LinkedNode(point1); // Constructor for LinkedNode

    }


    /**
     * Tests the constructor of QuadLeaf class
     */
    public void testQuadLeaf() {
        assertNotNull(this.data);
    }


    /**
     * tests the setNext method for QuadLeaf
     */
    public void testSetNext() {
        node.setNext(node);
        assertNotNull(this.node);
    }


    /**
     * tests the getNext method for QuadLeaf
     */
    public void testGetNext() {
        node.getNext();
        assertNotNull(this.node);
    }


    /**
     * tests the setData method for QuadLeaf
     */
    public void testSetData() {
        node.setData(point1);
        assertNotNull(this.node);
    }


    /**
     * tests the getNext method for QuadLeaf
     */
    public void testGetData() {
        node.getData();
        assertNotNull(this.node);
    }
}
