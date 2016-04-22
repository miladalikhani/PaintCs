import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Config.*;
import panel.KeyPanel;

public class Main extends JFrame {
    public Surface surface;
    public KeyPanel keyPanel;
    public Components shapes;
    public Container Pane;

    public Main() {
        Pane = getContentPane();
        Pane.setLayout(new BoxLayout(Pane, BoxLayout.Y_AXIS));
        shapes = new Components();
        surface = new Surface();
        surface.setPreferredSize(new Dimension(0, 650));
        surface.setAlignmentY(Box.CENTER_ALIGNMENT);
        surface.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        surface.setBackground(Color.WHITE);
        keyPanel = new KeyPanel();
        keyPanel.setPreferredSize(new Dimension(0, 50));
        keyPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        keyPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        keyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Pane.add(new JPanel());
        Pane.add(surface);
        Pane.add(keyPanel);

        setTitle("PaintCS");
        setSize(1300, 700);

        setLocationRelativeTo(null);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (!surface.saved) {
                    int answer = JOptionPane.showConfirmDialog(null, "File does not saved. Save now?", "alert", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (answer) {
                        case 0:
                            surface.save();
                            break;
                        case 1:
                            e.getWindow().dispose();
                            break;
                        case 2:
                            break;
                    }
                }
                else
                    e.getWindow().dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Timer timer = new Timer(100, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.surface.buttonCode = main.keyPanel.buttonCode;
                main.surface.borderColor = main.keyPanel.borderColor;
                main.surface.fillColor = main.keyPanel.fillColor;
                main.surface.polygon_n = main.keyPanel.polygon_n;
                main.surface.requestFocus();
            }
        });
        timer.start();
        main.setVisible(true);
    }
}
