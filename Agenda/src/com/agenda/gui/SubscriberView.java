/*
 * View window for Add subscribers.
 */
package com.agenda.gui;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Elena Ionita
 */
public class SubscriberView {
      public JTextField firstNameField = new JTextField(20);
      public JTextField lastNameField = new JTextField(20);
      public JTextField cnpField = new JTextField(13);
      public JTextField phoneField = new JTextField(10);
      private JPanel addSubscriberPanel = new JPanel();
      
      public SubscriberView(){
        addSubscriberPanel.add(new JLabel("First name: "));
        addSubscriberPanel.add(firstNameField);
        addSubscriberPanel.add(new JLabel("Last name: "));
        addSubscriberPanel.add(lastNameField);
        addSubscriberPanel.add(new JLabel("CNP: "));
        addSubscriberPanel.add(cnpField);
        addSubscriberPanel.add(Box.createHorizontalStrut(15));
        addSubscriberPanel.add(new JLabel("Mobile No.: "));
        addSubscriberPanel.add(phoneField);
      }

    public JPanel getAddSubscriberPanel() {
        return addSubscriberPanel;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getCnpField() {
        return cnpField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }
    
}
