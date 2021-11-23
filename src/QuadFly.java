/**
 * creates the flyweight for the class so that we can conserve space
 * -any leaf node that does not point to data points instead to the
 * single flyweight, instantiated inside the quadtree class
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class QuadFly implements QuadNode {
    private static QuadFly instance = new QuadFly();

    /**
     * creates an empty flyweight
     */
    private QuadFly() {
    }


    /**
     * @return the individual instance of the quadfly
     */
    public static QuadFly getInstance() {
        return instance;
    }


    @Override
    public int dump(int x, int y, int width, int depth) {
        String spaces = "";
        for (int i = 0; i < depth; i++) {
            spaces += "  ";
        }
        System.out.println(spaces + "Node at " + x + ", " + y + ", " + width
            + ": Empty");
        return 1;
    }


    @Override
    public QuadNode insert(int x, int y, int width, Point point) {
        QuadLeaf newLeaf = new QuadLeaf(point);
        return newLeaf;
    }


    @Override
    public LinkedList getData() {
        return null;
    }


    @Override
    public Point remove(
        int x,
        int y,
        int width,
        Point searchPoint,
        boolean byName) {
        return null;
    }


    @Override
    public QuadNode adjustTree(int x, int y, int width) {
        return this;
    }


    /**
     * Tries to find point in given region.
     * This is an empty node, so nothing found.
     * 
     * @param x
     *            x coordinate for region to be searched
     * @param y
     *            y coordinate for region to be searched
     * @param width
     *            of the region to be searched
     * @param height
     *            of the region to be searched
     * @param result
     *            list of points in given region
     * @param regionX
     *            x coordinate of the section of the tree
     *            that is being looked at
     * @param regionY
     *            y coordinate of the section of the tree
     *            that is being looked at
     * @param regionWidth
     *            width of region being looked at
     * @param numOfVisits
     *            used in spacing in the dump output, determined
     *            by how far down the tree that is being looked at
     * @return list of points in the region
     */
    @Override
    public LinkedList regionSearch(
        int x,
        int y,
        int width,
        int height,
        LinkedList result,
        int regionX,
        int regionY,
        int regionWidth,
        int[] numOfVisits) {
        numOfVisits[0]++;
        return result;
    }


    /**
     * @return that this has no unique values
     */
    public int getUnique() {
        return 0;
    }


    /**
     * Method only applicable to QuadLeaf node
     */
    @Override
    public void duplicates() {
        // Method only applicable to QuadLeaf node
        // QuadFly overrides QuadNode interface which has this method
    }

}
