
/**
 * Implementation of QuadTree
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class QuadTree {
    /**
     * Flyweight model used to for null pointers in the tree
     */
    public static final QuadFly FLYLEAF = QuadFly.getInstance();

    private QuadNode root;

    /**
     * default constructor that stores a flyweight in the root of the tree
     */
    public QuadTree() {
        root = QuadTree.FLYLEAF;
    }


    /**
     * insert a new point into the tree
     * 
     * @param point
     *            the new point to be added to the tree
     */
    public void insert(Point point) {
        if (point == null) {
            return;
        }
        root = root.insert(0, 0, 1024, point);
    }


    /**
     * output a list of every item in the list in the following format:
     * "Node at 0, 0, 1024: Empty"
     */
    public void dump() {
        System.out.println("QuadTree Dump:");
        System.out.println("QuadTree Size: " + root.dump(0, 0, 1024, 0)
            + " QuadTree Nodes Printed.");
    }


    /**
     * runs the duplicates command
     */
    public void duplicates() {
        root.duplicates();
    }


    /**
     * runs the remove method
     * 
     * @param findPoint
     *            the point to be found
     * @param byName
     *            whether we need the name for SkipList
     * @return the point that was removed, null if none removed
     */
    public Point remove(Point findPoint, boolean byName) {
        Point output = root.remove(0, 0, 1024, findPoint, byName);
        root = root.adjustTree(0, 0, 1024);
        return output;
    }


    /**
     * Searches for all points in a given region
     * 
     * @param x
     *            x coordinate of region
     * @param y
     *            y coordinate of region
     * @param width
     *            width of the region
     * @param height
     *            height of the region
     * @param numOfVisits
     *            number of nodes visited in the tree
     */
    public void regionSearch(
        int x,
        int y,
        int width,
        int height,
        int[] numOfVisits) {

        LinkedList result = new LinkedList();
        // Passing the initial coordinates as (0,0) and max width as 1024
        result = root.regionSearch(x, y, width, height, result, 0, 0, 1024,
            numOfVisits);
        LinkedNode curr = result.getHead();
        while (curr != null) {
            System.out.println("Point Found: " + curr.getData());
            curr = curr.getNext();
        }
        System.out.println(numOfVisits[0] + " QuadTree Nodes Visited");
    }

}
