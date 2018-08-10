import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class DvdTableModel extends AbstractTableModel {


    public static ArrayList<Dvd> dvds;
    private String[] columnHeadings = {"Title", "Genre", "Year", "New Release", "Available"};

    public DvdTableModel(){

        dvds = new ArrayList<>();

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex){

            case 3: return Boolean.class;

            case 4: return Boolean.class;


        }

        return Object.class;

    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Dvd dvd = dvds.get(rowIndex);

        switch (columnIndex){

            case 3:
                dvd.setRelease((Boolean) aValue);
                break;
            case 4:
                dvd.setAvailable((Boolean) aValue);
                break;


        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addData(Dvd dvd){

       dvds.add(dvd);
       fireTableDataChanged();

    }

    public void removeData(int index){

        dvds.remove(index);
        fireTableDataChanged();

    }


    @Override
    public String getColumnName(int column){

        return columnHeadings[column];
    }

    @Override
    public int getRowCount(){

        return dvds.size();

    }

    @Override
    public int getColumnCount(){

        return 5;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Dvd dvd = dvds.get(rowIndex);

        switch (columnIndex){

            case 0:
                return dvd.getTitle();

            case 1:
                return dvd.getCategory();

            case 2:
                return dvd.getYear();

            case 3:
                return dvd.isNewRelease();

            case 4:
                return dvd.isAvailableForRent();

            default:
                return null;
        }
    }

    public void saveToFile(File file) throws IOException {

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Dvd[] dvdss = dvds.toArray(new Dvd[dvds.size()]);

        oos.writeObject(dvdss);

        oos.close();


    }

    public void loadFromFile(File file) throws IOException{

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try{
            Dvd[] dv = (Dvd[]) ois.readObject();

            dvds.clear();

            dvds.addAll(Arrays.asList(dv));
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        ois.close();
        fireTableDataChanged();

    }

}
