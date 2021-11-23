/**
 * basic linked list implementation; based on storing Points
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class LinkedList {
    /**
     * pointer to the first node in the list
     */
    private LinkedNode head;
    /**
     * number of DIFFERENT Points in the list. Example: 4,2->2,4->4,2->0,5 has
     * size 3
     */
    private int size;

    private int numUnique;

    private boolean duplicates;

    /**
     * default constructor for the LinkedList
     */
    public LinkedList() {
        head = null;
        size = 0;
        numUnique = 0;
        duplicates = true;
    }


    /**
     * creates a LinkedList based on a single node
     * 
     * @param startPoint
     *            the data that will start the list
     */
    public LinkedList(Point startPoint) {
        head = new LinkedNode(startPoint);
        size = 1;
        numUnique = 1;
        duplicates = true;
    }


    /**
     * removes the head from the list, and replaces the head with the next node
     * 
     * @return the data stored in the head of the node
     */
    public Point removeHead() {
        if (head.getNext() != null) {
            Point temp = head.getData();
            head = head.getNext();
            return temp;
        }
        else {
            Point output = head.getData();
            head = null;
            return output;
        }
    }


    /**
     * inserts a specific Point into the list; inserts at the end so that it can
     * be determined if there are any duplicates in the list; sorted by
     * coordinate in ascending order, first by x coordinate, then by y
     * coordinate
     * 
     * @param newPoint
     *            the Point that will be added to the node
     */
    public void insert(Point newPoint) {
        LinkedNode newNode = new LinkedNode(newPoint);
        if (head == null) {
            head = newNode;
            numUnique++;
            size++;
        }
        else {
            LinkedNode curr = head;
            while (curr.getNext() != null) {
                if (!curr.getData().equals(newPoint)) {
                    duplicates = false;
                }
                if (newPoint.compareTo(curr.getData()) >= 0) {
                    newNode.setNext(curr.getNext());
                    curr.setNext(newNode);
                    size++;
                    return;
                }
                curr = (curr.getNext());
            }
            curr.setNext(newNode);
            size++;
        }
    }


    /**
     * This method returns the duplicate points in the linked
     * list
     */
    public void outputDuplicates() {
        LinkedNode curr = head;
        String output = "";
        while (curr.getNext() != null) {
            if (curr.getData().equals(curr.getNext().getData()) && !(output
                .contains(curr.getNext().getData().printCoordinates()))) {
                output += curr.getNext().getData().printCoordinates();
                System.out.println(curr.getNext().getData().printCoordinates());
            }
            curr = curr.getNext();
        }
    }


    /**
     * get the pointer to the head of the list
     * 
     * @return the head of the list
     */
    public LinkedNode getHead() {
        return head;
    }


    /**
     * get the size of the list; size should not include duplicates
     * 
     * @return size of the list, no duplicates
     */
    public int getSize() {
        return size;
    }


    /**
     * changes the size of the list depending on whether unique points exist
     */
    public void resize() {
        if (head == null) {
            size = 0;
            numUnique = 0;
        }
        else {
            LinkedNode curr = head;
            int newSize = 1;
            int newUnique = 1;
            while (curr.getNext() != null) {
                if (!curr.getData().equals(curr.getNext().getData())) {
                    newUnique++;
                }
                newSize++;
                curr = curr.getNext();
            }
            size = newSize;
            numUnique = newUnique;
        }
    }


    /**
     * returns whether or not the list contains only a unique point
     * 
     * @return true if there is only one unique set of coordinates in the list,
     *         false otherwise
     */
    public boolean onlyDuplicates() {
        return duplicates;
    }


    /**
     * @return the number of unique elements in the list
     */
    public int getUnique() {
        return numUnique;
    }


    /**
     * removes a point and checks to see whether it is supposed
     * to be returned by name or by value. if by name will check against
     * other points in the linked list
     * 
     * @param searchPoint
     *            the value to be found
     * @param byName
     *            whether to search by name
     * @return the point removed
     */
    public Point remove(Point searchPoint, boolean byName) {
        LinkedNode curr = head;
        if (curr == null) {
            return null;
        }
        if (curr.getData().equals(searchPoint)) {
            resize();
            return removeHead();
        }
        while (curr.getNext() != null) {
            if (curr.getNext().getData().equals(searchPoint)) {
                if (!byName) {
                    LinkedNode temp = curr.getNext();
                    curr.setNext(curr.getNext().getNext());
                    temp.setNext(null);
                    resize();
                    return temp.getData();
                }
                else if (curr.getNext().getData().getName().compareTo(
                    searchPoint.getName()) == 0 && curr.getNext().getData()
                        .equals(searchPoint)) {
                    LinkedNode temp = curr.getNext();
                    curr.setNext(curr.getNext().getNext());
                    temp.setNext(null);
                    resize();
                    return temp.getData();
                }
            }
            curr = curr.getNext();
        }
        return null;
    }
}
