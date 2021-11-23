/**
 * 
 * nodes to build a linked list of points
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class LinkedNode {
    /**
     * next node in the list
     */
    private LinkedNode next;
    /**
     * point stored in the node
     */
    private Point point;

    /**
     * creates a node that contains point and no next node
     * 
     * @param point
     *            the value stored in the node
     */
    public LinkedNode(Point point) {
        this.point = point;
        this.next = null;
    }


    /**
     * sets the value of the next node
     * 
     * @param next
     *            the node next to this one
     */
    public void setNext(LinkedNode next) {
        this.next = next;
    }


    /**
     * get the next node
     * 
     * @return the node next to this one
     */
    public LinkedNode getNext() {
        return next;
    }


    /**
     * sets the value of the node
     * 
     * @param inPoint
     *            point to be stored in the node
     */
    public void setData(Point inPoint) {
        this.point = inPoint;
    }


    /**
     * returns the private KVPair stored in the node
     * 
     * @return the pair in the node
     */
    public Point getData() {
        return point;
    }
}
