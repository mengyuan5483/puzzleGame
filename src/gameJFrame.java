import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameJFrame extends JFrame implements KeyListener {
    int[][] data = new int[4][4];
    // the location of blank picture
    int x = 0;
    int y = 0;

    int[][] win = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 },
    };

    public gameJFrame() {
        initJFrame();

        // create a Two-dimensional arrays

        initData();

        initImage();

        setVisible(true);

    }

    private void initData() {
        int[] tempArr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);

            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;

        }

        // put new results to the array
        for (int i = 0; i < tempArr.length; i++) {
            // record the location of blank picture
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;

            } else {

                data[i / 4][i % 4] = tempArr[i];
            }

        }

    }

    private void initImage() {
        this.getContentPane().removeAll();
        if (victory()) {
            JLabel winJLabel = new JLabel(
                    new ImageIcon("C:\\Users\\Wei\\Desktop\\java\\puzzleGame\\puzzleGame\\images\\victory.jpg"));
            winJLabel.setBounds(203, 283, 100, 100);
            this.getContentPane().add(winJLabel);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                // creat Jlabel object
                JLabel label = new JLabel(new ImageIcon(
                        "C:\\Users\\Wei\\Desktop\\java\\puzzleGame\\puzzleGame\\images\\" + num + ".png"));
                label.setBounds(105 * j + 83, 105 * i + 120, 105, 105);
                label.setBorder(new BevelBorder(0));
                // add JLabel to Jframe
                getContentPane().add(label);

            }
        }
        this.getContentPane().repaint();
    }

    public void initJFrame() {
        setSize(603, 680);
        setTitle("Puzzle Game");
        setAlwaysOnTop(true);
        // exit on close
        setDefaultCloseOperation(3);
        setLayout(null);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;

        }
        // left:37,right:38,up:39,down:40

        int code = e.getKeyCode();
        // x,y blank picture
        if (code == 37) {
            if (y == 3) {
                return;
            }

            // exchange the location
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            // y is changed
            y++;
            // load the picture again
            initImage();

        } else if (code == 38) {
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;

            initImage();
        } else if (code == 39) {
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;

            initImage();
        } else if (code == 40) {
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;

            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
