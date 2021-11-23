/**
 * Leaf node for the PR-QuadTree.
 * Contains a linkedlist of Points
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 *
 */
public class QuadLeaf implements QuadNode {
    /**
     * linkedlist of Points stored in the leaf node
     */
    private LinkedList data;

    /**
     * creates a quadleaf
     */
    public QuadLeaf() {
        data = new LinkedList();
    }


    /**
     * standard constructor for the leaf; when a leaf is created, it will have
     * data stored into it, so a new Point is inserted and a new LinkedList is
     * made
     * 
     * @param startPoint
     *            initial point stored in the LinkedList
     */
    public QuadLeaf(Point startPoint) {
        data = new LinkedList(startPoint);
    }


    /**
     * outputs that the current node, with the parameters, is leaf node outputs
     * each value of the LinkedList the leaf contains
     * 
     * @param x
     *            - x coordinate of the top left corner of the current region
     * @param y
     *            - y coordinate of the top left corner of the current region
     * @param width
     *            - width of the current region
     * @param depth
     *            - depth of the current node, relative to the root (depth 0)
     * @return the number of nodes visited
     */
    @Override
    public int dump(int x, int y, int width, int depth) {
        String spaces = "";
        for (int i = 0; i < depth; i++) {
            spaces += "  ";
        }
        String nodes = spaces + "Node at " + x + ", " + y + ", " + width + ":";
        System.out.println(nodes);
        LinkedNode curr = data.getHead();
        while (curr != null) {
            String output = spaces + curr.getData().toString();
            System.out.println(output);
            curr = curr.getNext();
        }
        return 1;
    }


    /**
     * helper function that adjusts the tree if a leaf node contains 4 data
     * points creates a new internal node, adds the data from the LinkedList as
     * children, and returns the internal node if it is made, otherwise return
     * this
     * 
     * @param x
     *            - x coordinate of the top left corner of the current region
     * @param y
     *            - y coordinate of the top left corner of the current region
     * @param width
     *            - size of the current region
     * @return the root of the subtree after is has been adjusted
     */
    @Override
    public QuadNode adjustTree(int x, int y, int width) {
        if (data.getSize() >= 4 && !data.onlyDuplicates()) {
            QuadInternal root = new QuadInternal();
            while (data.getHead() != null) {
                root.insert(x, y, width, data.removeHead());
            }
            return root;
        }
        else if (data.getSize() == 0) {
            return QuadTree.FLYLEAF;
        }
        else {
            data.resize();
            return this;
        }
    }


    /**
     * insert a new Point in the current node; once the insert has found this
     * leaf node, then it is finished moving down the end of this tree
     * 
     * @param x
     *            - x coordinate of the top left corner of the current region
     * @param y
     *            - y coordinate of the top left corner of the current region
     * @param width
     *            - width of the current region
     * @param newPoint
     *            - Point that will be added to the tree
     * @return the root of the subtree that is being inserted after adjusting
     */
    @Override
    public QuadNode insert(int x, int y, int width, Point newPoint) {
        data.insert(newPoint);
        return adjustTree(x, y, width);
    }


    /**
     * returns the pointer to the linkedlist stored in this leaf node
     * 
     * @return the pointer to the linkedlist
     */
    public LinkedList getData() {
        return data;
    }


    @Override
    public void duplicates() {
        data.outputDuplicates();
    }


    /**
     * removes the leaf node and adjusts the tree
     * 
     * @param x
     *            is the x value searched
     * @param y
     *            is the y value searched
     * @param width
     *            is the width of the interval
     * @param removePoint
     *            is the point to be removed
     * @param byName
     *            is whether or not we remove name for SkipList
     * @return the point that is removed
     */
    public Point remove(
        int x,
        int y,
        int width,
        Point removePoint,
        boolean byName) {
        Point output = data.remove(removePoint, byName);
        adjustTree(x, y, width);
        return output;
    }


    /**
     * Tries to find point in given region.
     * Goes through list to find points in given region
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
     *            length of region being looked at
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
        LinkedNode curr = data.getHead();
        while (curr != null) {
            int tempX = curr.getData().getX();
            int tempY = curr.getData().getY();
            if (tempX >= x && tempX <= x + width && tempY >= y && tempY <= y
                + height) {
                result.insert(curr.getData());
            }
            curr = curr.getNext();
        }

        return result;

    }


    /**
     * @return whether or not this is unique
     */
    public int getUnique() {
        return data.getSize();
    }
}
