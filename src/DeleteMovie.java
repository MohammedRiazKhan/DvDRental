import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */

public class DeleteMovie {

    private JFrame deleteDvdFrame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel movieTitle;
    private JTextField title;
    private JButton deleteButton;

    private DeleteDvdButtonListener deleteCusButtonListener = new DeleteDvdButtonListener();


    public DeleteMovie() {

        movieTitle = new JLabel("Movie Title");
        title = new JTextField(15);
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

        panel.add(movieTitle, c);

        c.gridy = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(title, c);

        deleteButton.addActionListener(deleteCusButtonListener);


        c.gridy = 1;
        c.gridx = 1;
        panel.add(deleteButton, c);

        //add panel to frame
        deleteDvdFrame.add(panel);

        //created frame and sets it properties
        createGUI();
    }

    private void createGUI() {

        deleteDvdFrame.setTitle("Delete Movie");
        deleteDvdFrame.setSize(300, 100);
        deleteDvdFrame.setVisible(true);
        deleteDvdFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        deleteDvdFrame.setLocationRelativeTo(null);
        deleteDvdFrame.setResizable(false);

    }

    private class DeleteDvdButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == deleteButton) {

                String movieToBeDeleted = title.getText();

                int valuesInTable = MainScreen.moviesTableModel.getRowCount() - 1;

                //int i = 0;

                //boolean dvdExists = false;

                for (int i = 0; i < MainScreen.moviesTableModel.getRowCount(); i++) {

                    if (MainScreen.moviesTableModel.getValueAt(i, 0).equals(movieToBeDeleted) && DvdTableModel.dvds.get(i).isAvailableForRent()) {

                        MainScreen.moviesTableModel.removeData(i);
                        JOptionPane.showMessageDialog(null, "Movie Successfully Deleted!");


                    }

                    else if (!MainScreen.moviesTableModel.getValueAt(i, 0).equals(movieToBeDeleted) && i == valuesInTable) {

                        JOptionPane.showMessageDialog(null, "Movie Does not Exits");

                    }

                    else if(MainScreen.moviesTableModel.getValueAt(i, 0).equals(movieToBeDeleted) && !DvdTableModel.dvds.get(i).isAvailableForRent()){


                        JOptionPane.showMessageDialog(null, DvdTableModel.dvds.get(i).getTitle() + " cannot be deleted!\nPlease wait till the customer returns it", "Delete movie", JOptionPane.ERROR_MESSAGE);



                        MainScreen.tabbedPane.setSelectedIndex(2);

                        break;

                    }

                }

                deleteDvdFrame.dispose();

                /*while (i < DvdTableModel.dvds.size()) {

                    if(DvdTableModel.dvds.get(i).getTitle().equals(movieToBeDeleted)) {

                        dvdExists = true;
                        break;

                    }

                    i++;

                }


                   if(dvdExists && DvdTableModel.dvds.get(i).isAvailableForRent()){


                        MainScreen.moviesTableModel.removeData(i);
                        JOptionPane.showMessageDialog(null, "Movie Successfully Deleted!");


                    }

                    else if(!dvdExists && (i == valuesInTable)) {


                        JOptionPane.showMessageDialog(null, "Movie Does not exist");


                    }

                    else if(dvdExists && !DvdTableModel.dvds.get(i).isAvailableForRent()){


                        JOptionPane.showMessageDialog(null, DvdTableModel.dvds.get(i).getTitle() + " cannot be deleted!\nPlease wait till the customer returns it", "Delete movie", JOptionPane.ERROR_MESSAGE);


                        MainScreen.tabbedPane.setSelectedIndex(2);


                    }

                    deleteDvdFrame.dispose();

                }*/

            }

        }


    }

}//delete when done

