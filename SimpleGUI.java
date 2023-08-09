import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SimpleGUI {
    private JFrame frame;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea resultArea;

    public SimpleGUI() {
        frame = new JFrame("Detector Plagio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel label1 = new JLabel("Archivo 1:");
        textField1 = new JTextField(20);
        JButton browseButton1 = new JButton("Examinar");
        browseButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textField1.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        JLabel label2 = new JLabel("Archivo 2:");
        textField2 = new JTextField(20);
        JButton browseButton2 = new JButton("Examinar");
        browseButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textField2.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        JButton compareButton = new JButton("Comparar");
        compareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path1 = textField1.getText();
                String path2 = textField2.getText();
                
                String result;
                try {
                    result = plagio.DetectorDePlagio(path1, path2);
                } catch (IOException e1) {
                    
                    result="ERROR";
                }
                resultArea.setText(result);
                resultArea.setForeground(Color.BLACK);
            }
        });

        resultArea = new JTextArea(5, 20);
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);
        resultArea.setForeground(Color.RED);

        panel.add(label1);
        panel.add(textField1);
        panel.add(browseButton1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(browseButton2);
        panel.add(compareButton);

        frame.add(panel);
        frame.add(resultArea, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleGUI();
            }
        });
    }
}
