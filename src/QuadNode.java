/**
 * Node used to implement the QuadTree; can be either internal or leaf
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 *
 */
public interface QuadNode {

    /**
     * 
     * outputs the value of the node, then recurses to the next node if it is an
     * internal node
     * 
     * @param x
     *            - x coordinate of the top left corner of the current region
     * @param y
     *            - y coordinate of the top left corner of the current region
     * @param width
     *            - width of the current region
     * @param depth
     *            - depth of the current node, relative to the root (0)
     * @return the number of nodes visited
     */
    public int dump(int x, int y, int width, int depth);


    /**
     * inserts a new Point to the QuadTree; recurses down internal nodes and
     * stops at leaf nodes
     * 
     * @param x
     *            x coordinate of the top left corner of the current region
     * @param y
     *            y coordinate of the top left corner of the current region
     * @param width
     *            width of the current region
     * @param point
     *            Point that is being added to the list
     * @return the root of the subtree after the tree has been traversed
     */
    public QuadNode insert(int x, int y, int width, Point point);


    /**
     * fixes the tree after removal of a point
     * 
     * @param x
     *            the x point to look for
     * @param y
     *            the y point to look for
     * @param width
     *            the width of adjustment
     * @return
     *         the node that was adjusted
     */
    public QuadNode adjustTree(int x, int y, int width);


    /**
     * getter for the data stored in the node
     * 
     * @return data in the node
     */
    public LinkedList getData();


    /**
     * looks for duplicate points
     */
    public void duplicates();


    /**
     * removes a point
     * 
     * @param x
     *            is the x point to look for
     * @param y
     *            the y point to look for
     * @param width
     *            the width of search
     * @param removePoint
     *            what point to remove
     * @param byName
     *            whether the name is necessary for SkipList
     * @return
     *         the node that is removed
     */
    public Point remove(
        int x,
        int y,
        int width,
        Point removePoint,
        boolean byName);


    /**
     * @return whether a node is unique
     */
    public int getUnique();


    /**
     * Searches for all points in a given rectangle
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
     *            list of all points in region
     * @param regionX
     *            current x value being checked in the tree
     * @param regionY
     *            current y value being checked in the tree
     * @param regionWidth
     *            length of the region in the tree
     * @param numOfVisits
     *            number of nodes visited while searching
     * @return list of all points in the region
     */
    public LinkedList regionSearch(
        int x,
        int y,
        int width,
        int height,
        LinkedList result,
        int regionX,
        int regionY,
        int regionWidth,
        int[] numOfVisits);
}
