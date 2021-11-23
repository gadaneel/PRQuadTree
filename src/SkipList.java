import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Neel Gada (neelg@vt.edu), Ayush Shah (ayushmanish@vt.edu)
 * 
 * @version 1.0 2021-10-15
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    private int level; // Level of head node

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
        level = -1;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * @return
     *         ArrayList of KVPairs with same key in SkipList
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        SkipNode x = head; // Dummy header node
        for (int i = level; i >= 0; i--) { // For each level...
            while ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                .compareTo(key) < 0)) { // go forward
                x = x.forward[i]; // Go one last step
            }
        }
        x = x.forward[0]; // Move to actual record, if it exists
        ArrayList<KVPair<K, V>> arr = new ArrayList<KVPair<K, V>>();
        while ((x != null) && (x.pair.getKey().compareTo(key) == 0)) {
            arr.add(x.pair);
            x = x.forward[0];
        } // Got it
        return arr; // Resulting array of KVPair
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    public void insert(KVPair<K, V> it) {
        int newLevel = randomLevel(); // New node's level
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        SkipNode[] update = new SkipNode(null, level + 1).forward;
        SkipNode x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(new KVPair<K, V>(null, null), newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }
        level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            key of the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    public V remove(K key) {
        SkipNode removeNode = null;
        SkipNode start = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find node to be removed
            while ((start.forward[i] != null) && (start.forward[i].pair.getKey()
                .compareTo(key) < 0)) {
                start = start.forward[i];
            }
            if ((start.forward[i] != null) && (start.forward[i].pair.getKey()
                .compareTo(key) == 0)) {
                removeNode = start.forward[i];
                break;
            }
        }
        if (removeNode == null) {
            return null;
        }
        SkipNode x = head;
        for (int i = level; i >= 0; i--) { // Update pointers for removed node
            while ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                .compareTo(key) <= 0)) {
                if (x.forward[i] != removeNode) {
                    x = x.forward[i];
                }
                if ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                    .compareTo(key) == 0)) {
                    if (x.forward[i] == removeNode) {
                        x.forward[i] = removeNode.forward[i];
                        break;
                    }
                    else {
                        x = x.forward[i];
                    }
                }
            }
        }
        size--; // Increment dictionary size
        return removeNode.pair.getValue();
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> remove(V val) {
        SkipNode removeNode = null;
        int level1 = -1;
        SkipNode start = head; // Start at header node
        while (start.forward[0] != null) {
            if (start.forward[0].pair.getValue().equals(val)) {
                removeNode = start.forward[0];
                level1 = removeNode.level;
                break;
            }
            start = start.forward[0];
        }
        if (removeNode == null) {
            return null;
        }
        K key = removeNode.pair.getKey();
        SkipNode x = head;
        for (int i = level1; i >= 0; i--) { // Find insert position
            while (x.forward[i] != removeNode) {
                x = x.forward[i];
            }
            x.forward[i] = x.forward[i].forward[i];
        }
        size--; // Increment dictionary size
        KVPair<K, V> pair1 = new KVPair<K, V>(key, removeNode.pair.getValue());
        return pair1;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        System.out.println("SkipList Dump:");
        System.out.println("level: " + this.head.forward.length
            + " Value: null");
        SkipNode start = head;
        while (start.forward[0] != null) {
            start = start.forward[0];
            System.out.println("level: " + start.forward.length + " Value: "
                + start.pair.getValue().toString());
        }
        System.out.println("The SkipList's Size is: " + this.size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author Neel Gada
     * 
     * @version 2021-09-23
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            return (current.forward[0] != null);
        }


        @Override
        public KVPair<K, V> next() {
            current = current.forward[0];
            return current.pair;
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
