public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        nextFirst = 4; // First pointer starts in the middle
        nextLast = 5; // Second pointer starts in the middle
        size = 0;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 < 0) ? items.length - 1 : nextFirst - 1;
        size += 1;
        this.resize(this.resizeCheck());
    }

    public void addLast(T item) {
        items[nextLast] = item;
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
        }
        nextFirst = (nextFirst + 1 == items.length) ? 0 : nextFirst + 1;
        T item = items[nextFirst];
        size -= 1;
        this.resize(this.resizeCheck());
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 < 0) ? items.length - 1 : nextLast - 1;
        T item = items[nextLast];
        size -= 1;
        this.resize(this.resizeCheck());
        return item;
    }

    public T get(int index) {
        return items[((nextFirst + 1 + index) % items.length)];
    }

    private void resize(int checkRslt) {

        int oldLength = items.length;
        int posFirst = (nextFirst + 1 == oldLength) ? 0 : nextFirst + 1;
        int posLast = (nextLast - 1 < 0) ? oldLength - 1 : nextLast - 1;

        /* scale up by 4 times */
        if (checkRslt == 1) {

            T[] rszItems = (T []) new Object[oldLength * 4];
            if (posLast - posFirst >= 0) {
                /*no wrap, copy entire array in one shot*/
                System.arraycopy(items, 0, rszItems, oldLength + oldLength / 2, oldLength);
                nextFirst = oldLength + oldLength / 2 - 1;
                nextLast = oldLength + size + 1;
            } else {
                /*wrap, copy array two time: head and tail*/
                int tailLen = size - (posLast + 1);
                int headLen = posLast + 1;
                System.arraycopy(items, posFirst, rszItems, rszItems.length - tailLen, tailLen);
                System.arraycopy(items, 0, rszItems, 0, headLen);
                nextFirst = rszItems.length - tailLen - 1;
            }
            items = rszItems;

        }
        /* scale down by half */
        if (checkRslt == -1) {
            T[] rszItems = (T []) new Object[oldLength / 2];
            if (posLast - posFirst >= 0) {
                /* no wrap, copy entire array in one shot */
                System.arraycopy(items, posFirst, rszItems, oldLength / 4, size);
                nextFirst = oldLength / 4 - 1;
                nextLast = oldLength / 4 + size + 1;
            } else {
                /* wrap, copy array two time: head and tail */
                int tailLen = size - (posLast + 1);
                int headLen = posLast + 1;
                System.arraycopy(items, posFirst, rszItems, rszItems.length - tailLen, tailLen);
                System.arraycopy(items, 0, rszItems, 0, headLen);
                nextFirst = rszItems.length - tailLen - 1;
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
