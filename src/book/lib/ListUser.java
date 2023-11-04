package book.lib;

import java.util.ArrayList;
import java.util.Iterator;

public class ListUser<T> implements Iterable<T> {
    private ArrayList<T> arrayList;

    public ListUser() {
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
