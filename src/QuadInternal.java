/**
 * Internal node for the QuadTree
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */

public class QuadInternal implements QuadNode {
    private QuadNode northEast;
    private QuadNode southEast;
    private QuadNode southWest;
    private QuadNode northWest;

    /**
     * default constructor that sets all of the children to the Flyweight
     * 
     */
    public QuadInternal() {
        northEast = QuadTree.FLYLEAF;
        southEast = QuadTree.FLYLEAF;
        southWest = QuadTree.FLYLEAF;
        northWest = QuadTree.FLYLEAF;
    }


    /**
     * outputs that the current node with the parameters is an internal node
     * recursive function that moves through each of the children counts the
     * number of notes visited
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
        String printer = "";
        for (int i = 0; i < depth; i++) {
            printer += "  ";
        }
        printer += "Node at " + x + ", " + y + ", " + width + ": Internal";
        System.out.println(printer);
        int newDepth = depth + 1;
        return 1 + northWest.dump(x, y, width / 2, newDepth) + northEast.dump(x
            + width / 2, y, width / 2, newDepth) + southWest.dump(x, y + width
                / 2, width / 2, newDepth) + southEast.dump(x + width / 2, y
                    + width / 2, width / 2, newDepth);

    }


    /**
     * insert a new Point in a child of the current internal node; recursive in
     * that it will continue following internal nodes if the child chosen is
     * internal, otherwise the function will end when it reaches a leaf
     * 
     * @param x
     *            - x coordinate of the top left corner of the current region
     * @param y
     *            - y coordinate of the top left corner of the current region
     * @param width
     *            - width of the current region
     * @param newPoint
     *            - Point that will be added to the tree
     * @return the root of the subtree that is changed after insert
     */
    @Override
    public QuadNode insert(int x, int y, int width, Point newPoint) {
        if (newPoint.getX() < x + width / 2) {
            if (newPoint.getY() < y + width / 2) {
                northWest = northWest.insert(x, y, width / 2, newPoint);
            }
            else {
                southWest = southWest.insert(x, y + width / 2, width / 2,
                    newPoint);
            }
        }
        else if (newPoint.getY() < y + width / 2) {
            northEast = northEast.insert(x + width / 2, y, width / 2, newPoint);
        }
        else {
            southEast = southEast.insert(x + width / 2, y + width / 2, width
                / 2, newPoint);
        }
        return this;
    }


    /**
     * gets the data stored in the internal node; which is null because the
     * internal node stores no information except pointers to its children
     * 
     * @return null because there is no data in the internal node
     */
    @Override
    public LinkedList getData() {
        return null;
    }


    @Override
    public void duplicates() {
        northWest.duplicates();
        southWest.duplicates();
        northEast.duplicates();
        southEast.duplicates();
    }


    /**
     * @param x
     *            is the x coordinate
     * @param y
     *            is the y coordinate
     * @param width
     *            is the width of the area searched
     * @param searchPoint
     *            is the point to be found
     * @param byName
     *            tells whether to remove by specific name or not
     * @return the point that was removed
     */
    public Point remove(
        int x,
        int y,
        int width,
        Point searchPoint,
        boolean byName) {
        Point foundPoint = null;
        if (searchPoint.getX() < x + width / 2)
            if (searchPoint.getY() < y + width / 2) {
                foundPoint = northWest.remove(x, y, width / 2, searchPoint,
                    byName);
                // northWest = northWest.adjustTree(x, y, width / 2);
            }
            else {
                foundPoint = southWest.remove(x, y + width / 2, width / 2,
                    searchPoint, byName);
                // southWest = southWest.adjustTree(x, y + width / 2,
                // width / 2);
            }
        else if (searchPoint.getY() < y + width / 2) {
            foundPoint = northEast.remove(x + width / 2, y, width / 2,
                searchPoint, byName);
            // northEast = northEast.adjustTree(x + width / 2, y, width / 2);
        }
        else {
            foundPoint = southEast.remove(x + width / 2, y + width / 2, width
                / 2, searchPoint, byName);
            // southEast = southEast.adjustTree(x + width / 2, y + width / 2,
            // width / 2);
        }
        return foundPoint;
    }


    /**
     * @return the value of whehter there is anything unique
     *         if unique then != 0
     *         if not unique (all flyweight) = 0
     */
    private int removeEmpty() {
        int nw = northWest.getUnique();
        int ne = northEast.getUnique();
        int sw = southWest.getUnique();
        int se = southEast.getUnique();
        return nw + ne + sw + se;
    }


    @Override
    public QuadNode adjustTree(int x, int y, int width) {
        northWest = northWest.adjustTree(x, y, width / 2);
        southWest = southWest.adjustTree(x, y + width / 2, width / 2);
        northEast = northEast.adjustTree(x + width / 2, y, width / 2);
        southEast = southEast.adjustTree(x + width / 2, y + width / 2, width
            / 2);
        int numUniques = removeEmpty();
        if (numUniques == 0) {
            return QuadTree.FLYLEAF;
        }
        else if (numUniques < 4) {
            QuadLeaf newLeaf = new QuadLeaf();
            while (northWest.getData() != null && northWest.getData()
                .getHead() != null) {
                newLeaf.insert(x, y, width, northWest.getData().removeHead());
            }
            while (northEast.getData() != null && northEast.getData()
                .getHead() != null) {
                newLeaf.insert(x, y, width, northEast.getData().removeHead());
            }
            while (southWest.getData() != null && southWest.getData()
                .getHead() != null) {
                newLeaf.insert(x, y, width, southWest.getData().removeHead());
            }
            while (southEast.getData() != null && southEast.getData()
                .getHead() != null) {
                newLeaf.insert(x, y, width, southEast.getData().removeHead());
            }
            return newLeaf.adjustTree(x, y, width);
        }
        else {
            return this;
        }
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
     *            length of region being looked at
     * @param numOfVisits
     *            used in spacing in the dump output, determined
     *            by how far down the tree that is being looked at
     * @return list of points in the region
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
        int[] numOfVisits) {
        numOfVisits[0]++;

        int half = regionWidth / 2;
        int xBound = regionX + half;
        int yBound = regionY + half;
        int yMax = y + height - 1;
        int xMax = x + width - 1;
        LinkedList found = result;

        if (xBound > x && yBound > y) {

            found = northWest.regionSearch(x, y, width, height, found, regionX,
                regionY, half, numOfVisits);

        }

        if (xBound <= xMax && yBound > y) {

            found = northEast.regionSearch(x, y, width, height, found, xBound,
                regionY, half, numOfVisits);

        }

        if (xBound > x && yBound <= yMax) {

            found = southWest.regionSearch(x, y, width, height, found, regionX,
                yBound, half, numOfVisits);

        }

        if (xMax >= xBound && yMax >= yBound) {

            found = southEast.regionSearch(x, y, width, height, found, xBound,
                yBound, half, numOfVisits);

        }

        return found;
    }


    /**
     * @return the number of children -- 4
     */
    public int getUnique() {
        return 4;
    }
}
