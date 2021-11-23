import student.TestCase;

/**
 * The purpose of this class is to test CommandProcessor class
 * with all possible commands and scenarios
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */

public class CommandProcessorTest extends TestCase {

    // Testing CommandProcessor class
    private CommandProcessor cmdProcessor;

    /**
     * Initializing the instance of CommandProcessor class
     */
    public void setUp() {
        // Initializing
        this.cmdProcessor = new CommandProcessor();
    }


    /**
     * Testing constructor of CommandProcessor
     */
    public void testCommandProcessor() {
        assertNotNull(this.cmdProcessor);
    }


    /**
     * Testing possible commands
     */
    public void testProcessor() {
        // Invalid insert, remove, regionsearch commands
        this.cmdProcessor.processor("insert abc       -1         20   ");
        this.cmdProcessor.processor("insert ab     3 -4");
        this.cmdProcessor.processor("insert     def       -2 -20");
        this.cmdProcessor.processor("insert def       -1 4");
        this.cmdProcessor.processor("insert     def       1025 1024");
        this.cmdProcessor.processor("insert     abc       21    2120");
        this.cmdProcessor.processor("remove 1111 20");
        this.cmdProcessor.processor("remove 1020 1020");
        this.cmdProcessor.processor("remove abc");
        this.cmdProcessor.processor("regionsearch   5   20 0 -13");
        this.cmdProcessor.processor("regionsearch 1 2 -11 -13");
        this.cmdProcessor.processor("regionsearch 52 50 17 -13");
        String[] systemOut = systemOut().getHistory().split("\n");
        // Check output for invalid commands
        for (int i = 1; i <= 6; i++) {
            assert (systemOut[i - 1].indexOf("Point REJECTED:") == 0);
        }
        String command = "insert           point1  15   15";
        // Adding horizontal tab to command
        this.cmdProcessor.processor(command);

        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1],
            "Point Inserted: (point1, 15, 15)");
        this.cmdProcessor.processor("search point1");

        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1],
            "Point Found (point1, 15, 15)");

        this.cmdProcessor.processor("invalid command");
        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1], "Command not recognized");

        this.cmdProcessor.processor("regionsearch 50 50 -11 -13");

        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1],
            "Rectangle Rejected: (50, 50, -11, -13)");

        this.cmdProcessor.processor("search rr");
        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1], "Point Not Found: rr");

        this.cmdProcessor.processor("insert p1 1 1");
        this.cmdProcessor.processor("insert p1 1 1");
        this.cmdProcessor.processor("insert p2 1 1");
        this.cmdProcessor.processor("insert p3 1 1");
        this.cmdProcessor.processor("insert p4 50 50");
        this.cmdProcessor.processor("insert p5 500 500");
        this.cmdProcessor.processor("insert p6 250 250");
        this.cmdProcessor.processor("insert p4 2 2");
        this.cmdProcessor.processor("insert p5 800 50");
        this.cmdProcessor.processor("insert p6 1000 1000");
        this.cmdProcessor.processor("insert p4 300 700");
        this.cmdProcessor.processor("insert p5 700 300");
        this.cmdProcessor.processor("insert p6 700 700");
        this.cmdProcessor.processor("insert p4 100 600");
        this.cmdProcessor.processor("insert p5 800 500");
        this.cmdProcessor.processor("insert p6 1000 1001");
        this.cmdProcessor.processor("insert p4 500 600");
        this.cmdProcessor.processor("insert p5 600 500");
        this.cmdProcessor.processor("insert p6 1022 1022");
        this.cmdProcessor.processor("insert p4 10 1000");
        this.cmdProcessor.processor("insert p5 600 1");
        this.cmdProcessor.processor("insert p6 513 513");
        this.cmdProcessor.processor("insert p4 10 600");
        this.cmdProcessor.processor("insert p5 1020 500");
        this.cmdProcessor.processor("insert p6 513 1020");
        this.cmdProcessor.processor("insert p4 275 900");
        this.cmdProcessor.processor("insert p5 900 450");
        this.cmdProcessor.processor("insert p6 1022 600");
        this.cmdProcessor.processor("remove p4");
        this.cmdProcessor.processor("remove p5");
        this.cmdProcessor.processor("remove 200 20");
        this.cmdProcessor.processor("remove p6");
        this.cmdProcessor.processor("remove p1");
        this.cmdProcessor.processor("remove 1 1");
        this.cmdProcessor.processor("insert p10 1 1");
        this.cmdProcessor.processor("insert p11 1 1");
        this.cmdProcessor.processor("regionsearch 0 0 100 100");
        this.cmdProcessor.processor("duplicates");

        systemOut = systemOut().getHistory().split("\n");
        assertEquals(systemOut[systemOut.length - 1], "(1, 1)");

        this.cmdProcessor.processor("dump");

        systemOut = systemOut().getHistory().split("\n");
        assert (systemOut[0].indexOf("QuadTree Size:") == -1);

    }

}
