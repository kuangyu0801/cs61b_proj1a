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
        this.sentiFirst.prev = this.sentiFirst;
        this.sentiFirst.next = this.sentiFirst;
        this.size = 0;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentiFirst.i = item;
        } else {
            /*buffering the first node*/
            DequeNode oldFirst = sentiFirst;
            sentiFirst = new DequeNode(item, sentiLast, oldFirst.next);

            /* in size == 1 case sentiLast == oldFrist*/
            if (size == 1) {
                oldFirst.prev = sentiFirst;
                oldFirst.next = sentiFirst;
            } else {
                oldFirst.prev = sentiFirst;
                sentiLast.next = sentiFirst;
            }
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentiLast.i = item;
        } else {
            /*buffering the last node*/
            DequeNode oldLast = sentiLast;
            sentiLast = new DequeNode(item, oldLast, sentiFirst);
            if (size == 1) {
                oldLast.prev = sentiLast;
                oldLast.next = sentiLast;
            } else {
                oldLast.next = sentiLast;
                sentiFirst.prev = sentiLast;
            }
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
                sentiFirst.prev = sentiLast;
                sentiLast.next = sentiFirst;
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
                sentiLast.next = sentiFirst;
                sentiFirst.prev = sentiLast;
                /*
                sentiFirst = sentiFirst.next;
                sentiFirst.prev = sentiLast;
                sentiLast.next = sentiFirst;*/
            }
            size -= 1;
        }
        return rtVal;
    }

    /*use iteration, not recursion*/
    public T get(int index) {
        DequeNode ptr;
        if (index < (size + size % 2) / 2) {
            ptr = sentiFirst;
            for (int i = 0; i < index - 1; i += 1) {
                ptr = ptr.next;
            }
        } else {
            ptr = sentiLast;
            for (int i = 0; i < size - index + 1; i += 1) {
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
        if (index < 0) {
            return null;
        }
        DequeNode rtNode = this.getNode(sentiFirst, index);
        return rtNode.i;
    }
}
