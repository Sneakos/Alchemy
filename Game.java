
/**
 * Write a description of class sajkdlfjhlkds here.
 * 
 * @author Alex
 * @version December 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Game extends JFrame implements ActionListener
{
    private static final int WIDTH = 200, HEIGHT = 300;
    private JButton[] buttons = new JButton[8];
    private final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
    private int[][] board;
    Object[] options = {"Rune Long", "Emerald", "Addy Helm", "Addy Shield", "Boots"};
    public Game()
    {
        createWindow();
        board = new int[2][2];
    }

    private void createWindow() 
    {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(gameSize);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(4, 2));
        addButtons();

        setPreferredSize(gameSize);
        setMinimumSize(gameSize);
        setMaximumSize(gameSize);

        pack();
    }

    private void addButtons()
    {
        int count = buttons.length - 1;
        for(int i = 0; i < buttons.length / 2; i++)
        {
            buttons[i] = new JButton();
            buttons[i + count] = new JButton();
            buttons[i].setFont(new Font(null, Font.PLAIN, 15));
            buttons[i + count].setFont(new Font(null, Font.PLAIN, 15));
            add(buttons[i]);
            add(buttons[i + count]);
            buttons[i].addActionListener(this);
            buttons[i + count].addActionListener(this);
            count -= 2;
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        int choice = JOptionPane.showOptionDialog(this, "Item", "Alchemy", 
                JOptionPane.DEFAULT_OPTION ,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        JButton button = (JButton) e.getSource();
        int loc = getIndex(button);
        int spots[] = fillList(loc, choice);
        String text = "";
        for(int i = 0; i < buttons.length; i++)
        {
            switch(spots[i])
            {
                case 0: text = "Sword"; break;
                case 1: text = "Emerald"; break;
                case 2: text = "Helm"; break;
                case 3: text = "Shield"; break;
                case 4: text = "Boots"; break;
                default: text = "Empty";
            }
            buttons[i].setText(text);
        }
        sendData(spots);
    }

    public int getIndex(JButton button)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            if(buttons[i] == button)
                return i;
        }
        return 0;
    }

    /* 
     * 0 = Rune Long  
     * 1 = Emerald
     * 2 = Addy Helm
     * 3 = Addy Shield
     * 4 = Boots
     */
    public int[] fillList(int start, int choice)
    {
        int[] spots = new int[buttons.length];
        int newStart = (start - choice);
        if(newStart < 0)
            newStart += buttons.length;
        for(int i = 0; i < buttons.length; i++)
        {
            spots[newStart%buttons.length] = i;
            newStart++;
        }
        return spots;
    }

    public void sendData(int[] spots)
    {
        try {
            String line = null;
            
            File file = new File("Data.txt");
            
            FileWriter fileWriter = 
                fileWriter = new FileWriter(file.getAbsoluteFile(), true);

            BufferedWriter bufferedWriter = 
                new BufferedWriter(fileWriter);
            String data = "";
            for(int i = 0; i< spots.length; i++)
            {
                data += spots[i];
            }
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            
            bufferedWriter.close();         
        }
        catch(IOException ex) {
            System.out.println("Error");
        }
    }
}
