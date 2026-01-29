import java.util.Random;
import java.util.Arrays;

public class DotProduct {
    
    public static void main(String[] args) {
        int n = 5; 
        
       
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        
        
        Random random = new Random();
        
        
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(10) + 1; // Random int between 1 and 10
            b[i] = random.nextInt(10) + 1;
        }
        
        
        for (int i = 0; i < n; i++) {
            c[i] = a[i] * b[i];
        }
        
      
        System.out.println("=== Dot Product of Two Arrays ===");
        System.out.println();
        System.out.println("Array a: " + Arrays.toString(a));
        System.out.println("Array b: " + Arrays.toString(b));
        System.out.println();
        System.out.println("Element-wise product c[i] = a[i] * b[i]:");
        System.out.println("Array c: " + Arrays.toString(c));
        
        
        System.out.println();
        System.out.println("Detailed computation:");
        for (int i = 0; i < n; i++) {
            System.out.println("c[" + i + "] = " + a[i] + " * " + b[i] + " = " + c[i]);
        }
    }
}
