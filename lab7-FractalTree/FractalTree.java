import javax.swing.*;
import java.awt.*;

public class FractalTree extends JPanel {

    private final int MAX_DEPTH = 9;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Start the recursion from the bottom center of the panel
        int startX = getWidth() / 2;
        int startY = getHeight() - 50;
        drawTree(g, startX, startY, -90, MAX_DEPTH);
    }

    /**
     * Recursively draws a fractal tree.
     * @param g      The graphics object to draw on.
     * @param x1     The starting x-coordinate of the branch.
     * @param y1     The starting y-coordinate of the branch.
     * @param angle  The angle of the branch in degrees.
     * @param depth  The current recursion depth.
     */
    private void drawTree(Graphics g, int x1, int y1, double angle, int depth) {

        // 1. Base Case — stop when depth hits 0
        if (depth == 0) {
            return;
        }

        // 2. Calculate branch length — gets shorter as depth decreases
        int length = depth * 10;

        // 3. Calculate the end point using trig
        int x2 = (int) (x1 + length * Math.cos(Math.toRadians(angle)));
        int y2 = (int) (y1 + length * Math.sin(Math.toRadians(angle)));

        // 4. Color — brown for trunk/thick branches, green for tips
        if (depth > 4) {
            g.setColor(new Color(101, 67, 33)); // brown
        } else {
            g.setColor(new Color(34, 139, 34)); // green
        }

        // 5. Thickness — thicker branches at higher depth
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(depth / 2.0f));

        // 6. Draw the branch
        g.drawLine(x1, y1, x2, y2);

        // 7. Recursive calls — left and right sub-branches
        drawTree(g, x2, y2, angle - 20, depth - 1); // left branch
        drawTree(g, x2, y2, angle + 20, depth - 1); // right branch
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Recursive Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.add(new FractalTree());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
