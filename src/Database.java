import java.util.ArrayList;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author 1.0 Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 2021-10-15
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the point and then
    // a point object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;

    /**
     * declares the quadtree for the database
     */
    private QuadTree tree;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and point as its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
        tree = new QuadTree();
    }


    /**
     * Inserts the KVPair in the SkipList if the point has valid coordinates
     * and dimensions, that is that the coordinates are non-negative. This
     * insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     * @return
     * 
     */
    public void insert(KVPair<String, Point> pair) {
        ArrayList<KVPair<String, Point>> points = list.search(pair.getKey());
        if (points.size() > 0) {
            for (KVPair<String, Point> point : points) {
                if (pair.getValue().equals(point.getValue())) {
                    System.out.println("Point REJECTED: " + pair.getValue());
                }
            }
        }
        list.insert(pair);
        tree.insert(pair.getValue());
        System.out.println("Point Inserted: " + pair.getValue());
    }


    /**
     * Removes a point with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the point to be removed
     * @return
     *         the removed point or null if point not found
     */
    public Point removeByKey(String name) {
        Point point = list.remove(name);
        if (point == null) {
            return null;
        }
        tree.remove(point, true);
        return point;
    }


    /**
     * remove a value from the database based on the value
     * 
     * @param val
     *            is the value to be found
     * @return
     *         the removed point or null if point not found
     */
    public Point removeByValue(Point val) {
        Point search = tree.remove(val, false);
        if (search == null) {
            return null;
        }
        list.remove(search);
        return search;
    }


    /**
     * Prints all the points inside the specified region. The points
     * must lie inside the area or on the edges/corners that is created by the
     * rectangular region
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param width
     *            width of the region
     * @param height
     *            height of the region
     */

    public void regionSearch(int x, int y, int width, int height) {
        int[] numOfVisits = { 0 }; // To count the number of nodes visited in
                                   // QuadTree
        tree.regionSearch(x, y, width, height, numOfVisits);
    }


    /**
     * searches for a specific key value
     * 
     * @param name
     *            the key/name of point that is being searched for
     */

    public void search(String name) {
        ArrayList<KVPair<String, Point>> search = list.search(name);
        if (search.size() > 0) {
            for (KVPair<String, Point> pair : search) {
                System.out.println("Point Found " + pair.getValue());
            }
        }
        else {
            System.out.println("Point Not Found: " + name);
        }
    }


    /**
     * find all duplicate points in the quad tree
     */
    public void duplicates() {
        tree.duplicates();
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
        tree.dump();
    }

}
