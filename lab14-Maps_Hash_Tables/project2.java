import java.util.ArrayList;
import java.util.LinkedList;

class Entry2<K, V> {
    private final K key;
    private V value;
    public Entry2(K key, V value) { this.key = key; this.value = value; }
    public K getKey()   { return key; }
    public V getValue() { return value; }
    public V setValue(V value) { V old = this.value; this.value = value; return old; }
}

class SeparateChainingMap<K, V> {
    private ArrayList<LinkedList<Entry2<K, V>>> table;
    private int size = 0;
    private final int N = 11;

    public SeparateChainingMap() {
        table = new ArrayList<>(N);
        for (int i = 0; i < N; i++) table.add(new LinkedList<>());
    }

    private int hash(K key) { return Math.abs(key.hashCode() % N); }

    public int size()        { return size; }
    public boolean isEmpty() { return size == 0; }

    public V get(K key) {
        for (Entry2<K, V> e : table.get(hash(key)))
            if (e.getKey().equals(key)) return e.getValue();
        return null;
    }

    public V put(K key, V value) {
        LinkedList<Entry2<K, V>> bucket = table.get(hash(key));
        for (Entry2<K, V> e : bucket)
            if (e.getKey().equals(key)) return e.setValue(value);
        bucket.addFirst(new Entry2<>(key, value));
        size++;
        return null;
    }

    public V remove(K key) {
        LinkedList<Entry2<K, V>> bucket = table.get(hash(key));
        for (Entry2<K, V> e : bucket) {
            if (e.getKey().equals(key)) {
                V old = e.getValue(); bucket.remove(e); size--; return old;
            }
        }
        return null;
    }

    public void printTable() {
        System.out.println("\n--- Hash Table State (N=" + N + ") ---");
        for (int i = 0; i < N; i++) {
            if (!table.get(i).isEmpty()) {
                System.out.print("Bucket[" + i + "]: ");
                for (Entry2<K, V> e : table.get(i))
                    System.out.print("(" + e.getKey() + "=" + e.getValue() + ") -> ");
                System.out.println("null");
            }
        }
    }
}

public class Project2 {
    public static void main(String[] args) {
        SeparateChainingMap<String, String> map = new SeparateChainingMap<>();

        System.out.println("--- SeparateChainingMap ---\n");
        System.out.println("put(apple,  red)    -> " + map.put("apple",  "red"));
        System.out.println("put(banana, yellow) -> " + map.put("banana", "yellow"));
        System.out.println("put(grape,  purple) -> " + map.put("grape",  "purple"));

        System.out.println("\n-- Collision demo --");
        System.out.println("put(Aa, first)  bucket=" + Math.abs("Aa".hashCode() % 11) + " -> " + map.put("Aa", "first"));
        System.out.println("put(BB, second) bucket=" + Math.abs("BB".hashCode() % 11) + " -> " + map.put("BB", "second"));
        System.out.println("put(Ca, third)  bucket=" + Math.abs("Ca".hashCode() % 11) + " -> " + map.put("Ca", "third"));

        System.out.println("\n-- Retrieval --");
        System.out.println("get(apple) -> " + map.get("apple"));
        System.out.println("get(Aa)    -> " + map.get("Aa"));
        System.out.println("get(BB)    -> " + map.get("BB"));

        System.out.println("\n-- Update --");
        System.out.println("put(apple, green) -> " + map.put("apple", "green"));
        System.out.println("get(apple)        -> " + map.get("apple"));

        System.out.println("\n-- Remove --");
        System.out.println("remove(banana) -> " + map.remove("banana"));
        System.out.println("size()         -> " + map.size());

        map.printTable();
    }
}
