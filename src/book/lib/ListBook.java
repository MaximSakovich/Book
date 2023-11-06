package book.lib;

import java.util.ArrayList;
import java.util.Iterator;

public class ListBook<T> implements Iterable<T> {
    private final ArrayList<T> arrayList;

    public ListBook() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return arrayList.iterator();
    }

    public void add(T element) {
        arrayList.add(element);
    }

    public void remove(T element) {
        arrayList.remove(element);
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    public int size() {
        return arrayList.size();
    }
}