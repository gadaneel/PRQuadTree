import student.TestCase;

/**
 * The purpose of this class is to test CommandProcessor class
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */

public class DatabaseTest extends TestCase {

    // Creating an instance of Database class
    private Database data;

    /**
     * setUp the Database
     */
    public void setUp() {
        // Initializing the instance
        this.data = new Database();
    }


    /**
     * Tests the constructor of Database class
     */
    public void testDatabase() {
        assertNotNull(this.data);
    }


    /**
     * Tests the insert method of Database class
     */
    public void testInsert() {
        KVPair<String, Point> kv = new KVPair<String, Point>("abc", new Point(
            "abc", 1, 1));
        data.insert(kv);
        // Checks output for correct insert
        data.search("abc");
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Point Found (abc, 1, 1)");

        // Clear inserted data
        data.removeByKey("abc");
    }


    /**
     * Tests the remove method of Database class
     */

    public void testRemove() {
        // Inserting record for testing remove operation
        KVPair<String, Point> kv = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        data.insert(kv);
        // Tests remove by key
        // record present
        this.data.removeByKey("point1");
        data.search("point1");

        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Point Not Found: point1");

        data.insert(kv);

        // Tests remove by coordinates

        // record present
        data.search("point1");
        this.data.removeByValue(new Point(null, 1, 1));

        data.search("point1");
        systemOutLogs = systemOut().getHistory().split("\n");

        // Check output for valid command
        assertEquals(systemOutLogs[systemOutLogs.length - 2],
            "Point Found (point1, 1, 1)");

        // Check output for valid command
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Point Not Found: point1");

    }


    /**
     * Tests the regionsearch method of Database class
     */

    public void testRegionsearch() {

        KVPair<String, Point> kv = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        this.data.insert(kv);
        // Point inserted in present in the region
        this.data.regionSearch(0, 0, 2, 2);
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Since rectangle "r" inserted above intersects with region (100, 100,
        // 8, 8)
        // it should be printed to logs
        assertEquals(systemOutLogs[systemOutLogs.length - 2],
            "Point Found: (point1, 1, 1)");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "1 QuadTree Nodes Visited");

        // Point not present in mentioned region
        this.data.regionSearch(10, 10, 10, 10);
        systemOutLogs = systemOut().getHistory().split("\n");

        // Since no rectangle is present in mentioned region
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "1 QuadTree Nodes Visited");

        // Clear inserted data
        this.data.removeByKey("point1");
    }


    /**
     * Tests the intersection in list of Database class
     */

    public void testDuplicates() {

        // Inserting a pair of intersecting rectangles
        KVPair<String, Point> kv1 = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        KVPair<String, Point> kv2 = new KVPair<String, Point>("point2",
            new Point("point2", 2, 2));
        KVPair<String, Point> kv3 = new KVPair<String, Point>("point3",
            new Point("point3", 10, 10));
        KVPair<String, Point> kv4 = new KVPair<String, Point>("point4",
            new Point("point4", 1, 1));
        this.data.insert(kv1);
        this.data.insert(kv2);
        this.data.insert(kv3);
        this.data.insert(kv4);
        this.data.duplicates();

        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Check the output for all intersections
        assertEquals(systemOutLogs[systemOutLogs.length - 1], "(1, 1)");

        // Clearing inserted data
        this.data.removeByKey("point1");
        this.data.removeByKey("point2");
        this.data.removeByKey("point3");
        this.data.removeByKey("point4");
    }


    /**
     * Tests the search method of Database class
     */

    public void testSearch() {
        KVPair<String, Point> kv = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        data.insert(kv);
        data.search("point1");
        data.search("abc");
        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // results
        assertEquals(systemOutLogs[systemOutLogs.length - 2],
            "Point Found (point1, 1, 1)");

        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "Point Not Found: abc");

        // Clearing inserted rectangles
        this.data.removeByKey("point1");
    }


    /**
     * Tests the dump method of Database class
     */
    public void testDump() {
        KVPair<String, Point> kv = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        data.insert(kv);
        data.insert(kv);
        data.insert(kv);
        this.data.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        // Confirm dump method by check size in last line and number of line in
        // between last line and opening link should be equal to size as each
        // node in list will take 1 line
        assertEquals(systemOutLogs[systemOutLogs.length - 7],
            "The SkipList's Size is: 3");
    }

}
