import java.util.ArrayList;

class Entry<K, V> {
    private final K key;
    private V value;
    public Entry(K key, V value) { this.key = key; this.value = value; }
    public K getKey()   { return key; }
    public V getValue() { return value; }
    public V setValue(V value) { V old = this.value; this.value = value; return old; }
}

interface MapADT<K, V> {
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    int size();
    boolean isEmpty();
}

class UnsortedListMap<K, V> implements MapADT<K, V> {
    private ArrayList<Entry<K, V>> list = new ArrayList<>();

    private int findEntryIndex(K key) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getKey().equals(key)) return i;
        return -1;
    }

    public int size()        { return list.size(); }
    public boolean isEmpty() { return list.isEmpty(); }

    public V get(K key) {
        int i = findEntryIndex(key);
        return (i != -1) ? list.get(i).getValue() : null;
    }

    public V remove(K key) {
        int i = findEntryIndex(key);
        if (i != -1) { V old = list.get(i).getValue(); list.remove(i); return old; }
        return null;
    }

    public V put(K key, V value) {
        int i = findEntryIndex(key);
        if (i != -1) return list.get(i).setValue(value);
        list.add(new Entry<>(key, value));
        return null;
    }
}

public class Project1 {
    public static void main(String[] args) {
        UnsortedListMap<Integer, String> map = new UnsortedListMap<>();

        System.out.println("--- UnsortedListMap ---\n");
        System.out.println("put(5, A) -> " + map.put(5, "A"));   // null
        System.out.println("put(7, B) -> " + map.put(7, "B"));   // null
        System.out.println("put(2, C) -> " + map.put(2, "C"));   // null
        System.out.println("put(2, E) -> " + map.put(2, "E"));   // C
        System.out.println("get(7)    -> " + map.get(7));         // B
        System.out.println("remove(5) -> " + map.remove(5));      // A
        System.out.println("size()    -> " + map.size());         // 2
    }
}
