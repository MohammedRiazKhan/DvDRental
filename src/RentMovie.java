/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class RentMovie extends JFrame{


    private JLabel mv, phNo, phoneVal;
    private JTextField custNum;
    private JComboBox movieTitle;
    private JButton addRentBtn, returnBtn;

    private JFrame rent = new JFrame();
    private JPanel rMenu = new JPanel();


    //array for the JComboBox
    private String[] name = new String[DvdTableModel.dvds.size()];


    //JOptionPane text
    private Object[] choices = {"Pay Fee", "Top-up", "Cancel"};


    public RentMovie() {

        rMenu.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 5, 0, 5);
        c.anchor = GridBagConstraints.LINE_START;


        ButtonListener2 btnL2 = new ButtonListener2();


        phNo = new JLabel("Customer number:");
        rMenu.add(phNo, c);
        c.anchor = GridBagConstraints.LINE_START;


        c.gridx = 1;
        c.gridy = 0;

        custNum = new JTextField(10);
        rMenu.add(custNum, c);
        c.anchor = GridBagConstraints.LINE_END;

        c.gridx = 2;
        c.gridy = 0;

        phoneVal = new JLabel();
        phoneVal.setForeground(Color.red);
        rMenu.add(phoneVal, c);
        c.anchor = GridBagConstraints.LINE_END;


        c.gridx = 0;
        c.gridy = 1;


        mv = new JLabel("Please choose the movie title: ");
        rMenu.add(mv, c);
        c.anchor = GridBagConstraints.LINE_START;


        c.gridx = 1;
        c.gridy = 1;

        for (int i = 0; i < name.length; i++) {

            name[i] = String.valueOf(DvdTableModel.dvds.get(i).getTitle());

        }


        movieTitle = new JComboBox(name);
        movieTitle.addItemListener(btnL2);
        rMenu.add(movieTitle, c);
        c.anchor = GridBagConstraints.LINE_END;


        c.gridx = 0;
        c.gridy = 2;

        addRentBtn = new JButton("Rent");
        addRentBtn.addActionListener(btnL2);
        rMenu.add(addRentBtn, c);
        c.anchor = GridBagConstraints.LINE_START;



        c.gridx = 1;
        c.gridy = 2;


        returnBtn = new JButton("Cancel");
        returnBtn.addActionListener(btnL2);
        rMenu.add(returnBtn, c);
        c.anchor = GridBagConstraints.LAST_LINE_END;

        rent.add(rMenu);


        if(!CustomerTableModel.customers.isEmpty() && !DvdTableModel.dvds.isEmpty()){

            createGUI();

        }
        else if(!CustomerTableModel.customers.isEmpty() && DvdTableModel.dvds.isEmpty()){

            JOptionPane.showMessageDialog(null, "Cannot Rent, Add Movies First", "Rent Error", JOptionPane.ERROR_MESSAGE);

            MainScreen.tabbedPane.setSelectedIndex(1);

        }
        else if(CustomerTableModel.customers.isEmpty() && !DvdTableModel.dvds.isEmpty()){

            JOptionPane.showMessageDialog(null, "Cannot Rent, Add Customers First", "Rent Error", JOptionPane.ERROR_MESSAGE);

            MainScreen.tabbedPane.setSelectedIndex(0);

        }
        else {

            JOptionPane.showMessageDialog(null, "Cannot Rent, There aren't and Customers or Movies", "Rent Error", JOptionPane.ERROR_MESSAGE);

        }



    }

    private void createGUI() {

        rent.setTitle("Rent a Movie");
        rent.setSize(450, 200);
        rent.setVisible(true);
        rent.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        rent.setLocationRelativeTo(null);
        rent.setResizable(false);

    }

    private class ButtonListener2 implements ActionListener, ItemListener {

        String valueInItem;

        @Override
        public void actionPerformed(ActionEvent e) {

            //getting the text and index of the customer number and movie selected
            String phNum = custNum.getText();
            int index = movieTitle.getSelectedIndex();

            double normalFee = 10;
            double newReleaseFee = 15;
            boolean customerExists = false;

            if(e.getSource() == addRentBtn){

                int i = 0;

                while (i < CustomerTableModel.customers.size()) {

                    if(CustomerTableModel.customers.get(i).getPhoneNum().equals(phNum)) {

                        customerExists = true;
                        break;

                    }

                    i++;

                }


                if(customerExists && CustomerTableModel.customers.get(i).CanRent()) {


                    //Executes If the movie is available and the credit is sufficient
                    if (DvdTableModel.dvds.get(index).isAvailableForRent() && CustomerTableModel.customers.get(i).getCredit() >= newReleaseFee) {


                        //Calculating the credit after renting a movie
                        //For new release price
                        if(DvdTableModel.dvds.get(index).isNewRelease()){


                            double value = CustomerTableModel.customers.get(i).getCredit();
                            double newValue = value - newReleaseFee;
                            CustomerTableModel.customers.get(i).setCredit(newValue);


                        }


                        //for normal release price
                        else if(!DvdTableModel.dvds.get(index).isNewRelease()){

                            double value = CustomerTableModel.customers.get(i).getCredit();
                            double newValue = value - normalFee;
                            CustomerTableModel.customers.get(i).setCredit(newValue);


                        }


                        //setting the canRent and isAvailable to false
                        CustomerTableModel.customers.get(i).setCanRent(false);
                        DvdTableModel.dvds.get(index).setAvailable(false);


                        //Setting the values for the rentTable
                        String movie = DvdTableModel.dvds.get(index).getTitle();
                        String cus = CustomerTableModel.customers.get(i).getFirstName();
                        String no = CustomerTableModel.customers.get(i).getPhoneNum();


                        //new Rent object
                        Rent rented = new Rent(movie, cus, no);


                        //Adding the renting details to the rentTable
                        MainScreen.rentTableModel.addData(rented);
                        MainScreen.tabbedPane.setSelectedIndex(2);


                        JOptionPane.showMessageDialog(null, "Transaction successful!", "Rent", JOptionPane.INFORMATION_MESSAGE);


                        //close the frame
                        rent.dispose();

                    }


                    //Executes If the movie is not available for rent
                    else if (!DvdTableModel.dvds.get(index).isAvailableForRent()) {

                        JOptionPane.showMessageDialog(null, DvdTableModel.dvds.get(index).getTitle() + " is not available");


                    }


                    //If the movie is available for rent but the customer does not have sufficient credit
                    else if (DvdTableModel.dvds.get(index).isAvailableForRent() && CustomerTableModel.customers.get(i).getCredit() < newReleaseFee) {


                        int confirm = JOptionPane.showOptionDialog(null, "You have insufficient credit", "Credit", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,  null, choices, choices[0]);


                        if (confirm == JOptionPane.YES_OPTION) {


                            CustomerTableModel.customers.get(i).setCanRent(false);
                            DvdTableModel.dvds.get(index).setAvailable(false);


                            //Setting the values for the rentTable
                            String movie = DvdTableModel.dvds.get(index).getTitle();
                            String cus = CustomerTableModel.customers.get(i).getFirstName();
                            String no = CustomerTableModel.customers.get(i).getPhoneNum();


                            Rent rented = new Rent(movie, cus, no);

                            //Adding the renting details to the rentTable
                            MainScreen.rentTableModel.addData(rented);
                            MainScreen.tabbedPane.setSelectedIndex(2);

                            JOptionPane.showMessageDialog(null, "Cash payment successful", "Payment", JOptionPane.INFORMATION_MESSAGE);

                            rent.dispose();


                        }

                        else if (confirm == JOptionPane.NO_OPTION) {

                            boolean incomplete = true;


                            do {


                                try{


                                    String amnt = JOptionPane.showInputDialog(null, "Credit account (R100 or greater)");

                                    double creditAmnt = Double.parseDouble(amnt);

                                    if(amnt.equals(JOptionPane.NO_OPTION)){

                                        rent.dispose();

                                    }


                                    while(creditAmnt < 100) {


                                        JOptionPane.showMessageDialog(null, "The entered amount is too low!");

                                        amnt = JOptionPane.showInputDialog(null, "Enter the amount you would like\n to credit your account with");

                                        creditAmnt = Double.parseDouble(amnt);


                                    }


                                    if (creditAmnt >= 100) {


                                        //For new release
                                        if(DvdTableModel.dvds.get(index).isNewRelease()){


                                            double value = CustomerTableModel.customers.get(i).getCredit();
                                            double newValue = (value + creditAmnt) - newReleaseFee;
                                            CustomerTableModel.customers.get(i).setCredit(newValue);


                                        }


                                        //for normal release
                                        else if(!DvdTableModel.dvds.get(index).isNewRelease()){

                                            double value = CustomerTableModel.customers.get(i).getCredit();
                                            double newValue = (value + creditAmnt) - normalFee;
                                            CustomerTableModel.customers.get(i).setCredit(newValue);


                                        }


                                        CustomerTableModel.customers.get(i).setCanRent(false);
                                        DvdTableModel.dvds.get(index).setAvailable(false);


                                        //Setting the values for the rentTable
                                        String movie = DvdTableModel.dvds.get(index).getTitle();
                                        String cus = CustomerTableModel.customers.get(i).getFirstName();
                                        String no = CustomerTableModel.customers.get(i).getPhoneNum();


                                        Rent rented = new Rent(movie, cus, no);


                                        //Adding the renting details to the rentTable
                                        MainScreen.rentTableModel.addData(rented);
                                        MainScreen.tabbedPane.setSelectedIndex(2);


                                        JOptionPane.showMessageDialog(null, "Credit Updated!\nTransaction Successful", "Rent", JOptionPane.INFORMATION_MESSAGE);


                                        incomplete = false;
                                        rent.dispose();


                                    }



                                }

                                catch (Exception exception){

                                    JOptionPane.showMessageDialog(null, "Only numeric values!", "Error", JOptionPane.ERROR_MESSAGE);

                                    incomplete = true;

                                }



                            }while (incomplete);//end of while loop


                        }//End of NO_OPTION election


                    }//end of credit less than new release


                }//end of customer can rent


                else if(custNum.getText().equals("")){


                    JOptionPane.showMessageDialog(null, "Complete Field", "Missing Input", JOptionPane.WARNING_MESSAGE);
                    phoneVal.setText("*");


                }


                else if(!customerExists){


                    JOptionPane.showMessageDialog(null, "Invalid Customer!", "Error", JOptionPane.ERROR_MESSAGE);
                    createGUI();


                }

                //executes if the customer cannot rent
                else if (!CustomerTableModel.customers.get(i).CanRent()) {

                    JOptionPane.showMessageDialog(null, "This customer has a movie rented!", "Error", JOptionPane.ERROR_MESSAGE);

                }



            }//End of addRentBtn

            //returning a movie
            if(e.getSource() == returnBtn){

                        rent.dispose();

                    }


                }//end of returnBtn




        @Override
        public void itemStateChanged (ItemEvent e) {

            Object item = movieTitle.getSelectedItem();

            if (e.getStateChange() == ItemEvent.SELECTED) {

                if (name[0] == item){

                    valueInItem = String.valueOf(item);

                }

                else if (name[1] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[2] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[3] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[4] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[5] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[6] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[7] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[8] == item) {

                    valueInItem = String.valueOf(item);

                } else if (name[9] == item) {

                    valueInItem = String.valueOf(item);

                }


            }

        }

    }//end of button listener

}
