import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("  (inventory is empty)");
            return;
        }
        for (Item item : items) {
            System.out.println("  - " + item.getName());
        }
    }

    public void combineItems(String name1, String name2) {
        boolean found1 = false;
        boolean found2 = false;

        Iterator<Item> iter = items.iterator();
        while (iter.hasNext()) {
            Item current = iter.next();

            // Check for name1 first (and not already found, in case of duplicates)
            if (!found1 && current.getName().equals(name1)) {
                found1 = true;
                iter.remove();           // safe removal — no ConcurrentModificationException
            } else if (!found2 && current.getName().equals(name2)) {
                found2 = true;
                iter.remove();
            }
        }

        // Only add the combined item AFTER the loop exits
        // Adding inside the loop would throw ConcurrentModificationException
        if (found1 && found2) {
            String combinedName = name1 + " + " + name2 + " => Magic Staff";
            items.add(new Item("Magic Staff"));
            System.out.println("  Combined [" + name1 + "] and [" + name2 + "] into [Magic Staff]!");
        } else {
            System.out.println("  Could not combine: '"
                + (!found1 ? name1 : name2) + "' not found in inventory.");
        }
    }
}
