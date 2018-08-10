import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class DeleteCustomer {


    private JFrame deleteCusFrame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel phoneNumber;
    private JTextField number;
    private JButton deleteButton;

    private DeleteCusButtonListener deleteCusButtonListener = new DeleteCusButtonListener();


    public DeleteCustomer() {

        phoneNumber = new JLabel("Phone Number");
        number = new JTextField(15);
        deleteButton = new JButton("Delete");


        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 5, 0, 5);

        c.anchor = GridBagConstraints.LINE_END;

        panel.add(phoneNumber, c);

        c.gridy = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(number, c);

        deleteButton.addActionListener(deleteCusButtonListener);


        c.gridy = 1;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(deleteButton, c);

        //add panel to frame
        deleteCusFrame.add(panel);

        //created frame and sets it properties
        createGUI();
    }

    private void createGUI() {

        deleteCusFrame.setTitle("Delete Customer");
        deleteCusFrame.setSize(300, 100);
        deleteCusFrame.setVisible(true);
        deleteCusFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        deleteCusFrame.setLocationRelativeTo(null);
        deleteCusFrame.setResizable(false);

    }

    private class DeleteCusButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == deleteButton) {


                String valueToDelete = number.getText();
                int valueInTable = MainScreen.customerTableModel.getRowCount() - 1;


                for(int i = 0; i < MainScreen.customerTableModel.getRowCount(); i++){


                    if(CustomerTableModel.customers.get(i).getPhoneNum().equals(valueToDelete) && CustomerTableModel.customers.get(i).CanRent()){


                        MainScreen.customerTableModel.removeData(i);
                        JOptionPane.showMessageDialog(null, "Successfully Deleted");


                    }

                    else if(!CustomerTableModel.customers.get(i).getPhoneNum().equals(valueToDelete) && i == valueInTable){


                        JOptionPane.showMessageDialog(null, "Number Does not exist!");


                    }

                    else if(!CustomerTableModel.customers.get(i).CanRent()){


                        JOptionPane.showMessageDialog(null, "Unable to remove customer!\n" + CustomerTableModel.customers.get(i).getFirstName() + " has rented a movie", "Delete Customer", JOptionPane.INFORMATION_MESSAGE);


                        MainScreen.tabbedPane.setSelectedIndex(2);


                    }


                }

                deleteCusFrame.dispose();

            }//End of deleteBtn

        }//end of actionPerformed

    }

}

