/* User Log In
 */
package com.agenda.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
/**
 *
 * @author Elena Ionita
 */
public final class LogIn extends JPanel implements ActionListener{
    
    private static final String OK = "ok";
    private static final String HELP = "help";
 
    private final JFrame controllingFrame; 
    private final JPasswordField passwordField;
 
    @SuppressWarnings("LeakingThisInConstructor")
    public LogIn (JFrame f) {
        
        controllingFrame = f;
 
        
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);
 
        JLabel label = new JLabel("Enter the password: ");
        label.setLabelFor(passwordField);
 
        JComponent buttonPane = createButtonPanel();
 
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(passwordField);
 
        Component add = add(textPane);
        add(buttonPane);
    }
 
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");
        JButton helpButton = new JButton("Help");
 
        okButton.setActionCommand(OK);
        helpButton.setActionCommand(HELP);
        okButton.addActionListener(this);
        helpButton.addActionListener(this);
 
        p.add(okButton);
        p.add(helpButton);
 
        return p;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { 
            char[] input = passwordField.getPassword();
            if (isPasswordCorrect(input)) {
                JOptionPane.showMessageDialog(controllingFrame,
                    "You have successfully logged in.");
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Invalid password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
 
            Arrays.fill(input, '0');
 
            passwordField.selectAll();
            resetFocus();
        } else { 
            JOptionPane.showMessageDialog(controllingFrame,
                "You can get the password in the About section.");
            
        }
    }
 
    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 't', 'a', 'r', 'g', 'e', 't', 's' };
 
        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }
 
        Arrays.fill(correctPassword,'0');
 
        return isCorrect;
    }
 
    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }
 
    /**
     * Create the GUI and show it.  
     */
    private static void createAndShowGUI() {
        
        JFrame frame = new JFrame("Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
        final LogIn newContentPane = new LogIn(frame);
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
 

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
    
        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }
}
   
    
