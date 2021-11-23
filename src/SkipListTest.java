import student.TestCase;

/**
 * The purpose of this class is to test SkipList class
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 */

public class SkipListTest extends TestCase {

    // Creating an instance of SkipList
    private SkipList<String, Point> skipList;

    /**
     * setUp the instance of SkipList
     */
    public void setUp() {
        // Initializing the instance
        this.skipList = new SkipList<>();
    }


    /**
     * Tests constructor of SkipList class
     */
    public void testConstructor() {
        assertNotNull(skipList);
    }


    /**
     * Tests the search method of SkipList class
     */
    public void testSearch() {
        KVPair<String, Point> kv = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        this.skipList.insert(kv);

        // search method should return ArrayList of size 1
        assertEquals(this.skipList.search("point1").size(), 1);

        // Tests if the key of returned node
        assert (this.skipList.search("point1").get(0).getKey().equals(
            "point1"));

        // ArrayList should be of size 0
        assertEquals(this.skipList.search("point2").size(), 0);
        kv = new KVPair<String, Point>("point2", new Point("point2", 2, 2));
        this.skipList.insert(kv);
        assertEquals(this.skipList.search("point1").size(), 1);
        assertEquals(this.skipList.search("point2").size(), 1);

    }


    /**
     * Tests the insert method of SkipList class
     */
    public void testInsert() {
        KVPair<String, Point> kv = new KVPair<String, Point>("point3",
            new Point("point3", 1, 1));

        // Before Insert
        // search method should return ArrayList of size 1
        assertEquals(this.skipList.search("point3").size(), 0);
        this.skipList.insert(kv);

        // After insert, Tests size and value of key using search to verify
        assertEquals(this.skipList.search("point3").size(), 1);
        assert (this.skipList.search("point3").get(0).getKey().equals(
            "point3"));
    }


    /**
     * Tests the remove method of SkipList class
     */
    public void testRemove() {
        // Inserting nodes with duplicate key & value
        KVPair<String, Point> kv = new KVPair<String, Point>("point4",
            new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("a", new Point("a", 2, 2));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("a", new Point("a", 2, 2));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("a", new Point("a", 2, 2));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("a", new Point("a", 2, 2));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("a", new Point("a", 2, 2));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("b", new Point("b", 1, 3));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("c", new Point("c", 1, 4));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("c", new Point("c", 1, 4));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("c", new Point("c", 1, 4));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("c", new Point("c", 1, 4));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("d", new Point("d", 1, 5));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("point4", new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("point4", new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("point4", new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("point4", new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("point4", new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("point4", new Point("point4", 1, 1));
        this.skipList.insert(kv);

        // Total 12 points with key = point4
        assertEquals(this.skipList.search("point4").size(), 7);

        // Tests size using search after every remove operation
        this.skipList.remove("point4");
        assertEquals(this.skipList.search("point4").size(), 6);

        this.skipList.remove("point4");
        assertEquals(this.skipList.search("point4").size(), 5);

        this.skipList.remove("point1");
        assertEquals(this.skipList.search("point1").size(), 0);

        // Since point1 is removed, remove will return null
        assertEquals(this.skipList.remove("point1"), null);

        this.skipList.remove("point2");
        assertEquals(this.skipList.search("point2").size(), 0);

        // notinlist key is not present in skiplist
        assertEquals(this.skipList.remove("notinlist"), null);

    }


    /**
     * Tests the removeByValue method of SkipList class
     */
    public void testRemoveByValue() {

        // Inserts points for testing
        KVPair<String, Point> kv = new KVPair<String, Point>("point4",
            new Point("point4", 1, 1));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("a", new Point("a", 2, 2));
        kv = new KVPair<String, Point>("b", new Point("b", 2, 2));
        kv = new KVPair<String, Point>("ab", new Point("ab", 2, 2));
        kv = new KVPair<String, Point>("ab", new Point("ab", 2, 2));
        kv = new KVPair<String, Point>("abc", new Point("abc", 2, 2));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("b", new Point("b", 1, 3));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("c", new Point("c", 1, 4));
        this.skipList.insert(kv);
        kv = new KVPair<String, Point>("d", new Point("d", 1, 5));
        this.skipList.insert(kv);

        // Insert for that values are done with key "point4" previously
        // Tests key and size of returned search
        assertEquals(this.skipList.remove(new Point("point4", 1, 1)).getKey(),
            "point4");
        assertEquals(this.skipList.search("point4").size(), 0);

        // Return null check if points not present in SkipList
        assertEquals(this.skipList.remove(new Point("a", 100, 100)), null);

        assertEquals(this.skipList.remove(new Point("c", 1, 4)).getKey(), "c");
        assertEquals(this.skipList.search("c").size(), 0);

        this.skipList.remove(new Point("abc", 2, 2));
    }


    /**
     * Tests the dump method of SkipList class
     */
    public void testDump() {
        // Insert points to test dump
        KVPair<String, Point> kv = new KVPair<String, Point>("point1",
            new Point("point1", 1, 1));
        this.skipList.insert(kv);
        this.skipList.insert(kv);
        this.skipList.insert(kv);

        this.skipList.dump();
        String[] systemOutLogs = systemOut().getHistory().split("\n");

        // Tests the final output line to verify SkipList size
        assertEquals(systemOutLogs[systemOutLogs.length - 1],
            "The SkipList's Size is: 3");
    }
    
    /**
     * Tests the Iterator method of SkipList class
     */
    public void testIterator() {
        assertNotNull(this.skipList.iterator());
    }

}
