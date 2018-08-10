

import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class MainScreen extends JFrame {

    //menu components initialisation
    private JMenu exit, sortCustomers, rentMovie, searchMovie, sortMovies, sortAvailMovies;
    private JMenuItem addMovie, addCus, deleteCus, deleteMovie, importDvd, exportDvd, importCus, exportCus, about;

    public static JTabbedPane tabbedPane;

    static RentTableModel rentTableModel;
    //Table Stuff
    public static CustomerTableModel customerTableModel;
    public static DvdTableModel moviesTableModel;
    public static JTable customerTable;
    public static JTable moviesTable;
    static JTable rentTable;
    private JFileChooser cusChooser, dvdChooser;

    public MainScreen(){
        super("DVD Rental");

        //creates menu bar
        createMenu();

        //createTable

        createTable();
        createTabbedPane();

        //creates gui
        createGUI();

        deleteRental();
    }

    private void createGUI(){

        setSize(750, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);

    }

    private void createMenu(){

        cusChooser = new JFileChooser();
        cusChooser.addChoosableFileFilter(new CustomerFileFilter());


        dvdChooser = new JFileChooser();
        dvdChooser.addChoosableFileFilter(new DvdFileFilter());

        //creating menu bar
        JMenuBar menu = new JMenuBar();
        MenuItemListener itemListener = new MenuItemListener();

        //menu bar item
        //add
        JMenu file = new JMenu("File");
        file.setToolTipText("Import Customer Table Data");
        file.setMnemonic(KeyEvent.VK_F);

        importCus = new JMenuItem("Import Customer Data");
        importCus.addActionListener(itemListener);

        exportCus = new JMenuItem("Export Customer Data");
        exportCus.addActionListener(itemListener);


        importDvd = new JMenuItem("Import DVD Data");
        importDvd.addActionListener(itemListener);

        exportDvd = new JMenuItem("Export DVD Data");
        exportDvd.addActionListener(itemListener);

        about = new JMenuItem("About");
        about.addActionListener(itemListener);

        file.add(importCus);
        file.add(importDvd);
       // file.add(exportCus);
       // file.add(exportDvd);
        file.addSeparator();
        file.add(about);


        JMenu addMenu = new JMenu("Add");
        addMenu.setToolTipText("Add Customer or Movie");

        //sub items
        addCus = new JMenuItem("Customer");
        addCus.addActionListener(itemListener);

        addMovie = new JMenuItem("Movie");
        addMovie.addActionListener(itemListener);

        //adding sub items to menu item
        addMenu.add(addCus);
        addMenu.add(addMovie);

        //delete
        JMenu deleteMenu = new JMenu("Delete");
        deleteMenu.setToolTipText("Delete Customer or Movie");

        //sub items
        deleteCus = new JMenuItem("Customer");
        deleteCus.addActionListener(itemListener);

        deleteMovie = new JMenuItem("Movie");
        deleteMovie.addActionListener(itemListener);

        //adding sub items to menu item
        deleteMenu.add(deleteCus);
        deleteMenu.add(deleteMovie);

        //sort movies
        sortMovies = new JMenu("Sort Movies");
        sortMovies.addMouseListener(itemListener);
        sortMovies.setToolTipText("Sorts Movie Titles");

        //sort availmovies
        sortAvailMovies = new JMenu("Sort Available Movies");
        sortAvailMovies.addMouseListener(itemListener);
        sortAvailMovies.setToolTipText("Sorts Available Movies Titles");

        //sort cust
        sortCustomers = new JMenu("Sort Customers");
        sortCustomers.addMouseListener(itemListener);
        sortCustomers.setToolTipText("Sorts Customers by Surname");


        //search movie starting with letter
        searchMovie = new JMenu("Search Movie");
        searchMovie.addMouseListener(itemListener);
        searchMovie.setToolTipText("Filters a movie based off first letter");

        //rent movie
        rentMovie = new JMenu("Rent Movie");
        rentMovie.addMouseListener(itemListener);
        rentMovie.setToolTipText("Rent a Movie");

        //exit
        exit = new JMenu("Exit");
        exit.setToolTipText("Exits the System");
        exit.addMouseListener(itemListener);

        //adding menu item to bar
        menu.add(file);
        menu.add(addMenu);
        menu.add(deleteMenu);
        menu.add(sortMovies);
        menu.add(sortAvailMovies);
        menu.add(sortCustomers);
        menu.add(searchMovie);
        menu.add(rentMovie);
        menu.add(exit);

        setJMenuBar(menu);

    }


    public void createTabbedPane(){

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Customers", new JScrollPane(customerTable));
        tabbedPane.addTab("Movies", new JScrollPane(moviesTable));
        tabbedPane.addTab("Rented", new JScrollPane(rentTable));

        tabbedPane.setToolTipTextAt(0, "Stores Customer Information");
        tabbedPane.setToolTipTextAt(1, "Stores Movie Information");
        tabbedPane.setToolTipTextAt(2, "Stores Rented Movies");

        add(tabbedPane);

    }

    public static void createTable(){

        customerTableModel = new CustomerTableModel();
        moviesTableModel = new DvdTableModel();

        customerTable = new JTable(customerTableModel);
        moviesTable = new JTable(moviesTableModel);

        rentTableModel = new RentTableModel();
        rentTable = new JTable(rentTableModel);



    }

    public void deleteRental(){

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem returnItem = new JMenuItem("Return Movie");

        popupMenu.add(returnItem);

        rentTable.addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {

                int row = rentTable.rowAtPoint(e.getPoint());

                rentTable.getSelectionModel().setSelectionInterval(row, row);

                if(e.getButton() == MouseEvent.BUTTON3){

                    popupMenu.show(rentTable, e.getX(), e.getY());

                }
            }
        });

        returnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = rentTable.getSelectedRow();

                int yes = JOptionPane.showConfirmDialog(null, "Return Movie?", "Return", JOptionPane.YES_NO_OPTION);

                if(yes == JOptionPane.YES_OPTION){

                    //find customer and update the customer table
                    boolean found = false;

                    int i = 0;

                    while (i < CustomerTableModel.customers.size()){

                        if(CustomerTableModel.customers.get(i).getPhoneNum().equals(RentTableModel.dvdsRental.get(row).getPhNum())) {

                            found = true;
                            break;

                        }

                        i++;
                    }

                    if(found){

                        CustomerTableModel.customers.get(i).setCanRent(true);

                    }


                    //find dvd and update dvd table
                    boolean foundDvd = false;

                    int x = 0;

                    while (x < DvdTableModel.dvds.size()){

                        if(DvdTableModel.dvds.get(x).getTitle().equals(RentTableModel.dvdsRental.get(row).getTitle())){

                            foundDvd = true;
                            break;

                        }

                        x++;
                    }

                    if(foundDvd){

                        DvdTableModel.dvds.get(x).setAvailable(true);

                    }

                    rentTableModel.removeData(row);

                    rentTableModel.fireTableRowsDeleted(row, row);

                }



            }
        });
    }

    public void saveToFile(File file) throws IOException{

        customerTableModel.saveToFile(file);

    }

    private class MovieComparator implements Comparator<Dvd> {

        @Override
        public int compare(Dvd o, Dvd o1) {

            return o.getTitle().compareTo(o1.getTitle());
        }

    }

    private class AvailableMovieComparator implements Comparator<Dvd> {

        @Override
        public int compare(Dvd o, Dvd o1) {

           return Boolean.compare(o1.isAvailableForRent(), o.isAvailableForRent());

        }

    }

    private class CustomerComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer c1, Customer c2) {

            return c1.getSurname().compareTo(c2.getSurname());
        }

    }

    private class MenuItemListener implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addCus){

                new AddCustomer();

            }

            else if(e.getSource() == addMovie){

                new AddDvd();

            }

            else if(e.getSource() == deleteCus){


                if(CustomerTableModel.customers.isEmpty()){

                    JOptionPane.showMessageDialog(null, "There are no customers to remove!", "Delete Customer", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    tabbedPane.setSelectedIndex(0);
                    new DeleteCustomer();

                }

            }

            else if(e.getSource() == deleteMovie){


                if(DvdTableModel.dvds.isEmpty()){

                    JOptionPane.showMessageDialog(null, "There are no movies to remove!", "Delete Movie", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    tabbedPane.setSelectedIndex(1);
                    new DeleteMovie();

                }


            }//end of deleteMovie

            else if(e.getSource() == importCus){

                if(cusChooser.showOpenDialog(MainScreen.this) == JFileChooser.APPROVE_OPTION){


                    try{

                        tabbedPane.setSelectedIndex(0);
                        customerTableModel.loadFromFile(cusChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(null, "Data Imported Successfully!", "Import Success", JOptionPane.INFORMATION_MESSAGE );

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainScreen.this, "Could not data from file", "Save Error", JOptionPane.ERROR_MESSAGE);
                    }

                }



            }

            else if(e.getSource() == exportCus){

                if(cusChooser.showSaveDialog(MainScreen.this) == JFileChooser.APPROVE_OPTION){

                    try{

                        customerTableModel.saveToFile(cusChooser.getSelectedFile());

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainScreen.this, "Could not save to file", "Save Error", JOptionPane.ERROR_MESSAGE);
                    }

                }



            }
            else if(e.getSource() == importDvd){

                if(dvdChooser.showOpenDialog(MainScreen.this) == JFileChooser.APPROVE_OPTION){

                    try{

                        tabbedPane.setSelectedIndex(1);
                        moviesTableModel.loadFromFile(dvdChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(null, "Data Imported Successfully!", "Import Success", JOptionPane.INFORMATION_MESSAGE );

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainScreen.this, "Could not load data from file", "Save Error", JOptionPane.ERROR_MESSAGE);
                    }


                }

            }

            else if(e.getSource() == exportDvd){

                if(dvdChooser.showSaveDialog(MainScreen.this) == JFileChooser.APPROVE_OPTION){

                    try{

                        moviesTableModel.saveToFile(dvdChooser.getSelectedFile());

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainScreen.this, "Could not save Dvd data to file", "Save Error", JOptionPane.ERROR_MESSAGE);
                    }


                }


            }

            else if(e.getSource() == about){


                new About();

            }

        }


        @Override
        public void mouseClicked(MouseEvent e) {

            if(e.getSource() == sortMovies){

                if(!DvdTableModel.dvds.isEmpty()){

                    tabbedPane.setSelectedIndex(1);

                    Collections.sort(DvdTableModel.dvds, new MovieComparator());
                    moviesTableModel.fireTableDataChanged();

                    JOptionPane.showMessageDialog(null, "Successfully Sorted!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
                else {

                    tabbedPane.setSelectedIndex(1);
                    JOptionPane.showMessageDialog(null, "List Empty, Add Movies.", "List Empty", JOptionPane.ERROR_MESSAGE);

                }

            }

            else if(e.getSource() == sortAvailMovies){

                if(!DvdTableModel.dvds.isEmpty()){

                    tabbedPane.setSelectedIndex(1);

                    Collections.sort(DvdTableModel.dvds, new AvailableMovieComparator());
                    moviesTableModel.fireTableDataChanged();

                    JOptionPane.showMessageDialog(null, "Successfully Sorted!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
                else {

                    tabbedPane.setSelectedIndex(1);
                    JOptionPane.showMessageDialog(null, "List Empty, Add Movies.", "List Empty", JOptionPane.ERROR_MESSAGE);

                }

            }

            else if(e.getSource() == sortCustomers){

                if(!CustomerTableModel.customers.isEmpty()){

                    tabbedPane.setSelectedIndex(0);

                    Collections.sort(CustomerTableModel.customers, new CustomerComparator());
                    customerTableModel.fireTableDataChanged();

                    JOptionPane.showMessageDialog(null, "Successfully Sorted!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
                else {

                    tabbedPane.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "List Empty, Add Customers.", "List Empty", JOptionPane.ERROR_MESSAGE);

                }

            }

            else if(e.getSource() == searchMovie){

                tabbedPane.setSelectedIndex(1);
                new SearchMovie();

            }

            else if(e.getSource() == rentMovie){

                tabbedPane.setSelectedIndex(0);
                new RentMovie();

            }

            else if(e.getSource() == exit){

              int confirm = JOptionPane.showConfirmDialog(null, "Exit DVD Rental?", "Exit", JOptionPane.YES_NO_OPTION);

              if(confirm == JOptionPane.YES_OPTION){

                  System.exit(0);

              }

            }


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
