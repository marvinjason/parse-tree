package parsetree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI
{
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField field;
    private JButton button;
    
    public GUI()
    {
        button = new JButton("Parse!");
        button.setBounds(200, 150, 100, 30);
        button.addActionListener((ActionEvent e) -> {
            String expression = field.getText();
            Parser parser = new Parser();
            Node node = parser.parse(expression);
            
            panel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    paint(238, 25, node, g);
                }
                
                private void paint(int x, int y, Node node, Graphics g)
                {
                    if (node.left != null)
                    {
                        g.drawLine(x + 13, y + 15, x - 37, y + 65);
                        paint(x - 50, y + 50, node.left, g);
                    }
                    
                    if (node.right != null)
                    {
                        g.drawLine(x + 13, y + 15, x + 63, y + 65);
                        paint(x + 50, y + 50, node.right, g);
                    }
                    
                    g.fillOval(x, y, 30, 30);
                    g.setColor(Color.WHITE);
                    g.drawString(parser.getValue(node.value) != null ? parser.getValue(node.value) : node.value, x + 12, y + 20);
                    g.setColor(Color.BLACK);
                }
            };
            
            panel.setPreferredSize(new Dimension(500, 500));
            frame.dispose();
            frame = new JFrame("Parse Tree - Sy, Marvin Jason L.");
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
        });
        
        field = new JTextField();
        field.setBounds(100, 100, 300, 25);
 
        label = new JLabel("Input an expression and we'll make a parse tree out of it!");
        label.setBounds(90, 50, 400, 25);
        
        panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(500, 225));
        panel.add(label);
        panel.add(field);
        panel.add(button);
        
        frame = new JFrame("Parse Tree - Sy, Marvin Jason L.");
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
    }
    
    public static void main(String[] args)
    {
        new GUI();
    }
}
