package juliensjukebox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Math extends JFrame implements Runnable {
    
    // Components
    JLabel clock = new JLabel(), task = new JLabel();
    JButton start = new JButton("Start");
    JTextField input = new JTextField(14);
    JRadioButton[] difficulty;
    
    Playlist pl = new Playlist("mathlist.txt"); // Playlist of math menu
    
    Thread thread = new Thread(this);
    Random r = new Random();
    
    int secondsLeft = 72, correct = 0, tries = 0, ersteZahl, zweiteZahl; // TODO: change difficulty
    char calc;
    final char[] CALCTYPES = {'a','s','m','d'};
    
    public Math() {
        
        // setBackground
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("resources/blackboard.png"))));
        
        // General settings
        setSize(700,500);
        setTitle("Math");
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
        
        // Center
        clock.setAlignmentX(Component.CENTER_ALIGNMENT);
        task.setAlignmentX(Component.CENTER_ALIGNMENT);
        input.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Set font
        task.setFont(new Font("Arial",Font.BOLD,96));
        
        // Push it to the center (vertically)
        Box box = Box.createVerticalBox();
        add(box.createVerticalStrut(getHeight()/5));
        
        // Change Size&Position of textfield
        input.setMaximumSize(new Dimension(getWidth()/2,80));
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setFont(new Font(null, Font.PLAIN,48));
        
        // Put an eventhandler on the button
        input.addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    evaluieren(Integer.parseInt(input.getText()));
                    changeText();
                }
            }
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
        });
        
        // WindowListener (to go back to main menu)
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new JuliensJukeBox();
                e.getWindow().dispose();
            }
        });
        
        // Start ButtonProperties
        start.setMargin(new Insets(10,50,10,50));
        start.setMaximumSize(new Dimension(200, 50));
        start.setFont(new Font("Tahoma",Font.BOLD,16));
        start.setForeground(new Color(255,255,255));
        start.setBackground(new Color(0,0,0));
        start.setBorder(BorderFactory.createEmptyBorder());
        
        // Start Button
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.setVisible(false);
                add(task);
                add(clock);
                add(input);
                thread.start();
            }
        });
        add(start);
        
        // Radio Buttons
        ButtonGroup group = new ButtonGroup();
        difficulty = new JRadioButton[5]; // Easy, medium, hard, mathter, impossibro
        
        difficulty[0] = new JRadioButton("Easy");
        difficulty[1] = new JRadioButton("Medium");
        difficulty[2] = new JRadioButton("Hard");
        difficulty[3] = new JRadioButton("Mathter");
        difficulty[4] = new JRadioButton("Impossibru");
        
        difficulty[0].setActionCommand("Easy");
        difficulty[1].setActionCommand("Medium");
        difficulty[2].setActionCommand("Hard");
        difficulty[3].setActionCommand("Mathter");
        difficulty[4].setActionCommand("Impossibru");
        
        JPanel radioPanel = new JPanel(new GridLayout(1,0));
        radioPanel.setOpaque(false);
        
        // Add actionlistener to buttons, add buttons to group, add group to panel
        for(int i = 0; i < difficulty.length; i++) {
            difficulty[i].setOpaque(false);
            difficulty[i].addActionListener(null);
            group.add(difficulty[i]);
            radioPanel.add(difficulty[i]);
        }
        radioPanel.setMaximumSize(new Dimension(400,20));
        add(radioPanel);
        // Refresh
        setSize(0,0);
        setSize(700,500);
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    public void run() {
        
        // Put an exercise on screen
        changeText();
        
        // One could say.. it is..
        showTime();
    }
    
    // Set the clock to the time remaining
    public void showTime() {
        while(true) {
            
            // Set the clock to current seconds Left
            clock.setText(formatTime(secondsLeft));
            
            // Pause
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) { }
        
            // When time is up, no possibility of entering an input
            if(secondsLeft != 0) {
                secondsLeft--;
            }else {
                input.setEditable(false);
                this.add(new JLabel("HALLLO")); //TODO: results
                break;
            }
        }
    }
    
    // Print new exercise to the textfield
    public void changeText() {
        
        // Determine calculation type
        calc = CALCTYPES[r.nextInt(CALCTYPES.length)];
        
        // Print accordingly
        switch(calc) {
            // Addition
            case 'a': 
                do {
                    ersteZahl = r.nextInt(1001);
                    zweiteZahl = r.nextInt(1001);
                }while(ersteZahl+zweiteZahl > 10000); // TODO: Set difficulty
                task.setText(ersteZahl + " + " + zweiteZahl);
                break;
            // Subtraction
            case 's':
                do {
                    ersteZahl = r.nextInt(1001);
                    zweiteZahl = r.nextInt(1001);
                }while(ersteZahl+zweiteZahl > 10000); // TODO: Set difficulty
                task.setText(ersteZahl + " - " + zweiteZahl);
                break;
            
            // Multiplication
            case 'm':
                do {
                    ersteZahl = r.nextInt(101);
                    zweiteZahl = r.nextInt(101);
                }while(ersteZahl+zweiteZahl > 100000); // TODO: Set difficulty
                task.setText(ersteZahl + " * " + zweiteZahl);
                break;
            
            // Division
            case 'd':
                do {
                    ersteZahl = r.nextInt(101);
                    zweiteZahl = 1 + r.nextInt(100);
                }while(ersteZahl % zweiteZahl != 0); // TODO: Set difficulty
                task.setText(ersteZahl + " / " + zweiteZahl);
                break;
            
            // Not sure
            default:
                System.err.println("What are you doing?!");
                break;
        }
    }
    
    // Change the time to a proper format
    private String formatTime(int seconds) {
        String m,s;
        int minutes;
        
        // Change seconds to minutes+seconds
        if(seconds / 60.0 > 1) {
            minutes = (int)(seconds/60.0);
            seconds -= minutes*60;
        }else{
            minutes = 0;
        }
        
        // Print time in xx:xx format
        if(minutes < 10)
            m = "0" + String.valueOf(minutes);
        else
            m = String.valueOf(minutes);
        
        if(seconds < 10)
            s = "0" + String.valueOf(seconds);
        else
            s = String.valueOf(seconds);
        
        return m + ":" + s;
    
    }

    // verify whether input is the right solution
    public void evaluieren(int input) {
        
        switch(calc) {
            // Addition
            case 'a':
                if((ersteZahl + zweiteZahl) == input) {
                    correct++;
                    System.out.println("Correct!"); // TODO: Print 'correct' statement on screen
                }
                break;
            
            // Subtraction
            case 's':
                if((ersteZahl - zweiteZahl) == input) {
                    correct++;
                    System.out.println("Correct!");
                }
                break;
            
            // Multiplication
            case 'm':
                if((ersteZahl * zweiteZahl) == input) {
                    correct++;
                    System.out.println("Correct!");
                }
                break;
            
            // Division
            case 'd':
                if((ersteZahl / zweiteZahl) == input) {
                    correct++;
                    System.out.println("Correct!");
                }
                break;
        }
        
        tries++;
        this.input.setText("");
    }
}
