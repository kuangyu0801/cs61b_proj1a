public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int first;
    private int last;

    /* buffering the old pointer for resizing*/
/*    private void oldUpdate() {
        if(size == items.length - 2) {
            oldFirst = nextFirst;
            oldLast = nextLast;
        }
    }*/
    public ArrayDeque() {
        items = (T []) new Object[8];
        nextFirst = 4; // First pointer starts in the middle
        nextLast = 5; // Second pointer starts in the middle
        first = -1;
        last = -1;
        size = 0;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        first = nextFirst;
        if (size == 0) {
            last = first;
        }
        nextFirst = (nextFirst - 1 < 0) ? items.length - 1 : nextFirst - 1;
        size += 1;
        this.resize(this.resizeCheck());
    }

    public void addLast(T item) {
        items[nextLast] = item;
        last = nextLast;
        if (size == 0) {
            first = last;
        }
        nextLast = (nextLast + 1 == items.length) ? 0 : nextLast + 1;
        size += 1;
        this.resize(this.resizeCheck());
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
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < size; i += 1) {
            str.append(this.get(i));
            str.append(",");
        }
        str.append("}");
        System.out.println(str.toString());
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T item = items[first];
            if (size == 1) {
                first = -1;
                last = -1;
                nextFirst = 4;
                nextFirst = 5;
            } else {
                nextFirst = first;
                first = (first + 1 == items.length) ? 0 : first + 1;
            }
            size -= 1;
            this.resize(this.resizeCheck());
            return item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }  else {
            T item = items[last];
            if (size == 1) {
                first = -1;
                last = -1;
                nextFirst = 4;
                nextFirst = 5;
            } else {
                nextLast = last;
                last = (last - 1 < 0) ? items.length - 1 : last - 1;
            }
            size -= 1;
            this.resize(this.resizeCheck());
            return item;
        }
    }

    public T get(int index) {
        return items[((first + index) % items.length)];
    }

    private void resize(int checkRslt) {

        int oldLength = items.length;
        int posFirst = first;
        int posLast = last;
        /*
        int posFirst = (oldFirst + 1 == oldLength) ? 0 : oldFirst + 1;
        int posLast = (oldLast - 1 < 0) ? oldLength - 1 : oldLast - 1;*/

        /* scale up by 4 times */
        if (checkRslt == 1) {

            T[] rszItems = (T []) new Object[oldLength * 4];
            if (last - first > 0) {
                /* no wrap, copy entire array in one shot */
                System.arraycopy(items, 0, rszItems, oldLength + oldLength / 2, oldLength);
                first = oldLength + oldLength / 2;
                last = first + size - 1;
                nextFirst = first - 1;
                nextLast = last + 1;
            } else {
                /* wrap, copy array two time: head and tail */
                int tailLen = oldLength - first;
                int headLen = last + 1;
                System.arraycopy(items, first, rszItems, rszItems.length - tailLen, tailLen);
                System.arraycopy(items, 0, rszItems, 0, headLen);
                first = rszItems.length - tailLen;
                nextFirst = rszItems.length - tailLen;
                nextFirst = first - 1;
                nextLast = last + 1;
            }

            items = rszItems;

        }
        /* scale down by half */
        if (checkRslt == -1) {
            T[] rszItems = (T []) new Object[oldLength / 2];
            if (last - first  > 0) {
                /* no wrap, copy entire array in one shot */
                System.arraycopy(items, first, rszItems, oldLength / 4, size);
                first = oldLength / 4 - 1;
                last = first + size - 1;
                nextFirst = first - 1;
                nextLast = last + 1;
            } else {
                /* wrap, copy array two time: head and tail */
                int tailLen = oldLength - first;
                int headLen = last + 1;
                System.arraycopy(items, first, rszItems, rszItems.length - tailLen, tailLen);
                System.arraycopy(items, 0, rszItems, 0, headLen);
                first = rszItems.length - tailLen;
                nextFirst = rszItems.length - tailLen;
                nextFirst = first - 1;
                nextLast = last + 1;
            }
            items = rszItems;
        }
    }

    /**
     * check whether the array needs to be resized
     * For arrays of length 16 or more,
     * your usage factor should always be at least 25%.
     * -1: u-rate < 25%, need shrink by half to reach > 25%
     * 1: array is full, scale to 4 times large>
     * 0: array is fine, no need to resize
     * */
    private int resizeCheck() {
        if (size == items.length) {
            return 1;
        }
        double uRate =  (double) size / items.length;
        if (uRate < 0.25 && items.length >= 16) {
            return -1;
        }
        return 0;
    }
}
