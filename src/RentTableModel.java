import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RentTableModel extends AbstractTableModel{

    static ArrayList<Rent> dvdsRental;
    private String[] columnHeadings = {"Title", "Customer", "Number"};

    public RentTableModel(){

        dvdsRental = new ArrayList<>();

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Rent dvd = dvdsRental.get(rowIndex);

    }

    public void addData(Rent dvd){

        dvdsRental.add(dvd);
        fireTableDataChanged();

    }

    public void removeData(int index){

        dvdsRental.remove(index);
        fireTableDataChanged();

    }


    @Override
    public String getColumnName(int column){

        return columnHeadings[column];
    }

    @Override
    public int getRowCount(){

        return dvdsRental.size();

    }

    @Override
    public int getColumnCount(){

        return 3;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Rent dvd = dvdsRental.get(rowIndex);

        switch (columnIndex){

            case 0:
                return dvd.getTitle();

            case 1:
                return dvd.getName();

            case 2:
                return dvd.getPhNum();

            default:
                return null;
        }
    }

}
