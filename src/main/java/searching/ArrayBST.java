package searching;

import java.util.ArrayList;


/**
 *  We study a BST representation using an arrayList internal representation.
 *  Rather than using a Linked-Node Data-Structure, the left/right children
 *  will be encoded with indices in array lists.
 *  More exactly, in this data-structure each node
 *  is represented by an index i (that correspond to the ith added element)
 *  The left  node of node i, if any, is located
 *        at index idxLeftNode.get(i) otherwise idxLeftNode.get(i) == NONE
 *  The right node of node i, if any is located
 *       at index idxRightNode.get(i) otherwise idxRightNode.get(i) == NONE
 *
 *  The tree below is the result of putting (key,value) pairs (12,A),(15,B),(5,C),(8,d),(1,E) (in this order)
 *
 *                12(A)
 *                / \
 *               /   \
 *             5(C)  15(B)
 *             / \
 *          1(E)  8(D)
 *
 *   The state of internal array list after those put operations is
 *    values:        A, B, C, D, E
 *    keys:        12, 15, 5, 8, 1
 *    idxLeftNode:  2, -1, 4,-1,-1
 *    idxRightNode: 1, -1, 3,-1,-1
 *
 *  You can implement the get method before the put method if you prefer since
 *  the two methods will be graded separately.
 *
 *  You cannot add of change the fields already declared, nor change
 *  the signatures of the methods in this
 *  class but feel free to add methods if needed.
 *
 */
public class ArrayBST<Key extends Comparable<Key>, Value> {

    // The next four array lists should always have exactly equal size after a put

    public ArrayList<Key> keys;
    public ArrayList<Value> values;

    public ArrayList<Integer> idxLeftNode; // idxLeftNode[i] = index of left node of i
    public ArrayList<Integer> idxRightNode; // idxRightNode[i] = index of right node of i

    final int NONE = -1;

    public ArrayBST() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
        idxLeftNode = new ArrayList<>();
        idxRightNode = new ArrayList<>();
    }

    /**
     * Insert the entry in the BST, replace the value if the key is already present
     * in O(h) where h is the height of the tree
     * @param key a key that is present or not in the BST
     * @param val the value that must be attached to this key
     * @return true if the key was added, false if already present and the value has simply been erased
     */
    public boolean put(Key key, Value val) {
        if (keys.isEmpty()) { // Special case: empty tree
            addNode(key, val);
            return true;
        }
        return putRecursive(0, key, val); // Start from the root
    }

    private boolean putRecursive(int idx, Key key, Value val) {
        int cmp = key.compareTo(keys.get(idx));
        if (cmp == 0) {
            values.set(idx, val); // Key found, replace value
            return false;
        }

        if (cmp < 0) {
            if (idxLeftNode.get(idx) == NONE) {
                addNode(idx, true, key, val); // Insert as left child
                return true;
            } else {
                return putRecursive(idxLeftNode.get(idx), key, val); // Go left
            }
        }

        if (idxRightNode.get(idx) == NONE) {
            addNode(idx, false, key, val); // Insert as right child
            return true;
        } else {
            return putRecursive(idxRightNode.get(idx), key, val); // Go right
        }

    }

    private void addNode(Key key, Value val) {
        keys.add(key);
        values.add(val);
        idxLeftNode.add(NONE);
        idxRightNode.add(NONE);
    }

    private void addNode(int parentIdx, boolean isLeft, Key key, Value val) {
        addNode(key, val);
        if (isLeft) idxLeftNode.set(parentIdx, keys.size() - 1);
        else idxRightNode.set(parentIdx, keys.size() - 1);
    }

    /**
     * Return the value attached to this key, null if the key is not present
     * in O(h) where h is the height of the tree
     * @param key
     * @return the value attached to this key, null if the key is not present
     */
    public Value get(Key key) {
        return getRecursive(0, key); // Start from the root
    }

    private Value getRecursive(int idx, Key key) {
        if (idx == NONE || idx >= keys.size()) return null; // Base case: key not found

        int cmp = key.compareTo(keys.get(idx));
        if (cmp == 0) return values.get(idx); // Key found
        else if (cmp < 0) return getRecursive(idxLeftNode.get(idx), key); // Go left
        else return getRecursive(idxRightNode.get(idx), key); // Go right
    }
}
