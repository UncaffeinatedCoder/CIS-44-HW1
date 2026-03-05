/**
 * SyntaxChecker - Uses a stack to verify that bracket symbols are balanced.
 * Checks for matching pairs of: (), {}, []
 */
public class SyntaxChecker {

    /**
     * Uses a stack to check if a line of code has balanced symbols.
     * @param line The string of code to check.
     * @return true if symbols are balanced, false otherwise.
     */
    public static boolean isBalanced(String line) {
        // Step 1: Create an empty stack to hold opening symbols
        Stack<Character> buffer = new ArrayStack<>(line.length());

        // Step 2: Iterate through each character of the input string
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            // Step 3: If opening symbol, push onto stack
            if (c == '(' || c == '{' || c == '[') {
                buffer.push(c);
            }
            // Step 4: If closing symbol, check for match
            else if (c == ')' || c == '}' || c == ']') {
                // Check if stack is empty (no matching opening symbol)
                if (buffer.isEmpty()) {
                    return false;
                }
                
                // Pop the top element
                char top = buffer.pop();
                
                // Check if popped symbol matches the current closing symbol
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
            // Ignore all other characters (not one of the six symbols)
        }
        
        // Step 5: After iteration, stack should be empty for balanced string
        // If not empty, there are unmatched opening symbols
        return buffer.isEmpty();
    }
    
    /**
     * Helper method to check if an opening and closing symbol are a matching pair.
     * @param open The opening symbol
     * @param close The closing symbol
     * @return true if they form a matching pair, false otherwise
     */
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("     SYNTAX CHECKER - BALANCED SYMBOLS");
        System.out.println("========================================\n");
        
        // Test cases from the assignment
        String line1 = "public static void main(String[] args) { ... }";
        String line2 = "int x = (5 + [a * 2]);";
        String line3 = "System.out.println('Hello');)";
        String line4 = "List list = new ArrayList<{String>();";
        String line5 = "if (x > 0) {";

        System.out.println("Test Case 1: " + line1);
        System.out.println("Expected: true");
        System.out.println("Result:   " + isBalanced(line1));
        System.out.println();

        System.out.println("Test Case 2: " + line2);
        System.out.println("Expected: true");
        System.out.println("Result:   " + isBalanced(line2));
        System.out.println();

        System.out.println("Test Case 3: " + line3);
        System.out.println("Expected: false (extra closing parenthesis)");
        System.out.println("Result:   " + isBalanced(line3));
        System.out.println();

        System.out.println("Test Case 4: " + line4);
        System.out.println("Expected: false (mismatched symbols)");
        System.out.println("Result:   " + isBalanced(line4));
        System.out.println();

        System.out.println("Test Case 5: " + line5);
        System.out.println("Expected: false (unmatched opening brace)");
        System.out.println("Result:   " + isBalanced(line5));
        System.out.println();

        System.out.println("========================================");
        System.out.println("         ADDITIONAL TEST CASES");
        System.out.println("========================================\n");

        String line6 = "((()))";
        System.out.println("Test Case 6: " + line6);
        System.out.println("Expected: true (nested parentheses)");
        System.out.println("Result:   " + isBalanced(line6));
        System.out.println();

        String line7 = "([{}])";
        System.out.println("Test Case 7: " + line7);
        System.out.println("Expected: true (properly nested mixed symbols)");
        System.out.println("Result:   " + isBalanced(line7));
        System.out.println();

        String line8 = "([)]";
        System.out.println("Test Case 8: " + line8);
        System.out.println("Expected: false (interleaved - not properly nested)");
        System.out.println("Result:   " + isBalanced(line8));
        System.out.println();

        String line9 = "";
        System.out.println("Test Case 9: (empty string)");
        System.out.println("Expected: true (no symbols to match)");
        System.out.println("Result:   " + isBalanced(line9));
        System.out.println();

        String line10 = "Hello World! No brackets here.";
        System.out.println("Test Case 10: " + line10);
        System.out.println("Expected: true (no bracket symbols)");
        System.out.println("Result:   " + isBalanced(line10));
        System.out.println();

        System.out.println("========================================");
        System.out.println("         ALL TESTS COMPLETED!");
        System.out.println("========================================");
    }
}
