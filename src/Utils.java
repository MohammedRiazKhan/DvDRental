/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class Utils {

    public static String getFileExtension(String name1){

        int dotIndex = name1.lastIndexOf(".");

        if(dotIndex == -1){
            return null;

        }

        if(dotIndex == name1.length() -1){
            return null;
        }

        return name1.substring(dotIndex+1, name1.length());


    }

}
