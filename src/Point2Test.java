import student.TestCase;

/**
 * The purpose of this class is to test Rectangle1 class
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */

public class Point2Test extends TestCase {

    // Testing Rectangle1 class
    private Point2 point;

    /**
     * setUp the instance of Rectangle1
     */
    public void setUp() {
        // Initializing the instance
        this.point = new Point2();
    }


    /**
     * Testing constructor of Rectangle1 class
     */
    public void testConstructor() {
        assertNotNull(point);
    }


    /**
     * Testing the main method execution
     * 
     */
    public void testValidScenario() {
        // File present in system
        Point2.main(new String[] { "./src/test.txt" });
        String[] systemOutLogs = systemOut().getHistory().split("\n");
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "QuadTree Size: 1 QuadTree Nodes Printed.");
    }


    /**
     * Tests the valid scenario by passing a
     * file that does exist in "src" folder
     */
    public void testInvalidScenario() {
        // file.txt not present in system
        Point2.main(new String[] { "file.txt" });
        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // Must print Invalid file as file is not present in system
        assertEquals(systemOutLogs[systemOutLogs.length - 1], "Invalid file");

    }

}
