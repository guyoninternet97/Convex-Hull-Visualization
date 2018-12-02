package hullVisual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    private JFrame window;
    private PointBoard board;
    private boolean shouldDraw = false;

    public static void main(String[] args) {
        new Frame();
    }

    private Frame() {
        setLayout(null);
        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);


        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("Convex Hull Visualizer");
        window.getContentPane().setPreferredSize(new Dimension(700, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        board = new PointBoard();

        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Click to add point. Press s to draw convex hull", 10, 15);
        g.drawString("Z to remove most recently added point, right click to remove a specific point.", 10, 35);

        Timer timer = new Timer(50, this);
        timer.setDelay(50);
        timer.start();
        board.paintPoints(g);

        if (shouldDraw) {
            board.shouldDrawLines = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        board.clearHullPoints();
        board.shouldDrawLines = false;
        shouldDraw = false;
        char key = e.getKeyChar();
        //This is disgusting
        key = Character.toUpperCase(key);
        if (key == KeyEvent.VK_Q) {
            System.exit(0);
        } else if (key == KeyEvent.VK_Z) {
            board.removeLastPoint();
        } else if (key == KeyEvent.VK_S) {
            ConvexHullFinder.getInstance().givePointBoard(board);
            ArrayList<Point> convexHull = ConvexHullFinder.getInstance().solve(board);
            shouldDraw = true;
            board.shouldDrawLines = true;
            for (Point point : convexHull) {
                board.setHullPoint(point);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Point newPoint = new Point(e.getX(), e.getY());
            board.addPoint(newPoint);
        }

        board.clearHullPoints();
        board.shouldDrawLines = false;
        shouldDraw = false;

        if (SwingUtilities.isRightMouseButton(e)) {
            board.removePoint(e.getX(), e.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
