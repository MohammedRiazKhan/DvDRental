import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class AddCustomer {

    private JLabel name, surname, phone, credit, nameVal, surnameVal, phoneVal;
    private JTextField nameField, sNameField, phoneField, creditField;
    private JButton cancelBtn, addBtn;
    private JFrame addFrame = new JFrame();
    private JPanel panel = new JPanel();
    private ButtonListener btnL = new ButtonListener();

    private Customer customer;

    public AddCustomer(){

        //creation of components objects
        name = new JLabel("Name: ");
        surname = new JLabel("Surname: ");
        phone = new JLabel("Phone: ");
        credit = new JLabel("Credit (R):");

        nameVal = new JLabel();
        nameVal.setForeground(Color.red);

        surnameVal = new JLabel();
        surnameVal.setForeground(Color.red);

        phoneVal = new JLabel();
        phoneVal.setForeground(Color.red);

        nameField = new JTextField(15);
        sNameField = new JTextField(15);
        phoneField = new JTextField(15);
        creditField = new JTextField(15);
        creditField.setEditable(false);
        creditField.setText("100");

        addBtn = new JButton("Add");
        addBtn.addActionListener(btnL);

        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 5,0, 5);

        c.anchor = GridBagConstraints.LINE_END;

        panel.add(name, c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(nameField, c);


        c.gridy = 1;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(surname, c);

        c.gridy = 1;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(sNameField, c);


        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(phone, c);

        c.gridy = 2;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(phoneField, c);

        c.gridy = 3;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(credit, c);

        c.gridy = 3;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(creditField, c);


        c.gridy = 4;
        c.gridx = 1;
        panel.add(addBtn, c);

        c.gridx = 3;
        c.gridy = 0;
        panel.add(nameVal, c);

        c.gridx = 3;
        c.gridy = 1;
        panel.add(surnameVal, c);

        c.gridx = 3;
        c.gridy = 2;
        panel.add(phoneVal, c);



        addFrame.add(panel);

        createGUI();
    }

    private void createGUI() {

        addFrame.setTitle("Add Customer");
        addFrame.setSize(350, 250);
        addFrame.setVisible(true);
        addFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addFrame.setLocationRelativeTo(null);
       // addFrame.setAlwaysOnTop(true);
        addFrame.setResizable(false);

    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addBtn){

                if(CustomerTableModel.customers.size() <= 9){


                    if(!nameField.getText().equals("") && !sNameField.getText().equals("") && !phoneField.getText().equals("")) {

                        String name = nameField.getText();
                        String surname = sNameField.getText();
                        String phone = phoneField.getText();
                        String credit = creditField.getText();

                        double creditConverted = Double.parseDouble(credit);

                        Customer customer = new Customer(name, surname, phone, creditConverted, true);

                        MainScreen.customerTableModel.addData(customer);

                        MainScreen.tabbedPane.setSelectedIndex(0);

                        addFrame.dispose();
                    }

                    else{

                        if(nameField.getText().equals("") && sNameField.getText().equals("") && phoneField.getText().equals("")){

                            JOptionPane.showMessageDialog(null, "Complete All Fields", "Missing Input", JOptionPane.WARNING_MESSAGE);
                            nameVal.setText("*");
                            surnameVal.setText("*");
                            phoneVal.setText("*");

                        }

                        else if(nameField.getText().equals("")){

                            JOptionPane.showMessageDialog(null, "Complete All Fields", "Missing Input", JOptionPane.WARNING_MESSAGE);
                            nameVal.setText("*");

                        }

                        else if(!nameField.getText().equals("")){

                            nameVal.setText("");

                            if(!sNameField.getText().equals("") && phoneField.getText().equals("")){
                                surnameVal.setText("");
                                JOptionPane.showMessageDialog(null, "Complete All Fields", "Missing Input", JOptionPane.WARNING_MESSAGE);
                            }
                            else if(phoneField.getText().equals("") && sNameField.getText().equals("")){

                                JOptionPane.showMessageDialog(null, "Complete All Fields", "Missing Input", JOptionPane.WARNING_MESSAGE);

                            }
                            else if(!phoneField.getText().equals("") && sNameField.getText().equals("")){

                                phoneVal.setText("");
                                JOptionPane.showMessageDialog(null, "Complete All Fields", "Missing Input", JOptionPane.WARNING_MESSAGE);

                            }

                        }

                    }

                }
                else {

                    JOptionPane.showMessageDialog(null, "No more space in list", "List Full", JOptionPane.ERROR_MESSAGE);
                    addFrame.dispose();

                }



            }


        }
    }


}
