/**
 * This class is responsible for creating and storing Points
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */

public class Point implements Comparable<Point> {
    private int x;
    private int y;
    private String name;

    /**
     * constructor to create a Point object
     * 
     * @param name
     *            name of the point
     * @param x
     *            x coordinate of point
     * @param y
     *            y coordinate of point
     */
    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    /**
     * Fetches the x coordinate of the Point
     * 
     * @return x coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * Fetches the x coordinate of the Point
     * 
     * @return y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Fetches the name of the Point
     * 
     * @return name of the Point
     */
    public String getName() {
        return name;
    }


    /**
     * toString method for Point object
     * 
     * @return the String representation of the Point
     */
    public String toString() {
        return "(" + this.name + ", " + this.x + ", " + this.y + ")";
    }


    /**
     * @return String representation of Point coordinates
     */
    public String printCoordinates() {
        return "(" + x + ", " + y + ")";
    }


    /**
     * Compares coordinates of 2 points to check if both are equivalent
     * 
     * @param p
     *            Point p compared to this point object
     * @return an integer to determine equivalence
     */
    public int compareTo(Point p) {
        if (this.x == p.getX()) {
            return p.getY() - this.y;
        }
        else {
            return p.getX() - this.x;
        }
    }


    /**
     * Overrides the equals method to check if points are equivalent
     * 
     * @param obj
     *            the Object being compared to this
     * @return true if equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point)obj;
            return (this.x == point.getX() && this.y == point.getY());
        }
        return false;
    }

}
