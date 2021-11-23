/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project specification.
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */
public class CommandProcessor {

    // the database object to manipulate the
    // Commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * 
     * the database object to manipulate
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file
     * line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // Removing extra spaces
        line = line.replaceAll("\\s+", " ");
        // Converting to commandArray
        String[] commandArray = line.split(" ");

        int x;
        int y;
        int width;
        int height;
        String name;

        switch (commandArray[0]) {
            case "insert":
                // Insert a point
                x = Integer.parseInt(commandArray[2]);
                y = Integer.parseInt(commandArray[3]);
                name = commandArray[1];
                if (checkCoordinates(x, y)) {
                    data.insert(new KVPair<String, Point>(name, new Point(name,
                        x, y)));
                }
                else {
                    System.out.println("Point REJECTED: (" + name + ", " + x
                        + ", " + y + ")");
                }
                break;
            case "remove":
                // Remove by name
                if (commandArray.length == 2) {
                    name = commandArray[1];
                    Point removed = data.removeByKey(name);
                    if (removed == null) {
                        System.out.println("Point Not Removed: " + name);
                    }
                    else {
                        System.out.println("Point " + removed.toString()
                            + " Removed");
                    }
                }
                else if (commandArray.length == 3) { // Remove by dimensions
                    x = Integer.parseInt(commandArray[1]);
                    y = Integer.parseInt(commandArray[2]);
                    if (checkCoordinates(x, y)) {
                        Point removePoint = new Point(null, x, y);
                        Point found = data.removeByValue(removePoint);
                        if (found == null) {
                            System.out.println("Point Not Found: (" + x + ", "
                                + y + ")");
                        }
                        else {
                            System.out.println("Point " + found.toString()
                                + " Removed");
                        }
                    }
                    else {
                        System.out.println("Point Rejected: (" + x + ", " + y
                            + ")");
                    }
                }
                break;
            case "regionsearch":
                // Find all points in this region
                x = Integer.parseInt(commandArray[1]);
                y = Integer.parseInt(commandArray[2]);
                width = Integer.parseInt(commandArray[3]);
                height = Integer.parseInt(commandArray[4]);
                if (width > 0 && height > 0) {
                    System.out.println("Points Intersecting Region: (" + x
                        + ", " + y + ", " + width + ", " + height + ")");
                    data.regionSearch(x, y, width, height);
                }
                else {
                    System.out.println("Rectangle Rejected: (" + x + ", " + y
                        + ", " + width + ", " + height + ")");
                }
                break;
            case "duplicates":
                // Find all points with duplicate coordinates
                System.out.println("Duplicate Points:");
                data.duplicates();
                break;
            case "search":
                // Find all points with this name
                name = commandArray[1];
                data.search(name);
                break;
            case "dump":
                // Print complete skip list
                data.dump();
                break;
            default:
                System.out.println("Command not recognized"); // Unknown command
                break;
        }
    }


    /**
     * Checks x & y coordinates of points to check if they lie in the allowed
     * region
     * 
     * @return
     *         true if both coordinates are within the region limit
     *         false if any one coordinate is outside the defined region
     */
    private boolean checkCoordinates(int x, int y) {
        return x >= 0 && y >= 0 && x < 1024 && y < 1024;
    }

}
