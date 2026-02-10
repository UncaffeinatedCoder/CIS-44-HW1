public class DynamicArray<T> {
    
    private T[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    
    // Constructor
    @SuppressWarnings("unchecked")
    public DynamicArray() {
        data = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
    
    // Add element to end
    public void add(T element) {
        if (size >= data.length) {
            resize();
        }
        data[size] = element;
        size++;
    }
    
    // Get element at index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }
    
    // Remove element at index
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        T removed = data[index];
        
        // Shift elements left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        data[size - 1] = null;
        size--;
        
        return removed;
    }
    
    // Return number of elements
    public int size() {
        return size;
    }
    
    // Double the array capacity
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    
    // Print the array nicely
    @Override
    public String toString() {
        if (size == 0) return "[]";
        
        String result = "[";
        for (int i = 0; i < size; i++) {
            result += data[i];
            if (i < size - 1) result += ", ";
        }
        return result + "]";
    }
}
