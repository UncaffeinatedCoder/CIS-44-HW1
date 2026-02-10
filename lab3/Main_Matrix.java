/**
 * Main.java
 * Demonstrates all Matrix operations including addition, multiplication,
 * and exception handling for invalid operations.
 * 
 * @author [Your Name]
 * @date [Current Date]
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("              MATRIX OPERATIONS DEMONSTRATION               ");
        System.out.println("============================================================");
        
        // ============================================================
        // Test 1: Creating Matrices
        // ============================================================
        System.out.println("\n========== TEST 1: Creating Matrices ==========\n");
        
        // Create matrix with dimensions
        System.out.println("Creating 3x3 matrix with dimensions constructor:");
        Matrix m1 = new Matrix(3, 3);
        System.out.println("Empty 3x3 matrix:");
        System.out.println(m1);
        
        // Create matrix from 2D array
        System.out.println("\nCreating matrix from 2D array:");
        int[][] arrayData = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        Matrix m2 = new Matrix(arrayData);
        System.out.println("Matrix from array:");
        System.out.println(m2);
        
        // ============================================================
        // Test 2: Populate with Random Values
        // ============================================================
        System.out.println("\n========== TEST 2: Populate Random ==========\n");
        
        Matrix randomMatrix = new Matrix(3, 4);
        System.out.println("3x4 matrix before populateRandom():");
        System.out.println(randomMatrix);
        
        randomMatrix.populateRandom();
        System.out.println("\n3x4 matrix after populateRandom() (values 1-10):");
        System.out.println(randomMatrix);
        
        // ============================================================
        // Test 3: Matrix Addition
        // ============================================================
        System.out.println("\n========== TEST 3: Matrix Addition ==========\n");
        
        int[][] dataA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] dataB = {
            {7, 8, 9},
            {10, 11, 12}
        };
        
        Matrix matrixA = new Matrix(dataA);
        Matrix matrixB = new Matrix(dataB);
        
        System.out.println("Matrix A (" + matrixA.getDimensions() + "):");
        System.out.println(matrixA);
        
        System.out.println("\nMatrix B (" + matrixB.getDimensions() + "):");
        System.out.println(matrixB);
        
        Matrix sum = matrixA.add(matrixB);
        System.out.println("\nA + B = (" + sum.getDimensions() + "):");
        System.out.println(sum);
        
        // Verify calculation
        System.out.println("\nVerification: A[0][0] + B[0][0] = " + 
                           dataA[0][0] + " + " + dataB[0][0] + " = " + sum.get(0, 0));
        
        // ============================================================
        // Test 4: Matrix Multiplication
        // ============================================================
        System.out.println("\n========== TEST 4: Matrix Multiplication ==========\n");
        
        int[][] dataC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };  // 3x2 matrix
        
        int[][] dataD = {
            {7, 8, 9},
            {10, 11, 12}
        };  // 2x3 matrix
        
        Matrix matrixC = new Matrix(dataC);
        Matrix matrixD = new Matrix(dataD);
        
        System.out.println("Matrix C (" + matrixC.getDimensions() + "):");
        System.out.println(matrixC);
        
        System.out.println("\nMatrix D (" + matrixD.getDimensions() + "):");
        System.out.println(matrixD);
        
        Matrix product = matrixC.multiply(matrixD);
        System.out.println("\nC x D = (" + product.getDimensions() + "):");
        System.out.println(product);
        
        // Verify calculation for [0][0]
        // C[0][0]*D[0][0] + C[0][1]*D[1][0] = 1*7 + 2*10 = 7 + 20 = 27
        System.out.println("\nVerification: C[0][0]*D[0][0] + C[0][1]*D[1][0]");
        System.out.println("            = " + dataC[0][0] + "*" + dataD[0][0] + 
                           " + " + dataC[0][1] + "*" + dataD[1][0]);
        System.out.println("            = " + (dataC[0][0]*dataD[0][0]) + 
                           " + " + (dataC[0][1]*dataD[1][0]));
        System.out.println("            = " + product.get(0, 0));
        
        // ============================================================
        // Test 5: Exception Handling - Invalid Addition
        // ============================================================
        System.out.println("\n========== TEST 5: Exception - Invalid Addition ==========\n");
        
        Matrix small = new Matrix(2, 2);
        small.populateRandom();
        Matrix large = new Matrix(3, 3);
        large.populateRandom();
        
        System.out.println("Matrix Small (" + small.getDimensions() + "):");
        System.out.println(small);
        
        System.out.println("\nMatrix Large (" + large.getDimensions() + "):");
        System.out.println(large);
        
        System.out.println("\nAttempting to add 2x2 matrix to 3x3 matrix...");
        try {
            Matrix invalidSum = small.add(large);
            System.out.println("Result: " + invalidSum); // Won't reach here
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // ============================================================
        // Test 6: Exception Handling - Invalid Multiplication
        // ============================================================
        System.out.println("\n========== TEST 6: Exception - Invalid Multiplication ==========\n");
        
        Matrix mat2x3 = new Matrix(2, 3);
        mat2x3.populateRandom();
        Matrix mat2x2 = new Matrix(2, 2);
        mat2x2.populateRandom();
        
        System.out.println("Matrix E (" + mat2x3.getDimensions() + "):");
        System.out.println(mat2x3);
        
        System.out.println("\nMatrix F (" + mat2x2.getDimensions() + "):");
        System.out.println(mat2x2);
        
        System.out.println("\nAttempting E (2x3) * F (2x2)...");
        System.out.println("This requires E.cols (3) == F.rows (2), which is FALSE");
        
        try {
            Matrix invalidProduct = mat2x3.multiply(mat2x2);
            System.out.println("Result: " + invalidProduct); // Won't reach here
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught!");
            System.out.println("Error: " + e.getMessage());
        }
        
        // ============================================================
        // Test 7: Chain Operations
        // ============================================================
        System.out.println("\n========== TEST 7: Chain Operations ==========\n");
        
        int[][] data1 = {{1, 2}, {3, 4}};
        int[][] data2 = {{5, 6}, {7, 8}};
        int[][] data3 = {{1, 0}, {0, 1}}; // Identity matrix
        
        Matrix m_a = new Matrix(data1);
        Matrix m_b = new Matrix(data2);
        Matrix identity = new Matrix(data3);
        
        System.out.println("Matrix A:");
        System.out.println(m_a);
        
        System.out.println("\nMatrix B:");
        System.out.println(m_b);
        
        System.out.println("\nIdentity Matrix I:");
        System.out.println(identity);
        
        // (A + B) * I should equal A + B
        Matrix sumAB = m_a.add(m_b);
        Matrix result = sumAB.multiply(identity);
        
        System.out.println("\n(A + B):");
        System.out.println(sumAB);
        
        System.out.println("\n(A + B) * I:");
        System.out.println(result);
        
        System.out.println("\nNote: Multiplying by identity matrix gives same result!");
        
        // ============================================================
        // Test 8: Larger Random Matrices
        // ============================================================
        System.out.println("\n========== TEST 8: Larger Random Matrices ==========\n");
        
        Matrix big1 = new Matrix(4, 3);
        Matrix big2 = new Matrix(3, 5);
        big1.populateRandom();
        big2.populateRandom();
        
        System.out.println("Random 4x3 Matrix:");
        System.out.println(big1);
        
        System.out.println("\nRandom 3x5 Matrix:");
        System.out.println(big2);
        
        Matrix bigProduct = big1.multiply(big2);
        System.out.println("\n4x3 * 3x5 = 4x5 Result:");
        System.out.println(bigProduct);
        
        // ============================================================
        // Summary
        // ============================================================
        System.out.println("\n============================================================");
        System.out.println("           ALL TESTS COMPLETED SUCCESSFULLY!                ");
        System.out.println("============================================================");
        System.out.println("\nMatrix class implements:");
        System.out.println("  ✓ Matrix(rows, cols)     - Create empty matrix");
        System.out.println("  ✓ Matrix(int[][])        - Create from 2D array");
        System.out.println("  ✓ populateRandom()       - Fill with random 1-10");
        System.out.println("  ✓ add(Matrix)            - Matrix addition");
        System.out.println("  ✓ multiply(Matrix)       - Matrix multiplication");
        System.out.println("  ✓ toString()             - Formatted output");
        System.out.println("  ✓ Exception handling     - Invalid dimensions");
    }
}
