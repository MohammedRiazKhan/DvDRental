import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class CustomerTableModel extends AbstractTableModel{

    public static ArrayList<Customer> customers;
    private String[] columnHeadings = {"Name", "Surname", "Phone", "Credit (R)", "Can Rent"};

    public CustomerTableModel(){

        customers = new ArrayList<>(10);

    }

    public void addData(Customer customer){

        customers.add(customer);
        fireTableDataChanged();

    }

    public void removeData(int row){

        customers.remove(row);
        fireTableDataChanged();

    }

    @Override
    public String getColumnName(int column){

        return columnHeadings[column];
    }

    @Override
    public int getRowCount(){

        return customers.size();

    }

    @Override
    public int getColumnCount(){

        return 5;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex){

            case 4: return Boolean.class;

        }

        return Object.class;

    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Customer customer = customers.get(rowIndex);

        switch (columnIndex){

            case 4:
                customer.setCanRent((Boolean) aValue);


        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Customer customer = customers.get(rowIndex);

        switch (columnIndex){

            case 0:
                return customer.getFirstName();

            case 1:
                return customer.getSurname();

            case 2:
                return customer.getPhoneNum();

            case 3:
                return customer.getCredit();

            case 4:
                return customer.CanRent();

            default:
                return null;
        }
    }

    public void saveToFile(File file) throws IOException {

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Customer[] customerss = customers.toArray(new Customer[customers.size()]);

        oos.writeObject(customerss);

        oos.close();

    }

    public void loadFromFile(File file) throws IOException{

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try{

            Customer[] cus = (Customer[]) ois.readObject();

            customers.clear();

            customers.addAll(Arrays.asList(cus));
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        ois.close();
        fireTableDataChanged();

    }


}
