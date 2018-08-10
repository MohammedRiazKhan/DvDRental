import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.PatternSyntaxException;

/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class SearchMovie {

    private JFrame searchFrame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel search;
    private JTextField searchField;
    RowFilter<DvdTableModel, Object> rf;

    public SearchMovie(){

        search = new JLabel("Enter First letter:");
        searchField = new JTextField(15);

        panel.add(search);
        panel.add(searchField);

        searchFrame.add(panel);

        if(!DvdTableModel.dvds.isEmpty()){

            createGUI();

            searchField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {

                    TableRowSorter sorter = new TableRowSorter<>(MainScreen.moviesTableModel);
                    MainScreen.moviesTable.setRowSorter(sorter);

                    String filtering = searchField.getText();

                    filtering = filtering.toUpperCase();

                    rf = null;

                    try {

                        rf = RowFilter.regexFilter(filtering,0);

                    }catch (PatternSyntaxException a){

                        return;

                    }

                    sorter.setRowFilter(rf);

                }
            });

        }
        else if(DvdTableModel.dvds.isEmpty()){

            JOptionPane.showMessageDialog(null, "No Movies to Search, Add Movies first.", "Search Error", JOptionPane.ERROR_MESSAGE);
            searchFrame.dispose();
        }
    }

    public void createGUI(){

        searchFrame.setTitle("Filter Movie");
        searchFrame.setSize(320, 70);
        searchFrame.setVisible(true);
        searchFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setResizable(false);


    }


}
