import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingVisualizer extends JPanel {
    private static final int SCREEN_WIDTH = 910;
    private static final int SCREEN_HEIGHT = 750;
    private static final int ARR_SIZE = 130;
    private static final int RECT_SIZE = 7;
    private int[] arr = new int[ARR_SIZE];
    private boolean complete = false;

    public SortingVisualizer() {
        randomizeArray();
    }

    public void randomizeArray() {
        Random rand = new Random();
        for (int i = 0; i < ARR_SIZE; i++) {
            arr[i] = rand.nextInt(SCREEN_HEIGHT);
        }
        repaint();
    }

    public void visualize(int x, int y, int z) {
        repaint();
        try {
            Thread.sleep(40);  // Delay for visualization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < ARR_SIZE; i++) {
            g2d.setColor(Color.GRAY);
            g2d.fillRect(i * RECT_SIZE, SCREEN_HEIGHT - arr[i], RECT_SIZE, arr[i]);
        }
    }

    public void bubbleSort() {
        for (int i = 0; i < ARR_SIZE - 1; i++) {
            for (int j = 0; j < ARR_SIZE - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    visualize(j + 1, j, ARR_SIZE - i);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        SortingVisualizer panel = new SortingVisualizer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.add(panel);
        frame.setVisible(true);

        // Simple control logic
        String[] options = {"Bubble Sort", "Randomize Array", "Exit"};
        while (true) {
            String input = (String) JOptionPane.showInputDialog(frame, "Select Sorting Algorithm", "Controls", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (input == null || input.equals("Exit")) {
                break;
            }
            switch (input) {
                case "Bubble Sort":
                    panel.bubbleSort();
                    break;
                case "Randomize Array":
                    panel.randomizeArray();
                    break;
            }
        }
    }
}
