/**
 * using two sentinel topology
 * */
public class LinkedListDeque<T> {
    private DequeNode sentiFirst;
    private DequeNode sentiLast;
    private int size;

    private class DequeNode {
        private T i;
        private DequeNode next;
        private DequeNode prev;

        public DequeNode(T item, DequeNode front, DequeNode back) {
            i = item;
            prev = front;
            next = back;
        }
        public DequeNode() {
            next = null;
            prev = null;
        }

        public void setPrev(DequeNode prev) {
            this.prev = prev;
        }

        public void setNext(DequeNode next) {
            this.next = next;
        }

        public T get() {
            return i;
        }
    }


    public LinkedListDeque() {
        // initiate a node without assigning item;
        this.sentiFirst = new DequeNode();
        this.sentiLast = this.sentiFirst;
        this.size = 0;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentiFirst.i = item;
        } else {
            /* buffering the first node */
            DequeNode oldFirst = sentiFirst;
            sentiFirst = new DequeNode(item, null, oldFirst);
            oldFirst.prev = sentiFirst;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentiLast.i = item;
        } else {
            /*buffering the last node*/
            DequeNode oldLast = sentiLast;
            sentiLast = new DequeNode(item, oldLast, null);
            oldLast.next = sentiLast;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        StringBuilder returnSB = new StringBuilder("{");
        DequeNode ptr = sentiFirst;
        for (int i = 0; i < size; i += 1) {
            returnSB.append(ptr.i);
            returnSB.append(", ");
            ptr = ptr.next;
        }

        returnSB.append("}");
        System.out.println("[Printing Output]");
        System.out.println(returnSB.toString());
    }

    public T removeFirst() {
        T rtVal;
        if (size == 0) {
            rtVal = null;
        } else {
            rtVal = sentiFirst.i;

            if (size == 1) {
                sentiFirst.i = null;
            } else {
                sentiFirst = sentiFirst.next;
                sentiFirst.prev = null;
            }
            size -= 1;
        }
        return rtVal;
    }

    public T removeLast() {
        T rtVal;
        if (size == 0) {
            rtVal = null;
        } else {
            rtVal = sentiLast.i;
            if (size == 1) {
                sentiLast.i = null;
            } else {
                sentiLast = sentiLast.prev;
                sentiLast.next = null;
            }
            size -= 1;
        }
        return rtVal;
    }

    /**
     * use iteration, not recursion
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * */
    public T get(int index) {
        if(size == 0 || index > size) {
            return null;
        }
        DequeNode ptr;
        if (index < (size + size % 2) / 2) {
            ptr = sentiFirst;
            for (int i = 1; i < index + 1; i += 1) {
                ptr = ptr.next;
            }
        } else {
            ptr = sentiLast;
            for (int i = 1; i < size - (index + 1); i += 1) {
                ptr = ptr.prev;
            }
        }
        return ptr.i;
    }

    private DequeNode getNode(DequeNode n, int index) {
        if (index == 0) {
            return n;
        }
        return getNode(n.next, index - 1);
    }

    public T getRecursive(int index) {
        if(size == 0 || index > size) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        DequeNode rtNode = this.getNode(sentiFirst, index);
        return rtNode.i;
    }
}
