import javax.swing.filechooser.FileFilter;
import java.io.File;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class DvdFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {

        if(f.isDirectory()){

            return true;

        }

        String name = f.getName();

        String extension = Utils.getFileExtension(name);

        if(extension == null){

            return false;

        }

        if(extension.equals("dvd")){

            return true;

        }

        return false;

    }

    @Override
    public String getDescription() {
        return "Rental Dvd file (.dvd)";
    }
}
