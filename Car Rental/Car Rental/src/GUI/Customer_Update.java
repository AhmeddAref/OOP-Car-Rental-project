package GUI;

import BackendCode.Customer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class Customer_Update {

    JButton OK_Button, Cancel_Button;
    JLabel ID_Label, IDValidity_Label;
    JTextField ID_TextField;
    JFrame frame = new JFrame();
    static Customer customer; // this customer object is used in UpdateCustomer_Inner class to obtain the record for entered ID

    public Customer_Update() {
        frame.setTitle("Update Customer");
        frame.setLayout(new AbsoluteLayout());
        frame.setSize(new Dimension(450, 290));
        frame.setResizable(false);
        frame.setLocationRelativeTo(MainFrame.getMainFrame());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });

        OK_Button = new JButton("OK");
        Cancel_Button = new JButton("Cancel");

        ID_Label = new JLabel("Enter ID to be Updated");
        IDValidity_Label = new JLabel();
        ID_TextField = new JTextField();

        ID_TextField.setPreferredSize(new Dimension(240, 22));

        ID_Label.setPreferredSize(new Dimension(175, 22));
        IDValidity_Label.setPreferredSize(new Dimension(240, 9));
        IDValidity_Label.setForeground(Color.red);
        frame.add(ID_Label, new AbsoluteConstraints(10, 5));
        frame.add(ID_TextField, new AbsoluteConstraints(195, 5));
        frame.add(IDValidity_Label, new AbsoluteConstraints(195, 30));
        frame.add(OK_Button, new AbsoluteConstraints(100, 225, 100, 22));
        frame.add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

        OK_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer CO = new Customer();
                String ID = ID_TextField.getText().trim();
                if (!ID_TextField.getText().isEmpty()) {
                    if (Customer.isIDvalid(ID)) {
                        CO.setID(Integer.parseInt(ID));
                        customer = Customer.SearchByID(Integer.parseInt(ID)); // the ID of this object is used in UpdateManage_GUI_B class. that is why it is kept static
                        if (customer != null) {
                            MainFrame.getMainFrame().setEnabled(false);
                            frame.dispose();
                            new UpdateCustomer_Inner().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Required ID is not found !");
                        }
                    } else {
                        IDValidity_Label.setText("Invalid ID !");
                    }
                } else {
                    IDValidity_Label.setText("Enter ID !");
                }
            }
        });

        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });
    }

    public class UpdateCustomer_Inner extends JFrame {

        JButton Update_Button, Cancel_Button;
        JLabel SSN_Label, Name_Label, Contact_Label, Email_Label, UserName_Label, Password_Label, SSNValidity_Label, contactValidity_Label, NameValidity_Label, EmailValidity_Label, UserNameValidity_Label, PasswordValidity_Label;
        JTextField SSN_TextField, Name_TextField, Contact_TextField, Email_TextField, UserName_TextField, Password_TextField;

        public UpdateCustomer_Inner() {
            super("Update Customer");
            setLayout(new AbsoluteLayout());
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            setSize(new Dimension(450, 290));
            setResizable(false);
            setLocationRelativeTo(this);
            Update_Button = new JButton("Update");
            Cancel_Button = new JButton("Cancel");

            SSN_Label = new JLabel("Enter SSN ");
            Name_Label = new JLabel("Enter Name");
            Contact_Label = new JLabel("Enter Contact");
            SSNValidity_Label = new JLabel();
            NameValidity_Label = new JLabel();
            contactValidity_Label = new JLabel();
            SSN_TextField = new JTextField(customer.getSSN());
            Name_TextField = new JTextField(customer.getName());
            Contact_TextField = new JTextField(customer.getContact_No());

            SSN_TextField.setPreferredSize(new Dimension(240, 22));
            Name_TextField.setPreferredSize(new Dimension(240, 22));
            Contact_TextField.setPreferredSize(new Dimension(240, 22));

            SSN_Label.setPreferredSize(new Dimension(175, 22));
            Name_Label.setPreferredSize(new Dimension(175, 22));
            Contact_Label.setPreferredSize(new Dimension(175, 22));
            SSNValidity_Label.setPreferredSize(new Dimension(240, 9));
            contactValidity_Label.setPreferredSize(new Dimension(240, 9));
            NameValidity_Label.setPreferredSize(new Dimension(240, 9));

            SSNValidity_Label.setForeground(Color.red);
            contactValidity_Label.setForeground(Color.red);
            NameValidity_Label.setForeground(Color.red);

            add(SSN_Label, new AbsoluteConstraints(10, 5));
            add(SSN_TextField, new AbsoluteConstraints(195, 5));
            add(SSNValidity_Label, new AbsoluteConstraints(195, 30));
            add(Name_Label, new AbsoluteConstraints(10, 42));
            add(Name_TextField, new AbsoluteConstraints(195, 42));
            add(NameValidity_Label, new AbsoluteConstraints(195, 66));
            add(Contact_Label, new AbsoluteConstraints(10, 77));
            add(Contact_TextField, new AbsoluteConstraints(195, 77));
            add(contactValidity_Label, new AbsoluteConstraints(195, 102));
            add(Update_Button, new AbsoluteConstraints(100, 225, 100, 22));
            add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

            Update_Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String SSN = SSN_TextField.getText().trim();
                    String name = Name_TextField.getText().trim();
                    String contact = Contact_TextField.getText().trim();
                    if (!SSN.isEmpty()) {
                        System.out.println("SSN is not empty");
                        if (Customer.isSSNValid(SSN)) {
                            System.out.println("SSN is valid");
                            Customer CO = Customer.SearchBySSN(SSN);
                            if (CO != null) {
                                if (SSN.equals(customer.getSSN())) {
                                    System.out.println("no change in SSN");
                                } else {
                                    SSN = null;
                                    JOptionPane.showMessageDialog(null, "This SSN is already registered !");
                                }
                            } else { // when Customer.SearchSSN(M) returned null
                                System.out.println("new SSN is entered");
                            }
                        } else {
                            SSN = null;
                            SSNValidity_Label.setText("Invalid SSN !");
                        }
                    } else {
                        SSN = null;
                        SSNValidity_Label.setText("Enter SSN !");
                    }
                    if (!name.isEmpty()) {
                        if (Customer.isNameValid(name)) {
                            System.out.println("valid Customer name !");
                        } else {
                            name = null;
                            NameValidity_Label.setText("Invalid Name !");
                        }
                    } else {
                        name = null;
                        NameValidity_Label.setText("Enter Name !");
                    }
                    if (!contact.isEmpty()) {
                        if (Customer.isContactNoValid(contact)) {
                            System.out.println("Valid Customer contact !");
                        } else {
                            contact = null;
                            contactValidity_Label.setText("Invalid Contact Number!");
                        }
                    } else {
                        contact = null;
                        contactValidity_Label.setText("Enter Contact Number !");
                    }
                    System.out.println("the value of SSN before null condition is " + SSN);
                    if (SSN != null && name != null && contact != null) {
                        customer = new Customer(customer.getBill(), customer.getID(), SSN, name, contact);
                        System.out.println(customer.toString());
                        customer.Update();
                        MainFrame.getMainFrame().getContentPane().removeAll();
                        Customer_Details cd = new Customer_Details();
                        MainFrame.getMainFrame().add(cd.getMainPanel());
                        MainFrame.getMainFrame().getContentPane().revalidate();
                        JOptionPane.showMessageDialog(null, "Record Successfully Updated !");
                        MainFrame.getMainFrame().setEnabled(true);
                        dispose();
                    }
                }
            });

            Cancel_Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainFrame.getMainFrame().setEnabled(true);
                    dispose();
                }
            });
        }

    }

}
