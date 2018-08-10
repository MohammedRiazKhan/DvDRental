import javax.swing.*;
/**

Authors
Mohammed Riaz Khan
Clayton Petersen

 */
public class Main {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new MainScreen();
            }
        });

    }

}
