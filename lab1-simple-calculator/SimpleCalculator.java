import java.util.Scanner;


public class SimpleCalculator {
    
    private double displayValue;
    
    private String pendingOperator;
    
    private boolean startNewCalculation;
    
  
    public SimpleCalculator() {
        clear();
    }
    
  
    public void clear() {
        displayValue = 0;
        pendingOperator = null;
        startNewCalculation = true;
    }
    
  
    public double getDisplayValue() {
        return displayValue;
    }
    
  
    public void inputNumber(double number) {
        if (startNewCalculation) {
            
            displayValue = number;
            startNewCalculation = false;
        } else if (pendingOperator != null) {
            
            displayValue = calculate(displayValue, pendingOperator, number);
            pendingOperator = null;
        } else {
           
            displayValue = number;
        }
    }
    
  
    public void inputOperator(String operator) {
        if (operator.equals("=")) {
            
            pendingOperator = null;
        } else {
           
            pendingOperator = operator;
        }
    }
    

    private double calculate(double a, String op, double b) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    System.out.println("Error: Division by zero!");
                    return a;
                }
            default:
                return b;
        }
    }
    

    public static boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || 
               input.equals("*") || input.equals("/") || input.equals("=");
    }
    
 
    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
  
    public String formatDisplay() {
        // Show as integer if it's a whole number
        if (displayValue == Math.floor(displayValue) && !Double.isInfinite(displayValue)) {
            return String.valueOf((long) displayValue);
        }
        return String.valueOf(displayValue);
    }
    
  
    public static void main(String[] args) {
        SimpleCalculator calc = new SimpleCalculator();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Simple Calculator ===");
        System.out.println("Enter numbers and operators on separate lines.");
        System.out.println("Operators: +, -, *, /, =");
        System.out.println("Type 'clear' to reset, 'exit' to quit.");
        System.out.println();
        
        
        System.out.println("Display: " + calc.formatDisplay());
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Calculator closed. Goodbye!");
                break;
            }
            
            
            if (input.equalsIgnoreCase("clear")) {
                calc.clear();
                System.out.println("Display: " + calc.formatDisplay());
                continue;
            }
            
            
            if (isNumber(input)) {
                double number = Double.parseDouble(input);
                calc.inputNumber(number);
                System.out.println("Display: " + calc.formatDisplay());
            } else if (isOperator(input)) {
                calc.inputOperator(input);
                System.out.println("Display: " + calc.formatDisplay());
            } else {
                System.out.println("Invalid input. Enter a number or operator (+, -, *, /, =)");
            }
        }
        
        scanner.close();
    }
}
