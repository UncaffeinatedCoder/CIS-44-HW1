import java.util.Random;

/**
 * Matrix.java
 * A class for matrix operations including addition and multiplication.
 * Demonstrates 2D arrays, exception handling, and mathematical operations.
 * 
 * @author [Your Name]
 * @date [Current Date]
 */
public class Matrix {
    
    // ============================================================
    // Instance Variables
    // ============================================================
    
    /** The 2D array storing matrix data */
    private int[][] data;
    
    /** Random number generator for populating matrix */
    private static Random random = new Random();
    
    // ============================================================
    // Constructors
    // ============================================================
    
    /**
     * Constructor that initializes an empty matrix with given dimensions.
     * All values are initialized to 0.
     * 
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IllegalArgumentException if rows or cols is non-positive
     */
    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException(
                "Matrix dimensions must be positive. Got: " + rows + "x" + cols
            );
        }
        this.data = new int[rows][cols];
    }
    
    /**
     * Constructor that initializes the matrix with a pre-existing 2D array.
     * Creates a deep copy of the input array.
     * 
     * @param data the 2D array to initialize from
     * @throws IllegalArgumentException if data is null or empty
     */
    public Matrix(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }
        
        // Deep copy the input array
        int rows = data.length;
        int cols = data[0].length;
        this.data = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }
    
    // ============================================================
    // Public Methods
    // ============================================================
    
    /**
     * Fills the matrix with random integer values between 1 and 10 (inclusive).
     */
    public void populateRandom() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = random.nextInt(10) + 1; // 1 to 10
            }
        }
    }
    
    /**
     * Adds this matrix to another matrix.
     * Both matrices must have the same dimensions.
     * 
     * @param other the matrix to add
     * @return a new Matrix that is the sum of this and other
     * @throws IllegalArgumentException if matrices have different dimensions
     */
    public Matrix add(Matrix other) {
        // Validate dimensions match
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            throw new IllegalArgumentException(
                "Matrix dimensions must match for addition. " +
                "This: " + this.getRows() + "x" + this.getCols() + 
                ", Other: " + other.getRows() + "x" + other.getCols()
            );
        }
        
        // Create result matrix
        Matrix result = new Matrix(this.getRows(), this.getCols());
        
        // Add corresponding elements
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        
        return result;
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * The number of columns in this matrix must equal the number of rows in the other.
     * 
     * Result dimensions: (this.rows) x (other.cols)
     * 
     * @param other the matrix to multiply by
     * @return a new Matrix that is the product of this and other
     * @throws IllegalArgumentException if dimensions are incompatible
     */
    public Matrix multiply(Matrix other) {
        // Validate: this.cols must equal other.rows
        if (this.getCols() != other.getRows()) {
            throw new IllegalArgumentException(
                "Matrix dimensions incompatible for multiplication. " +
                "This columns (" + this.getCols() + ") must equal Other rows (" + other.getRows() + "). " +
                "This: " + this.getRows() + "x" + this.getCols() + 
                ", Other: " + other.getRows() + "x" + other.getCols()
            );
        }
        
        // Result matrix has dimensions: this.rows x other.cols
        Matrix result = new Matrix(this.getRows(), other.getCols());
        
        // Matrix multiplication: C[i][j] = sum of A[i][k] * B[k][j] for all k
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                int sum = 0;
                for (int k = 0; k < this.getCols(); k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        
        return result;
    }
    
    /**
     * Returns the number of rows in the matrix.
     * @return number of rows
     */
    public int getRows() {
        return data.length;
    }
    
    /**
     * Returns the number of columns in the matrix.
     * @return number of columns
     */
    public int getCols() {
        return data[0].length;
    }
    
    /**
     * Gets the value at a specific position.
     * @param row the row index
     * @param col the column index
     * @return the value at [row][col]
     */
    public int get(int row, int col) {
        return data[row][col];
    }
    
    /**
     * Sets the value at a specific position.
     * @param row the row index
     * @param col the column index
     * @param value the value to set
     */
    public void set(int row, int col, int value) {
        data[row][col] = value;
    }
    
    /**
     * Returns a string representation of the matrix formatted in rows and columns.
     * @return formatted string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Find the maximum width needed for formatting
        int maxWidth = 1;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                int width = String.valueOf(data[i][j]).length();
                if (width > maxWidth) {
                    maxWidth = width;
                }
            }
        }
        
        // Build the string with proper formatting
        for (int i = 0; i < data.length; i++) {
            sb.append("[ ");
            for (int j = 0; j < data[0].length; j++) {
                // Right-align numbers with padding
                sb.append(String.format("%" + maxWidth + "d", data[i][j]));
                if (j < data[0].length - 1) {
                    sb.append("  ");
                }
            }
            sb.append(" ]");
            if (i < data.length - 1) {
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Returns dimension info as a string.
     * @return string showing rows x cols
     */
    public String getDimensions() {
        return getRows() + "x" + getCols();
    }
}
