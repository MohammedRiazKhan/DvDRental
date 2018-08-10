import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ObjectInputStream;

/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class AddDvd {

    private JFrame addDvdFrame = new JFrame();
    private JLabel movieName, category, year, movieVal, catVal, yearVal;
    private JTextField movieNameField, yearField;
    private JCheckBox newRelease1, available;
    private JComboBox categories;
    private JButton addMovieBtn;
    private JPanel panel = new JPanel();
    private String[] movieCategories = {" ", "------Genre------", "Sci-fi", "Action", "Comedy", "Romance", "Horror", "Cartoon"};

    private Dvd newDvd;

    public AddDvd(){

        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 5,0, 5);

        ButtonListener2 btnL2 = new ButtonListener2();

        //adding components
        movieName = new JLabel("Movie:");
        panel.add(movieName, c);
        c.anchor = GridBagConstraints.LINE_START;


        c.gridx = 1;
        c.gridy = 0;
        movieNameField = new JTextField(15);
        panel.add(movieNameField, c);
        c.anchor = GridBagConstraints.LINE_END;

        c.gridx = 0;
        c.gridy = 1;
        category = new JLabel("Category:");
        panel.add(category, c);
        c.anchor = GridBagConstraints.LINE_START;

        c.gridx = 1;
        c.gridy = 1;
        categories = new JComboBox(movieCategories);
        categories.addItemListener(btnL2);
        categories.setSelectedIndex(1);
        panel.add(categories, c);
        c.anchor = GridBagConstraints.LINE_START;

        c.gridy = 2;
        c.gridx = 0;
        year = new JLabel("Year: ");
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(year, c);

        c.gridx = 1;
        c.gridy = 2;
        yearField = new JTextField(15);
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(yearField, c);

        /*c.gridx = 1;
        c.gridy = 3;
        newRelease1 = new JCheckBox("New Release");
        newRelease1.addActionListener(btnL2);
        panel.add(newRelease1, c);*/

        /*c.gridx = 1;
        c.gridy = 4;
        available = new JCheckBox("Available");
        available.addActionListener(btnL2);
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(available, c);*/

        c.gridx = 1;
        c.gridy = 5;
        addMovieBtn = new JButton("Add");
        addMovieBtn.addActionListener(btnL2);
        panel.add(addMovieBtn, c);
        c.anchor = GridBagConstraints.LINE_END;

        movieVal = new JLabel();
        movieVal.setForeground(Color.red);

        catVal = new JLabel();
        catVal.setForeground(Color.red);

        yearVal = new JLabel();
        yearVal.setForeground(Color.red);

        c.gridx = 2;
        c.gridy = 0;
        panel.add(movieVal, c);

        c.gridx = 2;
        c.gridy = 1;
        panel.add(catVal, c);

        c.gridx = 2;
        c.gridy = 2;
        panel.add(yearVal, c);

        addDvdFrame.add(panel);

        createGUI();

    }

    private void createGUI() {

        addDvdFrame.setTitle("New Movie");
        addDvdFrame.setSize(350, 250);
        addDvdFrame.setVisible(true);
        addDvdFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addDvdFrame.setLocationRelativeTo(null);
        addDvdFrame.setResizable(false);

    }

    private class ButtonListener2 implements ActionListener, ItemListener {

        String valueInItem;


        boolean newRelease;
        //boolean avail;

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addMovieBtn){


                if(DvdTableModel.dvds.size() <= 9){


                    if(!movieNameField.getText().equals("") && !valueInItem.equals(movieCategories[0]) && !yearField.getText().equals("")){

                        String name = movieNameField.getText();

                        StringBuilder sb = new StringBuilder(name);

                        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

                        name = sb.toString();

                        String moviesYear = yearField.getText();
                        int year = Integer.parseInt(moviesYear);
                        //checks whether the year is valid
                        boolean validYear = false;

                        int v = 0;


                        while (!validYear){

                            if(year <= 1900 ){

                                JOptionPane.showMessageDialog(null, "Invalid Year", "Error", JOptionPane.ERROR_MESSAGE);
                                break;

                            }

                            else if(year <= 2017 && year > 1901){

                                validYear = true;
                                newRelease = false;

                            }

                            else if(year >= 2018){

                                validYear = true;
                                newRelease = true;


                            }

                        }

                        moviesYear = String.valueOf(year);

                        boolean exist = false;

                        for(int x = 0; x < DvdTableModel.dvds.size(); x++){

                            if(DvdTableModel.dvds.get(x).getTitle().equals(name)){
                                exist = true;
                                break;
                            }

                        }

                        if(!exist){

                            if(validYear){

                                newDvd = new Dvd(name, valueInItem, moviesYear, newRelease, true);
                                MainScreen.moviesTableModel.addData(newDvd);
                                MainScreen.tabbedPane.setSelectedIndex(1);
                                addDvdFrame.dispose();

                            }else {

                                addDvdFrame.dispose();
                            }


                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Movie Already exits");
                            addDvdFrame.dispose();
                        }

                    }

                    else if(movieNameField.getText().equals("") && yearField.getText().equals("") && valueInItem.equals(movieCategories[1])){

                        movieVal.setText("*");
                        catVal.setText("*");
                        yearVal.setText("*");
                        JOptionPane.showMessageDialog(null, "Complete Form", "Missing Input", JOptionPane.WARNING_MESSAGE);

                    }

                    else if(movieNameField.getText().equals("")){

                        JOptionPane.showMessageDialog(null, "Complete Form", "Missing Input", JOptionPane.WARNING_MESSAGE);
                        movieVal.setText("*");

                    }

                    else if(!movieNameField.getText().equals("")){

                        movieVal.setText("");

                        if(!yearField.getText().equals("") && valueInItem.equals(movieCategories[1])){

                            yearVal.setText("");
                            JOptionPane.showMessageDialog(null, "Complete Form", "Missing Input", JOptionPane.WARNING_MESSAGE);

                        }

                        else if(yearField.getText().equals("") && valueInItem.equals(movieCategories[1])){


                            JOptionPane.showMessageDialog(null, "Complete Form", "Missing Input", JOptionPane.WARNING_MESSAGE);

                        }

                        else if(yearField.getText().equals("") && !valueInItem.equals(movieCategories[1])){

                            catVal.setText("");
                            JOptionPane.showMessageDialog(null, "Complete Form", "Missing Input", JOptionPane.WARNING_MESSAGE);
                        }

                    }


                }
                else {

                    JOptionPane.showMessageDialog(null, "No more space in list", "List Full", JOptionPane.ERROR_MESSAGE);

                    addDvdFrame.dispose();

                }

            }
        }



        @Override
        public void itemStateChanged(ItemEvent e) {

            Object item = categories.getSelectedItem();


            if(e.getStateChange() == ItemEvent.SELECTED){

                if(movieCategories[0] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[1] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[2] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[3] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[4] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[5] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[6] == item){

                    valueInItem = String.valueOf(item);

                }

                else if(movieCategories[7] == item){

                    valueInItem = String.valueOf(item);

                }

            }


        }
    }


}
