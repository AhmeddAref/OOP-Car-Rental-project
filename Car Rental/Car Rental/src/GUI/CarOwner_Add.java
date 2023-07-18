package GUI;

import BackendCode.CarOwner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class CarOwner_Add {

    JButton Add_Button, Cancel_Button;
    JLabel SSN_Label, Name_Label, Contact_Label, Email_Label, UserName_Label, Password_Label, SSNValidity_Label, contactValidity_Label, NameValidity_Label, EmailValidity_Label, UserNameValidity_Label, PasswordValidity_Label;
    JTextField SSN_TextField, Name_TextField, Contact_TextField, Email_TextField, UserName_TextField, Password_TextField;
    JFrame frame = new JFrame();

    public CarOwner_Add() {
        frame.setTitle("Add CarOwner");
        frame.setLayout(new AbsoluteLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                MainFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });

        frame.setSize(new Dimension(450, 290));
        frame.setResizable(false);
        frame.setLocationRelativeTo(MainFrame.getMainFrame());

        Add_Button = new JButton("Add");
        Cancel_Button = new JButton("Cancel");

        SSN_Label = new JLabel("Enter SSN");
        Name_Label = new JLabel("Enter Name");
        Contact_Label = new JLabel("Enter Contact");
        Email_Label = new JLabel("Enter Email");
        UserName_Label = new JLabel("Enter Username");
        Password_Label = new JLabel("Enter Password");
        SSNValidity_Label = new JLabel();
        NameValidity_Label = new JLabel();
        EmailValidity_Label = new JLabel();
        UserNameValidity_Label = new JLabel();
        PasswordValidity_Label = new JLabel();
        contactValidity_Label = new JLabel();
        SSN_TextField = new JTextField();
        Name_TextField = new JTextField();
        Contact_TextField = new JTextField();
        Email_TextField = new JTextField();
        UserName_TextField = new JTextField();
        Password_TextField = new JTextField();

        SSN_TextField.setPreferredSize(new Dimension(240, 22));
        Name_TextField.setPreferredSize(new Dimension(240, 22));
        Contact_TextField.setPreferredSize(new Dimension(240, 22));
        Email_TextField.setPreferredSize(new Dimension(240, 22));
        UserName_TextField.setPreferredSize(new Dimension(240, 22));
        Password_TextField.setPreferredSize(new Dimension(240, 22));

        SSN_Label.setPreferredSize(new Dimension(175, 22));
        Name_Label.setPreferredSize(new Dimension(175, 22));
        Contact_Label.setPreferredSize(new Dimension(175, 22));
        Email_Label.setPreferredSize(new Dimension(175, 22));
        UserName_Label.setPreferredSize(new Dimension(175, 22));
        Password_Label.setPreferredSize(new Dimension(175, 22));
        SSNValidity_Label.setPreferredSize(new Dimension(240, 9));
        contactValidity_Label.setPreferredSize(new Dimension(240, 9));
        NameValidity_Label.setPreferredSize(new Dimension(240, 9));
        EmailValidity_Label.setPreferredSize(new Dimension(240, 9));
        UserNameValidity_Label.setPreferredSize(new Dimension(240, 9));
        PasswordValidity_Label.setPreferredSize(new Dimension(240, 9));

        SSNValidity_Label.setForeground(Color.red);
        contactValidity_Label.setForeground(Color.red);
        NameValidity_Label.setForeground(Color.red);
        EmailValidity_Label.setForeground(Color.red);
        UserNameValidity_Label.setForeground(Color.red);
        PasswordValidity_Label.setForeground(Color.red);

        frame.add(SSN_Label, new AbsoluteConstraints(10, 5));
        frame.add(SSN_TextField, new AbsoluteConstraints(195, 5));
        frame.add(SSNValidity_Label, new AbsoluteConstraints(195, 30));

        frame.add(Name_Label, new AbsoluteConstraints(10, 42));
        frame.add(Name_TextField, new AbsoluteConstraints(195, 42));
        frame.add(NameValidity_Label, new AbsoluteConstraints(195, 66));

        frame.add(Contact_Label, new AbsoluteConstraints(10, 77));
        frame.add(Contact_TextField, new AbsoluteConstraints(195, 77));
        frame.add(contactValidity_Label, new AbsoluteConstraints(195, 102));

        frame.add(Add_Button, new AbsoluteConstraints(100, 225, 100, 22));
        frame.add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

        Add_Button.addActionListener(new CarOwner_Add_ActionListener());

        Cancel_Button.addActionListener(new CarOwner_Add_ActionListener());
    }

    private class CarOwner_Add_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add": {
                    String SSN = SSN_TextField.getText().trim();
                    String name = Name_TextField.getText().trim();
                    String contact = Contact_TextField.getText().trim();

                    if (CarOwner.isSSNValid(SSN)) {
                        CarOwner carOwner = CarOwner.SearchBySSN(SSN);
                        if (carOwner == null) {
                            if (CarOwner.isNameValid(name)) {
                                if (CarOwner.isContactNoValid(contact)) {
                                    new CarOwner(0, 0, SSN, name, contact).Add(); // ID is Auto
                                    MainFrame.getMainFrame().getContentPane().removeAll();
                                    CarOwner_Details cd = new CarOwner_Details();
                                    MainFrame.getMainFrame().add(cd.getMainPanel());
                                    MainFrame.getMainFrame().getContentPane().revalidate();
                                    MainFrame.getMainFrame().setEnabled(true);
                                    JOptionPane.showMessageDialog(null, "Car Owner added successfully !");
                                    frame.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid contact no. !");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Name !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "This SSN is already registered !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid SSN");
                    }
                    break;
                }
                case "Cancel": {
                    MainFrame.getMainFrame().setEnabled(true);
                    frame.dispose();
                    break;
                }
            }
        }

    }
}
