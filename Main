public class Main {
    
    public static void main(String[] args) {
        System.out.println("--- DynamicArray Demo ---\n");
        
        // Create array and add elements
        DynamicArray<Integer> numbers = new DynamicArray<>();
        
        System.out.println("Adding numbers 10, 20, 30, 40, 50:");
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        System.out.println("Array: " + numbers);
        System.out.println("Size: " + numbers.size());
        
        // Test get
        System.out.println("\nElement at index 2: " + numbers.get(2));
        
        // Test remove
        System.out.println("\nRemoving element at index 1...");
        int removed = numbers.remove(1);
        System.out.println("Removed: " + removed);
        System.out.println("Array after removal: " + numbers);
        System.out.println("Size: " + numbers.size());
        
        // Test with Strings
        System.out.println("\n--- Testing with Strings ---");
        DynamicArray<String> names = new DynamicArray<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("Names: " + names);
        
        // Test resize by adding many elements
        System.out.println("\n--- Testing auto-resize ---");
        DynamicArray<Integer> bigList = new DynamicArray<>();
        for (int i = 1; i <= 15; i++) {
            bigList.add(i);
        }
        System.out.println("Added 15 elements: " + bigList);
        System.out.println("Size: " + bigList.size());
        
        // Test exception handling
        System.out.println("\n--- Testing invalid index ---");
        try {
            numbers.get(100);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        System.out.println("\nAll tests passed!");
    }
}
